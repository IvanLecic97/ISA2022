package project.isa.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import project.isa.security.TokenUtils;
import project.isa.security.auth.RestAuthenticationEntryPoint;
import project.isa.services.CustomUserDetailsService;
import project.isa.services.RegUserService;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception{
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private CustomUserDetailsService jwtUserDetailsService;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder());
    }

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .exceptionHandling().authenticationEntryPoint(restAuthenticationEntryPoint).and()
                .cors().and()

                .authorizeRequests()
                .antMatchers("/auth/**").permitAll()
                .antMatchers("/api/**").permitAll()
                .anyRequest().authenticated().and();


        http.csrf().disable();
        // .addFilterBefore(new TokenAuthenticationFilter(tokenUtils, jwtUserDetailsService),BasicAuthenticationFilter.class);


    }

    @Override
    public void configure(WebSecurity web) throws Exception{
        web.ignoring().antMatchers(HttpMethod.POST,"/auth/login");

        web.ignoring().antMatchers(HttpMethod.GET, "/**/public/**");
        web.ignoring().antMatchers(HttpMethod.POST, "/**/public/**");
        web.ignoring().antMatchers(HttpMethod.PUT, "/**/public/**");
        web.ignoring().antMatchers(HttpMethod.DELETE, "/**/public/**");

        web.ignoring().antMatchers(HttpMethod.GET, "/**/api/**");
        web.ignoring().antMatchers(HttpMethod.POST, "/**/api/**");
        web.ignoring().antMatchers(HttpMethod.PUT, "/**/api/**");
        web.ignoring().antMatchers(HttpMethod.DELETE, "/**/api/**");

        web.ignoring().antMatchers(HttpMethod.GET, "/api/users");
        web.ignoring().antMatchers(HttpMethod.GET, "/systemAdmin");
        web.ignoring().antMatchers(HttpMethod.POST, "/api/requestForReg");

        web.ignoring().antMatchers(HttpMethod.GET,"/","/webjars/**","/*.html","/favicon.ico","/**/*.html","/**/*.css","/**/*.js");
    }




}
