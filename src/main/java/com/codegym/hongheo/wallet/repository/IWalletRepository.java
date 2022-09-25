package com.codegym.hongheo.wallet.repository;

import com.codegym.hongheo.wallet.model.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IWalletRepository extends JpaRepository<Wallet, Long> {
    Optional<Wallet> findByName(String name);

}
