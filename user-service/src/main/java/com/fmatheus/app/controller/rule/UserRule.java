package com.fmatheus.app.controller.rule;

import com.fmatheus.app.controller.converter.UserConverter;
import com.fmatheus.app.controller.converter.UserCreateConverter;
import com.fmatheus.app.controller.converter.UserPartialConverter;
import com.fmatheus.app.controller.converter.UserUpdateConverter;
import com.fmatheus.app.controller.dto.request.create.UserCreateRequest;
import com.fmatheus.app.controller.dto.request.update.PasswordUpdateRequest;
import com.fmatheus.app.controller.dto.request.update.UserUpdateRequest;
import com.fmatheus.app.controller.dto.response.UserPartialResponse;
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

    private final UserConverter userConverter;

    private final UserPartialConverter userPartialConverter;

    private final UserUpdateConverter userUpdateConverter;

    private final UserCreateConverter userCreateConverter;

    private final MessageResponse messageResponse;


    /**
     * Retorna uma lista de usuarios (paginado) de acordo com o filtro informado.
     *
     * @param pageable Pageable
     * @param filter   Objeto contendo os filtros de pesquisa.
     * @return Page<UserPartialResponse>
     * @author fernando.matheus
     */
    public Page<UserPartialResponse> findAllFilter(Pageable pageable, UserRepositoryFilter filter) {
        var list = this.userService.findAllFilter(pageable, filter);
        var listConverter = list.map(this.userPartialConverter::converterToResponse).stream().toList();
        return new PageImpl<>(listConverter.stream().toList(), pageable, this.userService.totalPaginator(filter));
    }

    /**
     * Pesquisa o usuario pelo ID.
     *
     * @param id ID do usuario enviado na variavel de requisicao.
     * @return UserResponse
     * @author fernando.matheus
     */
    public UserResponse findById(UUID id) {
        var response = this.userService.findById(id).orElseThrow(this.messageResponse::errorRecordNotExist);
        return this.userConverter.converterToResponse(response);
    }

    /**
     * Atualiza dados do usuario (nome, endereco e contato). Somente o proprio usuario poderar alterar.
     *
     * @param request Objeto enviado no corpo da requisicao.
     * @param jwt     Token enviado na requisicao. Sera utilizado o username qu vem no token e verificar se o usuario existe na base.
     * @return UserResponse
     * @author fernando.matheus
     */
    public UserResponse update(UserUpdateRequest request, Jwt jwt) {
        var username = jwt.getClaims().get("username").toString();
        var result = this.findUser(username);
        var commit = this.userService.save(this.userUpdateConverter.converterToUpdate(result, request));
        var converter = this.userConverter.converterToResponse(commit);
        converter.setMessage(this.messageResponse.messageSuccessUpdate());
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

    public UserResponse create(UserCreateRequest request) {
        var user = this.userService.findByUsername(request.getPerson().getDocument()).orElse(null);
        if (Objects.nonNull(user)) {
            throw this.messageResponse.errorExistDocument();
        }

        var contact = this.contactService.findByEmail(request.getPerson().getContact().getEmail()).orElse(null);
        if (Objects.nonNull(contact)) {
            throw this.messageResponse.errorExistEmail();
        }

        contact = this.contactService.findByPhone(request.getPerson().getContact().getPhone()).orElse(null);
        if (Objects.nonNull(contact)) {
            throw this.messageResponse.errorExistPhone();
        }

        var person = this.userCreateConverter.converterToEntity(request);
        var commit = this.personService.save(person);

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
