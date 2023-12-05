package com.ar.cac.homebanking.services;

import com.ar.cac.homebanking.exceptions.UserNotExistsException;
import com.ar.cac.homebanking.mappers.UserMapper;
import com.ar.cac.homebanking.models.Account;
import com.ar.cac.homebanking.models.User;
import com.ar.cac.homebanking.models.dtos.UserDTO;
import com.ar.cac.homebanking.models.enums.AccountType;
import com.ar.cac.homebanking.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class UserService {
    // Inyectar una instancia del Repositorio
    @Autowired
    private UserRepository repository;

    // Metodos

    public List<UserDTO> getUsers(){
        // Obtengo la lista de la entidad usuario de la db
        List<User> users = repository.findAll();
        // Mapear cada usuario de la lista hacia un dto
         List<UserDTO> usersDtos = users.stream()
                .map(UserMapper::userToDto)
                .collect(Collectors.toList());
        return usersDtos;
    }
    private String generarAliasAleatorio() {
        List<String> palabras = Arrays.asList("sol", "luna", "estrella", "rio", "montaña"); // Puedes agregar más palabras aquí
        Random random = new Random();
        StringBuilder alias = new StringBuilder();

        for (int i = 0; i < 3; i++) {
            int indicePalabra = random.nextInt(palabras.size());
            alias.append(palabras.get(indicePalabra));

            if (i < 2) {
                alias.append(".");
            }
        }

        return alias.toString();
    }
    private String generarCbuAleatorio() {
        StringBuilder cbu = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < 22; i++) {
            cbu.append(random.nextInt(10));
        }

        return cbu.toString();
    }

    public UserDTO createUser(UserDTO userDto){
        User userValidated = validateUserByEmail(userDto);
        if (userValidated == null){
            User NewUser = UserMapper.dtoToUser(userDto);
            NewUser.setAccounts(new ArrayList<>());
            Account newAccount = new Account();
            newAccount.setType(AccountType.SAVINGS_BANK);
            newAccount.setCbu(generarCbuAleatorio());
            newAccount.setAlias(generarAliasAleatorio());
            newAccount.setAmount(BigDecimal.valueOf(00.0));
            User userSaved = repository.save(UserMapper.dtoToUser(userDto));
            // Asignar la cuenta al usuario
            userSaved.addAccount(newAccount);
            // Guardar los cambios en la base de datos
            repository.save(userSaved);
            return UserMapper.userToDto(userSaved);
        } else{
            throw new UserNotExistsException("Usuario con mail: " + userDto.getEmail() + " ya existe");
        }
    }


    public UserDTO getUserById(Long id) {
        User entity = repository.findById(id).get();
        return UserMapper.userToDto(entity);
    }

    public String deleteUser(Long id){
        if (repository.existsById(id)){
            repository.deleteById(id);
            return "El usuario con id: " + id + " ha sido eliminado";
        } else {
            throw new UserNotExistsException("El usuario a eliminar elegido no existe");
        }

    }

    public UserDTO updateUser(Long id, UserDTO dto) {
        if (repository.existsById(id)){
            User userToModify = repository.findById(id).get();
            // Validar qué datos no vienen en null para setearlos al objeto ya creado

            // Logica del Patch
            if (dto.getNombreUsuario() != null){
                userToModify.setNombreUsuario(dto.getNombreUsuario());
            }

            if (dto.getFechaDeNacimiento() != null){
                userToModify.setFechaDeNacimiento(dto.getFechaDeNacimiento());
            }

            if (dto.getDomicilio() != null){
                userToModify.setDomicilio(dto.getDomicilio());
            }

            if (dto.getEmail() != null){
                userToModify.setEmail(dto.getEmail());
            }

            if (dto.getPassword() != null){
                userToModify.setPassword(dto.getPassword());
            }

            if (dto.getDni() != null){
                userToModify.setDni(dto.getDni());
            }

            User userModified = repository.save(userToModify);

            return UserMapper.userToDto(userModified);
        }

        return null;
    }

    // Validar que existan usuarios unicos por mail
    public User validateUserByEmail(UserDTO dto){
        return repository.findByEmail(dto.getEmail());
    }
}
