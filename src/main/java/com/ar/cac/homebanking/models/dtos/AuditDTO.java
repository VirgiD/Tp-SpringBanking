package com.ar.cac.homebanking.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AuditDTO {
    private Long id;
    private String action;
    private String tabla;
    private String alias;
    private String cbu;
    private Date date;
}
