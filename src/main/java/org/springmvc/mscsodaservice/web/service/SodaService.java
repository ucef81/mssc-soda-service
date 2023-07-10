package org.springmvc.mscsodaservice.web.service;

import org.springmvc.mscsodaservice.web.controller.SodaNotFoundException;
import org.springmvc.mscsodaservice.web.model.SodaDto;

import java.util.UUID;

public interface SodaService {

    SodaDto getById(UUID id) throws SodaNotFoundException;
    SodaDto saveNewSoda(SodaDto sodaDto);
    SodaDto updateSoda(UUID id, SodaDto sodaDto) throws SodaNotFoundException;
}
