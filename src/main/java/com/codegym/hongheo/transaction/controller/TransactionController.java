package com.codegym.hongheo.transaction.controller;

import com.codegym.hongheo.transaction.model.Transaction;
import com.codegym.hongheo.transaction.service.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
    @Autowired
    private ITransactionService iTransactionService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<Transaction> create(@RequestBody Optional<Transaction> transaction) {
        return transaction.map(value -> new ResponseEntity<>(iTransactionService.save(value), HttpStatus.CREATED)).orElseGet(()
                -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }
}
