package Save.O.Save.O.Data.Storage.repository;

import Save.O.Save.O.Data.Storage.dao.Category;
import Save.O.Save.O.Data.Storage.dao.User;
import Save.O.Save.O.Data.Storage.enums.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findByUserAndNameAndType(User user, String name, Type type);
}
