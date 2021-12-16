package Save.O.Save.O.User.Profile.repository;


import Save.O.Save.O.User.Profile.dao.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserDetails, Long> {
    Optional<UserDetails> findByUsername(String username);

    Optional<UserDetails> findByEmail(String email);

    void deleteByEmail(String email);
}
