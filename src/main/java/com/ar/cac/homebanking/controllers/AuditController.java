package com.ar.cac.homebanking.controllers;

import com.ar.cac.homebanking.models.dtos.AuditDTO;
import com.ar.cac.homebanking.services.AuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/auditorias")
public class AuditController {
    @Autowired
    private final AuditService service;
    public AuditController(AuditService service){
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<AuditDTO>> getAudit(){
        List<AuditDTO> lista = service.getAudit();
        return ResponseEntity.status(HttpStatus.OK).body(lista);

    }

    @GetMapping(value = "/{id}")
    public AuditDTO getAuditById(@PathVariable Long id){return null;
    }
}
