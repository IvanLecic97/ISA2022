package services;


import model.Users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import repository.UserRepository;

@RestController
@RequestMapping(value = "api/users")
public class UserService {
    @Autowired
    UserRepository userRepository;


   @PostMapping(value = "/saveUser")
    public void saveUser(@RequestBody User user)
   {
       User u = new User();
       //u.setId(user.getId());
       u.setEmail(user.getEmail());
       u.setPassword(user.getPassword());
       u.setName(user.getName());
       u.setSurname(user.getSurname());
       u.setAddress(user.getAddress());
       u.setCity(user.getCity());
       u.setCountry(user.getCountry());
       u.setPhoneNumber(user.getPhoneNumber());
       u.setValid(user.isValid());
       userRepository.save(u);
   }


}
