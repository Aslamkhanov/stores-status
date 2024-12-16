package com.javaacademy.stores_and_status.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Обновление цены товара")
public class GoodPriceUpdateDto {

    @Schema(description = "Название товара")
    private String name;

    @JsonProperty("new_price")
    @Schema(description = "Новая цена")
    private BigDecimal newPrice;
}
