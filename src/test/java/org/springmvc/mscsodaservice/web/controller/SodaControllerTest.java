package org.springmvc.mscsodaservice.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springmvc.mscsodaservice.web.model.SodaDto;
import org.springmvc.mscsodaservice.web.model.SodaStyleNum;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.UUID;

import static org.mockito.Mockito.*;

@WebMvcTest(SodaController.class)
class SodaControllerTest {

    public static final String BEER_1_UPC = "0631234200036";
    public static final String BEER_2_UPC = "0631234300019";
    public static final String BEER_3_UPC = "0083783375213";

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    MockMvc mockMvc;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    ObjectMapper objectMapper;


    SodaDto validSoda;

    @BeforeEach
    public void setUp() {
        validSoda = SodaDto.builder().id(null)
                .name("Co")
                .sodaStyle(SodaStyleNum.COLA)
                .upc("12345678901").price(new BigDecimal("5.20"))
                .quantity(10)
                .build();
    }


    @Test
    void getSodaById() throws Exception {
        mockMvc.perform(get("/api/v1/soda/" + UUID.randomUUID().toString())
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    void handlePost() throws Exception {
        //SodaDto sodaDto = SodaDto.builder().build();
        String sodaDtoJson = objectMapper.writeValueAsString(validSoda);
        mockMvc.perform(post("/api/v1/soda/")
                .contentType(MediaType.APPLICATION_JSON).content(sodaDtoJson))
                .andExpect(status().isCreated());
    }

    @Test
    void handleUpdate() throws Exception {
        //SodaDto sodaDto = SodaDto.builder().build();
        String sodaDtoJson = objectMapper.writeValueAsString(validSoda);
        mockMvc.perform(put("/api/v1/soda/" + UUID.randomUUID().toString())
                        .contentType(MediaType.APPLICATION_JSON).content(sodaDtoJson))
                .andExpect(status().isNoContent());
    }
}