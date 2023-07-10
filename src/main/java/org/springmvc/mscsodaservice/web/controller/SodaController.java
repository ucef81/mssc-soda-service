package org.springmvc.mscsodaservice.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springmvc.mscsodaservice.web.model.SodaDto;
import org.springmvc.mscsodaservice.web.service.SodaService;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequestMapping("/api/v1/soda")
@RestController

@RequiredArgsConstructor
public class SodaController {


    private final SodaService sodaService;

    @GetMapping("/{sodaId}")
    public ResponseEntity<SodaDto> getSodaById(@PathVariable UUID sodaId) throws SodaNotFoundException {
        return new ResponseEntity<>(sodaService.getById(sodaId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity handlePost(@Validated  @RequestBody  SodaDto sodaDto){

        return new ResponseEntity(sodaService.saveNewSoda(sodaDto),HttpStatus.CREATED);

    }

    @PutMapping("/{sodaId}")
    public ResponseEntity handleUpdate(@PathVariable UUID sodaId,@Validated @RequestBody SodaDto sodaDto) throws SodaNotFoundException {


        return new ResponseEntity(sodaService.updateSoda(sodaId,sodaDto),HttpStatus.NO_CONTENT);

    }


    @DeleteMapping("/{sodaId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void handleDelete(@PathVariable UUID sodaId){
        
    }





}