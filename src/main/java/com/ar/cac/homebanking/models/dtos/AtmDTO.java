package com.ar.cac.homebanking.models.dtos;

import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class AtmDTO {

    private Long id;

    private Long origin;

    private Date date;

    private BigDecimal amount;

}
