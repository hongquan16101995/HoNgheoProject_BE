package com.codegym.hongheo.transaction.controller;

import com.codegym.hongheo.category.model.dto.CategoryDTO;
import com.codegym.hongheo.core.mapper.ICategoryMapper;
import com.codegym.hongheo.core.mapper.ITransactionMapper;
import com.codegym.hongheo.transaction.model.dto.Search;
import com.codegym.hongheo.transaction.model.dto.TransactionDTO;
import com.codegym.hongheo.transaction.model.entity.Transaction;
import com.codegym.hongheo.transaction.service.ITransactionService;
import com.codegym.hongheo.wallet.model.entity.Wallet;
import com.codegym.hongheo.wallet.service.IWalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/transactions")
public class TransactionController {
    @Autowired
    private ITransactionService iTransactionService;

    @Autowired
    private IWalletService iWalletService;

    @Autowired
    private ITransactionMapper iTransactionMapper;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<TransactionDTO>> findAllTransactions() {
        List<Transaction> transactions = iTransactionService.findAll();
        List<TransactionDTO> transactionDTOS = iTransactionMapper.toDto(transactions);
        return new ResponseEntity<>(transactionDTOS, HttpStatus.OK);
    }

    @RequestMapping(value = "/{wallet_id}", method = RequestMethod.POST)
    public ResponseEntity<TransactionDTO> createTransaction(@PathVariable("wallet_id") Long walletId,
                                                            @RequestBody TransactionDTO transactionDTO) {
        Optional<Wallet> walletOptional = iWalletService.findById(walletId);
        Transaction transaction = iTransactionMapper.toEntity(transactionDTO);
        if (walletOptional.isPresent()) {
            if (iTransactionService.checkMoney(transaction, walletOptional.get())) {
                walletOptional.get().setMoney(walletOptional.get().getMoney() - transaction.getTotal());
                transaction.setWallet(walletOptional.get());
                return new ResponseEntity<>(iTransactionMapper.toDto(iTransactionService.save(transaction)), HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<TransactionDTO> updateTransaction(@PathVariable("id") Long id,
                                                            @RequestBody TransactionDTO transactionDTO) {
        Optional<Transaction> transactionOptional = iTransactionService.findById(id);
        if (transactionOptional.isPresent()) {
            if (iTransactionService.checkMoney(transactionOptional.get(), transactionOptional.get().getWallet())) {
                Transaction transaction = iTransactionMapper.toEntity(transactionDTO);
                transaction.setId(id);
                transactionOptional.get().getWallet().setMoney(transactionOptional.get().getWallet().getMoney() + transactionOptional.get().getTotal() - transaction.getTotal());
                transaction.setWallet(transactionOptional.get().getWallet());
                return new ResponseEntity<>(iTransactionMapper.toDto(iTransactionService.save(transaction)), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
            }
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
        return transactionOptional.map(category -> new ResponseEntity<>(iTransactionMapper.toDto(category), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @RequestMapping(value = "/wallet/{wallet_id}", method = RequestMethod.GET)
    public ResponseEntity<List<TransactionDTO>> findTransactionByWallet(@PathVariable("wallet_id") Long walletId) {
        Optional<Wallet> walletOptional = iWalletService.findById(walletId);
        if (walletOptional.isPresent()) {
            List<Transaction> transactions = iTransactionService.findAllTransactionByWallet(walletOptional.get());
            List<TransactionDTO> transactionDTOS = iTransactionMapper.toDto(transactions);
            return new ResponseEntity<>(transactionDTOS, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public ResponseEntity<List<TransactionDTO>> searchByDate(@RequestBody Search search) {
        LocalDateTime start = LocalDateTime.parse(search.getStart(), DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        LocalDateTime end = LocalDateTime.parse(search.getEnd(), DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        List<Transaction> transactions = iTransactionService.findAllTransactionByTime(start, end);
        if (!transactions.isEmpty()) {
            List<TransactionDTO> transactionDTOS = iTransactionMapper.toDto(transactions);
            return new ResponseEntity<>(transactionDTOS, HttpStatus.OK);
        } else  {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/demo", method = RequestMethod.GET)
    public ResponseEntity<Void> demo() {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
