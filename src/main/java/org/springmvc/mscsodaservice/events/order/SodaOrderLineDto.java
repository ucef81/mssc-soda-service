package org.springmvc.mscsodaservice.events.order;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SodaOrderLineDto extends BaseItem {

    @Builder
    public SodaOrderLineDto(UUID id, Integer version, OffsetDateTime createdDate, OffsetDateTime lastModifiedDate,
                            String upc, UUID sodaId, String sodaName, String sodaStyle , Integer orderQuantity,
                            Integer quantityAllocated, BigDecimal price) {
        super(id, version, createdDate, lastModifiedDate);
        this.upc = upc;
        this.sodaId = sodaId;
        this.sodaName = sodaName;
        this.sodaStyle = sodaStyle;
        this.orderQuantity = orderQuantity;
        this.price = price;
        this.quantityAllocated = quantityAllocated;
    }

    private String upc;
    private String sodaName;
    private String sodaStyle;
    private UUID sodaId;
    private BigDecimal price;

    private Integer orderQuantity = 0;
    private Integer quantityAllocated = 0;
}
