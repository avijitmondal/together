package com.avijitmondal.together.user.service;

import com.avijitmondal.together.core.dto.ResponseDTO;
import com.avijitmondal.together.core.exception.TogetherException;
import com.avijitmondal.together.user.dao.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    void findAll() {
        ResponseDTO<List<User>> responseDTO = userService.findAll(Pageable.unpaged());
        Assertions.assertTrue(responseDTO.getContent().isEmpty());
    }

    @Test
    void findByIdNotFound() throws TogetherException {
        Optional<User> optionalUser = userService.findById(UUID.randomUUID().toString());
        Assertions.assertTrue(optionalUser.isEmpty());
    }

    @Test
    void delete() {

    }

    @Test
    void save() {
    }

    @Test
    void findByEmailNotExists() throws TogetherException {
        Optional<User> optionalUser = userService.findByEmail("email@email.com");
        Assertions.assertTrue(optionalUser.isEmpty());
    }

    @Test
    void isNotExists() throws TogetherException {
        boolean userExists = userService.isExists(UUID.randomUUID().toString());
        Assertions.assertFalse(userExists);
    }
}