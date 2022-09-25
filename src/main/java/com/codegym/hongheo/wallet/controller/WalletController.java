package com.codegym.hongheo.wallet.controller;

import com.codegym.hongheo.wallet.model.entity.Wallet;
import com.codegym.hongheo.wallet.service.IWalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
    @GetMapping
    public ResponseEntity<List<Wallet>> findAllWallet(){
        return new ResponseEntity<>(iWalletService.findAll(), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Wallet> createWallet(@RequestBody Wallet wallet){

        iWalletService.save(wallet);
        return new ResponseEntity<>(wallet, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Wallet> deleteWallet(@PathVariable Long id){
        Optional<Wallet> wallet = iWalletService.findById(id);
        if(!wallet.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        iWalletService.remove(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Wallet> updateWallet(@PathVariable Long id, @RequestBody Wallet newWallet){
        Optional<Wallet> walletOptional = iWalletService.findById(id);
        if(!walletOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return walletOptional.map(wallet ->{
            newWallet.setId(wallet.getId());
            return new ResponseEntity<>(iWalletService.save(newWallet), HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
