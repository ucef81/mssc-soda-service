package org.springmvc.mscsodaservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springmvc.mscsodaservice.domain.Soda;
import org.springmvc.mscsodaservice.repository.SodaRepository;
import org.springmvc.mscsodaservice.web.controller.SodaNotFoundException;
import org.springmvc.mscsodaservice.web.mapper.SodaMapper;
import org.springmvc.mscsodaservice.web.model.SodaDto;
import org.springmvc.mscsodaservice.web.model.SodaPagedList;
import org.springmvc.mscsodaservice.web.model.SodaStyleNum;

import java.util.UUID;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class SodaServiceImpl implements SodaService {

    private final SodaRepository sodaRepository;
    private final SodaMapper sodaMapper;

    @Override
    @Cacheable(cacheNames = "sodaListCache", condition = "#showInventoryOnHand == false ")
    public SodaPagedList getAllSodas(String sodaName, SodaStyleNum sodaStyle, PageRequest pageRequest, boolean showInventoryOnHand) {


        SodaPagedList sodaPagedList;
        Page<Soda> sodaPage;

            if(StringUtils.hasLength(sodaName) && sodaStyle != null ){
                sodaPage = sodaRepository.findAllBySodaNameAndSodaStyle(sodaName,sodaStyle, pageRequest);

            }

            else if(StringUtils.hasLength(sodaName) && sodaStyle == null) {
                sodaPage = sodaRepository.findAllBySodaName(sodaName, pageRequest);

            }

            else if(!StringUtils.hasLength(sodaName) && sodaStyle != null) {
                sodaPage = sodaRepository.findAllBySodaStyle(sodaStyle, pageRequest);

            }
            else {
                sodaPage = sodaRepository.findAll(pageRequest);

            }

            if (showInventoryOnHand) {
                return new SodaPagedList(sodaPage.stream()
                        .map(sodaMapper::sodaToSodaDtoWithInventory)
                        .collect(Collectors.toList()),
                        PageRequest.of(
                                sodaPage.getPageable().getPageNumber(),
                                sodaPage.getPageable().getPageSize()
                        ),
                        sodaPage.getTotalElements());
            }
            else{
                return new SodaPagedList(sodaPage.stream()
                        .map(sodaMapper::sodaToSodaDto)
                        .collect(Collectors.toList()),
                        PageRequest.of(
                                sodaPage.getPageable().getPageNumber(),
                                sodaPage.getPageable().getPageSize()
                        ),
                        sodaPage.getTotalElements());

            }

    }

   // @Cacheable(cacheNames = "sodaUpcCache", condition = "#showInventoryOnHand == false ")
    @Override
    public SodaDto getByUpc(String upc, boolean showInventoryOnHand) {
        if (showInventoryOnHand)
            return sodaMapper.sodaToSodaDtoWithInventory(sodaRepository.findByUpc(upc));
        else
            return sodaMapper.sodaToSodaDto(sodaRepository.findByUpc(upc));
    }


    @Cacheable(cacheNames = "sodaCache", key = "#sodaId", condition = "#showInventoryOnHand == false ")
    @Override
    public SodaDto getById(UUID id, boolean showInventoryOnHand) throws SodaNotFoundException {
        if (showInventoryOnHand)
             return sodaMapper.sodaToSodaDtoWithInventory(sodaRepository.findById(id)
                .orElseThrow(() -> new SodaNotFoundException("Object with this Id not found")));
        else
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
        soda.setSodaStyle(sodaDto.getSodaStyle());
        soda.setPrice(sodaDto.getPrice());
        soda.setUpc(sodaDto.getUpc());
        soda.setQuantityOnHand(sodaDto.getQuantityOnHand());
        return sodaMapper.sodaToSodaDto(sodaRepository.save(soda));



    }


}
