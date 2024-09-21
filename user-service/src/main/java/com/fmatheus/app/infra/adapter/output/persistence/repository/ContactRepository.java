package com.fmatheus.app.infra.adapter.output.persistence.repository;

import com.fmatheus.app.application.domain.ContactDomain;
import com.fmatheus.app.application.port.ContactRepositoryPort;
import com.fmatheus.app.infra.adapter.output.persistence.data.ContactRepositoryData;
import com.fmatheus.app.infra.adapter.output.persistence.entity.Contact;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Component
@Primary
public class ContactRepository implements ContactRepositoryPort {

    private final ContactRepositoryData data;
    private final ModelMapper modelMapper;


    @Override
    public Optional<ContactDomain> findByEmail(String email) {
        var result = this.data.findByEmail(email);
        return result.map(this::convertToDomain);
    }

    @Override
    public Optional<ContactDomain> findByPhone(String phone) {
        var result = this.data.findByPhone(phone);
        return result.map(this::convertToDomain);
    }

    @Override
    public List<ContactDomain> findAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public ContactDomain save(ContactDomain contactDomain) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<ContactDomain> findById(Integer integer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteById(Integer integer) {
        throw new UnsupportedOperationException();
    }

    private ContactDomain convertToDomain(Contact contact) {
        return this.modelMapper.map(contact, ContactDomain.class);
    }

}
