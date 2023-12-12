package com.ar.cac.homebanking.services;


import com.ar.cac.homebanking.mappers.AuditAccountMapper;
import com.ar.cac.homebanking.mappers.AuditUserMapper;
import com.ar.cac.homebanking.models.Audit;
import com.ar.cac.homebanking.models.AuditUser;
import com.ar.cac.homebanking.models.dtos.AuditAccountDTO;
import com.ar.cac.homebanking.models.dtos.AuditUserDTO;
import com.ar.cac.homebanking.repositories.AuditAccountRepository;
import com.ar.cac.homebanking.repositories.AuditUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuditUserService {

    @Autowired
    private AuditUserRepository repository;


    public List<AuditUserDTO> getAudit(){
        //Obtengo la lista de la entidad audit de la base
        List<AuditUser> Audits = repository.findAll();
        //Mapeamos cada audit de la lista hacia un dto
        List<AuditUserDTO> auditUserDTOS = Audits.stream()
                .map(AuditUserMapper::auditUserDTO)
                .collect(Collectors.toList());
        return auditUserDTOS;
    }


    public AuditUserDTO createAudit(AuditUserDTO auditDTO){

        AuditUser auditUser =  repository.save(AuditUserMapper.dtoToAuditUser(auditDTO));
        return AuditUserMapper.auditUserDTO(auditUser);
    }
}
