package com.rst.taxiprovider;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class ProviderApplicationTests {
    @Autowired
    private ProviderApplication providerApplication;

    @Test
    void contextLoads(){
        assertNotNull(providerApplication);
    }
}
