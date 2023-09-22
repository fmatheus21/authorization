package com.fmatheus.app.model.service.impl;

import com.fmatheus.app.controller.util.CharacterUtil;
import com.fmatheus.app.model.entity.Contact;
import com.fmatheus.app.model.repository.ContactRepository;
import com.fmatheus.app.model.service.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ContactServiceImpl implements ContactService {

    private final ContactRepository repository;

    @Override
    public Optional<Contact> findByEmail(String email) {
        return this.repository.findByEmail(CharacterUtil.removeSpecialCharacters(email));
    }

    @Override
    public Optional<Contact> findByPhone(String phone) {
        return this.repository.findByPhone(CharacterUtil.removeSpecialCharacters(phone));
    }

    @Override
    public List<Contact> findAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<Contact> findById(Integer id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Contact save(Contact contact) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteById(Integer id) {
        throw new UnsupportedOperationException();
    }
}
