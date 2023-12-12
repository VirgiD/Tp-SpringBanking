package com.ar.cac.homebanking.mappers;

import com.ar.cac.homebanking.models.Audit;
import com.ar.cac.homebanking.models.AuditUser;
import com.ar.cac.homebanking.models.dtos.AuditAccountDTO;
import com.ar.cac.homebanking.models.dtos.AuditUserDTO;
import lombok.experimental.UtilityClass;

@UtilityClass
public class AuditUserMapper {
    public static AuditUser dtoToAuditUser (AuditUserDTO dto){
    AuditUser auditUser = new AuditUser();
        auditUser.setAction(dto.getAction());
        auditUser.setTabla(dto.getTabla());
        auditUser.setId(dto.getId());
        auditUser.setEmail(dto.getEmail());
        auditUser.setPassword(dto.getPassword());
        auditUser.setNombreUsuario(dto.getNombreUsuario());
        auditUser.setFechaDeNacimiento(dto.getFechaDeNacimiento());
        auditUser.setDomicilio(dto.getDomicilio());
        auditUser.setDni(dto.getDni());
        return auditUser;
    }

    public static AuditUserDTO auditUserDTO(AuditUser auditUser){
        AuditUserDTO dto = new AuditUserDTO();
        dto.setAction(auditUser.getAction());
        dto.setTabla(auditUser.getTabla());
        dto.setId(auditUser.getId());
        dto.setEmail(auditUser.getEmail());
        dto.setPassword(auditUser.getPassword());
        dto.setNombreUsuario(auditUser.getNombreUsuario());
        dto.setFechaDeNacimiento(auditUser.getFechaDeNacimiento());
        dto.setDomicilio(auditUser.getDomicilio());
        dto.setDni(auditUser.getDni());

        return dto;
    }
}
