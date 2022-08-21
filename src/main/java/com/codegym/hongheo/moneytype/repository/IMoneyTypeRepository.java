package com.codegym.hongheo.moneytype.repository;

import com.codegym.hongheo.moneytype.model.MoneyType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMoneyTypeRepository  extends JpaRepository<MoneyType, Long> {
}
