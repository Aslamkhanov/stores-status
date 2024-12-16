package com.javaacademy.stores_and_status.controller;

import com.javaacademy.stores_and_status.dto.GoodPriceUpdateDto;
import com.javaacademy.stores_and_status.dto.StatusDto;
import com.javaacademy.stores_and_status.service.StoreService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/shop")
@Tag(name = "Store Controller", description = "Контроллер для получения статуса магазинов и управления товарами")
public class StoreController {
    private final StoreService storeService;

    @Operation(
            tags = "Получение статуса магазинов",
            summary = "Получение статусов всех магазинов",
            description = "Возвращает статусы всех магазинов")
    @ApiResponse(
            responseCode = "200",
            description = "Успешно",
            content = @Content(
                    mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = StatusDto.class))))
    @GetMapping("/status")
    public List<StatusDto> getAllStatuses() {
        return storeService.getStatuses();
    }

    @Operation(
            tags = "Обновление цены товара",
            summary = "Обновление цены товара",
            description = "Обновление цены товара в магазинах")
    @ApiResponse(
            responseCode = "200",
            description = "Цена товара успешно обновлена",
            content = @Content(mediaType = "application/json"))
    @PatchMapping("/good")
    public void updateGoodPrice(@RequestBody GoodPriceUpdateDto priceUpdate) {
        storeService.updateGoodPrice(priceUpdate);
    }
}

