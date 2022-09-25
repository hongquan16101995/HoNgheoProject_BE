package com.codegym.hongheo.transaction.repository;

import com.codegym.hongheo.transaction.model.entity.Transaction;
import com.codegym.hongheo.wallet.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findAllByWallet(Wallet wallet);
}
