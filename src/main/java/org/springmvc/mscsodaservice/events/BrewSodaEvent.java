package org.springmvc.mscsodaservice.events;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.NoArgsConstructor;
import org.springmvc.mscsodaservice.web.model.SodaDto;

@NoArgsConstructor
public class BrewSodaEvent extends SodaEvent{
    public BrewSodaEvent(SodaDto sodaDto) {
        super(sodaDto);
    }


}
