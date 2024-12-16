package com.javaacademy.stores_and_status.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javaacademy.stores_and_status.dto.GoodPriceUpdateDto;
import com.javaacademy.stores_and_status.dto.StatusDto;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreService {
    private static final String URL_PORT_ONE = "http://localhost:8081/application/status";
    private static final String URL_PORT_TWO = "http://localhost:8082/application/status";
    private static final String URL_SHOP_ONE = "http://localhost:8081/shop/good";
    private static final String URL_SHOP_TWO = "http://localhost:8082/shop/good";
    private final OkHttpClient client = new OkHttpClient();
    private final ObjectMapper mapper = new ObjectMapper();

    @SneakyThrows
    public List<StatusDto> getStatuses() {
        return List.of(getShopStatusFromUrl(URL_PORT_ONE), getShopStatusFromUrl(URL_PORT_TWO));
    }

    @SneakyThrows
    private StatusDto getShopStatusFromUrl(String url) {
        Request request = new Request.Builder()
                .url(url)
                .build();
        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful() && response.body() != null) {
                return new ObjectMapper().readValue(response.body().string(), StatusDto.class);
            } else {
                throw new IOException("что то пошло не так " + response);
            }
        }
    }

    @SneakyThrows
    public void updateGoodPrice(GoodPriceUpdateDto priceUpdate) {
        byte[] body = mapper.writeValueAsBytes(priceUpdate);

        patchGoodPrice(URL_SHOP_ONE, priceUpdate);
        patchGoodPrice(URL_SHOP_TWO, priceUpdate);
    }

    @SneakyThrows
    private void patchGoodPrice(String url, GoodPriceUpdateDto priceUpdate) {
        byte[] body = mapper.writeValueAsBytes(priceUpdate);

        Request request = new Request.Builder()
                .patch(okhttp3.RequestBody.create(body))
                .url(url)
                .addHeader("Content-Type", "application/json")
                .build();

        Response response = client.newCall(request).execute();
        if (response.isSuccessful()) {
            System.out.println("стоимость товара в магазинах, успешно изменена");
        }
    }
}
