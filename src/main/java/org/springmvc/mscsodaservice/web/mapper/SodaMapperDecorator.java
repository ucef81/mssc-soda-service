package org.springmvc.mscsodaservice.web.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springmvc.mscsodaservice.domain.Soda;
import org.springmvc.mscsodaservice.service.inventory.SodaInventoryService;
import org.springmvc.mscsodaservice.web.model.SodaDto;

public  abstract class SodaMapperDecorator implements SodaMapper{

    private SodaInventoryService sodaInventoryService;
    private SodaMapper mapper;

    @Autowired
    public void setSodaInventoryService(SodaInventoryService sodaInventoryService) {
        this.sodaInventoryService = sodaInventoryService;
    }

    @Autowired
    public void setMapper(SodaMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public SodaDto sodaToSodaDto(Soda soda) {
        return  mapper.sodaToSodaDto(soda);
    }

    @Override
    public SodaDto sodaToSodaDtoWithInventory(Soda soda) {
        SodaDto dto = mapper.sodaToSodaDto(soda);
        dto.setQuantityOnHand(sodaInventoryService.getOnHandInventory(soda.getId()));
        return dto;
    }

    @Override
    public Soda sodaDtoToSoda(SodaDto sodaDto) {
        return mapper.sodaDtoToSoda(sodaDto);
    }
}
