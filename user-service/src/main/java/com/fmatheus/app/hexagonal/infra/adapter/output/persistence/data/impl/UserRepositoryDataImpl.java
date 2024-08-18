package com.fmatheus.app.hexagonal.infra.adapter.output.persistence.data.impl;

import com.fmatheus.app.hexagonal.infra.adapter.input.enumerable.EntityEnum;
import com.fmatheus.app.hexagonal.infra.adapter.output.persistence.data.impl.restriction.UserRestriction;
import com.fmatheus.app.hexagonal.infra.adapter.output.persistence.data.query.UserRepositoryDataQuery;
import com.fmatheus.app.hexagonal.infra.adapter.output.persistence.entity.Person;
import com.fmatheus.app.hexagonal.infra.adapter.output.persistence.entity.User;
import com.fmatheus.app.hexagonal.infra.adapter.output.persistence.repository.filter.UserRepositoryFilter;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

public class UserRepositoryDataImpl extends UserRestriction implements UserRepositoryDataQuery {

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
