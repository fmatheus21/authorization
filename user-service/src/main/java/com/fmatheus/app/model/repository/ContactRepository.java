package com.fmatheus.app.model.repository;

import com.fmatheus.app.model.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ContactRepository extends JpaRepository<Contact, UUID>{

    Optional<Contact> findByEmail(String email);

    Optional<Contact> findByPhone(String phone);


}