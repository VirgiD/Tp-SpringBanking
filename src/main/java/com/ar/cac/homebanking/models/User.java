package com.ar.cac.homebanking.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "mail")
    private String email;

    @Column(name = "contrasena")
    private String password;

    @Column(name = "nombreUsuario")
    private String nombreUsuario;

    @Column(name = "fechaDeNacimiento")
    private String fechaDeNacimiento;

    @Column(name = "domicilio")
    private String domicilio;

    private String dni;

    //private List<Account> lista;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Account> accounts = new ArrayList<>();

    public void addAccount(Account account) {
        accounts.add(account);
        account.setOwner(this);
    }


}
