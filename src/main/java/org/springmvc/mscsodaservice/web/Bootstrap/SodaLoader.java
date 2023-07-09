package org.springmvc.mscsodaservice.web.Bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springmvc.mscsodaservice.web.domain.Soda;
import org.springmvc.mscsodaservice.web.repository.SodaRepository;

import java.math.BigDecimal;

@Component
public class SodaLoader implements CommandLineRunner {

    private final SodaRepository sodaRepository;

    public SodaLoader(SodaRepository sodaRepository){
        this.sodaRepository = sodaRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadSodaObjects();
    }
    
    private void loadSodaObjects(){
        if (sodaRepository.count() == 0) {
            sodaRepository.save(Soda.builder().sodaName("Orangina")
                    .sodaStyle("CLUB_SODA")
                    .minOnHand(1)
                    .quantity(20)
                    .upc(122335412L)
                    .price(new BigDecimal("8.10"))
                    .build());
            sodaRepository.save(Soda.builder().sodaName("Oulmes")
                    .sodaStyle("TONIC_WATER")
                    .minOnHand(1)
                    .quantity(18)
                    .upc(1223354112L)
                    .price(new BigDecimal("10.20"))
                    .build());
            sodaRepository.save(Soda.builder().sodaName("AIN SULTAN")
                    .sodaStyle("WATER")
                    .minOnHand(1)
                    .quantity(20)
                    .upc(9662335412L)
                    .price(new BigDecimal("5.00"))
                    .build());

        }
    }
}

