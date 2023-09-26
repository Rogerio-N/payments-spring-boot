package com.payments.payments.services;

import com.payments.payments.domain.shared.validations.ValidationResult;
import com.payments.payments.domain.transaction.dtos.CreateTransactionDTO;
import com.payments.payments.domain.transaction.entities.Transaction;
import com.payments.payments.domain.transaction.validations.ValidationChain;
import com.payments.payments.domain.user.entities.User;
import com.payments.payments.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {
    @Autowired
    private ValidationChain<Transaction> validationChain;
    @Autowired
    private UserService userService;
    @Autowired
    private TransactionRepository repository;

    public void createTransaction(CreateTransactionDTO transactionDTO) throws Exception {
//        Transaction transaction = this.mapDTOtoEntity(transactionDTO);
        Transaction transaction = new Transaction();
        ValidationResult validationResult = validationChain.validate(transaction);

        if(!validationResult.getSuccess()){
            throw new Exception(validationResult.getErrors().toString());
        }

//        userService.decreaseUserBalance(transaction.getSender(), transaction.getValue());
//        userService.increaseUserBalance(transaction.getReceiver(), transaction.getValue());
    }

//    private Transaction mapDTOtoEntity(CreateTransactionDTO transactionDTO) throws Exception {
//        User receiver = userService.findUserById(transactionDTO.receiverId());
//        User sender = userService.findUserById(transactionDTO.senderId());
//        return Transaction.builder()
//                .sender(sender)
//                .receiver(receiver)
//                .value(transactionDTO.value())
//                .build();
//    }
}
