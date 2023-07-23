package org.springmvc.mscsodaservice.service.order;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springmvc.mscsodaservice.config.JmsConfig;
import org.springmvc.mscsodaservice.events.ValidateOrderRequest;
import org.springmvc.mscsodaservice.events.ValidateOrderResult;
import org.springmvc.mscsodaservice.repository.SodaRepository;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderValidatorListner {

    private final SodaOrderValidator validator;
    private final JmsTemplate jmsTemplate;

    @JmsListener(destination = JmsConfig.VALIDATE_ORDER_QUEUE)
    public void listen(ValidateOrderRequest validateOrderRequest){
        Boolean isValid = validator.validateOrder(validateOrderRequest.getSodaOrder());

        jmsTemplate.convertAndSend(JmsConfig.VALIDATE_ORDER_RESPONSE_QUEUE,
                ValidateOrderResult.builder()
                        .isValid(isValid)
                        .orderId(validateOrderRequest.getSodaOrder().getId())
                        .build());
    }



}
