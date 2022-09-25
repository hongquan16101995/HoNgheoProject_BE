package com.codegym.hongheo.wallet.service;

import com.codegym.hongheo.core.model.entity.User;
import com.codegym.hongheo.core.service.ICoreService;
import com.codegym.hongheo.wallet.model.entity.Wallet;

import java.util.List;

public interface IWalletService extends ICoreService<Wallet, Long> {
    Wallet findByName(String name);

    List<Wallet> findAllByUser(User user);

    List<Wallet> findAllByUserAndStatus(User user);
}
