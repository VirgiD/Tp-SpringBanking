package com.ar.cac.homebanking.models;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class FuncionCajeroExpress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "atm_id")

    private Long id;

    private Long origin;

    private Date date;

    private BigDecimal amount;


}
