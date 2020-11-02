package com.example.demo.security;

import com.example.demo.auth.ApplicationUserService;
import com.example.demo.auth.JwtTokenVerifier;
import com.example.demo.jwt.JwtUsernameAndPasswordAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AppSecurityConfig  extends WebSecurityConfigurerAdapter {


    private final PasswordEncoder passwordEncoder;
    private final ApplicationUserService applicationUserService;

    @Autowired
    public AppSecurityConfig(PasswordEncoder passwordEncoder, ApplicationUserService applicationUserService){
        this.passwordEncoder = passwordEncoder;
        this.applicationUserService = applicationUserService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
               // .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                .csrf().disable()
                .sessionManagement()
                        .sessionCreationPolicy(
                                SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(new JwtUsernameAndPasswordAuthentication(authenticationManager()))
                .addFilterAfter(new JwtTokenVerifier(),JwtUsernameAndPasswordAuthentication.class)
                .authorizeRequests()
                .antMatchers("/","/index","/css/*","/js/*")
                .permitAll()
                .antMatchers("/api/**").hasRole(UserRole.DEV.name())
                .anyRequest()
                .authenticated();
//                .and()
//                .formLogin();
//                .loginPage("/login").permitAll()
//                .defaultSuccessUrl("/courses", true)
//                .usernameParameter("username")
//                .passwordParameter("password")
//                 .and()
//                .rememberMe().tokenValiditySeconds(300)
//                .key("secureKey")
//                .and()
//                .logout()
//                    .logoutUrl("/logout")
//                    .clearAuthentication(true)
//                    .invalidateHttpSession(true)
//                    .deleteCookies("JSESSIONID","remember-me").
//                    logoutSuccessUrl("/login");
    }

//    @Override
//    @Bean
//    protected UserDetailsService userDetailsService() {
//        UserDetails waelUser = User.builder()
//                .username("wael").
//                password(passwordEncoder.encode("password"))
//                //.roles(UserRole.CEO.name()) //ROLE_DEV
//                .authorities(UserRole.CEO.getGrantedAuthorities())
//                .build();
//
//        UserDetails anna = User.builder()
//                .username("anna")
//                .password(passwordEncoder.encode("password1"))
//                //.roles(UserRole.DEV.name())
//                .authorities(UserRole.DEV.getGrantedAuthorities())
//                .build();
//        UserDetails tom = User.builder()
//                .username("tom")
//                .password(passwordEncoder.encode("password3"))
//                //.roles(UserRole.ADMINTRAINEE.name())
//                .authorities(UserRole.ADMINTRAINEE.getGrantedAuthorities())
//                .build();
//
//        return new InMemoryUserDetailsManager(waelUser,anna,tom);
//    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(applicationUserService);
        return provider;
    }
}
