package com.danielnunesro.backend.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.danielnunesro.backend.entities.Users;

public interface UserRepository extends JpaRepository<Users, Long> {
	
	Optional<Users> findUserByDocument(String document);
	
	
}
