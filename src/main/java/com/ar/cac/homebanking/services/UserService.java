package com.ar.cac.homebanking.services;

import com.ar.cac.homebanking.exceptions.UserNotExistsException;
import com.ar.cac.homebanking.mappers.UserMapper;
import com.ar.cac.homebanking.models.Account;
import com.ar.cac.homebanking.utilities.GeneradorCbuAlias;
import com.ar.cac.homebanking.models.User;
import com.ar.cac.homebanking.models.dtos.UserDTO;
import com.ar.cac.homebanking.models.enums.AccountType;
import com.ar.cac.homebanking.repositories.AccountRepository;
import com.ar.cac.homebanking.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserService {
    // Inyectar una instancia del Repositorio
    private final UserRepository userRepository;
    private final AccountRepository accountRepository;

    @Autowired
    public UserService(UserRepository userRepository, AccountRepository accountRepository) {
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
    }

    // Metodos

    public List<UserDTO> getUsers(){
        // Obtengo la lista de la entidad usuario de la db
        List<User> users = userRepository.findAll();
        // Mapear cada usuario de la lista hacia un dto
         List<UserDTO> usersDtos = users.stream()
                .map(UserMapper::userToDto)
                .collect(Collectors.toList());
        return usersDtos;
    }


    public UserDTO createUser(UserDTO userDto){
        User userValidated = validateUserByEmail(userDto);
        if (userValidated == null){
            User newUser = UserMapper.dtoToUser(userDto);
            newUser.setAccounts(new ArrayList<>());
            Account newAccount = new Account();
            newAccount.setType(AccountType.SAVINGS_BANK);
            newAccount.setCbu(GeneradorCbuAlias.generarCbuAleatorio());
            newAccount.setAlias(GeneradorCbuAlias.generarAliasAleatorio());
            newAccount.setAmount(BigDecimal.valueOf(00.0));
            newUser.agregarCuenta(newAccount);
            // Guardar el usuario y la cuenta en la base de datos
            User userSaved = userRepository.save(newUser);
            return UserMapper.userToDto(userSaved);
        } else{
            throw new UserNotExistsException("Usuario con mail: " + userDto.getEmail() + " ya existe");
        }
    }


    public UserDTO getUserById(Long id) {
        User entity = userRepository.findById(id).get();
        return UserMapper.userToDto(entity);
    }

    public String deleteUser(Long id){

        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            List<Account> accounts = user.getAccounts();
            // Eliminar cada cuenta asociada al usuario
            for (Account account : accounts) {
                accountRepository.deleteById(account.getId());
            }
            userRepository.deleteById(id);
            return "El usuario con id: " + id + " y su cuenta asociada han sido eliminados";
        } else {
            throw new UserNotExistsException("El usuario a eliminar elegido no existe");
        }

    }

    public UserDTO updateUser(Long id, UserDTO dto) {
        if (userRepository.existsById(id)){
            User userToModify = userRepository.findById(id).get();
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

            User userModified = userRepository.save(userToModify);

            return UserMapper.userToDto(userModified);
        }

        return null;
    }

    // Validar que existan usuarios unicos por mail
    public User validateUserByEmail(UserDTO dto){
        return userRepository.findByEmail(dto.getEmail());
    }
}
