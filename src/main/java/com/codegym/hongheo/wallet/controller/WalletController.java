package com.codegym.hongheo.wallet.controller;

import com.codegym.hongheo.core.mapper.IWalletMapper;
import com.codegym.hongheo.core.model.entity.User;
import com.codegym.hongheo.core.service.user.IUserService;
import com.codegym.hongheo.wallet.model.dto.WalletDTO;
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

    @Autowired
    private IUserService iUserService;

    @Autowired
    private IWalletMapper iWalletMapper;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<WalletDTO> findAllWallets(@PathVariable("id") Long id) {
        Optional<Wallet> walletOptional = iWalletService.findById(id);
        if (walletOptional.isPresent()) {
            WalletDTO walletDTO = iWalletMapper.toDto(walletOptional.get());
            return new ResponseEntity<>(walletDTO, HttpStatus.OK);
        } else  {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<WalletDTO>> findAllWallet() {
        List<Wallet> wallets = iWalletService.findAll();
        List<WalletDTO> walletDTOS = iWalletMapper.toDto(wallets);
        return new ResponseEntity<>(walletDTOS, HttpStatus.OK);
    }

    @RequestMapping(value = "/{user_id}", method = RequestMethod.POST)
    public ResponseEntity<WalletDTO> createWallet(@RequestBody WalletDTO walletDTO,
                                               @PathVariable("user_id") Long userId) {
        Optional<User> userOptional = iUserService.findById(userId);
        if (userOptional.isPresent()) {
            Wallet wallet = iWalletMapper.toEntity(walletDTO);
            wallet.setUser(userOptional.get());
            return new ResponseEntity<>(iWalletMapper.toDto(iWalletService.save(wallet)), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<WalletDTO> updateWallet(@PathVariable("id") Long id,
                                               @RequestBody WalletDTO walletDTO) {
        Optional<Wallet> walletOptional = iWalletService.findById(id);
        if (walletOptional.isPresent()) {
            Wallet wallet = iWalletMapper.toEntity(walletDTO);
            wallet.setId(id);
            wallet.setUser(walletOptional.get().getUser());
            return new ResponseEntity<>(iWalletMapper.toDto(iWalletService.save(wallet)),HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteWallet(@PathVariable Long id) {
        Optional<Wallet> wallet = iWalletService.findById(id);
        if (wallet.isPresent()) {
            iWalletService.remove(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/user/{user_id}", method = RequestMethod.GET)
    public ResponseEntity<List<WalletDTO>> findAllByUser(@PathVariable("user_id") Long userId) {
        Optional<User> userOptional = iUserService.findById(userId);
        if (userOptional.isPresent()) {
            List<Wallet> wallets = iWalletService.findAllByUser(userOptional.get());
            List<WalletDTO> walletDTOS = iWalletMapper.toDto(wallets);
            return new ResponseEntity<>(walletDTOS, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
