package project.isa.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import project.isa.model.users.Client;
import project.isa.model.users.RegUser;
import project.isa.repository.RegUserRepository;
import project.isa.services.RegUserService;

import java.util.List;

@Controller
@CrossOrigin("http://localhost:3000")
@RequestMapping("api/user")
public class RegUserController {
    @Autowired
    private RegUserService regUserService;



    @GetMapping(value = "/getAllUsers")
    public ResponseEntity<List<RegUser>> getAllRegUsers()
    {
        List<RegUser> list = regUserService.getAllRegUsers();
        return new ResponseEntity<List<RegUser>>(list, HttpStatus.OK);
    }


    @PostMapping(value = "/registerClient")
    public ResponseEntity<?> registerClient (@RequestBody RegUser user)
    {
        Client c = regUserService.registerClient(user);
        if(c != null)
        {
            return new ResponseEntity<Client>(c, HttpStatus.OK);
        }
        else
            return  new ResponseEntity<String>("error!", HttpStatus.OK);
    }

    @PostMapping(value = "/confirmEmail/{email}")
    public ResponseEntity<?> confirmEmail(@PathVariable("email") String email)
    {
        List<RegUser> userList = regUserService.getAllRegUsers();
        String retVal = "";
        for(RegUser u : userList)
        {
            if(u.getUsername().equals(email))
            {
                u.setActivated(true);
                regUserService.saveRegUser(u);
                retVal = "SUCCESS";
            }
            else
                retVal = "FAILURE";
        }
        return new ResponseEntity<String>(retVal, HttpStatus.OK);
    }

    @GetMapping(value = "/getUser/{username}")
    public ResponseEntity<?> getUser(@PathVariable("username") String username)
    {
        RegUser user = regUserService.getUser(username);


        return new ResponseEntity<RegUser>(user, HttpStatus.OK);
    }

    @PostMapping(value = "/updateUser")
    public ResponseEntity<?> updateUser(@RequestBody RegUser user)
    {
        regUserService.updateUser(user);
        return new ResponseEntity<String>("Updated!", HttpStatus.OK);

    }


}
