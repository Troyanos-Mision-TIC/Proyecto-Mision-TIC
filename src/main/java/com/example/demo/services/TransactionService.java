package com.example.demo.services;

import com.example.demo.model.Employee;
import com.example.demo.model.Transaction;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.example.demo.repositories.TransactionRepository;
import com.example.demo.repositories.EmployeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final EmployeeRepository userRepository;

    public TransactionService(TransactionRepository transactionRepository, EmployeeRepository userRepository) {
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
    }

    public List<Transaction> findAll() {
        return transactionRepository.findAll();
    }

    public Optional<Transaction> findById(long id) {
        return transactionRepository.findById(id);
    }

    public Transaction save(Transaction movimiento) {
        return transactionRepository.save(movimiento);
    }

    public Transaction editTransaction(Transaction movement, Map<String, Object> fields) {
        fields.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(Transaction.class, key);
            if (field != null) {
                field.setAccessible(true);
                if (key.equals("user")) {
                    Employee user = userRepository.findById((long) (int) value).get();
                    ReflectionUtils.setField(field, movement, user);
                } else {
                    ReflectionUtils.setField(field, movement, value);
                }
            }
        });
        return movement;
    }

    public void deleteById(long id) {
        transactionRepository.deleteById(id);
    }
}
