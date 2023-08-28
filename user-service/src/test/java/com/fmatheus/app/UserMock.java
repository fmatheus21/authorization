package com.fmatheus.app;

import com.fmatheus.app.controller.dto.response.ContactResponse;
import com.fmatheus.app.controller.dto.response.PersonPartialResponse;
import com.fmatheus.app.controller.dto.response.UserPartialResponse;
import com.fmatheus.app.model.entity.Address;
import com.fmatheus.app.model.entity.Contact;
import com.fmatheus.app.model.entity.Person;
import com.fmatheus.app.model.entity.User;
import com.fmatheus.app.model.repository.filter.UserRepositoryFilter;

import java.time.LocalDateTime;
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
        person.setId(UUID.fromString("581c2c14-f5f4-11ed-9216-7085c2be6d69"));
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
}
