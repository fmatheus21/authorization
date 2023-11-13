package com.fmatheus.app.model.repository;

import com.fmatheus.app.model.entity.UserSessions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserSessionsRepository extends JpaRepository<UserSessions, Integer> {
}
