package com.fmatheus.app.model.repository.impl.restriction;

import com.fmatheus.app.controller.enumerable.EntityEnum;
import com.fmatheus.app.controller.util.CharacterUtil;
import com.fmatheus.app.model.entity.Contact;
import com.fmatheus.app.model.entity.Person;
import com.fmatheus.app.model.entity.User;
import com.fmatheus.app.model.repository.filter.UserRepositoryFilter;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class UserRestriction {

    private static final String PERCENT = "%";

    /**
     * Metodo responsavel por criar uma array de Predicate para implementar uma
     * consulta de acordo com o filtro.
     *
     * @param filter  - Filtro de consulta
     * @param builder - CriteriaBuilder
     * @param root    - Root
     * @return Predicate[]
     * @author Fernando Matheus
     */
    protected Predicate[] createRestrictions(UserRepositoryFilter filter, CriteriaBuilder builder, Root<User> root) {

        List<Predicate> predicates = new ArrayList<>();

        Join<Person, User> joinPerson = root.join(EntityEnum.ID_PERSON.getValue());
        Join<Contact, Person> joinContact = joinPerson.join(EntityEnum.CONTACT.getValue());

        if (Objects.nonNull(filter.getName())) {
            predicates.add(builder.like(builder.lower(joinPerson.get(EntityEnum.NAME.getValue())),
                    PERCENT + filter.getName().toLowerCase() + PERCENT));
        }

        if (Objects.nonNull(filter.getDocument())) {
            predicates.add(builder.like(builder.lower(joinPerson.get(EntityEnum.DOCUMENT.getValue())),
                    PERCENT + CharacterUtil.removeSpecialCharacters(filter.getDocument()) + PERCENT));
        }

        if (Objects.nonNull(filter.getEmail())) {
            predicates.add(builder.like(builder.lower(joinContact.get(EntityEnum.EMAIL.getValue())),
                    PERCENT + filter.getEmail().toLowerCase() + PERCENT));
        }

        if (Objects.nonNull(filter.getUsername())) {
            predicates.add(builder.like(builder.lower(root.get(EntityEnum.USERNAME.getValue())),
                    PERCENT + filter.getUsername().toLowerCase() + PERCENT));
        }

        return predicates.toArray(new Predicate[0]);

    }


    /**
     * Metodo responsavel por criar paginacao.
     *
     * @param typedQuery - TypedQuery
     * @param pageable   - Pageable
     * @author Fernando Matheus
     */
    protected void addPageRestrictions(TypedQuery<User> typedQuery, Pageable pageable) {
        int currentPage = pageable.getPageNumber();
        int totalRecordsPerPage = pageable.getPageSize();
        int firstPageRecord = currentPage * totalRecordsPerPage;

        typedQuery.setFirstResult(firstPageRecord);
        typedQuery.setMaxResults(totalRecordsPerPage);
    }


}
