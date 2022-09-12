package com.codegym.hongheo.transaction.controller;

import com.codegym.hongheo.category.model.dto.CategoryDTO;
import com.codegym.hongheo.category.model.entity.Category;
import com.codegym.hongheo.core.mapper.IMapper;
import com.codegym.hongheo.transaction.model.dto.TransactionDTO;
import com.codegym.hongheo.transaction.model.entity.Transaction;
import com.codegym.hongheo.transaction.service.ITransactionService;
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
    private IMapper<Transaction, TransactionDTO> iMapperTransaction;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<TransactionDTO>> findAllTransactions() {
        List<Transaction> transactions = iTransactionService.findAll();
        List<TransactionDTO> transactionDTOS = transactions.stream().map(value -> iMapperTransaction.convertToD(value))
                .collect(Collectors.toList());
        return new ResponseEntity<>(transactionDTOS, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<TransactionDTO> createTransaction(@RequestBody TransactionDTO transactionDTO) {
        Transaction transaction = iMapperTransaction.convertToE(transactionDTO);
        return new ResponseEntity<>(iMapperTransaction.convertToD(iTransactionService.save(transaction)), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<TransactionDTO> updateTransaction(@PathVariable("id") Long id,
                                                      @RequestBody TransactionDTO transactionDTO) {
        Optional<Transaction> transactionOptional = iTransactionService.findById(id);
        if (transactionOptional.isPresent()) {
            Transaction transaction = iMapperTransaction.convertToE(transactionDTO);
            transaction.setId(id);
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
}
