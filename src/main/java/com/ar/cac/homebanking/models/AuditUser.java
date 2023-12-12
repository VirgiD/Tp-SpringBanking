package com.ar.cac.homebanking.models;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "AuditUser")

@Getter
@Setter


public class AuditUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String action;
    private String tabla;
    private String email;
    private String password;
    private String nombreUsuario;
    private String fechaDeNacimiento;
    private String domicilio;
    private String dni;
}
