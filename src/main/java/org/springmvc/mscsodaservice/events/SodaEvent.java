package org.springmvc.mscsodaservice.events;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springmvc.mscsodaservice.web.model.SodaDto;

import java.io.Serializable;


@Data
@RequiredArgsConstructor
@Builder
public class SodaEvent implements Serializable {

    static final long serialVersionUID = -5815566940065181210L;
    private final SodaDto sodaDto;
}
