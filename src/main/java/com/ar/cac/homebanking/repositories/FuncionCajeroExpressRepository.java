package com.ar.cac.homebanking.repositories;

import com.ar.cac.homebanking.models.FuncionCajeroExpress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FuncionCajeroExpressRepository extends JpaRepository<FuncionCajeroExpress, Long> {

}
