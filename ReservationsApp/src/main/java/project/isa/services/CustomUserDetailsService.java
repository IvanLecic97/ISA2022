package project.isa.services;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import project.isa.Roles;
import project.isa.dto.RegUserDTO;
import project.isa.model.users.RegUser;
import project.isa.model.users.UserTokenState;
import project.isa.repository.RegUserRepository;
import project.isa.security.TokenUtils;
import project.isa.security.auth.JwtAuthenticationRequest;

import java.util.NoSuchElementException;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    protected final Log LOGGER = LogFactory.getLog(getClass());

    @Autowired
    private RegUserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private RegUserService userService;

    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        //User user = userRepository.findByUsername(username);
        RegUser user = userRepository.findByUsername(email);

        if (user == null) {
            throw new UsernameNotFoundException(String.format("No user found with email '%s'.", email));
        } else {

            return user;

        }
    }

    public RegUserDTO login(JwtAuthenticationRequest authenticationRequest)
    {
        Authentication authentication;
        RegUser user1 = userRepository.findByUsername(authenticationRequest.getUsername());
        if(!user1.getActivated() && user1.getRole().equals(Roles.ROLE_ADMIN)){
            user1.setPassword(bCryptPasswordEncoder.encode(authenticationRequest.getPassword()));
            user1.setActivated(true);
            userRepository.save(user1);
        }

        if(userService.checkIfEnabled(authenticationRequest.getUsername())) {
            try {
                authentication = authenticationManager
                        .authenticate(new UsernamePasswordAuthenticationToken(
                                authenticationRequest.getUsername(),
                                authenticationRequest.getPassword()
                        ));
            } catch (BadCredentialsException e) {
                throw new NoSuchElementException("Credentials are not valid!");
            }
            // Insert username and password into context
            SecurityContextHolder.getContext().setAuthentication(authentication);

            //Create token

            RegUser user = (RegUser) authentication.getPrincipal();
            String jwt = tokenUtils.generateToken(user.getUsername());
            int expiresIn = tokenUtils.getExpiredIn();

            RegUserDTO userRet = new RegUserDTO(user);
            userRet.setToken(new UserTokenState(jwt, expiresIn));
            userRet.setId(null);


            return userRet;

        }
        else return null;


    }







}
