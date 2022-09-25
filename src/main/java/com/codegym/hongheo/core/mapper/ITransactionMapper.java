package com.codegym.hongheo.core.mapper;

import com.codegym.hongheo.transaction.model.dto.TransactionDTO;
import com.codegym.hongheo.transaction.model.entity.Transaction;
import org.mapstruct.Mapper;

@Mapper
public interface ITransactionMapper extends EntityMapper<TransactionDTO, Transaction> {
}
