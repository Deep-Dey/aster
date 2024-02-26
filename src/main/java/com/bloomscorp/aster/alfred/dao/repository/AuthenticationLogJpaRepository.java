package com.bloomscorp.aster.alfred.dao.repository;

import com.bloomscorp.aster.alfred.orm.AsterAuthenticationLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthenticationLogJpaRepository extends JpaRepository<AsterAuthenticationLog, Long> {
}
