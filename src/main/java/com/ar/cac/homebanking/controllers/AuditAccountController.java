package com.ar.cac.homebanking.controllers;

import com.ar.cac.homebanking.models.dtos.AuditAccountDTO;
import com.ar.cac.homebanking.services.AuditAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/audits/accounts")
public class AuditAccountController {
    @Autowired
    private final AuditAccountService service;
    public AuditAccountController(AuditAccountService service){
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<AuditAccountDTO>> getAudit(){
        List<AuditAccountDTO> lista = service.getAudit();
        return ResponseEntity.status(HttpStatus.OK).body(lista);

    }

    @GetMapping(value = "/{id}")
    public AuditAccountDTO getAuditById(@PathVariable Long id){return null;
    }
}
