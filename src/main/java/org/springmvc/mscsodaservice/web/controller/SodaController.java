package org.springmvc.mscsodaservice.web.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springmvc.mscsodaservice.web.model.SodaDto;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequestMapping("/api/v1/soda")
@RestController
public class SodaController {

    /*private final SodaService sodaService;

    public SodaController(SodaService sodaService) {
        this.sodaService = sodaService;
    }*/


    @GetMapping("/{sodaId}")
    public ResponseEntity<SodaDto> getSodaById(@PathVariable UUID sodaId){
        return new ResponseEntity<>(SodaDto.builder().id(sodaId).build(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity handlePost(@Validated  @RequestBody  SodaDto sodaDto){

        return new ResponseEntity(HttpStatus.CREATED);

    }

    @PutMapping("/{sodaId}")
    public ResponseEntity handleUpdate(@PathVariable UUID sodaId,@Validated @RequestBody SodaDto sodaDto){


        return new ResponseEntity(HttpStatus.NO_CONTENT);

    }


    @DeleteMapping("/{sodaId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void handleDelete(@PathVariable UUID sodaId){
        
    }





}