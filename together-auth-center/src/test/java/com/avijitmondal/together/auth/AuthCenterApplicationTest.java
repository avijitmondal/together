package com.avijitmondal.together.auth;

import com.avijitmondal.together.auth.controller.SecuredController;
import com.avijitmondal.together.auth.controller.UnsecuredController;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TogetherAuthCenterApplicationTest {

    @Autowired
    private SecuredController securedController;
    @Autowired
    private UnsecuredController unsecuredController;

    @Test
    public void contextLoads() {
        Assertions.assertThat(securedController).isNotNull();
        Assertions.assertThat(unsecuredController).isNotNull();
    }
}