package com.cac.springbankinggrupo4.Services;

import com.cac.springbankinggrupo4.Repositories.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServicio {
    @Autowired
    private UsuarioRepositorio repositorio;

}
