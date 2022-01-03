package project.isa.services;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import project.isa.model.users.Client;
import project.isa.model.users.RegUser;
import project.isa.repository.ClientRepository;
import project.isa.repository.RegUserRepository;
import project.isa.services.IServices.IRegUserService;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

@Service
@Transactional
@Slf4j
public class RegUserService implements IRegUserService, UserDetailsService {

    @Autowired
    private RegUserRepository regUserRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private EmailSenderService emailSenderService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        RegUser user = regUserRepository.findByUsername(username);
        if(user == null)
        {
            throw new UsernameNotFoundException("Not found");
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole()));
       return new org.springframework.security.core.userdetails.User(user.getUsername(),
               user.getPassword(), authorities
               );
    }

    @Override
    public void saveRegUser(RegUser user) {
        regUserRepository.save(user);

    }

    @Override
    public List<RegUser> getAllRegUsers() {
        List<RegUser> list = regUserRepository.findAll();
        return list;
    }

    @Override
    public void deleteRegUser(RegUser user) {
        regUserRepository.delete(user);
    }


    @Override
    public Client registerClient(RegUser user) {
        if(regUserRepository.findByUsernameEquals(user.getUsername()) == null)
        {
            Client u = new Client();
            u.setAddress(user.getAddress());
            u.setCity(user.getCity());
            u.setCountry(user.getCountry());
            u.setName(user.getName());
            u.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            u.setPhone(user.getPhone());
            u.setRole("ROLE_CLIENT");
            u.setSurname(user.getSurname());
            u.setUsername(user.getUsername());
            u.setActivated(false);

            String msg = "http://localhost:3000/confirm";

            emailSenderService.sendSimpleEmail(u.getUsername(), msg, "Mail messsage");
            clientRepository.save(u);
            return u;
        }
        else
            return null;
    }

    @Override
  public  boolean checkIfEnabled(String username)
    {
        boolean retVal = false;
        RegUser user = regUserRepository.findByUsernameEquals(username);
        if(user.getActivated() == true)
        {
            retVal = true;
        }

        return retVal;
    }


}
