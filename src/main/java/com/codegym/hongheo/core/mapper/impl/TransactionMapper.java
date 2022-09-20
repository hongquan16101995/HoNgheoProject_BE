package com.codegym.hongheo.core.mapper.impl;

import com.codegym.hongheo.category.model.dto.CategoryDTO;
import com.codegym.hongheo.category.model.entity.Category;
import com.codegym.hongheo.core.mapper.IMapper;
import com.codegym.hongheo.transaction.model.dto.TransactionDTO;
import com.codegym.hongheo.transaction.model.entity.Transaction;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TransactionMapper implements IMapper<Transaction, TransactionDTO> {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private IMapper<Category, CategoryDTO> iMapperCategory;

    @Override
    public Transaction convertToE(TransactionDTO transactionDTO) {
        Transaction transaction = modelMapper.map(transactionDTO, Transaction.class);
        Set<Category> categories = transactionDTO.getCategoryDTOSet()
                .stream().map(value -> iMapperCategory.convertToE(value))
                .collect(Collectors.toSet());
        transaction.setCategories(categories);
        return transaction;
    }

    @Override
    public TransactionDTO convertToD(Transaction transaction) {
        TransactionDTO transactionDTO = modelMapper.map(transaction, TransactionDTO.class);
        Set<CategoryDTO> categoryDTOSet = transaction.getCategories()
                .stream().map(value -> iMapperCategory.convertToD(value))
                .collect(Collectors.toSet());
        transactionDTO.setCategoryDTOSet(categoryDTOSet);
        return transactionDTO;
    }
}
