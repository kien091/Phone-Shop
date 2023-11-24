package Source.Services;

import Source.Models.PhoneInCart;
import Source.Repositories.PhoneInCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PhoneInCartService {
    private final PhoneInCartRepository phoneInCartRepository;

    @Autowired
    public PhoneInCartService(PhoneInCartRepository phoneInCartRepository){
        this.phoneInCartRepository = phoneInCartRepository;
    }

    public List<PhoneInCart> findAll(){
        return phoneInCartRepository.findAll();
    }

    public PhoneInCart findById(int id){
        return phoneInCartRepository.findById(id).orElse(null);
    }

    public PhoneInCart save(PhoneInCart phoneInCart){
        return phoneInCartRepository.save(phoneInCart);
    }

    public void update(PhoneInCart phoneInCart){
        PhoneInCart phone = phoneInCartRepository.findById(phoneInCart.getId()).orElse(null);
        if(phone != null){
            phone.setQuantity(phoneInCart.getQuantity());
            phone.setCart(phoneInCart.getCart());
            phone.setPhone(phoneInCart.getPhone());

            phoneInCartRepository.save(phone);
        }
    }

    public void delete(PhoneInCart phone){
        if(phoneInCartRepository.findById(phone.getId()).orElse(null) != null)
            phoneInCartRepository.delete(phone);
    }


    public List<PhoneInCart> findAllByIdCart(int id){
        List<PhoneInCart> phoneInCarts = phoneInCartRepository.findAll();
        List<PhoneInCart> phones = new ArrayList<>();
        for(PhoneInCart phone: phoneInCarts){
            if(phone.getCart().getId() == id){
                phones.add(phone);
            }
        }
        return phones;
    }

    public PhoneInCart findByIdCartAndIdPhone(int idCart, int idPhone){
        return phoneInCartRepository.findByCartIdAndPhoneId(idCart, idPhone);
    }

    public void deleteAllByIdCart(int idCart){
        phoneInCartRepository.deleteAllByIdCart(idCart);
    }
}
