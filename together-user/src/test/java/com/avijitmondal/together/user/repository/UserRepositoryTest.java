package com.avijitmondal.together.user.repository;

import com.avijitmondal.together.user.dao.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    void findByEmail(){
        Optional<User> optionalUser = userRepository.findByEmail("email@email.com");
        Assertions.assertTrue(optionalUser.isEmpty());
    }

    @Test
    void findByIdNotPresent() {
        Optional<User> optionalUser = userRepository.findById(UUID.randomUUID());
        Assertions.assertTrue(optionalUser.isEmpty());
    }

    @Test
    void findAll() {
        List<User> users = userRepository.findAll();
        Assertions.assertTrue(users.isEmpty());
    }
}