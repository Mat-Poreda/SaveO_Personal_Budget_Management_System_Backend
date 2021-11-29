package Save.O.Save.O.Data.Storage.service;

import Save.O.Save.O.Data.Storage.dao.User;
import Save.O.Save.O.Data.Storage.dto.TransactionDTO;
import Save.O.Save.O.Data.Storage.dto.UserDTO;
import Save.O.Save.O.Data.Storage.repository.TransactionRepository;
import Save.O.Save.O.Data.Storage.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    UserService userService;



//    public List<TransactionDTO> getAllUserTransactions(Long user_id) {
//        if(userService.getUserById(user_id)==null){
//            return null;
//        }else{
//            UserDTO userDTO=userService.getUserById(user_id);
//
//        }
//    };

//    public TransactionDTO getUserById(Long user_id, Long id) {
//    }
//
//    public TransactionDTO createNewTransaction(TransactionDTO transaction) {
//
//    }

    public void deleteTransactionById(Long id) {
    }
}

