package Source.Services;

import Source.Models.Phone;
import Source.Repositories.PhoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhoneService {
    private final PhoneRepository phoneRepository;

    @Autowired
    public PhoneService(PhoneRepository phoneRepository){
        this.phoneRepository = phoneRepository;
    }

    public List<Phone> findAll(){
        return phoneRepository.findAll();
    }

    public Phone findById(int id){
        return phoneRepository.findById(id).orElse(null);
    }

    public Phone save(Phone phone){
        return phoneRepository.save(phone);
    }

    public void update(Phone phone){
        Phone p = phoneRepository.findById(phone.getId()).orElse(null);
        if (p != null) {
            p.setName(phone.getName());
            p.setImage(phone.getImage());
            p.setDescription(phone.getDescription());
            p.setPrice(p.getPrice());

            phoneRepository.save(p);
        }
    }

    public void delete(Phone phone){
        if(phoneRepository.findById(phone.getId()).orElse(null) != null)
            phoneRepository.delete(phone);
    }

    public List<Phone> findAllBySearch(String search){
        return phoneRepository.findAllBySearch(search);
    }
}
