package com.ar.cac.homebanking.controllers;

import com.ar.cac.homebanking.models.dtos.FuncionCajeroExpressDTO;
import com.ar.cac.homebanking.services.FuncionCajeroExpressService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/atm")
public class FuncionCajeroExpressController {

    private final FuncionCajeroExpressService service;


    public FuncionCajeroExpressController(FuncionCajeroExpressService service){this.service = service;}

    @GetMapping
    public ResponseEntity<List<FuncionCajeroExpressDTO>> getFuncionCajeroExpress() {
        List<FuncionCajeroExpressDTO> funcionCajeroExpress = service.getFuncionCajeroEspress();
        return ResponseEntity.status(HttpStatus.OK).body(funcionCajeroExpress);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<FuncionCajeroExpressDTO> getFuncionCajeroExpressById(@PathVariable Long id){
        FuncionCajeroExpressDTO funcionCajeroExpress = service.getFuncionCajeroExpressById(id);
        return ResponseEntity.status((HttpStatus.OK)).body(funcionCajeroExpress);
    }

    @PostMapping(value = "/deposit")
    public ResponseEntity<FuncionCajeroExpressDTO> depositFuncionCajeroExpress(@RequestBody FuncionCajeroExpressDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.depositFuncionCajeroExpress(dto));
    }

    @PostMapping(value = "/withdraw")
    public ResponseEntity<FuncionCajeroExpressDTO> withdrawFuncionCajeroExpress(@RequestBody FuncionCajeroExpressDTO dto){
        return ResponseEntity.status(HttpStatus.OK).body(service.withdrawFuncionCajeroExpress(dto));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<FuncionCajeroExpressDTO> updateFuncionCajeroExpress(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(service.updateFuncionCajeroExpress(id));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteAtm(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(service.deleteFuncionCajeroExpress(id));
    }

}
