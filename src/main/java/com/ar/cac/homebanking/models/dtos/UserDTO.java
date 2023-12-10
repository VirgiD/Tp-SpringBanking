package com.ar.cac.homebanking.models.dtos;

import com.ar.cac.homebanking.models.Account;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UserDTO {

    private Long id;

    private String email;

    private String password;

    private String nombreUsuario;

    private String fechaDeNacimiento;

    private String domicilio;

    private String dni;

    private List<Account> lista;

}
