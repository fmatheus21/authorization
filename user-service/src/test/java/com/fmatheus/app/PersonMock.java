package com.fmatheus.app;

import com.fmatheus.app.controller.dto.request.UserCreateDtoRequest;
import com.fmatheus.app.controller.dto.request.UserUpdateDtoRequest;
import com.fmatheus.app.controller.dto.response.UserDtoResponse;
import com.fmatheus.app.model.entity.*;
import com.fmatheus.app.model.repository.filter.UserRepositoryFilter;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.UUID;

public class PersonMock {

    private static final UUID USER_UUID = UUID.fromString("ae46dc08-2c64-11ee-a204-581122c7752d");
    private static final UUID PERMISSION_UUID = UUID.fromString("f8ed48e0-5879-11ee-94e1-581122c7752d");
    private static final String PERMISSION_NAME = "user_service_all_permissions";
    private static final LocalDateTime CREATED_AT = LocalDateTime.now();
    private static final LocalDateTime UPDATED_AT = LocalDateTime.now();
    private static final String SYSTEM_NAME = "user_service";
    private static final UUID SYSTEM_UUID = UUID.fromString("50b56e7d-2c5e-11ee-a204-581122c7752d");
    private static final String USER_NAME = "fernando.matheuss@hotmail.com";
    private static final String NAME = "Fernando Braga Matheus";
    private static final String EMAIL = "fernando.matheuss@hotmail.com";
    private static final String PHONE = "21986731552";
    private static final String DOCUMENT = "67780886050";
    private static final String PLACE = "Avenida Das Américas";
    private static final String NUMBER = "12000";
    private static final String COMPLEMENT = "Apt 401";
    private static final String DISTRICT = "Barra Da Tijuca";
    private static final String CITY = "Rio De Janeiro";
    private static final String STATE = "RJ";
    private static final String ZIPCODE = "22793082";
    private static final Integer PERSON_ID = 1;
    private static final UUID PERSON_TYPE_UUID = UUID.fromString("0015dc51-05d5-11ee-900d-7085c2be6d69");
    private static final int PERSON_TYPE_ID = 1;


    public static User loadUser() {

        var address = Address.builder()
                .place(PLACE)
                .number(NUMBER)
                .complement(COMPLEMENT)
                .district(DISTRICT)
                .city(CITY)
                .state(STATE)
                .zipCode(ZIPCODE)
                .build();
        address.setId(1);

        var contact = Contact.builder().email(EMAIL)
                .phone(PHONE)
                .build();
        contact.setId(1);

        var person = Person.builder()
                .name(NAME)
                .document(DOCUMENT)
                .address(address)
                .contact(contact)
                .build();
        person.setId(PERSON_ID);

        var user = User.builder()
                .uuid(USER_UUID)
                .username(USER_NAME)
                .password(null)
                .active(true)
                .createdAt(CREATED_AT)
                .updatedAt(UPDATED_AT)
                .build();
        user.setId(1);

        user.setPerson(person);

        return user;
    }

    public static Person loadPerson() {

        var address = Address.builder()
                .place(PLACE)
                .number(NUMBER)
                .complement(COMPLEMENT)
                .district(DISTRICT)
                .city(CITY)
                .state(STATE)
                .zipCode(ZIPCODE)
                .build();
        address.setId(1);

        var contact = Contact.builder().email(EMAIL)
                .phone(PHONE)
                .build();
        contact.setId(1);

        var person = Person.builder()
                .name(NAME)
                .document(DOCUMENT)
                .address(address)
                .contact(contact)
                .build();
        person.setId(PERSON_ID);

        var user = User.builder()
                .uuid(USER_UUID)
                .username(USER_NAME)
                .password(null)
                .active(true)
                .createdAt(CREATED_AT)
                .updatedAt(UPDATED_AT)
                .build();
        user.setId(1);

        person.setUsers(Collections.singleton(user));

        return person;
    }


    public static UserRepositoryFilter loadUserRepositoryFilter() {
        return UserRepositoryFilter.builder()
                .name("xxxxxxx")
                .build();
    }

    public static UserDtoResponse loadPersonResponse() {
        var person = UserDtoResponse.builder()
                .name(NAME)
                .document(DOCUMENT)
                .address(UserDtoResponse.AddressResponse.builder()
                        .city(CITY)
                        .complement(COMPLEMENT)
                        .district(DISTRICT)
                        .number(NUMBER)
                        .state(STATE)
                        .place(PLACE)
                        .zipCode(ZIPCODE)
                        .build())
                .contact(UserDtoResponse.ContactResponse.builder()
                        .phone(PHONE)
                        .email(EMAIL)
                        .build())
                .message(MessageResponseHandlerMock.loadMessageResponseHandlerSuccessCreate())
                .build();

        var system = UserDtoResponse.UserResponse.PermissionResponse.SystemsPermissionResponse.builder()
                .name(SYSTEM_NAME)
                .build();
        system.setUuid(SYSTEM_UUID);

        var permission = UserDtoResponse.UserResponse.PermissionResponse.builder()
                .name(PERMISSION_NAME)
                .system(system)
                .build();
        permission.setUuid(PERMISSION_UUID);

        var user = UserDtoResponse.UserResponse.builder()
                .username(USER_NAME)
                .active(true)
                .createdAt(CREATED_AT)
                .updatedAt(UPDATED_AT)
                .permissions(Collections.singletonList(permission))
                .build();
        user.setUuid(USER_UUID);

        return person;
    }


    public static UserCreateDtoRequest loadUserCreateRequest() {

        var permission = UserCreateDtoRequest.PermissionRequest.builder()
                .id(1)
                .name(PERMISSION_NAME)
                .build();

        return UserCreateDtoRequest.builder()
                .name(NAME)
                .document(DOCUMENT)
                .personType(UserCreateDtoRequest.PersonTypeRequest.builder()
                        .id(PERSON_TYPE_ID)
                        .uuid(PERSON_TYPE_UUID)
                        .build())
                .address(UserCreateDtoRequest.AddressRequest.builder()
                        .city(CITY)
                        .complement(COMPLEMENT)
                        .district(DISTRICT)
                        .number(NUMBER)
                        .state(STATE)
                        .place(PLACE)
                        .zipCode(ZIPCODE)
                        .build())
                .contact(UserCreateDtoRequest.ContactRequest.builder()
                        .phone(PHONE)
                        .email(EMAIL)
                        .build())
                .permissions(Collections.singletonList(permission))
                .build();
    }

    public static UserUpdateDtoRequest loadUserUpdateRequest() {
        return UserUpdateDtoRequest.builder()
                .name(NAME)
                .address(UserUpdateDtoRequest.AddressRequest.builder()
                        .city(CITY)
                        .complement(COMPLEMENT)
                        .district(DISTRICT)
                        .number(NUMBER)
                        .state(STATE)
                        .place(PLACE)
                        .zipCode(ZIPCODE)
                        .build())
                .contact(UserUpdateDtoRequest.ContactRequest.builder()
                        .phone(PHONE)
                        .email(EMAIL)
                        .build())
                .build();
    }

}
