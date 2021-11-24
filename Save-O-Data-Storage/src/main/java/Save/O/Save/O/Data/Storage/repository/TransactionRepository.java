package Save.O.Save.O.Data.Storage.repository;

import Save.O.Save.O.Data.Storage.dao.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
