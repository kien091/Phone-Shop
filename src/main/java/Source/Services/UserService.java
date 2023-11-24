package Source.Services;

import Source.Models.User;
import Source.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User findById(int id){
        return userRepository.findById(id).orElse(null);
    }

    public User save(User user){
        return userRepository.save(user);
    }

    public boolean checkIfExistUsername(String username){
        List<User> users = userRepository.findAll();
        for(User user: users){
            if(user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkIfExistEmail(String email){
        List<User> users = userRepository.findAll();
        for(User user: users){
            if(user.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }
}
