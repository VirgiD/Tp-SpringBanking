package com.cac.springbankinggrupo4.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

    @Entity
    @Table(name = "transferencias")
    @Getter
    @Setter

    public class Transferencia {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private Double monto;

        @ManyToOne
        @JoinColumn(name = "cuenta_origen_id")
        private Cuenta cuentaOrigen;

        @ManyToOne
        @JoinColumn(name = "cuenta_destino_id")
        private Cuenta cuentaDestino;

        @Temporal(TemporalType.TIMESTAMP)
        private Date fecha;


    }


