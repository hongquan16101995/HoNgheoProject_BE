package com.codegym.hongheo.core.mapper;

import com.codegym.hongheo.wallet.model.dto.WalletDTO;
import com.codegym.hongheo.wallet.model.entity.Wallet;
import org.mapstruct.Mapper;

@Mapper
public interface IWalletMapper extends EntityMapper<WalletDTO, Wallet> {
}
