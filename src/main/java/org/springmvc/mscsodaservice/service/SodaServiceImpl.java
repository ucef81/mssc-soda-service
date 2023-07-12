package org.springmvc.mscsodaservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springmvc.mscsodaservice.web.controller.SodaNotFoundException;
import org.springmvc.mscsodaservice.domain.Soda;
import org.springmvc.mscsodaservice.web.mapper.SodaMapper;
import org.springmvc.mscsodaservice.web.model.SodaDto;
import org.springmvc.mscsodaservice.repository.SodaRepository;
import org.springmvc.mscsodaservice.web.model.SodaPagedList;
import org.springmvc.mscsodaservice.web.model.SodaStyleNum;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class SodaServiceImpl implements SodaService {

    private final SodaRepository sodaRepository;
    private final SodaMapper sodaMapper;

    @Override
    public SodaPagedList getAllSodas(String sodaName, SodaStyleNum sodaStyle, PageRequest pageRequest) {


        SodaPagedList sodaPagedList;
        Page<Soda> sodaPage;

            if(StringUtils.hasLength(sodaName) && sodaStyle != null ){
                sodaPage = sodaRepository.findAllBySodaNameAndSodaStyle(sodaName,sodaStyle, pageRequest);
                System.out.println("111111111111111111111111111");
            }

            else if(StringUtils.hasLength(sodaName) && sodaStyle == null) {
                sodaPage = sodaRepository.findAllBySodaName(sodaName, pageRequest);
                System.out.println("22222222222222222222222222");
            }

            else if(!StringUtils.hasLength(sodaName) && sodaStyle != null) {
                sodaPage = sodaRepository.findAllBySodaStyle(sodaStyle, pageRequest);
                System.out.println("33333333333333333333333333333");
            }
            else {
                sodaPage = sodaRepository.findAll(pageRequest);
                System.out.println("44444444444444444444444444");
            }
            return new SodaPagedList(sodaPage.stream()
                    .map(sodaMapper::sodaToSodaDto)
                    .collect(Collectors.toList()),
                    PageRequest.of(
                            sodaPage.getPageable().getPageNumber(),
                            sodaPage.getPageable().getPageSize()
                    ),
                    sodaPage.getTotalElements());

    }

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
        soda.setSodaName(sodaDto.getSodaName());
        soda.setSodaStyle(sodaDto.getSodaStyle().name());
        soda.setPrice(sodaDto.getPrice());
        soda.setUpc(sodaDto.getUpc());
        soda.setMinOnHand(sodaDto.getMinOnHand());
        return sodaMapper.sodaToSodaDto(sodaRepository.save(soda));



    }


}
