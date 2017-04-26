package com.petstore.security;

import com.petstore.entities.User;
import com.petstore.exceptions.PetStoreException;
import com.petstore.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * User: Ionut Barau (ionutbarau)
 * Project: petstore
 * Date: 23/04/2017.
 * Time: 23:05
 */
@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
class GlobalAuthenticationSecurityConfiguration extends GlobalAuthenticationConfigurerAdapter {

    @Autowired
    private UserService userService;

    @Override
    public void init(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService());
    }

    @Bean
    UserDetailsService userDetailsService() {
        return new UserDetailsService() {

            @Override
            public UserDetails loadUserByUsername(String username) throws PetStoreException {
                User user = userService.findByUsername(username);
                return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                        true, true, true, true, AuthorityUtils.createAuthorityList(user.getRole()));
            }
        };
    }
}
