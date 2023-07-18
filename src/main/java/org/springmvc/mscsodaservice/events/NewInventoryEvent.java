package org.springmvc.mscsodaservice.events;


import lombok.NoArgsConstructor;
import org.springframework.jms.annotation.EnableJms;
import org.springmvc.mscsodaservice.web.model.SodaDto;

public class NewInventoryEvent extends SodaEvent {
    public NewInventoryEvent(SodaDto sodaDto) {
        super(sodaDto);
    }
}
