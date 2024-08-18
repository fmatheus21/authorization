package com.fmatheus.app.hexagonal.infra.adapter.output.persistence.data;

import com.fmatheus.app.hexagonal.infra.adapter.output.persistence.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepositoryData extends JpaRepository<Person, Integer> {
}
