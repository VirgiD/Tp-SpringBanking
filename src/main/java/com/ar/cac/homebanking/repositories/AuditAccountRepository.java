package com.ar.cac.homebanking.repositories;

import com.ar.cac.homebanking.models.Audit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditAccountRepository extends JpaRepository<Audit, Long> {

}
