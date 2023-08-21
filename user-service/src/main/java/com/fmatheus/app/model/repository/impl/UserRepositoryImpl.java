package com.fmatheus.app.model.repository.impl;

import com.fmatheus.app.controller.enumerable.EntityEnum;
import com.fmatheus.app.model.entity.Person;
import com.fmatheus.app.model.entity.User;
import com.fmatheus.app.model.repository.filter.UserRepositoryFilter;
import com.fmatheus.app.model.repository.impl.restriction.UserRestriction;
import com.fmatheus.app.model.repository.query.UserRepositoryQuery;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

public class UserRepositoryImpl extends UserRestriction implements UserRepositoryQuery {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Page<User> findAllFilter(Pageable pageable, UserRepositoryFilter filter) {
        var builder = manager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = builder.createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);
        Join<Person, User> joinPerson = root.join(EntityEnum.ID_PERSON.getValue());
        Predicate[] predicates = createRestrictions(filter, builder, root);
        criteriaQuery
                .where(predicates)
                .orderBy(builder.asc(joinPerson.get(EntityEnum.NAME.getValue())));

        TypedQuery<User> typedQuery = manager.createQuery(criteriaQuery);

        addPageRestrictions(typedQuery, pageable);

        return new PageImpl<>(typedQuery.getResultList(), pageable, total(filter));
    }


    /**
     * Metodo responsavel por contar o total de registros.
     *
     * @param filter - Filtro de consulta
     * @return Long
     * @author Fernando Matheus
     */
    public Long total(UserRepositoryFilter filter) {
        var builder = manager.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = builder.createQuery(Long.class);
        Root<User> root = criteriaQuery.from(User.class);

        Predicate[] predicates = createRestrictions(filter, builder, root);
        criteriaQuery.where(predicates);

        criteriaQuery.select(builder.count(root));

        return manager.createQuery(criteriaQuery).getSingleResult();
    }




}
