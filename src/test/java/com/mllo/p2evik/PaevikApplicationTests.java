package com.mllo.p2evik;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class P2evikApplicationTests {

    @Test
    void contextLoads() {
        // This test will check if the application context loads successfully
    }

    @Test
    void applicationStartsSuccessfully() {
        P2evikApplication.main(new String[] {});
        assertTrue(true);
    }
}
