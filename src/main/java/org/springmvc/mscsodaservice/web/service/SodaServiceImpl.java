package org.springmvc.mscsodaservice.web.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springmvc.mscsodaservice.web.controller.SodaNotFoundException;
import org.springmvc.mscsodaservice.web.domain.Soda;
import org.springmvc.mscsodaservice.web.mapper.SodaMapper;
import org.springmvc.mscsodaservice.web.model.SodaDto;
import org.springmvc.mscsodaservice.web.repository.SodaRepository;

import java.util.UUID;


@Service
@RequiredArgsConstructor
public class SodaServiceImpl implements SodaService {

    private final SodaRepository sodaRepository;
    private final SodaMapper sodaMapper;
    @Override
    public SodaDto getById(UUID id) throws SodaNotFoundException {
        return sodaMapper.sodaToSodaDto(sodaRepository.findById(id)
                .orElseThrow(() -> new SodaNotFoundException("Object with this Id not found")));
    }

    @Override
    public SodaDto saveNewSoda(SodaDto sodaDto) {

        return sodaMapper.sodaToSodaDto(sodaRepository
                .save(sodaMapper.sodaDtoToSoda(sodaDto)));
    }

    @Override
    public SodaDto updateSoda(UUID id, SodaDto sodaDto) throws SodaNotFoundException {
        Soda soda = sodaRepository.findById(id)
                .orElseThrow(() -> new SodaNotFoundException("Object with this Id not found"));
        soda.setSodaName(sodaDto.getName());
        soda.setSodaStyle(sodaDto.getSodaStyle().name());
        soda.setPrice(sodaDto.getPrice());
        soda.setUpc(sodaDto.getUpc());
        soda.setMinOnHand(sodaDto.getMinOnHand());
        return sodaMapper.sodaToSodaDto(sodaRepository.save(soda));



    }
}
