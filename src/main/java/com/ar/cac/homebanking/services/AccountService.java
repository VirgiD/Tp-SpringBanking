package com.ar.cac.homebanking.services;

import com.ar.cac.homebanking.exceptions.UserNotExistsException;
import com.ar.cac.homebanking.mappers.AccountMapper;
import com.ar.cac.homebanking.models.Account;
import com.ar.cac.homebanking.models.GeneradorCbuAlias;
import com.ar.cac.homebanking.models.User;
import com.ar.cac.homebanking.models.dtos.AccountDTO;
import com.ar.cac.homebanking.repositories.AccountRepository;
import com.ar.cac.homebanking.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;


@Service
public class AccountService {

    private final AccountRepository repository;

    private final UserRepository userRepository;


    public AccountService(AccountRepository repository, UserRepository userRepository){
        this.repository = repository;
        this.userRepository = userRepository;
    }
    @Transactional
    public List<AccountDTO> getAccountsByUserId(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotExistsException("User not found with id: " + userId));

        List<Account> accounts = repository.findAll();
        return accounts.stream()
                .map(AccountMapper::accountToDto)
                .collect(Collectors.toList());
    }


    public AccountDTO createAccount(AccountDTO dto) {
        User user = userRepository.findById(dto.getUsuarioId()).
                orElseThrow(() -> new UserNotExistsException("User not found with id: " + dto.getUsuarioId()));
        Account newAccount = new Account();
        String alias = GeneradorCbuAlias.generarAliasAleatorio();
        String cbu = GeneradorCbuAlias.generarCbuAleatorio();
        newAccount.setOwner(user);
        newAccount.setType(dto.getType());
        newAccount.setCbu(cbu);
        newAccount.setAlias(alias);
        newAccount.setAmount(dto.getAmount());
        newAccount = repository.save(newAccount);

        return AccountMapper.accountToDto(newAccount);
    }

    public AccountDTO getAccountById(Long id) {
        Account entity = repository.findById(id).get();
        return AccountMapper.accountToDto(entity);
    }

    public String deleteAccount(Long id){
        if (repository.existsById(id)){
            repository.deleteById(id);
            return "La cuenta con id: " + id + " ha sido eliminada";
        } else {
            // TODO: REFACTOR create new exception
            throw new UserNotExistsException("La cuenta a eliminar no existe");
        }

    }

    public AccountDTO updateAccount(Long id, AccountDTO dto) {
        if (repository.existsById(id)) {
            Account accountToModify = repository.findById(id).get();
            // Validar qué datos no vienen en null para setearlos al objeto ya creado

            // Logica del Patch
            if (dto.getAlias() != null) {
                accountToModify.setAlias(dto.getAlias());
            }

            if (dto.getType() != null) {
                accountToModify.setType(dto.getType());
            }

            if (dto.getCbu() != null) {
                accountToModify.setCbu(dto.getCbu());
            }

            if (dto.getAmount() != null) {
                accountToModify.setAmount(dto.getAmount());
            }

            Account accountModified = repository.save(accountToModify);

            return AccountMapper.accountToDto(accountModified);
        }
        return null;
    }


}
