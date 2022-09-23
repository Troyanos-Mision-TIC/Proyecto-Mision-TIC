package com.example.demo.controllers;

import com.example.demo.model.Employee;
import com.example.demo.model.Enterprise;
import com.example.demo.model.Transaction;
import com.example.demo.services.EnterpriseService;
import com.example.demo.services.TransactionService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
public class TransactionController {
    private final TransactionService transactionService;
    private final EnterpriseService enterpriseService;

    public TransactionController(TransactionService transactionService, EnterpriseService enterpriseService) {
        this.transactionService = transactionService;
        this.enterpriseService = enterpriseService;
    }

    @GetMapping("/movements")
    public ResponseEntity<List<Transaction>> getAll() {
        return new ResponseEntity<>(transactionService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/movements/{id}")
    public ResponseEntity<Transaction> getOne(@PathVariable("id") long id){
        try {
            Optional<Transaction> optionalTransaction = transactionService.findById(id);
            Transaction transaction = optionalTransaction.get();
            return new ResponseEntity<>(transaction, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/movements")
    public ResponseEntity<Transaction> postTransaction(@RequestBody Map<String, Object> fields) {
        try {
            Transaction newMovement = new Transaction();
            newMovement = transactionService.editTransaction(newMovement, fields);
            return new ResponseEntity<>(transactionService.save(newMovement), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/movements/{id}")
    public ResponseEntity<Transaction> deleteTransaction(@PathVariable("id") long id) {
        try {
            transactionService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IndexOutOfBoundsException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/movements/{id}")
    public ResponseEntity<Transaction> patchTransaction(@PathVariable long id, @RequestBody Map<String, Object> fields) {
        try {
            Optional<Transaction> optionalTransaction = transactionService.findById(id);
            Transaction transaction = optionalTransaction.get();
            transaction = transactionService.editTransaction(transaction, fields);
            return new ResponseEntity<>(transactionService.save(transaction), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/enterprises/{id}/movements")
    public ResponseEntity<List<Transaction>> getEnterpriseMovements(@PathVariable long id) {
        Optional<Enterprise> optionalEnterprise = enterpriseService.findById(id);
        Enterprise enterprise = optionalEnterprise.get();
        List<Transaction> movements = transactionService.findAll();
        List<Transaction> enterpriseMovements = new ArrayList<>();
        movements.forEach(transaction -> {
            Employee user = transaction.getUser();
            Enterprise userEnterprise = user.getEnterprise();
            if (userEnterprise.equals(enterprise)) {
                enterpriseMovements.add(transaction);
            }
        });
        return new ResponseEntity<>(enterpriseMovements, HttpStatus.OK);
    }
}
