package com.fmatheus.app.hexagonal.infra.adapter.output.persistence.repository;

import com.fmatheus.app.hexagonal.application.domain.PersonDomain;
import com.fmatheus.app.hexagonal.application.port.PersonRepositoryPort;
import com.fmatheus.app.hexagonal.infra.adapter.output.persistence.data.PersonRepositoryData;
import com.fmatheus.app.hexagonal.infra.adapter.output.persistence.entity.Person;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;


import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Component
@Primary
public class PersonRepository implements PersonRepositoryPort {

    private final PersonRepositoryData data;
    private final ModelMapper modelMapper;

    @Override
    public List<PersonDomain> findAll() {
        var result = this.data.findAll();
        return result.stream().map(this::convertToDomain).toList();
    }

    @Override
    public PersonDomain save(PersonDomain domain) {
        var converter = this.data.save(this.converterToEntity(domain));
        return this.convertToDomain(converter);
    }

    @Override
    public Optional<PersonDomain> findById(Integer id) {
        var resutl = this.data.findById(id);
        return resutl.map(this::convertToDomain);
    }

    @Override
    public void deleteById(Integer id) {

    }

    private PersonDomain convertToDomain(Person person) {
        return this.modelMapper.map(person, PersonDomain.class);
    }

    private Person converterToEntity(PersonDomain domain) {
        return this.modelMapper.map(domain, Person.class);
    }

}
