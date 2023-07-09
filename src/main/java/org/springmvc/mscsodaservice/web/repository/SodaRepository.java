package org.springmvc.mscsodaservice.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springmvc.mscsodaservice.web.domain.Soda;

import java.util.UUID;

public interface SodaRepository extends JpaRepository<Soda, UUID> , JpaSpecificationExecutor<Soda> {
}
