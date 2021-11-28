package Save.O.Save.O.User.Profile.repository;

import Save.O.Save.O.Data.Storage.dao.User;
import Save.O.Save.O.User.Profile.dao.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
