package com.codegym.hongheo.moneytype.service;

import com.codegym.hongheo.moneytype.model.MoneyType;
import com.codegym.hongheo.moneytype.repository.IMoneyTypeRepository;
import com.codegym.hongheo.moneytype.service.impl.IMoneyTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MoneyTypeService implements IMoneyTypeService {
    @Autowired
    private IMoneyTypeRepository iMoneyTypeRepository;

    @Override
    public List<MoneyType> findAll() {
        return iMoneyTypeRepository.findAll();
    }

    @Override
    public Optional<MoneyType> findById(Long id) {
        return iMoneyTypeRepository.findById(id);
    }

    @Override
    public MoneyType save(MoneyType moneyType) {
        return iMoneyTypeRepository.save(moneyType);
    }

    @Override
    public void remove(Long id) {
        iMoneyTypeRepository.deleteById(id);
    }
}
