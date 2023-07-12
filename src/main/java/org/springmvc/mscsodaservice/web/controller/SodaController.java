package org.springmvc.mscsodaservice.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springmvc.mscsodaservice.web.model.SodaDto;
import org.springmvc.mscsodaservice.service.SodaService;
import org.springmvc.mscsodaservice.web.model.SodaPagedList;
import org.springmvc.mscsodaservice.web.model.SodaStyleNum;

import java.util.UUID;

@RequestMapping("/api/v1/soda")
@RestController

@RequiredArgsConstructor
public class SodaController {

    private static final Integer DEFAULT_PAGE_NUMBER = 0;
    private static final Integer DEFAULT_PAGE_SIZE = 25;

    private final SodaService sodaService;


    @GetMapping(produces = "application/json")
    public ResponseEntity<SodaPagedList> listSodas(@RequestParam(value = "pageNumber", required = false) Integer pageNumber,
                                                   @RequestParam(value = "pageSize", required = false) Integer pageSize,
                                                   @RequestParam(value = "sodaName", required = false) String sodaName,
                                                   @RequestParam(value = "sodaStyle", required = false) SodaStyleNum sodaStyle) {

        if (pageNumber == null || pageNumber < 0){
            pageNumber = DEFAULT_PAGE_NUMBER;
        }

        if (pageSize == null || pageSize < 1) {
            pageSize = DEFAULT_PAGE_SIZE;
        }

        SodaPagedList sodaList = sodaService.getAllSodas(sodaName, sodaStyle, PageRequest.of(pageNumber, pageSize));
        return new ResponseEntity<>(sodaList, HttpStatus.OK);
    }



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