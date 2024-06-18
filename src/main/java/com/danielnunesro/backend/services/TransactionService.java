package com.danielnunesro.backend.services;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.danielnunesro.backend.dtos.TransactionDTO;
import com.danielnunesro.backend.entities.Transactions;
import com.danielnunesro.backend.entities.Users;
import com.danielnunesro.backend.repositories.TransactionRepository;

@Service
public class TransactionService {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private TransactionRepository transactionRepository;
	
	@Autowired
	private NotificationService notificacaoService;
	
	@Autowired
	private RestTemplate restTemplate;
	
	public Transactions createTransaction(TransactionDTO transaction) throws Exception {
		
		Users sender = userService.findUserById(transaction.senderId());
		Users receiver = userService.findUserById(transaction.reciverId());
		
		userService.validateTransaction(sender, transaction.value());
		
		/**boolean authorized = authorizeTransaction(sender, transaction.value());
		if(!authorized) {
			throw new Exception("A transação não foi autorizada!");
		}*/
		
		Transactions transactions = new Transactions();
		transactions.setAmount(transaction.value());
		transactions.setSender(sender);
		transactions.setReceiver(receiver);
		transactions.setTimestamp(LocalDateTime.now());
		
		sender.setBalance(sender.getBalance().subtract(transaction.value()));
		receiver.setBalance(receiver.getBalance().add(transaction.value()));
		
		transactionRepository.save(transactions);
		userService.create(sender);
		userService.create(receiver);
		
		notificacaoService.notificar(sender, "Transação realizada com sucesso!");
		notificacaoService.notificar(receiver, "Transação recebida com sucesso!");
		
		
		return transactions;
		
		
		
	}
	
	public boolean authorizeTransaction(Users sender, BigDecimal value) {
		ResponseEntity<Map> response = restTemplate.getForEntity("https://util.devi.tools/api/v2/authorize", Map.class);
		
		if(response.getStatusCode() == HttpStatus.OK) {
			String message = (String) response.getBody().get("message");
			return "Autorizado".equalsIgnoreCase(message);
		} else return false;
	}
	
}
