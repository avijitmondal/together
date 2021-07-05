package com.avijitmondal.together.auth.repository;

import com.avijitmondal.together.auth.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	User findOneByUsername(String username);
}
