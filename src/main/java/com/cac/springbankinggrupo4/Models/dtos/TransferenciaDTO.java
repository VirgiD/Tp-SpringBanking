package com.cac.springbankinggrupo4.Models.dtos;

import com.cac.springbankinggrupo4.Models.Cuenta;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class TransferenciaDTO {

    private Long id;

    private Double monto;

    private Cuenta cuentaOrigen;

    private Cuenta cuentaDestino;

    private Date fecha;


}
