package org.springmvc.mscsodaservice.events;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springmvc.mscsodaservice.events.order.SodaOrderDto;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ValidateOrderRequest {

    private SodaOrderDto sodaOrder;

}
