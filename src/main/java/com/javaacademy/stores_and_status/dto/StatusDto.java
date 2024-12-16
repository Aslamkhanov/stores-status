package com.javaacademy.stores_and_status.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StatusDto {

    @Schema(description = "Название магазина")
    private String name;

    @Schema(description = "Статус магазина (закрыт/открыт)")
    private ShopStatus shopStatus;

    @Schema(description = "Время открытия магазина")
    @JsonProperty("time_open")
    private String timeOpen;

    @Schema(description = "Время закрытия магазина")
    @JsonProperty("time_close")
    private String timeClose;
}
