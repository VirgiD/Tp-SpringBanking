package com.ar.cac.homebanking.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Entity
@Table(name = "Audit")
@Getter
@Setter

public class Audit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tabla;
    private String cbu;
    private String action;
    private Date date;
    private String alias;
}
