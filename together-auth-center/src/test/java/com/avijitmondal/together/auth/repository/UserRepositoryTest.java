package com.avijitmondal.together.auth.repository;

import com.avijitmondal.together.auth.model.Authority;
import com.avijitmondal.together.auth.model.User;
import com.avijitmondal.together.auth.model.bean.AuthorityRole;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void findOneByUsernameUserNotFound() {
        Optional<User> userNotFound = userRepository.findOneByUsername("wrong-username");
        Assertions.assertTrue(userNotFound.isEmpty());
    }

    @Test
    void findOneByUsernameUserFound() {
        Authority authority = new Authority();
        authority.setId(1L);
        authority.setRole(AuthorityRole.ROLE_USER);

        User user = new User();
        user.setId(UUID.randomUUID());
        user.setUsername("user1");
        user.setEmail("user1@email.com");
        user.setEnabled(true);
        user.setAuthorities(Set.of(authority));
        user.setActivationKey("activation-key1");
        user.setPassword("secret-password");
        user.setResetPasswordKey("reset-password-key1");

        userRepository.save(user);

        Optional<User> userNotFound = userRepository.findOneByUsername(user.getUsername());
        Assertions.assertTrue(userNotFound.isPresent());
    }
}
