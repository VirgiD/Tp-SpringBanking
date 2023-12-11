package com.ar.cac.homebanking.mappers;

import com.ar.cac.homebanking.models.Audit;
import com.ar.cac.homebanking.models.dtos.AuditDTO;
import lombok.experimental.UtilityClass;

@UtilityClass
public class AuditMapper {

    //Metodos para transformar Objetos
    public static Audit dtoToAudit(AuditDTO dto){
        Audit audit = new Audit();
        audit.setId(dto.getId());
        audit.setDate(dto.getDate());
        audit.setTabla(dto.getTabla());
        audit.setAction(dto.getAction());
        audit.setCbu(dto.getCbu());
        audit.setAlias(dto.getAlias());
        return audit;
    }

    public static AuditDTO auditToDto(Audit audit){
        AuditDTO dto = new AuditDTO();
        dto.setId(audit.getId());
        dto.setDate(audit.getDate());
        dto.setTabla(audit.getTabla());
        dto.setAction(audit.getAction());
        dto.setCbu(audit.getCbu());
        dto.setAlias(audit.getAlias());
        return dto;
    }

}
