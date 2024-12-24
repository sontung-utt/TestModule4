package com.codegym.testmodule4.repository;

import com.codegym.testmodule4.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByCustomerNameContainingIgnoreCase(String customerName);
}
