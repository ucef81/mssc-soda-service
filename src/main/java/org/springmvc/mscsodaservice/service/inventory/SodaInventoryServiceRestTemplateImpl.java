package org.springmvc.mscsodaservice.service.inventory;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springmvc.mscsodaservice.web.model.SodaDto;

import java.util.List;
import java.util.Objects;
import java.util.UUID;


@Slf4j
@ConfigurationProperties(prefix = "org.grosserie", ignoreUnknownFields = false)
@Component
public class SodaInventoryServiceRestTemplateImpl implements SodaInventoryService {

    private final  String INVENTORY_PATH = "/api/v1/soda/{sodaId}/Inventory";

    @Autowired
    private  RestTemplate restTemplate;

    private String sodaInventoryServiceHost;


    public void setSodaInventoryServiceHost(String sodaInventoryServiceHost) {
        this.sodaInventoryServiceHost = sodaInventoryServiceHost;
    }


    @Override
    public Integer getOnHandInventory(UUID sodaId) {
        log.debug("Calling Inventory Service");
        ResponseEntity<List<SodaInventoryDto>> responseEntity = restTemplate
                .exchange(sodaInventoryServiceHost + INVENTORY_PATH, HttpMethod.GET,null,
                        new ParameterizedTypeReference<List<SodaInventoryDto>>(){},
                        (Object) sodaId);
        Integer onHand = Objects.requireNonNull(responseEntity.getBody())
                .stream().mapToInt(SodaInventoryDto::getQuantityOnHand).sum();
        return onHand;
    }

}
