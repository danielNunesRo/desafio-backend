package com.danielnunesro.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.danielnunesro.backend.dtos.NotificationDTO;
import com.danielnunesro.backend.entities.Users;

@Service
public class NotificationService {
	
	@Autowired
	private RestTemplate restTemplate;
	
	public void notificar(Users user, String message) throws Exception {
		String email = user.getEmail();
		NotificationDTO notificationRequest = new NotificationDTO(email, "message");
		
		ResponseEntity<String> notificationResponse= restTemplate.postForEntity("https://util.devi.tools/api/v1/notify)", notificationRequest, String.class);
		
		if(!(notificationResponse.getStatusCode() == HttpStatus.OK)) {
			throw new Exception("Notificação falhou!");
		}
	}
	
	
}
