package com.laptrinhjavaweb.service.impl;

import com.laptrinhjavaweb.enums.TransactionTypeEnum;
import com.laptrinhjavaweb.service.ITransactionTypeService;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class TransactionTypeService implements ITransactionTypeService {
    @Override
    public Map<String, String> getTransactionType() {
        Map<String, String> results = new LinkedHashMap<>();
        for (TransactionTypeEnum transactionTypeEnum : TransactionTypeEnum.values()) {
            results.put(transactionTypeEnum.name(), transactionTypeEnum.getValue());
        }
        return results;
    }
}