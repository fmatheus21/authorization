package com.fmatheus.app.model.repository;

import com.fmatheus.app.hexagonal.infra.adapter.output.persistence.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {

}