package org.springmvc.mscsodaservice.events;



import lombok.NoArgsConstructor;
import org.springmvc.mscsodaservice.web.model.SodaDto;

@NoArgsConstructor
public class NewInventoryEvent extends SodaEvent {
    public NewInventoryEvent(SodaDto sodaDto) {
        super(sodaDto);
    }
}
