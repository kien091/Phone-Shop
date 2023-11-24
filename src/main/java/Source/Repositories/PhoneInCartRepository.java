package Source.Repositories;

import Source.Models.PhoneInCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface PhoneInCartRepository extends JpaRepository<PhoneInCart, Integer> {

    @Query(value = "SELECT DISTINCT * FROM cart_phone WHERE cart_id = ?1 AND phone_id = ?2", nativeQuery = true)
    PhoneInCart findByCartIdAndPhoneId(int idCart, int idPhone);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM cart_phone WHERE cart_id = ?1", nativeQuery = true)
    void deleteAllByIdCart(int idCart);
}
