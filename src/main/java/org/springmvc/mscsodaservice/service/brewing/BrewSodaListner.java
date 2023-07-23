package org.springmvc.mscsodaservice.service.brewing;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springmvc.mscsodaservice.config.JmsConfig;
import org.springmvc.mscsodaservice.domain.Soda;
import org.springmvc.mscsodaservice.events.BrewSodaEvent;
import org.springmvc.mscsodaservice.events.NewInventoryEvent;
import org.springmvc.mscsodaservice.repository.SodaRepository;
import org.springmvc.mscsodaservice.web.model.SodaDto;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class BrewSodaListner {
    private final SodaRepository sodaRepository;
    private final JmsTemplate jmsTemplate;
    private ObjectMapper mapper;

    @Transactional
    @JmsListener(destination= JmsConfig.MY_QUEUE)
    public void listen(BrewSodaEvent brewSodaEvent){
        SodaDto dto = brewSodaEvent.getSodaDto();
        Optional<Soda> soda = sodaRepository.findById(dto.getId());

        soda.ifPresent(sodaEvent -> {
            dto.setQuantityOnHand(sodaEvent.getQuantityToBrew());
            NewInventoryEvent newInventoryEvent = new NewInventoryEvent(dto);
            log.info("Checking Inventory for: " + sodaEvent.getSodaName() + " / " + dto.getQuantityOnHand());
            //newInventoryEvent.setSodaDto(dto);
            jmsTemplate.convertAndSend(JmsConfig.NEW_INVENTORY_QUEUE, newInventoryEvent);
            log.info(newInventoryEvent.toString());
                });

    }
}
