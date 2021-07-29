package com.avijitmondal.together.user.dao;

import com.avijitmondal.together.core.bean.Gender;
import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
    }

    @AfterEach
    void tearDown() {
        user = null;
    }

    @Test
    void setId() {
        Assertions.assertNull(user.getId());
        user.setId(UUID.randomUUID());
        Assertions.assertNotNull(user.getId());
    }

    @Test
    void setEmail() {
        Assertions.assertNull(user.getEmail());
        user.setEmail("email@email.com");
        Assertions.assertNotNull(user.getEmail());
    }

    @Test
    void setPhone() {
        Assertions.assertNull(user.getPhone());
        user.setPhone("1234567890");
        Assertions.assertNotNull(user.getPhone());
    }

    @Test
    void setFirstName() {
        Assertions.assertNull(user.getFirstName());
        user.setFirstName("FirstName");
        Assertions.assertNotNull(user.getFirstName());
    }

    @Test
    void setMiddleName() {
        Assertions.assertNull(user.getMiddleName());
        user.setMiddleName("MiddleName");
        Assertions.assertNotNull(user.getMiddleName());
    }

    @Test
    void setLastName() {
        Assertions.assertNull(user.getLastName());
        user.setLastName("LastName");
        Assertions.assertNotNull(user.getLastName());
    }

    @Test
    void setGender() {
        Assertions.assertNull(user.getGender());
        user.setGender(Gender.MALE);
        Assertions.assertNotNull(user.getGender());
    }

    @Test
    void setBirthday() {
        Assertions.assertNull(user.getBirthday());
        user.setBirthday(LocalDate.now());
        Assertions.assertNotNull(user.getBirthday());
    }

    @Test
    void setIsActive() {

    }

    @Test
    void setIsReported() {
    }

    @Test
    void setIsBlocked() {
    }

    @Test
    void setCreatedAt() {
        Assertions.assertNull(user.getCreatedAt());
        user.setCreatedAt(LocalDateTime.now());
        Assertions.assertNotNull(user.getCreatedAt());
    }

    @Test
    void setUpdatedAt() {
        Assertions.assertNull(user.getUpdatedAt());
        user.setUpdatedAt(LocalDateTime.now());
        Assertions.assertNotNull(user.getUpdatedAt());
    }
}
