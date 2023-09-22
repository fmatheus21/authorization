package com.fmatheus.app.controller.rule;

import com.fmatheus.app.controller.converter.PersonConverter;
import com.fmatheus.app.controller.converter.UserCreateConverter;
import com.fmatheus.app.controller.converter.UserPartialConverter;
import com.fmatheus.app.controller.converter.UserUpdateConverter;
import com.fmatheus.app.controller.dto.request.create.UserCreateRequest;
import com.fmatheus.app.controller.dto.request.update.PasswordUpdateRequest;
import com.fmatheus.app.controller.dto.request.update.UserUpdateRequest;
import com.fmatheus.app.controller.dto.response.PersonResponse;
import com.fmatheus.app.controller.dto.response.UserResponse;
import com.fmatheus.app.controller.exception.message.MessageResponse;
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
     * @return Page<PersonResponse>
     * @author fernando.matheus
     */
    public Page<PersonResponse> findAllFilter(Pageable pageable, UserRepositoryFilter filter) {
        var list = this.userService.findAllFilter(pageable, filter);
        var listConverter = list.map(map -> this.personConverter.converterToResponse(map.getPerson())).stream().toList();
        return new PageImpl<>(listConverter.stream().toList(), pageable, this.userService.totalPaginator(filter));
    }

    /**
     * Pesquisa o usuario pelo UUID.
     *
     * @param uuid ID do usuario enviado na variavel de requisicao.
     * @return PersonResponse
     * @author fernando.matheus
     */
    public PersonResponse findByUuid(UUID uuid) {
        var response = this.userService.findByUuid(uuid).orElseThrow(this.messageResponse::errorRecordNotExist);
        return this.personConverter.converterToResponse(response.getPerson());
    }

    /**
     * Atualiza dados do usuario (nome, endereco e contato). Somente o proprio usuario poderar alterar.
     *
     * @param request Objeto enviado no corpo da requisicao.
     * @param jwt     Token enviado na requisicao. Sera utilizado o username qu vem no token e verificar se o usuario existe na base.
     * @return PersonResponse
     * @author fernando.matheus
     */
    public PersonResponse update(UserUpdateRequest request, Jwt jwt) {
        var username = jwt.getClaims().get("username").toString();
        var result = this.findUser(username);
        var commit = this.userService.save(this.userUpdateConverter.converterToUpdate(result, request));
        var converter = this.personConverter.converterToResponse(commit.getPerson());
        converter.setMessage(this.messageResponse.messageSuccessUpdate());
        converter.setUser(null);
        return converter;
    }

    /**
     * Atualiza a senha do usuario. Somente o proprio usuario poderar alterar.
     *
     * @param request Objeto enviado no corpo da requisicao.
     * @param jwt     Token enviado na requisicao. Sera utilizado o username qu vem no token e verificar se o usuario existe na base.
     * @author fernando.matheus
     */
    public void updatePassword(PasswordUpdateRequest request, Jwt jwt) {
        var username = jwt.getClaims().get("username").toString();
        var result = this.findUser(username);
        if (!request.getPassword().equals(request.getConfirmPassword())) {
            throw this.messageResponse.errorPasswordNotMatchException();
        }
        var password = this.passwordEncoder.encode(request.getPassword());
        result.setPassword(password);
        this.userService.save(result);
    }

    public PersonResponse create(UserCreateRequest request) {
        var user = this.userService.findByUsername(request.getDocument()).orElse(null);
        if (Objects.nonNull(user)) {
            throw this.messageResponse.errorExistDocument();
        }

        var contact = this.contactService.findByEmail(request.getContact().getEmail()).orElse(null);
        if (Objects.nonNull(contact)) {
            throw this.messageResponse.errorExistEmail();
        }

        contact = this.contactService.findByPhone(request.getContact().getPhone()).orElse(null);
        if (Objects.nonNull(contact)) {
            throw this.messageResponse.errorExistPhone();
        }

        var commit = this.personService.save(this.userCreateConverter.converterToEntity(request));

        //return this.personConverter.converterToResponse(commit.getUser());
        return null;
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
