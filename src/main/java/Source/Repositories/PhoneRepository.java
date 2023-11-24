package Source.Repositories;

import Source.Models.Phone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhoneRepository extends JpaRepository<Phone, Integer> {
    @Query(value = "SELECT * FROM phone WHERE name LIKE %?1% " +
            "OR description LIKE %?1% OR price LIKE %?1%", nativeQuery = true)
    List<Phone> findAllBySearch(String search);
}
