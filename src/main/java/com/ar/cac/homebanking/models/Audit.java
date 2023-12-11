package com.ar.cac.homebanking.models;

import com.ar.cac.homebanking.models.enums.AccountType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.type.descriptor.java.BigDecimalJavaType;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.DateFormat;

@Entity
@Table(name = "Audit")
@Getter
@Setter

public class Audit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long id_owner;
    private String tabla;
    private String cbu;
    private String action;
    private Date date;
    private String alias;
    private AccountType type;
    private BigDecimal amount;
}
