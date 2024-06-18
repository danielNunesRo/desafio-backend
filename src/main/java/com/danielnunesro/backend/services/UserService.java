package com.danielnunesro.backend.services;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.danielnunesro.backend.dtos.UserDTO;
import com.danielnunesro.backend.entities.UserType;
import com.danielnunesro.backend.entities.Users;
import com.danielnunesro.backend.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;
	
	public void validateTransaction(Users sender, BigDecimal amount) throws Exception {
		
		if(sender.getUserType() == UserType.LOJISTA) {
			throw new Exception("Lojistas não podem realizar transação");
		}
		
		if(sender.getBalance().compareTo(amount) < 0) {
			throw new Exception("Não há saldo suficiente");
		}
		
	}
	
	public Users findUserById(Long id) throws Exception {
		return repository.findById(id).orElseThrow(() -> new Exception("Usuário não encontrado"));
	}
	
	public Users create(Users user) {
		return repository.save(user);
	}
	
	public Users createUser(UserDTO user) {
		Users newUser = new Users(user);
		repository.save(newUser);
		return newUser;
	}
	
	public List<Users> findAll() {
		return repository.findAll();
	}
}
