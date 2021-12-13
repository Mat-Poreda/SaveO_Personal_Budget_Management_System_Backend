package Save.O.Save.O.Data.Storage.repository;

import Save.O.Save.O.Data.Storage.dao.Category;
import Save.O.Save.O.Data.Storage.dao.User;
import Save.O.Save.O.Data.Storage.dto.CategoryDTO;
import Save.O.Save.O.Data.Storage.dto.ReportDTO;
import Save.O.Save.O.Data.Storage.dto.ReportRequestDTO;
import Save.O.Save.O.Data.Storage.enums.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query(value="SELECT c.id, SUM(t.price) as Sum, COUNT(t.id) as Count, AVG(t.price) as Avg from transaction t left join category c on t.category_id=c.id where c.id=:id  and t.date between :startDate and :endDate  group by c.id"
            , nativeQuery = true)
    String getUserCategoriesStats(@Param("id") Long id, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query(value= """
            SELECT 'Balance' as name, SUM(coalesce(i.price,0))-SUM(coalesce(e.price,0))-SUM(coalesce(d.price,0)) as value 
            FROM category c
            
            LEFT JOIN transaction i
            on c.id=i.category_id
            and c.type='INCOME'
            and i.date BETWEEN :START_DATE and :END_DATE
                        
            LEFT JOIN transaction e
            on c.id=e.category_id
            and c.type='EXPENSE'
            and e.date BETWEEN :START_DATE and :END_DATE
                        
            LEFT JOIN transaction d
            on c.id=d.category_id
            and c.type='DEPOSIT'
            and d.date BETWEEN :START_DATE and :END_DATE
            
            WHERE c.user_id=:userId
            
            UNION ALL
            
            SELECT c.type as name, SUM(coalesce(t.price,0)) as value 
            FROM category c
            
            LEFT JOIN transaction t
            on c.id=t.category_id
            and t.date BETWEEN :START_DATE and :END_DATE
            
            WHERE c.user_id=:userId
            GROUP BY c.type
            
            """, nativeQuery = true)
    String[] getBalance(@Param("userId") Long userId, @Param("START_DATE") LocalDate startDate, @Param("END_DATE") LocalDate endDate);


    @Query(value= """
            SELECT c.type as name, SUM(coalesce(t.price,0)) as value FROM category c
            LEFT JOIN transaction t
            on c.id=t.category_id
            and t.date BETWEEN :START_DATE and :END_DATE
            WHERE c.user_id=:userId
            GROUP BY c.type   
            """, nativeQuery = true)
    String getCategorySum(@Param("userId") Long userId, @Param("START_DATE") LocalDate startDate, @Param("END_DATE") LocalDate endDate);



    @Query(value= """
            SELECT c.name as name, SUM(coalesce(t.price,0)) as value FROM category c
            LEFT JOIN transaction t
            on c.id=t.category_id
            and t.date BETWEEN :START_DATE and :END_DATE
            WHERE c.user_id=:userId
            and c.type=:TYPE
            GROUP BY c.name   
            """, nativeQuery = true)
    String[] getCategoryStatsByType(
            @Param("userId") Long userId,
            @Param("START_DATE") LocalDate startDate,
            @Param("END_DATE") LocalDate endDate,
            @Param("TYPE") String type);


}
