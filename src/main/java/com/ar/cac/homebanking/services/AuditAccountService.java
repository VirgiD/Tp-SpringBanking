package com.ar.cac.homebanking.services;

import com.ar.cac.homebanking.mappers.AuditAccountMapper;
import com.ar.cac.homebanking.models.Audit;
import com.ar.cac.homebanking.models.dtos.AuditAccountDTO;
import com.ar.cac.homebanking.repositories.AuditAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuditAccountService {
    @Autowired
    private AuditAccountRepository repository;

    public List<AuditAccountDTO> getAudit(){
        //Obtengo la lista de la entidad audit de la base
        List<Audit> Audits = repository.findAll();
        //Mapeamos cada audit de la lista hacia un dto
        List<AuditAccountDTO> auditDtos = Audits.stream()
                .map(AuditAccountMapper::auditToDto)
                .collect(Collectors.toList());
        return auditDtos;
    }


    public AuditAccountDTO createAudit(AuditAccountDTO auditDTO){

        Audit audit =  repository.save(AuditAccountMapper.dtoToAudit(auditDTO));
        return AuditAccountMapper.auditToDto(audit);
    }
}
