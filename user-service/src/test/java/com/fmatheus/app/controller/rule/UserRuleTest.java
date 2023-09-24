package com.fmatheus.app.controller.rule;

import com.fmatheus.app.MessageResponseHandlerMock;
import com.fmatheus.app.UserMock;
import com.fmatheus.app.controller.converter.PersonConverter;
import com.fmatheus.app.controller.converter.UserCreateConverter;
import com.fmatheus.app.controller.converter.UserUpdateConverter;
import com.fmatheus.app.controller.dto.request.UserCreateRequest;
import com.fmatheus.app.controller.dto.request.extension.PasswordUpdateRequest;
import com.fmatheus.app.controller.dto.request.UserUpdateRequest;
import com.fmatheus.app.controller.enumerable.MessagesEnum;
import com.fmatheus.app.controller.exception.BadRequestException;
import com.fmatheus.app.controller.exception.PasswordNotMatchException;
import com.fmatheus.app.controller.exception.message.MessageResponse;
import com.fmatheus.app.model.entity.Person;
import com.fmatheus.app.model.entity.User;
import com.fmatheus.app.model.repository.filter.UserRepositoryFilter;
import com.fmatheus.app.model.service.ContactService;
import com.fmatheus.app.model.service.PersonService;
import com.fmatheus.app.model.service.UserService;
import com.fmatheus.app.model.service.impl.ContactServiceImpl;
import com.fmatheus.app.model.service.impl.PersonServiceImpl;
import com.fmatheus.app.model.service.impl.UserServiceImpl;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

@DisplayName("Teste de regra de negocio User")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ContextConfiguration(classes = {UserRule.class})
@ExtendWith(SpringExtension.class)
class UserRuleTest {

    private static final UUID ID = UUID.fromString("ae46dc08-2c64-11ee-a204-581122c7752d");

    @Mock
    private Jwt jwt;

    @Mock
    private UserService userService;
    @Mock
    private ContactService contactService;

    @Mock
    private PersonService personService;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @MockBean
    private UserServiceImpl userServiceImpl;

    @MockBean
    private PersonServiceImpl personServiceImpl;

    @MockBean
    private ContactServiceImpl contactServiceImpl;

    @MockBean
    private PersonConverter personConverter;

    @MockBean
    private UserUpdateConverter userUpdateConverter;

    @MockBean
    private UserCreateConverter userCreateConverter;

    @MockBean
    private MessageResponse messageResponse;

    private UserRule rule;

    @BeforeEach
    public void setUp() {
        openMocks(this);
        rule = new UserRule(passwordEncoder, userService, personService, contactService, personConverter,
                userUpdateConverter, userCreateConverter, messageResponse);

        Map<String, Object> claims = new HashMap<>();
        claims.put("username", "67780886050");
        when(jwt.getClaims()).thenReturn(claims);
    }

    /**
     * Metodo de teste: {@link UserRule#findAllFilter(Pageable, UserRepositoryFilter)}
     */
    @Test
    @Order(1)
    @DisplayName("findAllFilter - Sucesso na listagem de registros.")
    void findAllFilter() {

        Pageable pageable = PageRequest.of(0, 10);
        UserRepositoryFilter filter = UserMock.loadUserRepositoryFilter();
        var user = UserMock.loadUser();
        var page = new PageImpl<>(Collections.singletonList(user));

        when(this.userService.findAllFilter(any(Pageable.class), any(UserRepositoryFilter.class))).thenReturn(page);
        when(this.personConverter.converterToResponse(any(Person.class))).thenReturn(UserMock.loadPersonResponse());
        when(this.userService.totalPaginator(any(UserRepositoryFilter.class))).thenReturn(1L);

        var actualResult = this.userService.findAllFilter(pageable, filter);
        assertFalse(actualResult.isEmpty());
        assertEquals(PageImpl.class, actualResult.getClass());
        verify(this.userService).findAllFilter(pageable, filter);

        var result = this.rule.findAllFilter(pageable, filter);
        assertFalse(result.isEmpty());

    }

    /**
     * Metodo de teste: {@link UserRule#findAllFilter(Pageable, UserRepositoryFilter)}
     */
    @Test
    @Order(2)
    @DisplayName("findAllFilter - Sucesso na listagem de registros vazia.")
    void findAllFilterEmpty() {
        Pageable pageable = PageRequest.of(0, 10);
        UserRepositoryFilter filter = UserMock.loadUserRepositoryFilter();
        Page<User> page = Page.empty();
        when(this.userService.findAllFilter(any(Pageable.class), any(UserRepositoryFilter.class))).thenReturn(page);

        var actualResult = this.userService.findAllFilter(pageable, filter);
        assertTrue(actualResult.isEmpty());
        verify(this.userService).findAllFilter(pageable, filter);

        var result = rule.findAllFilter(pageable, filter);

        assertTrue(result.isEmpty());
    }

