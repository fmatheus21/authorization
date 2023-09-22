package com.fmatheus.app.controller.rule;

import com.fmatheus.app.UserMock;
import com.fmatheus.app.controller.converter.UserConverter;
import com.fmatheus.app.controller.converter.UserCreateConverter;
import com.fmatheus.app.controller.converter.UserPartialConverter;
import com.fmatheus.app.controller.converter.UserUpdateConverter;
import com.fmatheus.app.controller.dto.response.UserResponse;
import com.fmatheus.app.controller.exception.message.MessageResponse;
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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

@DisplayName("Teste de regra de negocio User")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ContextConfiguration(classes = {UserRule.class})
@ExtendWith(SpringExtension.class)
class UserRuleTest {

    private static final UUID ID = UUID.fromString("ae46dc08-2c64-11ee-a204-581122c7752d");

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
    private UserConverter userConverter;

    @MockBean
    private UserPartialConverter userPartialConverter;

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
        rule = new UserRule(passwordEncoder, userService, personService, contactService, userConverter, userPartialConverter,
                userUpdateConverter, userCreateConverter, messageResponse);
    }

    /**
     * Metodo de teste: {@link UserRule#findAllFilter(Pageable, UserRepositoryFilter)}
     */
    @Test
    @Order(1)
    @DisplayName("Sucesso na listagem de registros.")
    void findAllFilter() {

        Pageable pageable = PageRequest.of(0, 10);
        UserRepositoryFilter filter = UserMock.loadUserRepositoryFilter();
        var user = UserMock.loadUser();
        var page = new PageImpl<>(Collections.singletonList(user));

        when(this.userService.findAllFilter(any(Pageable.class), any(UserRepositoryFilter.class))).thenReturn(page);
        when(this.userPartialConverter.converterToResponse(any(User.class))).thenReturn(UserMock.loadUserPartialResponse());
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
    @DisplayName("Sucesso na listagem de registros vazia.")
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
    @DisplayName("Sucesso ao encontrar o registro pelo ID")
    void findByUuidSuccess() {

        var ID_CONSULT = UUID.fromString("ae46dc08-2c64-11ee-a204-581122c7752d");
        var optional = Optional.of(UserMock.loadUser());
        var response = UserMock.loadUserResponse();

        when(this.userConverter.converterToResponse(any(User.class))).thenReturn(response);
        when(this.userService.findByUuid(ID_CONSULT)).thenReturn(optional);

        var actualResult = this.userService.findByUuid(ID);
        assertSame(optional, actualResult);
        assertTrue(actualResult.isPresent());
        assertEquals(User.class, actualResult.get().getClass());
        verify(this.userService).findByUuid(ID);

        UserResponse result = rule.findByUuid(ID);
        assertNotNull(result);
        assertEquals(response, result);
    }

    /**
     * Metodo de teste: {@link UserRule#findByUuid(UUID)}
     */
    @Test
    @Order(4)
    @DisplayName("Erro ao pesquisar o registro pelo ID")
    void findByUuidException() {
        UUID uuid = UUID.randomUUID();
        when(userService.findByUuid(any(UUID.class))).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> rule.findByUuid(uuid));
    }


}