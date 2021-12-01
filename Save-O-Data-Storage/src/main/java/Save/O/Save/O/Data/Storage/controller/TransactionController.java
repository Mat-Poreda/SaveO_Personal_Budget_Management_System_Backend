package Save.O.Save.O.Data.Storage.controller;

import Save.O.Save.O.Data.Storage.dao.Transaction;
import Save.O.Save.O.Data.Storage.dto.TransactionDTO;
import Save.O.Save.O.Data.Storage.dto.UserDTO;
import Save.O.Save.O.Data.Storage.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
    @Autowired
    TransactionService transactionService;

//    @GetMapping
//    public List<TransactionDTO> getAllTransactionsFromCategory(@PathVariable(name="category_id") Long category_id){
//        return transactionService.getAllUserTransactions(user_id);
//    }
//
    @GetMapping("/{user_id}/{transactionId}")
    public TransactionDTO getTransactionById(@PathVariable(name="user_id") Long userId, @PathVariable(name="transactionId")Long transactionId){
        return transactionService.getTransactionDTOById(userId, transactionId);
    }

    @PostMapping("/{user_id}")
    public Transaction createNewTransaction(@PathVariable(name="user_id") Long userId, @RequestBody TransactionDTO transaction){
        System.out.println("api is working");
        return transactionService.createNewTransaction(userId, transaction);
    }

    @DeleteMapping("/{id}")
    public void deleteTransactionById(@PathVariable(name="id")Long id){
        transactionService.deleteTransactionById(id);
    }

}
