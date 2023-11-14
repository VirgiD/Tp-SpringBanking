package com.cac.springbankinggrupo4.Repositories;

import com.cac.springbankinggrupo4.Models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario,Long> {
}

