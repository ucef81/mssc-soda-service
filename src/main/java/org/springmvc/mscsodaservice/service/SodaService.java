package org.springmvc.mscsodaservice.service;

import org.springframework.data.domain.PageRequest;
import org.springmvc.mscsodaservice.web.controller.SodaNotFoundException;
import org.springmvc.mscsodaservice.web.model.SodaDto;
import org.springmvc.mscsodaservice.web.model.SodaPagedList;
import org.springmvc.mscsodaservice.web.model.SodaStyleNum;

import java.util.UUID;

public interface SodaService {

    SodaDto getById(UUID id, boolean showInventoryOnHand) throws SodaNotFoundException;
    SodaDto saveNewSoda(SodaDto sodaDto);
    SodaDto updateSoda(UUID id, SodaDto sodaDto) throws SodaNotFoundException;
    SodaPagedList getAllSodas(String sodaName, SodaStyleNum sodaStyleNum, PageRequest pageRequest, boolean showInventoryOnHand);
    SodaDto getByUpc(String upc, boolean showInventoryOnHand);
}
