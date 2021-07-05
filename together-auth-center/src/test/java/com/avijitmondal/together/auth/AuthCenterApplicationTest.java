package com.avijitmondal.together.auth;

import com.avijitmondal.together.auth.controller.AuthenticationController;
import com.avijitmondal.together.auth.controller.SecuredController;
import com.avijitmondal.together.auth.controller.UnsecuredController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AuthCenterApplicationTest {

    @Autowired
    private AuthenticationController authenticationController;

    @Autowired
    private SecuredController securedController;

    @Autowired
    private UnsecuredController unsecuredController;

    @Test
    void contextLoads() {
        Assertions.assertNotNull(authenticationController);
        Assertions.assertNotNull(securedController);
        Assertions.assertNotNull(unsecuredController);
    }
}
