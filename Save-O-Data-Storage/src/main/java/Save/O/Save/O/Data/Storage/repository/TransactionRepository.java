package Save.O.Save.O.Data.Storage.repository;

import Save.O.Save.O.Data.Storage.dao.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    @Query(value="" +
            "SELECT c.name, SUM(t.price) vol from transaction t left join category c on t.category_id=c.id where c.user_id=:userid and t.date between :start_date and :end_date  group by c.name"
            , nativeQuery = true)
    List<String> userCategoriesBalance(@Param("userid") Long id, @Param("start_date") LocalDate startDate, @Param("end_date") LocalDate endDate);

}
