package com.fmatheus.app.controller.rule;

import com.fmatheus.app.controller.converter.PersonConverter;
import com.fmatheus.app.controller.converter.UserCreateConverter;
import com.fmatheus.app.controller.converter.UserUpdateConverter;
import com.fmatheus.app.controller.dto.request.UserCreateRequest;
import com.fmatheus.app.controller.dto.request.UserPermissionUpdateRequest;
import com.fmatheus.app.controller.dto.request.UserUpdateRequest;
import com.fmatheus.app.controller.dto.request.base.PasswordUpdateBase;
import com.fmatheus.app.controller.dto.request.base.PermissionUpdateBase;
import com.fmatheus.app.controller.dto.response.UserResponse;
import com.fmatheus.app.controller.enumerable.MethodEnum;
import com.fmatheus.app.controller.exception.handler.MessageResponseHandler;
import com.fmatheus.app.controller.exception.message.MessageResponse;
import com.fmatheus.app.model.entity.Permission;
import com.fmatheus.app.model.entity.User;
import com.fmatheus.app.model.repository.filter.UserRepositoryFilter;
import com.fmatheus.app.model.service.ContactService;
import com.fmatheus.app.model.service.PersonService;
import com.fmatheus.app.model.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class UserRule {

    private final PasswordEncoder passwordEncoder;

    private final UserService userService;

    private final PersonService personService;

    private final ContactService contactService;

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
    public Page<UserResponse> findAllFilter(Pageable pageable, UserRepositoryFilter filter) {
        var list = this.userService.findAllFilter(pageable, filter);
        var listConverter = list.map(map -> this.personConverter.converterToResponse(map.getPerson()));
        return new PageImpl<>(listConverter.stream().toList(), pageable, this.userService.totalPaginator(filter));
    }

    /**
     * Pesquisa o usuario pelo UUID.
     *
     * @param uuid ID do usuario enviado na variavel de requisicao.
     * @return UserReadBase
     * @author fernando.matheus
     */
    public UserResponse findByUuid(UUID uuid) {
        var response = this.userService.findByUuid(uuid).orElseThrow(this.messageResponse::errorRecordNotExist);
        return this.personConverter.converterToResponse(response.getPerson());
    }

    /**
     * Atualiza dados do usuario (nome, endereco e contato). Somente o proprio usuario poderar alterar.
     *
     * @param request Objeto enviado no corpo da requisicao.
     * @param jwt     Token enviado na requisicao. Sera utilizado o username qu vem no token e verificar se o usuario existe na base.
     * @return UserReadBase
     * @author fernando.matheus
     */
    public UserResponse update(UserUpdateRequest request, Jwt jwt) {
        var username = jwt.getClaims().get("username").toString();
        var result = this.findUser(username);
        var commit = this.userService.save(this.userUpdateConverter.converterToUpdate(result, request));
        var converter = this.personConverter.converterToResponse(commit.getPerson());
        converter.setMessage(this.messageResponse.messageSuccessUpdate());
        converter.setUsers(null);
        return converter;
    }

    /**
     * Atualiza a senha do usuario. Somente o proprio usuario poderar alterar.
     *
     * @param request Objeto enviado no corpo da requisicao.
     * @param jwt     Token enviado na requisicao. Sera utilizado o username qu vem no token e verificar se o usuario existe na base.
     * @author fernando.matheus
     */
    public MessageResponseHandler updatePassword(PasswordUpdateBase request, Jwt jwt) {
        var username = jwt.getClaims().get("username").toString();
        var result = this.findUser(username);

        if (!request.getPassword().equals(request.getConfirmPassword())) {
            throw this.messageResponse.errorPasswordNotMatchException();
        }

        var encodedPassword = this.passwordEncoder.encode(request.getPassword());
        result.setPassword(encodedPassword);
        this.userService.save(result);

        return this.messageResponse.messageSuccessUpdate();
    }

    /**
     * Cria um novo usuario seguindo as seguintes condicoes:
     * - Verifica se o nome de usuario (email) não existe.
     * - Verifica se o email nao existe.
     * - Verifica se o telefone nao existe.
     *
     * @param request Objeto enviado no corpo da requisicao.
     * @return UserResponse
     * @author fernando.matheus
     */
    public UserResponse create(UserCreateRequest request) {

        if (this.userService.findByUsername(request.getContact().getEmail()).isPresent()) {
            throw this.messageResponse.errorExistEmail();
        }

        if (this.contactService.findByEmail(request.getContact().getEmail()).isPresent()) {
            throw this.messageResponse.errorExistEmail();
        }

        if (this.contactService.findByPhone(request.getContact().getPhone()).isPresent()) {
            throw this.messageResponse.errorExistPhone();
        }
        var commit = this.personService.save(this.userCreateConverter.converterToEntity(request));
        var converter = this.personConverter.converterToResponse(commit);
        converter.setMessage(this.messageResponse.messageSuccessCreate());

        return converter;

    }

    public void updatePermissions(UUID uuid, UserPermissionUpdateRequest request) {
        var user = this.userService.findByUuid(uuid).orElseThrow(this.messageResponse::errorUserdNotExist);

        user.setPermissions(request.getPermissions().stream()
                .filter(filter -> !Objects.equals(filter.getMethod().getValue(), MethodEnum.DELETE.getValue()))
                .toList().stream()
                .map(this::converter).collect(Collectors.toList()));
        this.userService.save(user);
    }

    private Permission converter(PermissionUpdateBase request) {
        var permission = new Permission();
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
    private User findUser(String username) {
        return this.userService.findByUsername(username).orElseThrow(this.messageResponse::errorUserdNotExist);
    }
}
