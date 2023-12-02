package com.ar.cac.homebanking.controllers;

import com.ar.cac.homebanking.models.dtos.AtmDTO;
import com.ar.cac.homebanking.services.AtmService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/atm")
public class AtmController {

    private final AtmService service;

    public AtmController(AtmService service){this.service = service;}

    @GetMapping
    public ResponseEntity<List<AtmDTO>> getAtms() {
        List<AtmDTO> atm = service.getAtms();
        return ResponseEntity.status(HttpStatus.OK).body(atm);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<AtmDTO> getAtmById(@PathVariable Long id){
        AtmDTO atm = service.getAtmById(id);
        return ResponseEntity.status((HttpStatus.OK)).body(atm);
    }

    @PostMapping(value = "/deposit")
    public ResponseEntity<AtmDTO> depositAtm(@RequestBody AtmDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.depositAtm(dto));
    }

    @PostMapping(value = "/withdraw")
    public ResponseEntity<AtmDTO> withdrawAtm(@RequestBody AtmDTO dto){
        return ResponseEntity.status(HttpStatus.OK).body(service.withdrawAtm(dto));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<AtmDTO> updateAtm(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(service.updateAtm(id));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteAtm(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(service.deleteAtm(id));
    }

}
