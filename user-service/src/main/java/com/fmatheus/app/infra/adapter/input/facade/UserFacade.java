package com.fmatheus.app.infra.adapter.input.facade;

import com.fmatheus.app.application.domain.PermissionDomain;
import com.fmatheus.app.application.domain.UserDomain;
import com.fmatheus.app.application.service.ContactServicePort;
import com.fmatheus.app.application.service.PersonServicePort;
import com.fmatheus.app.application.service.UserServicePort;
import com.fmatheus.app.infra.adapter.input.converter.PersonConverter;
import com.fmatheus.app.infra.adapter.input.converter.UserCreateConverter;
import com.fmatheus.app.infra.adapter.input.converter.UserUpdateConverter;
import com.fmatheus.app.infra.adapter.input.dto.request.PasswordUpdateDtoRequest;
import com.fmatheus.app.infra.adapter.input.dto.request.UserCreateDtoRequest;
import com.fmatheus.app.infra.adapter.input.dto.request.UserPermissionUpdateRequest;
import com.fmatheus.app.infra.adapter.input.dto.request.UserUpdateDtoRequest;
import com.fmatheus.app.infra.adapter.input.dto.response.UserDtoResponse;
import com.fmatheus.app.infra.adapter.input.enumerable.MethodEnum;
import com.fmatheus.app.infra.adapter.input.exception.handler.MessageResponseHandler;
import com.fmatheus.app.infra.adapter.input.exception.message.MessageResponse;
import com.fmatheus.app.infra.adapter.output.persistence.repository.filter.UserRepositoryFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class UserFacade {

    private final PasswordEncoder passwordEncoder;
    private final UserServicePort userServicePort;
    private final ContactServicePort contactServicePort;
    private final PersonServicePort personServicePort;
    private final PersonConverter personConverter;
    private final UserUpdateConverter userUpdateConverter;
    private final UserCreateConverter userCreateConverter;
    private final MessageResponse messageResponse;


    /**
     * Retorna uma lista de usuarios (paginado) de acordo com o filtro informado.
     *
     * @param pageable Pageable
     * @param filter   Objeto contendo os filtros de pesquisa.
     * @return Page<UserReadBase>
     * @author fernando.matheus
     */
    public Page<UserDtoResponse> findAllFilter(Pageable pageable, UserRepositoryFilter filter) {
        var list = this.userServicePort.findAllFilter(pageable, filter);
        var listConverter = list.map(map -> this.personConverter.converterToResponse(map.getPerson()));
        return new PageImpl<>(listConverter.stream().toList(), pageable, this.userServicePort.total(filter));

    }

    /**
     * Pesquisa o usuario pelo UUID.
     *
     * @param uuid ID do usuario enviado na variavel de requisicao.
     * @return UserReadBase
     * @author fernando.matheus
     */
    public UserDtoResponse findByUuid(UUID uuid) {
        var response = this.userServicePort.findByUuid(uuid).orElseThrow(this.messageResponse::errorRecordNotExist);
        return this.personConverter.converterToResponse(response.getPerson());
    }

    /**
     * Cria um novo usuario seguindo as seguintes condicoes:
     * - Verifica se o nome de usuario (email) não existe.
     * - Verifica se o email nao existe.
     * - Verifica se o telefone nao existe.
     *
     * @param request Objeto enviado no corpo da requisicao.
     * @return UserDtoResponse
     * @author fernando.matheus
     */
    public UserDtoResponse create(UserCreateDtoRequest request) {

        if (this.userServicePort.findByUsername(request.getDocument()).isPresent()) {
            throw this.messageResponse.errorExistDocument();
        }

        if (this.contactServicePort.findByEmail(request.getContact().getEmail()).isPresent()) {
            throw this.messageResponse.errorExistEmail();
        }

        if (this.contactServicePort.findByPhone(request.getContact().getPhone()).isPresent()) {
            throw this.messageResponse.errorExistPhone();
        }
        var commit = this.personServicePort.save(this.userCreateConverter.converterToEntity(request));
        var converter = this.personConverter.converterToResponse(commit);
        converter.setMessage(this.messageResponse.messageSuccessCreate());

        this.enviarEmail();

        return converter;

    }

    /**
     * Atualiza dados do usuario (nome, endereco e contato). Somente o proprio usuario poderar alterar.
     *
     * @param request Objeto enviado no corpo da requisicao.
     * @param jwt     Token enviado na requisicao. Sera utilizado o username que vem no token e verificar se o usuario existe na base.
     * @return UserReadBase
     * @author fernando.matheus
     */
    public UserDtoResponse update(UserUpdateDtoRequest request, Jwt jwt) {
        var username = jwt.getClaims().get("username").toString();
        var result = this.findUser(username);
        var commit = this.personServicePort.save(this.userUpdateConverter.converterToUpdate(result, request));
        var format = this.personConverter.converterToResponse(commit);
        format.setMessage(this.messageResponse.messageSuccessUpdate());
        format.setUsers(null);
        return format;
    }

    /**
     * Atualiza a senha do usuario. Somente o proprio usuario poderar alterar.
     *
     * @param request Objeto enviado no corpo da requisicao.
     * @param jwt     Token enviado na requisicao. Sera utilizado o username qu vem no token e verificar se o usuario existe na base.
     * @author fernando.matheus
     */
    public MessageResponseHandler updatePassword(PasswordUpdateDtoRequest request, Jwt jwt) {
        var username = jwt.getClaims().get("username").toString();
        var result = this.findUser(username);

        if (!request.getPassword().equals(request.getConfirmPassword())) {
            throw this.messageResponse.errorPasswordNotMatchException();
        }

        var encodedPassword = this.passwordEncoder.encode(request.getPassword());
        result.setPassword(encodedPassword);
        this.userServicePort.save(result);

        this.enviarEmail();

        return this.messageResponse.messageSuccessUpdate();
    }



    public void updatePermissions(UUID uuid, UserPermissionUpdateRequest request) {
        var user = this.userServicePort.findByUuid(uuid).orElseThrow(this.messageResponse::errorUserdNotExist);

        user.setPermissions(request.getPermissions().stream()
                .filter(filter -> !Objects.equals(filter.getMethod().getValue(), MethodEnum.DELETE.getValue()))
                .toList().stream()
                .map(this::format).toList());
        this.userServicePort.save(user);
    }

    private PermissionDomain format(UserPermissionUpdateRequest.PermissionRequest request) {
        var permission = new PermissionDomain();
        permission.setId(request.getId());
        return permission;
    }


    /**
     * Pesquisa pelo nome de usuario.
     *
     * @param username Username do usuario.
     * @return User
     * @author fernando.matheus
     */
    private UserDomain findUser(String username) {
        return this.userServicePort.findByUsername(username).orElseThrow(this.messageResponse::errorUserdNotExist);
    }


    private void enviarEmail(){

    }
}
