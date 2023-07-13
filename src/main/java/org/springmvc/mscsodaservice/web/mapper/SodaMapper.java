package org.springmvc.mscsodaservice.web.mapper;

import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

import org.springmvc.mscsodaservice.domain.Soda;
import org.springmvc.mscsodaservice.web.model.SodaDto;

@Mapper(uses = {DateMapper.class})
@DecoratedWith(SodaMapperDecorator.class)
public interface SodaMapper {

    SodaDto sodaToSodaDto(Soda soda);
    Soda sodaDtoToSoda(SodaDto sodaDto);
    SodaDto sodaToSodaDtoWithInventory(Soda soda);

}
