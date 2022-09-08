package com.codegym.hongheo.wallet.service;

import com.codegym.hongheo.core.service.ICoreService;
import com.codegym.hongheo.wallet.model.entity.Wallet;

public interface IWalletService extends ICoreService<Wallet, Long> {
    Wallet findByName(String name);

}
