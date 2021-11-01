package project.isa.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.isa.model.Users.User;
import project.isa.repository.UserRepository;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(value = "api/users")
public class UserService {

    @Autowired
    private UserRepository userRepository;

   // @GetMapping(value = "/return")
    public List<User> getAllUsers()
    {
        return userRepository.findAll();
    }

    //@PostMapping(value = "/saveUser", consumes = "application/json")

    public void saveUser(@RequestBody User user)
    {
        userRepository.save(user);
    }
    


}
