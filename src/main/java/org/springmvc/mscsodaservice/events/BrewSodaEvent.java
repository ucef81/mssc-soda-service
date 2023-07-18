package org.springmvc.mscsodaservice.events;


import lombok.NoArgsConstructor;
import org.springmvc.mscsodaservice.web.model.SodaDto;


public class BrewSodaEvent extends SodaEvent{

    public BrewSodaEvent(SodaDto sodaDto) {
        super(sodaDto);
    }
}