    /**
     * Metodo de teste: {@link UserRule#findByUuid(UUID)} (UUID)}
     */
    @Test
    @Order(3)
    @DisplayName("findByUuid - Sucesso ao encontrar o registro pelo ID")
    void findByUuidSuccess() {

        var ID_CONSULT = UUID.fromString("ae46dc08-2c64-11ee-a204-581122c7752d");
        var optional = Optional.of(UserMock.loadUser());
        var response = UserMock.loadPersonResponse();

        when(this.personConverter.converterToResponse(any(Person.class))).thenReturn(response);
        when(this.userService.findByUuid(ID_CONSULT)).thenReturn(optional);

        var actualResult = this.userService.findByUuid(ID);
        assertSame(optional, actualResult);
        assertTrue(actualResult.isPresent());
        assertEquals(User.class, actualResult.get().getClass());
        verify(this.userService).findByUuid(ID);

        var result = rule.findByUuid(ID);
        assertNotNull(result);
        assertEquals(response, result);
    }

    /**
     * Metodo de teste: {@link UserRule#findByUuid(UUID)}
     */
    @Test
    @Order(4)
    @DisplayName("findByUuid - Erro ao pesquisar o registro pelo ID")
    void findByUuidException() {
        UUID uuid = UUID.randomUUID();
        when(userService.findByUuid(any(UUID.class))).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> rule.findByUuid(uuid));
    }

    /**
     * Metodo de teste: {@link UserRule#create(UserCreateRequest)}
     */
    @Test
    @Order(5)
    @DisplayName("create - Sucesso ao criar registro.")
    void createTestSuccess() {

        var person = UserMock.loadPerson();
        var response = UserMock.loadPersonResponse();

        when(messageResponse.messageSuccessCreate()).thenReturn(MessageResponseHandlerMock.loadMessageResponseHandlerSuccessCreate());
        when(userService.findByUsername(anyString())).thenReturn(Optional.empty());
        when(contactService.findByEmail(anyString())).thenReturn(Optional.empty());
        when(contactService.findByPhone(anyString())).thenReturn(Optional.empty());
        when(personService.save(any())).thenReturn(person);
        when(personConverter.converterToEntity(any(Person.class))).thenReturn(person);
        when(personConverter.converterToResponse(any(Person.class))).thenReturn(response);

        var result = rule.create(UserMock.loadUserCreateRequest());

        verify(userService).findByUsername("67780886050");
        verify(contactService).findByEmail("fernando.matheuss@hotmail.com");
        verify(contactService).findByPhone("21986731552");
        verify(personService).save(any());
        assertThat(result).isNotNull();

    }

    /**
     * Metodo de teste: {@link UserRule#create(UserCreateRequest)}
     */
    @Test
    @Order(6)
    @DisplayName("create - Quando o nome de usuario existir, lançar excecao")
    void createTestUsernameExistsException() {

        var request = UserMock.loadUserCreateRequest();
        var user = UserMock.loadUser();

        when(messageResponse.errorExistDocument()).thenReturn(new BadRequestException(MessagesEnum.ERROR_EXIST_DOCUMENT));
        when(userService.findByUsername(anyString())).thenReturn(Optional.of(user));

        assertThatExceptionOfType(BadRequestException.class)
                .isThrownBy(() -> rule.create(request))
                .withMessage("message.error.exist-document");

    }

    /**
     * Metodo de teste: {@link UserRule#create(UserCreateRequest)}
     */
    @Test
    @Order(7)
    @DisplayName("create - Quando o e-mail existir, lanca excecao")
    void createTestEmailExistsException() {

        var request = UserMock.loadUserCreateRequest();
        var contact = UserMock.loadUser().getPerson().getContact();

        when(messageResponse.errorExistEmail()).thenReturn(new BadRequestException(MessagesEnum.ERROR_EXIST_EMAIL));
        when(contactService.findByEmail(anyString())).thenReturn(Optional.of(contact));

        assertThatExceptionOfType(BadRequestException.class)
                .isThrownBy(() -> rule.create(request))
                .withMessage("message.error.exist-email");
    }

    /**
     * Metodo de teste: {@link UserRule#create(UserCreateRequest)}
     */
    @Test
    @Order(8)
    @DisplayName("create - Quando o telefone existir, lanca excecao")
    void createTestPhoneExistsException() {

        var request = UserMock.loadUserCreateRequest();
        var contact = UserMock.loadUser().getPerson().getContact();

        when(messageResponse.errorExistPhone()).thenReturn(new BadRequestException(MessagesEnum.ERROR_EXIST_PHONE));
        when(contactService.findByPhone(anyString())).thenReturn(Optional.of(contact));

        assertThatExceptionOfType(BadRequestException.class)
                .isThrownBy(() -> rule.create(request))
                .withMessage("message.error.exist-phone");
    }

    /**
     * Metodo de teste: {@link UserRule#update(UserUpdateRequest, Jwt)}
     */
    @Test
    @Order(9)
    @DisplayName("update - Sucesso ao atualizar registro")
    void updateTestSuccess() {

        var request = UserMock.loadUserUpdateRequest();
        var user = UserMock.loadUser();

        when(messageResponse.messageSuccessUpdate()).thenReturn(MessageResponseHandlerMock.loadMessageResponseHandlerSuccessUpdate());
        when(userService.findByUsername(any(String.class))).thenReturn(Optional.of(user));
        when(this.userUpdateConverter.converterToUpdate(any(User.class), any(UserUpdateRequest.class))).thenReturn(user);
        when(this.personConverter.converterToResponse(any(Person.class))).thenReturn(UserMock.loadPersonResponse());
        when(userService.save(any(User.class))).thenReturn(user);

        var response = rule.update(request, jwt);

        verify(userService).save(any(User.class));
        assertNotNull(response);
        assertEquals("Fernando Braga Matheus", response.getName());
    }

    /**
     * Metodo de teste: {@link UserRule#update(UserUpdateRequest, Jwt)}
     */
    @Test
    @Order(10)
    @DisplayName("update - Quando o usuario nao existir, lanca excecao")
    void updateTestUserDoesNotExistException() {

        var request = UserMock.loadUserUpdateRequest();

        when(messageResponse.errorUserdNotExist()).thenReturn(new BadRequestException(MessagesEnum.ERROR_USER_NOT_EXIST));
        when(userService.findByUsername(any(String.class))).thenReturn(Optional.empty());

        assertThatExceptionOfType(BadRequestException.class)
                .isThrownBy(() -> rule.update(request, jwt))
                .withMessage("message.error.user-not-exist");

    }


    /**
     * Metodo de teste: {@link UserRule#updatePassword(PasswordUpdateRequest, Jwt)}
     */
    @Test
    @Order(11)
    @DisplayName("updatePassword - Sucsso ao atualizar senha")
    void updatePasswordTestSuccess() {

        var user = UserMock.loadUser();
        PasswordUpdateRequest request = new PasswordUpdateRequest("password123", "password123");

        when(jwt.getClaims()).thenReturn(Map.of("username", "67780886050"));
        when(messageResponse.messageSuccessUpdate()).thenReturn(MessageResponseHandlerMock.loadMessageResponseHandlerSuccessUpdate());
        when(userService.findByUsername("67780886050")).thenReturn(Optional.of(user));
        when(passwordEncoder.encode("password123")).thenReturn("encodedPassword123");

        rule.updatePassword(request, jwt);

        verify(userService).save(any(User.class));
    }


    /**
     * Metodo de teste: {@link UserRule#updatePassword(PasswordUpdateRequest, Jwt)}
     */
    @Test
    @Order(12)
    @DisplayName("updatePassword - Quando as senhas nao conferem, lanca excecao")
    void updateTestPasswordNotMatchException() {

        PasswordUpdateRequest request = new PasswordUpdateRequest("password123", "password456");
        User user = new User();
        when(jwt.getClaims()).thenReturn(Map.of("username", "67780886050"));
        when(messageResponse.errorUserdNotExist()).thenReturn(new BadRequestException(MessagesEnum.ERROR_USER_NOT_EXIST));
        when(messageResponse.errorPasswordNotMatchException()).thenReturn(new PasswordNotMatchException());
        when(userService.findByUsername(anyString())).thenReturn(Optional.of(user));

        assertThatExceptionOfType(PasswordNotMatchException.class)
                .isThrownBy(() -> rule.updatePassword(request, jwt))
                .withMessage("message.error.password-not-match");
    }


}