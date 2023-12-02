package com.ar.cac.homebanking.mappers;

import com.ar.cac.homebanking.models.Atm;
import com.ar.cac.homebanking.models.dtos.AtmDTO;
import lombok.experimental.UtilityClass;

@UtilityClass
public class AtmMapper {

    public Atm dtoToAtm(AtmDTO dto){
        return Atm.builder()
                .amount(dto.getAmount())
                .date(dto.getDate())
                .origin(dto.getOrigin())
                .build();
    }

    public AtmDTO atmToDto(Atm atm){
        return AtmDTO.builder()
                .id(atm.getId())
                .amount(atm.getAmount())
                .origin(atm.getOrigin())
                .date(atm.getDate())
                .build();
    }

}
