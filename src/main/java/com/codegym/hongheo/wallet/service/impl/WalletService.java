package com.codegym.hongheo.wallet.service.impl;

import com.codegym.hongheo.wallet.model.entity.Wallet;
import com.codegym.hongheo.wallet.repository.IWalletRepository;
import com.codegym.hongheo.wallet.service.IWalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WalletService implements IWalletService {
    @Autowired
    IWalletRepository iWalletRepository;

    @Override
    public List<Wallet> findAll() {
        return iWalletRepository.findAll();
    }

    @Override
    public Optional<Wallet> findById(Long id) {
        return iWalletRepository.findById(id);
    }

    @Override
    public Wallet save(Wallet wallet) {
        return iWalletRepository.save(wallet);
    }

    @Override
    public void remove(Long id) {
        iWalletRepository.deleteById(id);
    }

    @Override
    public Wallet findByName(String name) {
        Optional<Wallet> walletOptional = iWalletRepository.findByName(name);
        return walletOptional.orElse(null);
    }
}
