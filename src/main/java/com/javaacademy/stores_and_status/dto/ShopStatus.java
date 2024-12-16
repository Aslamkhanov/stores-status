package com.javaacademy.stores_and_status.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Статус магазина")
public enum ShopStatus {
    WORK, CLOSED;
}
