package com.fmatheus.app.controller.rule;

import com.fmatheus.app.UserMock;
import com.fmatheus.app.controller.converter.UserConverter;
import com.fmatheus.app.controller.converter.UserPartialConverter;
import com.fmatheus.app.controller.converter.UserUpdateConverter;
import com.fmatheus.app.controller.exception.message.MessageResponse;
import com.fmatheus.app.model.entity.User;
import com.fmatheus.app.model.repository.filter.UserRepositoryFilter;
import com.fmatheus.app.model.service.UserService;
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

    @Mock
    private UserService service;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @MockBean
    private UserServiceImpl userService;

    @MockBean
    private UserConverter userConverter;

    @MockBean
    private UserPartialConverter userPartialConverter;

    @MockBean
    private UserUpdateConverter userUpdateConverter;

    @MockBean
    private MessageResponse messageResponse;

    private UserRule userRule;

    @BeforeEach
    public void setUp() {
        openMocks(this);
        userRule = new UserRule(passwordEncoder, service, userConverter, userPartialConverter, userUpdateConverter, messageResponse);
    }

    /**
     * Metodo de teste: {@link UserRule#findAllFilter(Pageable, UserRepositoryFilter)}
     *
     * @author fernando.matheus
     */
    @Test
    @Order(1)
    @DisplayName("Sucesso na listagem de registros.")
    void findAllFilter() {

        Pageable pageable = PageRequest.of(0, 10);
        UserRepositoryFilter filter = UserMock.loadUserRepositoryFilter();
        var user = UserMock.loadUser();
        var page = new PageImpl<>(Collections.singletonList(user));

        when(this.service.findAllFilter(any(Pageable.class), any(UserRepositoryFilter.class))).thenReturn(page);
        when(this.userPartialConverter.converterToResponse(any(User.class))).thenReturn(UserMock.loadUserPartialResponse());
        when(this.service.totalPaginator(any(UserRepositoryFilter.class))).thenReturn(1L);

        var actualResult = this.service.findAllFilter(pageable, filter);
        assertFalse(actualResult.isEmpty());
        assertEquals(PageImpl.class, actualResult.getClass());
        verify(this.service).findAllFilter(pageable, filter);

        var result = this.userRule.findAllFilter(pageable, filter);

        assertFalse(result.isEmpty());

    }

    @Test
    @Order(2)
    @DisplayName("Sucesso na listagem de registros vazia.")
    void findAllFilterEmpty() {
        Pageable pageable = PageRequest.of(0, 10);
        UserRepositoryFilter filter = UserMock.loadUserRepositoryFilter();
        Page<User> page = Page.empty();
        when(this.service.findAllFilter(any(Pageable.class), any(UserRepositoryFilter.class))).thenReturn(page);

        var actualResult = this.service.findAllFilter(pageable, filter);
        assertTrue(actualResult.isEmpty());
        verify(this.service).findAllFilter(pageable, filter);

        var result = userRule.findAllFilter(pageable, filter);

        assertTrue(result.isEmpty());
    }

}