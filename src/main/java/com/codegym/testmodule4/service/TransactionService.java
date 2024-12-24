package com.codegym.testmodule4.service;

import com.codegym.testmodule4.model.Transaction;
import com.codegym.testmodule4.repository.ITransactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionService implements ITransactionService{
    private final ITransactionRepository iTransactionRepository;
    public TransactionService(ITransactionRepository iTransactionRepository){
        this.iTransactionRepository = iTransactionRepository;
    }
    @Override
    public Iterable<Transaction> findAll() {
        return iTransactionRepository.findAll();
    }

    @Override
    public Optional<Transaction> findById(Long id) {
        return iTransactionRepository.findById(id);
    }

    @Override
    public void save(Transaction transaction) {
        iTransactionRepository.save(transaction);
    }

    @Override
    public void delete(Long id) {
        iTransactionRepository.deleteById(id);
    }

    public List<Transaction> findByCustomerName(String customerName) {
        return iTransactionRepository.findByCustomerNameContainingIgnoreCase(customerName);
    }
}
