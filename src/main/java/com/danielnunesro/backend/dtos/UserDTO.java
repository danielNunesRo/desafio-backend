package com.danielnunesro.backend.dtos;

import java.math.BigDecimal;

import com.danielnunesro.backend.entities.UserType;

public record UserDTO(String firstName, String lastName, String document, BigDecimal balance, String email, String password, UserType userType) {

}
