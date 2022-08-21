package com.codegym.hongheo.wallet.repository;

import com.codegym.hongheo.wallet.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IWalletRepository extends JpaRepository<Wallet, Long> {
}
