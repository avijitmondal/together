package com.avijitmondal.together.auth.repository;

import com.avijitmondal.together.auth.model.UserTokenSession;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Optional;
import java.util.UUID;

@DataJpaTest
class UserTokenSessionRepositoryTest {

    @Autowired
    private UserTokenSessionRepository userTokenSessionRepository;

    @Test
    void findOneByUsernameNotFound() {
        Optional<UserTokenSession> userNotFound = userTokenSessionRepository.findOneByUsername("wrong-username");
        Assertions.assertTrue(userNotFound.isEmpty());
    }

    @Test
    void findOneByUsernameFound() {
        userTokenSessionRepository.save(new UserTokenSession(UUID.randomUUID(), "user1", "abcdefg", "swssion1", LocalDateTime.now().toEpochSecond(ZoneOffset.UTC)));
        Optional<UserTokenSession> userTokenSessionFound = userTokenSessionRepository.findOneByUsername("user1");
        Assertions.assertTrue(userTokenSessionFound.isPresent());
    }
}
