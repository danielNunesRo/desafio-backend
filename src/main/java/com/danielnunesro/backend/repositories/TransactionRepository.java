package com.danielnunesro.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.danielnunesro.backend.entities.Transactions;

public interface TransactionRepository extends JpaRepository<Transactions, Long> {

}
