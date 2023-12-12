package com.ar.cac.homebanking.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

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
    private List<Account>accounts = new ArrayList<>();

    public List<Account>getAccounts() {
        return accounts;
    }


    public void agregarCuenta(Account cuenta) {
        accounts.add(cuenta);
        cuenta.setOwner(this);
    }

    public void quitarCuenta(Account cuenta) {
        accounts.remove(cuenta);
        cuenta.setOwner(null);
    }

}
