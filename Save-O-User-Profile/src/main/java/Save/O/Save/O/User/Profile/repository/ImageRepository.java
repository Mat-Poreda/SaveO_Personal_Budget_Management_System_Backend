package Save.O.Save.O.User.Profile.repository;

import Save.O.Save.O.Data.Storage.dao.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
}