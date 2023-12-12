package com.ar.cac.homebanking.mappers;

import com.ar.cac.homebanking.models.FuncionCajeroExpress;
import com.ar.cac.homebanking.models.dtos.FuncionCajeroExpressDTO;
import lombok.experimental.UtilityClass;

@UtilityClass
public class FuncionCajeroExpressMapper {

    public FuncionCajeroExpress dtoToFuncionCajeroExpress(FuncionCajeroExpressDTO dto){
        return FuncionCajeroExpress.builder()
                .amount(dto.getAmount())
                .date(dto.getDate())
                .origin(dto.getOrigin())
                .build();
    }

    public FuncionCajeroExpressDTO funcionCajeroExpressToDto(FuncionCajeroExpress funcionCajeroExpress){
        return FuncionCajeroExpressDTO.builder()
                .id(funcionCajeroExpress.getId())
                .amount(funcionCajeroExpress.getAmount())
                .origin(funcionCajeroExpress.getOrigin())
                .date(funcionCajeroExpress.getDate())
                .build();
    }

}
