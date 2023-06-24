package com.rst.taxiprovider.controller;

import com.rst.metrics.dto.Price;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
class TaxiControllerTest {
    private String path;
    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate testRestTemplate;

    @BeforeEach
    void init() {
        path = "http://localhost:" + port + "/taxi/price";
    }

    @ParameterizedTest
    @MethodSource("getPriceTestDataProvider")
    void getPrice(String clid, String apikey, String rll) {
        // GIVEN
        String data = String.format("?clid=%s&apikey=%s&rll=%s", clid, apikey, rll);

        // WHEN
        ResponseEntity<Price> result = testRestTemplate.getForEntity(path + data, Price.class);

        // THEN
        assertNotNull(result);
        assertTrue(result.getStatusCode().is2xxSuccessful());
        assertNotNull(result.getBody());
        assertEquals("RUB", result.getBody().getCurrency());
    }

    private static Stream<Arguments> getPriceTestDataProvider() {
        return Stream.of(
                Arguments.of("key", "123", "50.45,45.45~53.1,50.4"),
                Arguments.of("key", "123", "55.45,40.45~57.1,51.4"),
                Arguments.of("key", "123", "45.45,50.45~55.1,52.4")
        );
    }
}
