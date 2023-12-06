package com.ar.cac.homebanking.services;

import com.ar.cac.homebanking.exceptions.AtmNotFoundException;
import com.ar.cac.homebanking.exceptions.InsufficientFoundsException;
import com.ar.cac.homebanking.mappers.AtmMapper;
import com.ar.cac.homebanking.models.Account;
import com.ar.cac.homebanking.models.Atm;
import com.ar.cac.homebanking.models.dtos.AtmDTO;
import com.ar.cac.homebanking.models.dtos.TransferDTO;
import com.ar.cac.homebanking.repositories.AccountRepository;
import com.ar.cac.homebanking.repositories.AtmRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AtmService {

    public final AtmRepository repository;

    private final AccountRepository accountRepository;

    public AtmService(AtmRepository repository, AccountRepository accountRepository){
        this.repository = repository;
        this.accountRepository = accountRepository;
    }

    public List<AtmDTO> getAtms(){
        List<Atm> atms = repository.findAll();
        return atms.stream()
                .map(AtmMapper :: atmToDto)
                .collect(Collectors.toList());
    }

    public AtmDTO getAtmById (Long id){
        Atm atm = repository.findById(id).orElseThrow(() ->
                new AtmNotFoundException("Atm not found with id: " + id));
        return AtmMapper.atmToDto(atm);
    }

    public AtmDTO updateAtm(Long id){
        return null;
    }

    public String deleteAtm(Long id){
        return "No se puede borar una operacion";
    }

    @Transactional
    public AtmDTO depositAtm(AtmDTO dto){
        //Comprobamos si la cuenta existe
        Account originAccount = accountRepository.findById(dto.getOrigin()).orElseThrow(() -> new AtmNotFoundException("Account not found with id: " + dto.getOrigin()));
        //Le agregamos monto
        originAccount.setAmount(originAccount.getAmount().add(dto.getAmount()));
        //Actualizamos la cuenta
        accountRepository.save(originAccount);
        //Crear la extraccion y guardarla
        Atm atm = new Atm();
        //Creamos un objeto de tipo Date para la fecha
        Date date = new Date();
        //Seteamos el objeto fecha actual en el AtmDTO
        atm.setDate(date);
        atm.setOrigin(originAccount.getId());
        atm.setAmount(dto.getAmount());
        atm = repository.save(atm);

        //Devolver el DTO de la atm realizada
        return AtmMapper.atmToDto(atm);

    }

    @Transactional
    public AtmDTO withdrawAtm(AtmDTO dto){
        //Comprobamos si la cuenta existe
        Account originAccount = accountRepository.findById(dto.getOrigin()).orElseThrow(() -> new AtmNotFoundException("Account not found with id: " + dto.getOrigin()));
        //Verficamos si existe el motor para sacar
        if((originAccount.getAmount().compareTo(dto.getAmount()) < 0 )){
            throw new InsufficientFoundsException("Insufficient funds in the account with id: " + dto.getOrigin());
        }
        //Le agregamos monto
        originAccount.setAmount(originAccount.getAmount().subtract(dto.getAmount()));
        //Actualizamos la cuenta
        accountRepository.save(originAccount);
        //Crear la extraccion y guardarla
        Atm atm = new Atm();
        //Creamos un objeto de tipo Date para la fecha
        Date date = new Date();
        //Seteamos el objeto fecha actual en el AtmDTO
        atm.setDate(date);
        atm.setOrigin(originAccount.getId());
        atm.setAmount(dto.getAmount().negate());
        atm = repository.save(atm);

        //Devolver el DTO de la atm realizada
        return AtmMapper.atmToDto(atm);
    }



}
