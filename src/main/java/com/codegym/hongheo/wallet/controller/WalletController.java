package com.codegym.hongheo.wallet.controller;

import com.codegym.hongheo.wallet.model.Wallet;
import com.codegym.hongheo.wallet.service.IWalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/wallets")
public class WalletController {
    @Autowired
    private IWalletService iWalletService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Wallet> findAllWallets(@PathVariable("id") Long id) {
        Optional<Wallet> wallet = iWalletService.findById(id);
        return wallet.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(()
                -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
