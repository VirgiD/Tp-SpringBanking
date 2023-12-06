package com.ar.cac.homebanking.models;


import com.ar.cac.homebanking.models.enums.AccountType;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Entity
@Table(name = "cuentas")
@Getter
@Setter

public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "account_type")
    private AccountType type;

    private String cbu;

    private String alias;

    private BigDecimal amount;


   // private Long idUsuario;


    @ManyToOne
    @JoinColumn(name = "id_owner")
    private User owner;

    //declaro la variable PALABRAS como estática
    private static final List<String> PALABRAS = Arrays.asList("sol", "luna", "estrella", "rio", "montaña");
    //defino los metodos generarAlias y generarCBU como estáticos para que puedan ser accedidos desde AccountService
    //y desde UserService
    public static String generarAliasAleatorio() {
        Random random = new Random();
        StringBuilder alias = new StringBuilder();

        for (int i = 0; i < 3; i++) {
            int indicePalabra = random.nextInt(PALABRAS.size());
            alias.append(PALABRAS.get(indicePalabra));

            if (i < 2) {
                alias.append(".");
            }
        }

        return alias.toString();
    }

    public static String generarCbuAleatorio() {
        StringBuilder cbu = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < 22; i++) {
            cbu.append(random.nextInt(10));
        }

        return cbu.toString();
    }

}
