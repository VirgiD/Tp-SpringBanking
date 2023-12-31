package com.ar.cac.homebanking.mappers;

import com.ar.cac.homebanking.models.User;
import com.ar.cac.homebanking.models.dtos.UserDTO;
import lombok.experimental.UtilityClass;

import java.util.stream.Collectors;

@UtilityClass
public class UserMapper {

    // Metodos para transformar objetos

    public static User dtoToUser(UserDTO dto){
        User user = new User();
        user.setNombreUsuario(dto.getNombreUsuario());
        user.setFechaDeNacimiento(dto.getFechaDeNacimiento());
        user.setDomicilio(dto.getDomicilio());
        user.setDni(dto.getDni());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        return user;
    }

    public static UserDTO userToDto(User user){
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setNombreUsuario(user.getNombreUsuario());
        dto.setFechaDeNacimiento(user.getFechaDeNacimiento());
        dto.setDomicilio(user.getDomicilio());
        dto.setDni(user.getDni());
        dto.setEmail(user.getEmail());
        dto.setPassword(user.getPassword());
        //mapeo la lista de cuentas
        if (user.getAccounts() != null) {
            dto.setAccounts(user.getAccounts().stream()
                    .map(AccountMapper::accountToDto)
                    .collect(Collectors.toList()));
        }

        return dto;
    }
}
