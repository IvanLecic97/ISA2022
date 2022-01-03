package project.isa.services.IServices;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface IUserDetailsService {
    UserDetails loadByEmail(String email)
            throws UsernameNotFoundException;



}
