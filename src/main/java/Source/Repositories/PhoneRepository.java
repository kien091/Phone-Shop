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

    @Query(value = "SELECT * FROM phone ORDER BY price DESC", nativeQuery = true)
    List<Phone> findAllByPriceDesc();

    @Query(value = "SELECT * FROM phone " +
            "ORDER BY cast(substring(description, 1, position('GB' IN description)) AS UNSIGNED) DESC"
            , nativeQuery = true)
    List<Phone> findAllByMemoryDesc();

    @Query(value = "SELECT * FROM phone ORDER BY name ASC", nativeQuery = true)
    List<Phone> findAllByNameAsc();

    @Query(value = "SELECT * FROM phone ORDER BY substring_index(description, ' - ', -1) ASC"
                    , nativeQuery = true)
    List<Phone> findAllByColorAsc();
}
