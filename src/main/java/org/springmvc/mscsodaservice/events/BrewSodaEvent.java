package org.springmvc.mscsodaservice.events;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springmvc.mscsodaservice.web.model.SodaDto;


public class BrewSodaEvent extends SodaEvent{

    @JsonCreator
    public BrewSodaEvent(@JsonProperty("sodaDto") SodaDto sodaDto) {
        super(sodaDto);
    }
}
