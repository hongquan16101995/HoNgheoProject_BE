package com.codegym.hongheo.transaction.controller;

import com.codegym.hongheo.category.model.dto.CategoryDTO;
import com.codegym.hongheo.category.model.entity.Category;
import com.codegym.hongheo.core.mapper.IMapper;
import com.codegym.hongheo.transaction.model.dto.TransactionDTO;
import com.codegym.hongheo.transaction.model.entity.Transaction;
import com.codegym.hongheo.transaction.service.ITransactionService;
import com.codegym.hongheo.wallet.model.Wallet;
import com.codegym.hongheo.wallet.service.IWalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
    @Autowired
    private ITransactionService iTransactionService;

    @Autowired
    private IWalletService iWalletService;

    @Autowired
    private IMapper<Transaction, TransactionDTO> iMapperTransaction;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<TransactionDTO>> findAllTransactions() {
        List<Transaction> transactions = iTransactionService.findAll();
        List<TransactionDTO> transactionDTOS = transactions.stream().map(value -> iMapperTransaction.convertToD(value))
                .collect(Collectors.toList());
        return new ResponseEntity<>(transactionDTOS, HttpStatus.OK);
    }

    @RequestMapping(value = "/{wallet_id}", method = RequestMethod.POST)
    public ResponseEntity<TransactionDTO> createTransaction(@PathVariable("wallet_id") Long walletId,
                                                            @RequestBody TransactionDTO transactionDTO) {
        Optional<Wallet> walletOptional = iWalletService.findById(walletId);
        Transaction transaction = iMapperTransaction.convertToE(transactionDTO);
        if (walletOptional.isPresent()) {
            transaction.setWallet(walletOptional.get());
            iTransactionService.save(transaction);
            return new ResponseEntity<>(iMapperTransaction.convertToD(iTransactionService.save(transaction)), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<TransactionDTO> updateTransaction(@PathVariable("id") Long id,
                                                            @RequestBody TransactionDTO transactionDTO) {
        Optional<Transaction> transactionOptional = iTransactionService.findById(id);
        if (transactionOptional.isPresent()) {
            Transaction transaction = iMapperTransaction.convertToE(transactionDTO);
            transaction.setId(id);
            transaction.setWallet(transactionOptional.get().getWallet());
            return new ResponseEntity<>(iMapperTransaction.convertToD(iTransactionService.save(transaction)), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteTransaction(@PathVariable("id") Long id) {
        Optional<Transaction> transactionOptional = iTransactionService.findById(id);
        if (transactionOptional.isPresent()) {
            iTransactionService.remove(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<TransactionDTO> findTransactionById(@PathVariable("id") Long id) {
        Optional<Transaction> transactionOptional = iTransactionService.findById(id);
        return transactionOptional.map(category -> new ResponseEntity<>(iMapperTransaction.convertToD(category), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @RequestMapping(value = "/wallet/{wallet_id}", method = RequestMethod.GET)
    public ResponseEntity<List<TransactionDTO>> findTransactionByWallet(@PathVariable("wallet_id") Long walletId) {
        Optional<Wallet> walletOptional = iWalletService.findById(walletId);
        if (walletOptional.isPresent()) {
            List<Transaction> transactions = iTransactionService.findAllTransactionByWallet(walletOptional.get());
            List<TransactionDTO> transactionDTOS = transactions.stream().map(value -> iMapperTransaction.convertToD(value))
                    .collect(Collectors.toList());
            return new ResponseEntity<>(transactionDTOS, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
}
