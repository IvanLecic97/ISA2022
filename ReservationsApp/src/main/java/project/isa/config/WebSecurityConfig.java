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
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import project.isa.Roles;
import project.isa.repository.AuthoritiesRepository;
import project.isa.repository.RegUserRepository;
import project.isa.security.TokenUtils;
import project.isa.security.auth.RestAuthenticationEntryPoint;
import project.isa.security.auth.TokenAuthenticationFilter;
import project.isa.services.CustomUserDetailsService;
import project.isa.services.RegUserService;

@EnableWebSecurity
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

    @Autowired
    private AuthoritiesRepository authoritiesRepository;

    //@Autowired
   // private UserDetailsService userDetailsService;

    @Autowired
    private RegUserRepository regUserRepository;

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .exceptionHandling().authenticationEntryPoint(restAuthenticationEntryPoint).and()
                .cors().and()

                .authorizeRequests()
                .antMatchers("/auth/**").permitAll()
                .antMatchers("/api/user/registerClient").permitAll()
                .antMatchers("/api/user/confirmEmail/**").permitAll()
                .antMatchers("/api/user/registerBungalowOwner").permitAll()
                .antMatchers("/api/user/registerShipOwner").permitAll()
                .antMatchers("/api/user/registerFishingInstructorOwner").permitAll()
                .antMatchers("/api/attraction/addBungalow").hasAuthority(Roles.ROLE_BUNGALOW_OWNER)
                .antMatchers("/api/attraction/addShip").hasAuthority(Roles.ROLE_SHIP_OWNER)
                .antMatchers("/api/attraction/getAttractions").hasAuthority(Roles.ROLE_CLIENT)
                .antMatchers("/api/attraction/getAllCountries").hasAuthority(Roles.ROLE_CLIENT)
                .antMatchers("/api/attraction/getAttractionById/**").hasAnyAuthority(Roles.ROLE_CLIENT, Roles.ROLE_BUNGALOW_OWNER, Roles.ROLE_SHIP_OWNER, Roles.ROLE_FISHING_INSTRUCTOR )
                .antMatchers("/api/reservation/makeReservation").hasAuthority(Roles.ROLE_CLIENT)
                .antMatchers("/api/reservation/makeDiscountReservation").hasAuthority(Roles.ROLE_CLIENT)
                .antMatchers("/api/attraction/getUsersBungalows").hasAuthority(Roles.ROLE_BUNGALOW_OWNER)
                .antMatchers("/api/attraction/getUsersShips").hasAuthority(Roles.ROLE_SHIP_OWNER)
                .antMatchers("/api/attraction/getUsersInstructors").hasAuthority(Roles.ROLE_FISHING_INSTRUCTOR)
                .antMatchers("/api/discount/setDiscountedEntity").hasAnyAuthority(Roles.ROLE_BUNGALOW_OWNER, Roles.ROLE_SHIP_OWNER, Roles.ROLE_FISHING_INSTRUCTOR )
                .antMatchers("/api/user/approveRegistration").hasAuthority(Roles.ROLE_ADMIN)
                .antMatchers("/api/user/disApproveRegistration").hasAuthority(Roles.ROLE_ADMIN)
                .antMatchers("/api/user/getRegistrationRequests").hasAuthority(Roles.ROLE_ADMIN)
                .antMatchers("/api/user/getUserByUsername/**").hasAuthority(Roles.ROLE_ADMIN)
                .anyRequest().authenticated().and()
                .addFilterBefore(new TokenAuthenticationFilter(tokenUtils, jwtUserDetailsService, regUserRepository, authoritiesRepository), BasicAuthenticationFilter.class);


        http.csrf().disable();
         //.addFilterBefore(new TokenAuthenticationFilter(tokenUtils, jwtUserDetailsService),BasicAuthenticationFilter.class);


    }

    @Override
    public void configure(WebSecurity web) throws Exception{
        web.ignoring().antMatchers(HttpMethod.POST,"/auth/login");
        web.ignoring().antMatchers(HttpMethod.POST, "/api/user/registerClient");
        web.ignoring().antMatchers(HttpMethod.POST, "/api/user/confirmEmail/**");
        web.ignoring().antMatchers(HttpMethod.POST, "/api/user/registerBungalowOwner");
        web.ignoring().antMatchers(HttpMethod.POST, "/api/user/registerShipOwner");
        web.ignoring().antMatchers(HttpMethod.POST, "/api/user/registerFishingInstructorOwner");
        //web.ignoring().antMatchers(HttpMethod.POST, "api/attraction/addBungalow");

        //web.ignoring().antMatchers(HttpMethod.GET, "/**/public/**");
        // web.ignoring().antMatchers(HttpMethod.POST, "/**/public/**");
        // web.ignoring().antMatchers(HttpMethod.PUT, "/**/public/**");
        //web.ignoring().antMatchers(HttpMethod.DELETE, "/**/public/**");

        //  web.ignoring().antMatchers(HttpMethod.GET, "/**/api/**");
        // web.ignoring().antMatchers(HttpMethod.POST, "/**/api/**");
        // web.ignoring().antMatchers(HttpMethod.PUT, "/**/api/**");
        // web.ignoring().antMatchers(HttpMethod.DELETE, "/**/api/**");

        // web.ignoring().antMatchers(HttpMethod.GET, "/api/users");
        //web.ignoring().antMatchers(HttpMethod.GET, "/systemAdmin");
        //web.ignoring().antMatchers(HttpMethod.POST, "/api/requestForReg");

        web.ignoring().antMatchers(HttpMethod.GET,"/","/webjars/**","/*.html","/favicon.ico","/**/*.html","/**/*.css","/**/*.js");
    }




}
