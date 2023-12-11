package com.ar.cac.homebanking.services;

import com.ar.cac.homebanking.mappers.AuditMapper;
import com.ar.cac.homebanking.models.Audit;
import com.ar.cac.homebanking.models.dtos.AuditDTO;
import com.ar.cac.homebanking.repositories.AuditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuditService {
    @Autowired
    private AuditRepository repository;

    public List<AuditDTO> getAudit(){
        //Obtengo la lista de la entidad audit de la base
        List<Audit> Audits = repository.findAll();
        //Mapeamos cada audit de la lista hacia un dto
        List<AuditDTO> auditDtos = Audits.stream()
                .map(AuditMapper::auditToDto)
                .collect(Collectors.toList());
        return auditDtos;
    }


    public AuditDTO createAudit(AuditDTO auditDTO){

        Audit audit =  repository.save(AuditMapper.dtoToAudit(auditDTO));
        return AuditMapper.auditToDto(audit);
    }
}
