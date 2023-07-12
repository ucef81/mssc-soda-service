package org.springmvc.mscsodaservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springmvc.mscsodaservice.domain.Soda;
import org.springmvc.mscsodaservice.web.model.SodaStyleNum;

import java.util.UUID;

public interface SodaRepository extends JpaRepository<Soda, UUID> , JpaSpecificationExecutor<Soda> {
    Page<Soda> findAllBySodaNameAndSodaStyle(String sodaName, SodaStyleNum sodaStyle, PageRequest pageRequest);
    Page<Soda> findAllBySodaName(String sodaName, PageRequest pageRequest);
    Page<Soda> findAllBySodaStyle(SodaStyleNum sodaStyle, PageRequest pageRequest);

}
