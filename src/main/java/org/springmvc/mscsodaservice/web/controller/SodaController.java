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

@RequestMapping("/api/v1/")
@RestController
@RequiredArgsConstructor
public class SodaController {

    private static final Integer DEFAULT_PAGE_NUMBER = 0;
    private static final Integer DEFAULT_PAGE_SIZE = 25;

    private final SodaService sodaService;


    @GetMapping(produces = "application/json", path = "soda")
    public ResponseEntity<SodaPagedList> listSodas(@RequestParam(value = "pageNumber", required = false) Integer pageNumber,
                                                   @RequestParam(value = "pageSize", required = false) Integer pageSize,
                                                   @RequestParam(value = "sodaName", required = false) String sodaName,
                                                   @RequestParam(value = "sodaStyle", required = false) SodaStyleNum sodaStyle,
                                                   @RequestParam(value = "showInventoryOnHand", required = false) boolean showInventoryOnHand) {

        if (pageNumber == null || pageNumber < 0){
            pageNumber = DEFAULT_PAGE_NUMBER;
        }

        if (pageSize == null || pageSize < 1) {
            pageSize = DEFAULT_PAGE_SIZE;
        }

        SodaPagedList sodaList = sodaService.getAllSodas(sodaName, sodaStyle, PageRequest.of(pageNumber, pageSize), showInventoryOnHand);
        return new ResponseEntity<>(sodaList, HttpStatus.OK);
    }



    @GetMapping("soda/{sodaId}")
    public ResponseEntity<SodaDto> getSodaById(@PathVariable UUID sodaId,
                                               @RequestParam(value = "showInventoryOnHand", required = false) boolean showInventoryOnHand)
                                            throws SodaNotFoundException {
        return new ResponseEntity<>(sodaService.getById(sodaId, showInventoryOnHand), HttpStatus.OK);
    }

    @GetMapping("sodaUpc/{upc}")
    public ResponseEntity<SodaDto> getSodaByUpc(@PathVariable String upc ,
                                                @RequestParam(value = "showInventoryOnHand", required = false) boolean showInventoryOnHand)
                                             {
        return new ResponseEntity<>(sodaService.getByUpc(upc,showInventoryOnHand), HttpStatus.OK);
    }

    @PostMapping(path = "soda")
    public ResponseEntity handlePost(@Validated  @RequestBody  SodaDto sodaDto){

        return new ResponseEntity(sodaService.saveNewSoda(sodaDto),HttpStatus.CREATED);

    }

    @PutMapping("soda/{sodaId}")
    public ResponseEntity handleUpdate(@PathVariable UUID sodaId,@Validated @RequestBody SodaDto sodaDto) throws SodaNotFoundException {


        return new ResponseEntity(sodaService.updateSoda(sodaId,sodaDto),HttpStatus.NO_CONTENT);

    }


    @DeleteMapping("soda/{sodaId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void handleDelete(@PathVariable UUID sodaId){
        
    }





}