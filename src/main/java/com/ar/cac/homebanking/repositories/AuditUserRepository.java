package com.ar.cac.homebanking.repositories;

import com.ar.cac.homebanking.models.AuditUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditUserRepository extends JpaRepository<AuditUser, Long> {
}
