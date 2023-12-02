package com.ar.cac.homebanking.repositories;

import com.ar.cac.homebanking.models.Atm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AtmRepository extends JpaRepository<Atm, Long> {
}
