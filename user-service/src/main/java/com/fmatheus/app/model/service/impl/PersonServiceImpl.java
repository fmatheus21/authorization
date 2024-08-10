package com.fmatheus.app.model.service.impl;

import com.fmatheus.app.hexagonal.infra.adapter.output.persistence.entity.Person;
import com.fmatheus.app.model.repository.PersonRepository;
import com.fmatheus.app.model.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository repository;

    @Override
    public List<Person> findAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<Person> findById(Integer id) {
        return this.repository.findById(id);
    }

    @Override
    public Person save(Person person) {
        return this.repository.save(person);
    }

    @Override
    public void deleteById(Integer id) {
        throw new UnsupportedOperationException();
    }
}
