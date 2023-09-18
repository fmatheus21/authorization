package com.fmatheus.app;

import com.fmatheus.app.controller.dto.response.*;
import com.fmatheus.app.model.entity.*;
import com.fmatheus.app.model.repository.filter.UserRepositoryFilter;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.UUID;

public class UserMock {

    private static final UUID ID = UUID.fromString("ae46dc08-2c64-11ee-a204-581122c7752d");
    private static final String USER_NAME = "67780886050";
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
    private static final UUID ID_PERSON = UUID.fromString("581c2c14-f5f4-11ed-9216-7085c2be6d69");



    public static UserPartialResponse loadUserPartialResponse() {
        var user = UserPartialResponse.builder()
                .username(USER_NAME)
                .active(true)
                .person(PersonPartialResponse.builder()
                        .name(NAME)
                        .createdAt(LocalDateTime.now())
                        .build())
                .contact(ContactResponse.builder()
                        .email(EMAIL)
                        .phone(PHONE)
                        .build())
                .build();
        user.setId(ID);
        return user;
    }

    public static User loadUser() {
        var user = User.builder()
                .username(USER_NAME)
                .active(true)
                .person(loadPerson())
                .build();
        user.setId(ID);
        return user;
    }


    public static UserRepositoryFilter loadUserRepositoryFilter() {
        return UserRepositoryFilter.builder()
                .name("Fernando")
                .build();
    }

    private static Person loadPerson() {
        var person = Person.builder()
                .name(NAME)
                .document(DOCUMENT)
                .createdAt(LocalDateTime.now())
                .address(loadAddress())
                .contact(loadContact())
                .build();
        person.setId(ID_PERSON);
        return person;
    }

    private static Address loadAddress() {
        var address = Address.builder()
                .place(PLACE)
                .number(NUMBER)
                .complement(COMPLEMENT)
                .district(DISTRICT)
                .city(CITY)
                .state(STATE)
                .zipCode(ZIPCODE)
                .build();
        address.setId(UUID.fromString("b5afe373-05d5-11ee-900d-7085c2be6d69"));
        return address;
    }

    private static Contact loadContact() {
        var contact = Contact.builder().email(EMAIL)
                .phone(PHONE)
                .build();
        contact.setId(UUID.fromString("8541944c-05d5-11ee-900d-7085c2be6d69"));
        return contact;
    }

    private static Permission loadPermission() {
        var system = Systems.builder()
                .name("user_service")
                .build();
        system.setId(UUID.fromString("50b56e7d-2c5e-11ee-a204-581122c7752d"));
        var permission = Permission.builder()
                .name("user_service_all_permissions")
                .system(system)
                .build();
        permission.setId(UUID.fromString("5a86bd48-2c66-11ee-a204-581122c7752d"));
        return permission;
    }

    public static UserResponse loadUserResponse() {
        var user = loadUser();
        var permission = loadPermission();

        var systemResponse = SystemsResponse.builder()
                .name("user_service")
                .build();


        var permissionResponse = PermissionResponse.builder()
                .name(permission.getName())
                .system(systemResponse)
                .build();

        var personResponse = PersonResponse.builder()
                .name(user.getPerson().getName())
                .document(user.getPerson().getDocument())
                .createdAt(user.getPerson().getCreatedAt())
                .address(AddressResponse.builder()
                        .city(user.getPerson().getAddress().getCity())
                        .complement(user.getPerson().getAddress().getComplement())
                        .district(user.getPerson().getAddress().getDistrict())
                        .number(user.getPerson().getAddress().getNumber())
                        .state(user.getPerson().getAddress().getState())
                        .place(user.getPerson().getAddress().getPlace())
                        .zipCode(user.getPerson().getAddress().getZipCode())
                        .build())
                .contact(ContactResponse.builder()
                        .phone(user.getPerson().getContact().getPhone())
                        .email(user.getPerson().getContact().getEmail())
                        .build())
                .build();

        var response = UserResponse.builder()
                .username(user.getUsername())
                .active(user.isActive())
                .person(personResponse)
                .permissions(Collections.singletonList(permissionResponse))
                .build();

        response.setId(user.getId());

        return response;
    }
}
