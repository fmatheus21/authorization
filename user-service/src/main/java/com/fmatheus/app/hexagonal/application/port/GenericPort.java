package com.fmatheus.app.hexagonal.application.port;

import java.util.List;
import java.util.Optional;

public interface GenericPort<T, ID> {

    List<T> findAll();

    T save(T t);

    Optional<T> findById(ID id);

    void deleteById(ID id);

}