package Source.Repositories;

import Source.Models.PhoneInCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneInCartRepository extends JpaRepository<PhoneInCart, Integer> {
}
