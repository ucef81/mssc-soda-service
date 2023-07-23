package org.springmvc.mscsodaservice.service.order;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springmvc.mscsodaservice.events.order.SodaOrderDto;
import org.springmvc.mscsodaservice.repository.SodaRepository;

import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@RequiredArgsConstructor
@Component
public class SodaOrderValidator {

    private final SodaRepository sodaRepository;

    public Boolean validateOrder(SodaOrderDto beerOrder){

        AtomicInteger beersNotFound = new AtomicInteger();

        beerOrder.getSodaOrderLines().forEach(orderline -> {
            if(sodaRepository.findByUpc(orderline.getUpc()) == null){
                beersNotFound.incrementAndGet();
            }
        });

        return beersNotFound.get() == 0;
    }

}
