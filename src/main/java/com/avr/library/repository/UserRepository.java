package com.avr.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.avr.library.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByUsername(String username);

}
