package Save.O.Save.O.Data.Storage.repository;

import Save.O.Save.O.Data.Storage.dao.Category;
import Save.O.Save.O.Data.Storage.dao.User;
import Save.O.Save.O.Data.Storage.dto.CategoryDTO;
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


}
