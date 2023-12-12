package com.ar.cac.homebanking.controllers;

import com.ar.cac.homebanking.models.AuditUser;
import com.ar.cac.homebanking.models.dtos.AuditAccountDTO;
import com.ar.cac.homebanking.models.dtos.AuditUserDTO;
import com.ar.cac.homebanking.services.AuditAccountService;
import com.ar.cac.homebanking.services.AuditUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/aaa")
public class AuditUserController {

    @Autowired
    private final AuditUserService service;
    public AuditUserController(AuditUserService service){
        this.service = service;
    }


    @GetMapping
    public ResponseEntity<List<AuditUserDTO>> getAudit(){
        List<AuditUserDTO> lista = service.getAudit();
        return ResponseEntity.status(HttpStatus.OK).body(lista);

    }

    @GetMapping(value = "/{id}")
    public AuditUserDTO getAuditById(@PathVariable Long id){return null;
    }
}