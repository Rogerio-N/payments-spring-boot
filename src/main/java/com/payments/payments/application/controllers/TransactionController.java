package com.payments.payments.application.controllers;

import com.payments.payments.domain.transaction.dtos.CreateTransactionDTO;
import com.payments.payments.domain.transaction.entities.Transaction;
import com.payments.payments.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/v1/transaction")
public class TransactionController {

    @Autowired
    private TransactionService service;

    @PostMapping()
    public ResponseEntity<Transaction> createTransaction(@RequestBody CreateTransactionDTO createTransactionDTO) throws Exception {
        return ResponseEntity.ok(service.createTransaction(createTransactionDTO));
    }

}
