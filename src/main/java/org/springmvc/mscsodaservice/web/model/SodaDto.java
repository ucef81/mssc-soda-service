package org.springmvc.mscsodaservice.web.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Version;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SodaDto {

    @Null
    private UUID id;

    @Version
    private Long version;

    @Null
    private OffsetDateTime createdDate;

    @Null
    private OffsetDateTime lastModifiedDate;

    @NotBlank(message = "wa3333333333333333333333333333")
    @Size(min = 3,max = 10, message = "Size name have to be between 3 and 10 caracters")
    private String name;

    @NotNull
    private SodaStyleNum sodaStyle;

    @Positive
    private Long upc;


    @Positive
    private BigDecimal price;

    @PositiveOrZero
    private Integer quantity;


}
