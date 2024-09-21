package com.fmatheus.app.infra.adapter.output.persistence.data;

import com.fmatheus.app.infra.adapter.output.persistence.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContactRepositoryData extends JpaRepository<Contact, Integer> {

    Optional<Contact> findByEmail(String email);

    Optional<Contact> findByPhone(String phone);

}
