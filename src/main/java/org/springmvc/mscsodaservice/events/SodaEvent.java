package org.springmvc.mscsodaservice.events;

import lombok.*;
import org.springmvc.mscsodaservice.web.model.SodaDto;

import java.io.Serializable;


@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class SodaEvent implements Serializable {

    static final long serialVersionUID = 5815566940065181210L;
    private    SodaDto sodaDto;



}

