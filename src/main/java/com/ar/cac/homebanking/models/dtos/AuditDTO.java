package com.ar.cac.homebanking.models.dtos;

import com.ar.cac.homebanking.models.enums.AccountType;
import jakarta.persistence.Column;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.DateFormat;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AuditDTO {
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
