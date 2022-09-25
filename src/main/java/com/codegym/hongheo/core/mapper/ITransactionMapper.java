package com.codegym.hongheo.core.mapper;

import com.codegym.hongheo.category.model.entity.Category;
import com.codegym.hongheo.transaction.model.dto.TransactionDTO;
import com.codegym.hongheo.transaction.model.entity.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper
public interface ITransactionMapper extends EntityMapper<TransactionDTO, Transaction> {
}
