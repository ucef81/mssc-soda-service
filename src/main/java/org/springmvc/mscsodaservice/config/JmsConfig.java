package org.springmvc.mscsodaservice.config;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

@Configuration
public class JmsConfig {

    public static final String MY_QUEUE = "soda_queue";
    public static final String NEW_INVENTORY_QUEUE = "new_inventory_queue";

    @Bean
    @ConditionalOnBean(ObjectMapper.class)
    public MessageConverter messageConverter(ObjectMapper aObjectMapper){
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setObjectMapper(aObjectMapper);
        converter.setTargetType(MessageType.TEXT);
        converter.setTypeIdPropertyName("type");

        return  converter;
    }
}
