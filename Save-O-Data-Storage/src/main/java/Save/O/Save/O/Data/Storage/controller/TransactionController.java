package Save.O.Save.O.Data.Storage.controller;

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
//    @GetMapping("/{id}")
//    public TransactionDTO getTransactionById(@PathVariable(name="user_id") Long user_id, @PathVariable(name="id")Long id){
//        return transactionService.getUserById(user_id, id);
//    }
//
//    @PostMapping()
//    public TransactionDTO createNewTransaction(TransactionDTO transaction){
//        return transactionService.createNewTransaction(transaction);
//    }

    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable(name="id")Long id){
        transactionService.deleteTransactionById(id);
    }

}
