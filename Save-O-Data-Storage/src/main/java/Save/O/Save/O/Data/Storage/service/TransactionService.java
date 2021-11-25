package Save.O.Save.O.Data.Storage.service;

import Save.O.Save.O.Data.Storage.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {
    @Autowired
    TransactionRepository transactionRepository;



}

