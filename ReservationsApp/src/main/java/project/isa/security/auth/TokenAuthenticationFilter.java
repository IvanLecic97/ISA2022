package project.isa.security.auth;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;
import project.isa.Roles;
import project.isa.model.users.Authorities;
import project.isa.model.users.RegUser;
import project.isa.repository.AuthoritiesRepository;
import project.isa.repository.RegUserRepository;
import project.isa.security.TokenUtils;
import project.isa.services.RegUserService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TokenAuthenticationFilter extends OncePerRequestFilter {


    private TokenUtils tokenUtils;
    private UserDetailsService userDetailsService;
    private RegUserRepository regUserRepository;

    private AuthoritiesRepository authoritiesRepository;

    public TokenAuthenticationFilter(TokenUtils tokenUtils, UserDetailsService userDetailsService,
                                     RegUserRepository regUserRepository, AuthoritiesRepository authoritiesRepository) {
        this.tokenUtils = tokenUtils;
        this.userDetailsService = userDetailsService;
        this.regUserRepository = regUserRepository;
        this.authoritiesRepository = authoritiesRepository;
    }

    @Override
    public void  doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException
    {
        String mail;
        String authToken = tokenUtils.getToken(request);
        Claims claims = tokenUtils.getAllClaimsFromToken(authToken);
        System.out.println(authToken + " token");
        //System.out.println(authToken.substring(7) + " isjecen token");
        //System.out.println(regUserRepository.findByUsernameEquals("ika619@gmail.com").getUsername() + " korisnik");
        //System.out.println(regUserRepository.findByUsernameEquals("ika619@gmail.com").getRole() + " rola");
        String authority = (String) claims.get("authority");
        //System.out.println("EVO ME IVOOOOO SRCE ZIVOOOO");
        //System.out.println(claims + " claims");
       // System.out.println(authority + "evo ga authority");
        if (authToken != null) {
            mail = tokenUtils.getMailFromToken(authToken);
            System.out.println(mail);

            if (mail != null) {
                RegUser regUser = regUserRepository.findByUsernameEquals(mail);
                //regUser.getAuthorities().add(authority);
                Authorities authorities = authoritiesRepository.findByName(authority);
                List<Authorities> authorities1 = new ArrayList<Authorities>();
                authorities1.add(authorities);
                regUser.setAuthorities(authorities1);
                regUserRepository.save(regUser);

                UserDetails userDetails = userDetailsService.loadUserByUsername(mail);


                // Is token valid
                if (tokenUtils.validateToken(authToken, userDetails)) {
                    // Create authentication
                    TokenBasedAuthenticationFilter authentication = new TokenBasedAuthenticationFilter(userDetails);
                    authentication.setToken(authToken);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }

        chain.doFilter(request, response);
    }
    }



