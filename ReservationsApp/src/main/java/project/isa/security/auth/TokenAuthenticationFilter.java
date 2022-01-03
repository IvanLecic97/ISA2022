package project.isa.security.auth;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;
import project.isa.security.TokenUtils;
import project.isa.services.RegUserService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TokenAuthenticationFilter extends OncePerRequestFilter {


    private TokenUtils tokenUtils;
    private UserDetailsService userDetailsService;

    public TokenAuthenticationFilter(TokenUtils tokenUtils, RegUserService userDetailsService) {
        this.tokenUtils = tokenUtils;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public void  doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException
    {
        String mail;
        String authToken = tokenUtils.getToken(request);
        if (authToken != null) {
            mail = tokenUtils.getMailFromToken(authToken);

            if (mail != null) {
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



