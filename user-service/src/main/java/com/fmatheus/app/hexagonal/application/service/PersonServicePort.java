package com.fmatheus.app.hexagonal.application.service;

import com.fmatheus.app.hexagonal.application.domain.PersonDomain;
import com.fmatheus.app.hexagonal.application.port.GenericPort;

public interface PersonServicePort extends GenericPort<PersonDomain, Integer> {
}
