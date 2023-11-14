package com.cac.springbankinggrupo4.Controllers;

import com.cac.springbankinggrupo4.Services.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/usuario")

public class UsuarioControllers {
    @Autowired
    private final UsuarioServicio servicio;

    public UsuarioControllers(UsuarioServicio servicio) {

        this.servicio = servicio;
    }

}
