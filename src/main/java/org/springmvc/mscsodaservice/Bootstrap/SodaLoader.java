package org.springmvc.mscsodaservice.Bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springmvc.mscsodaservice.domain.Soda;
import org.springmvc.mscsodaservice.repository.SodaRepository;

import java.math.BigDecimal;

//@Component
public class SodaLoader implements CommandLineRunner {

    public static final String BEER_1_UPC = "0631234200036";
    public static final String BEER_2_UPC = "0631234300019";
    public static final String BEER_3_UPC = "0083783375213";
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
                    .quantityOnHand(20)
                    .upc(BEER_1_UPC)
                    .price(new BigDecimal("8.10"))
                    .build());
            sodaRepository.save(Soda.builder().sodaName("Oulmes")
                    .sodaStyle("TONIC_WATER")
                    .quantityOnHand(20)
                    .upc(BEER_2_UPC)
                    .price(new BigDecimal("10.20"))
                    .build());
            sodaRepository.save(Soda.builder().sodaName("AIN SULTAN")
                    .sodaStyle("WATER")
                    .quantityOnHand(20)
                    .upc(BEER_3_UPC)
                    .price(new BigDecimal("5.00"))
                    .build());

        }
    }
}

