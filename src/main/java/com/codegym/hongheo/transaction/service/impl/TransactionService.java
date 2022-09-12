package com.codegym.hongheo.transaction.service.impl;

import com.codegym.hongheo.transaction.model.entity.Transaction;
import com.codegym.hongheo.transaction.repository.ITransactionRepository;
import com.codegym.hongheo.transaction.service.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionService implements ITransactionService {
    @Autowired
    private ITransactionRepository iTransactionRepository;

    @Override
    public List<Transaction> findAll() {
        return iTransactionRepository.findAll();
    }

    @Override
    public Optional<Transaction> findById(Long id) {
        return iTransactionRepository.findById(id);
    }

    @Override
    public Transaction save(Transaction transaction) {
        return iTransactionRepository.save(transaction);
    }

    @Override
    public void remove(Long id) {
        iTransactionRepository.deleteById(id);
    }
}
