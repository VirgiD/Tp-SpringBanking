package com.ar.cac.homebanking.models.dtos;

import com.ar.cac.homebanking.models.enums.AccountType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AuditAccountDTO {
    private Long id;
    private String action;
    private Long id_owner;
    private String tabla;
    private String alias;
    private String cbu;
    private Date date;
    private AccountType type;
    private BigDecimal amount;
}
