package org.springmvc.mscsodaservice.service.brewing;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springmvc.mscsodaservice.config.JmsConfig;
import org.springmvc.mscsodaservice.domain.Soda;
import org.springmvc.mscsodaservice.events.BrewSodaEvent;
import org.springmvc.mscsodaservice.repository.SodaRepository;
import org.springmvc.mscsodaservice.service.inventory.SodaInventoryService;
import org.springmvc.mscsodaservice.web.mapper.SodaMapper;

import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
public class BrewingService {

    private final SodaRepository sodaRepository;
    private final SodaInventoryService sodaInventoryService;
    private final JmsTemplate jmsTemplate;
    private final SodaMapper sodaMapper;




    @Scheduled(fixedRate = 5000) //every 5 seconds
    public void checkForLowInventory(){
        List<Soda> sodas = sodaRepository.findAll();

        sodas.forEach(soda -> {
            Integer invQOH = sodaInventoryService.getOnHandInventory(soda.getId());
            log.debug("Checking Inventory for: " + soda.getSodaName() + " / " + soda.getId());
            log.debug("Min Onhand is: " + soda.getMinOnHand());
            log.debug("Inventory is: "  + invQOH);

            if(soda.getMinOnHand() >= invQOH){
                BrewSodaEvent brew = new BrewSodaEvent(sodaMapper.sodaToSodaDto(soda));
                jmsTemplate.convertAndSend(JmsConfig.MY_QUEUE,brew);
            }
        });

    }
}

