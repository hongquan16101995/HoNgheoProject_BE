package com.codegym.hongheo.transaction.service.impl;

import com.codegym.hongheo.transaction.model.entity.Transaction;
import com.codegym.hongheo.transaction.repository.ITransactionRepository;
import com.codegym.hongheo.transaction.service.ITransactionService;
import com.codegym.hongheo.wallet.model.entity.Wallet;
import com.codegym.hongheo.wallet.service.IWalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
        transaction.setTime(LocalDateTime.now());
        return iTransactionRepository.save(transaction);
    }

    @Override
    public void remove(Long id) {
        iTransactionRepository.deleteById(id);
    }

    @Override
    public List<Transaction> findAllTransactionByWallet(Wallet wallet) {
        return iTransactionRepository.findAllByWallet(wallet);
    }

    @Override
    public List<Transaction> findAllTransactionByTime(LocalDateTime start, LocalDateTime end) {
        return iTransactionRepository.findAllByTimeBetween(start, end);
    }
}
