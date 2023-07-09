package org.springmvc.mscsodaservice.web.model;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class SodaPagedList extends PageImpl<SodaDto>{
    public SodaPagedList(List<SodaDto> content, Pageable pageable, long total) {
        super(content, pageable, total);
    }

    public SodaPagedList(List<SodaDto> content) {
        super(content);
    }
}
