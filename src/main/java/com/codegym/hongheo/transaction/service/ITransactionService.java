package com.codegym.hongheo.transaction.service;

import com.codegym.hongheo.core.service.ICoreService;
import com.codegym.hongheo.transaction.model.entity.Transaction;
import com.codegym.hongheo.wallet.model.entity.Wallet;

import java.time.LocalDateTime;
import java.util.List;

public interface ITransactionService extends ICoreService<Transaction, Long> {
    List<Transaction> findAllTransactionByWallet(Wallet wallet);

    List<Transaction> findAllTransactionByTime(LocalDateTime start, LocalDateTime end);

    boolean checkMoney(Transaction transaction, Wallet wallet);
}
