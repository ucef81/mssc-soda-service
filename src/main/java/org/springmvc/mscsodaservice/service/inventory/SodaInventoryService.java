package org.springmvc.mscsodaservice.service.inventory;

import java.util.UUID;

public interface SodaInventoryService {

    Integer getOnHandInventory(UUID sodaId);
}
