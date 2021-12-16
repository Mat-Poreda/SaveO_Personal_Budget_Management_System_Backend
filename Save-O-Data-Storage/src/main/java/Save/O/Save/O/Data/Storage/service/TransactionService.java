package Save.O.Save.O.Data.Storage.service;

import Save.O.Save.O.Data.Storage.dao.Category;
import Save.O.Save.O.Data.Storage.dao.Transaction;
import Save.O.Save.O.Data.Storage.dao.User;
import Save.O.Save.O.Data.Storage.dto.TransactionDTO;
import Save.O.Save.O.Data.Storage.repository.CategoryRepository;
import Save.O.Save.O.Data.Storage.repository.TransactionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;

@Service
public class TransactionService {
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    UserService userService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    private ModelMapper modelMapper;

    public TransactionDTO convertTransactionToDto(Transaction transaction) {
        TransactionDTO transactionDTO = modelMapper.map(transaction, TransactionDTO.class);
        return transactionDTO;
    }

    public Transaction convertTransactionToEntity(TransactionDTO transactionDTO) throws ParseException {
        Transaction transaction = modelMapper.map(transactionDTO, Transaction.class);
        return transaction;
    }

    public Transaction createNewTransaction(Long userId,TransactionDTO transactionDTO) {
        try{
            Category category=categoryService.getCategory(transactionDTO.getCategoryId(), userId);
            User user=userService.getUserById(userId);
            Transaction transaction= new Transaction();
            transaction.setDate(transactionDTO.getDate());
            transaction.setDescription(transactionDTO.getDescription());
            transaction.setPrice(transactionDTO.getPrice());
            transaction=transactionRepository.save(transaction);
            var transactions=category.getTransactions();
            transactions.add(transaction);
            category.setTransactions(transactions);
            category=categoryRepository.save(category);
            System.out.println("apis is saving. cat name= "+ category.getName());
            return transaction;
        }
        catch (Exception e){
        return null;
        }


    }

    public void deleteTransactionById(Long id) {
        if(transactionRepository.findById(id).isPresent()){
            transactionRepository.delete(transactionRepository.findById(id).get());
        }
    }

    public TransactionDTO getTransactionDTOById(Long userId, Long transactionId) {
        if(transactionRepository.findById(transactionId).isPresent()){
            return convertTransactionToDto(transactionRepository.findById(transactionId).get());
        }else{
            return null;
        }
    }
}

