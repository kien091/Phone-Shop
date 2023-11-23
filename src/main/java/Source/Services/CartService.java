package Source.Services;

import Source.Models.Cart;
import Source.Repositories.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {
    private final CartRepository cartRepository;

    @Autowired
    private CartService(CartRepository cartRepository){
        this.cartRepository = cartRepository;
    }

    public List<Cart> findAll(){
        return cartRepository.findAll();
    }

    public Cart findById(int id){
        return cartRepository.findById(id).orElse(null);
    }
    public Cart save(Cart cart){
        return cartRepository.save(cart);
    }

    public void delete(Cart cart){
        if(cartRepository.existsById(cart.getId()))
            cartRepository.delete(cart);
    }
}
