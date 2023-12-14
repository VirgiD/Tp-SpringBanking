package com.ar.cac.homebanking.models.dtos;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AuditUserDTO {
    private Long id;
    private Long id_owner;
    private String action;
    private String tabla;
    private String email;
    private String password;
    private String nombreUsuario;
    private String fechaDeNacimiento;
    private String domicilio;
    private String dni;
}
