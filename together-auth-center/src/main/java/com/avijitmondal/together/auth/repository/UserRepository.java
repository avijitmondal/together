package com.avijitmondal.together.auth.repository;

import com.avijitmondal.together.auth.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, UUID> {

	Optional<User> findOneByUsername(String username);
}
