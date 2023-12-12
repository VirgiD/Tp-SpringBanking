package com.ar.cac.homebanking.services;


import com.ar.cac.homebanking.exceptions.FuncionCajeroExpressNotFoundException;
import com.ar.cac.homebanking.exceptions.InsufficientFoundsException;
import com.ar.cac.homebanking.mappers.FuncionCajeroExpressMapper;
import com.ar.cac.homebanking.models.Account;
import com.ar.cac.homebanking.models.FuncionCajeroExpress;
import com.ar.cac.homebanking.models.dtos.FuncionCajeroExpressDTO;
import com.ar.cac.homebanking.repositories.AccountRepository;
import com.ar.cac.homebanking.repositories.FuncionCajeroExpressRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FuncionCajeroExpressService {

    public final FuncionCajeroExpressRepository repository;

    private final AccountRepository accountRepository;

    public FuncionCajeroExpressService(FuncionCajeroExpressRepository repository, AccountRepository accountRepository){
        this.repository = repository;
        this.accountRepository = accountRepository;
    }

    public List<FuncionCajeroExpressDTO> getFuncionCajeroEspress(){
        List<FuncionCajeroExpress> funcionCajeroExpresses = repository.findAll();
        return funcionCajeroExpresses.stream()
                .map(FuncionCajeroExpressMapper:: funcionCajeroExpressToDto)
                .collect(Collectors.toList());
    }

    public FuncionCajeroExpressDTO getFuncionCajeroExpressById (Long id){
        FuncionCajeroExpress funcionCajeroExpress = repository.findById(id).orElseThrow (()-> new FuncionCajeroExpressNotFoundException("FuncionCajeroExpress not found with id: " + id));
        return FuncionCajeroExpressMapper.funcionCajeroExpressToDto(funcionCajeroExpress);
    }

    public FuncionCajeroExpressDTO updateFuncionCajeroExpress(Long id){
        return null;
    }

    public String deleteFuncionCajeroExpress(Long id){
        return "No se puede borar una operacion";
    }

    @Transactional
    public FuncionCajeroExpressDTO depositFuncionCajeroExpress(FuncionCajeroExpressDTO dto){
        //Comprobamos si la cuenta existe
        Account originAccount = accountRepository.findById(dto.getOrigin()).orElseThrow(()-> new FuncionCajeroExpressNotFoundException("Account not found with id: " + dto.getOrigin()));
        //Le agregamos monto
        originAccount.setAmount(originAccount.getAmount().add(dto.getAmount()));
        //Actualizamos la cuenta
        accountRepository.save(originAccount);
        //Crear la extraccion y guardarla
        FuncionCajeroExpress funcionCajeroExpress = new FuncionCajeroExpress();
        //Creamos un objeto de tipo Date para la fecha
        Date date = new Date();
        //Seteamos el objeto fecha actual en FuncionCajeroExpress
        funcionCajeroExpress.setDate(date);
        funcionCajeroExpress.setOrigin(originAccount.getId());
        funcionCajeroExpress.setAmount(dto.getAmount());
        funcionCajeroExpress = repository.save(funcionCajeroExpress);

        //Devolver el DTO de la funcionCajeroExpress realizada
        return FuncionCajeroExpressMapper.funcionCajeroExpressToDto(funcionCajeroExpress);

    }

    @Transactional
    public FuncionCajeroExpressDTO withdrawFuncionCajeroExpress(FuncionCajeroExpressDTO dto){
        //Comprobamos si la cuenta existe
        Account originAccount = accountRepository.findById(dto.getOrigin()).orElseThrow(() -> new FuncionCajeroExpressNotFoundException("Account not found with id: " + dto.getOrigin()));
        //Verficamos si existe el motor para sacar
        if((originAccount.getAmount().compareTo(dto.getAmount()) < 0 )){
            throw new InsufficientFoundsException("Insufficient funds in the account with id: " + dto.getOrigin());
        }
        //Le agregamos monto
        originAccount.setAmount(originAccount.getAmount().subtract(dto.getAmount()));
        //Actualizamos la cuenta
        accountRepository.save(originAccount);
        //Crear la extraccion y guardarla
        FuncionCajeroExpress funcionCajeroExpress = new FuncionCajeroExpress();
        //Creamos un objeto de tipo Date para la fecha
        Date date = new Date();
        //Seteamos el objeto fecha actual en el FuncionCajeroExpressDTO
        funcionCajeroExpress.setDate(date);
        funcionCajeroExpress.setOrigin(originAccount.getId());
        funcionCajeroExpress.setAmount(dto.getAmount().negate());
        funcionCajeroExpress = repository.save(funcionCajeroExpress);

        //Devolver el DTO de la funcionCajeroExpress realizada
        return FuncionCajeroExpressMapper.funcionCajeroExpressToDto(funcionCajeroExpress);
    }



}
