package org.springmvc.mscsodaservice.web.mapper;

import org.mapstruct.Mapper;

import org.springmvc.mscsodaservice.web.domain.Soda;
import org.springmvc.mscsodaservice.web.model.SodaDto;

@Mapper(
        uses = {DateMapper.class}
)
public interface SodaMapper {

    SodaDto sodaToSodaDto(Soda soda);
    Soda sodaDtoToSoda(SodaDto sodaDto);
}
