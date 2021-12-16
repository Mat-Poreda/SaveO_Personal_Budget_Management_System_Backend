package Save.O.Save.O.Data.Storage.repository;

import Save.O.Save.O.Data.Storage.dao.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
