package com.petstore.security;

import com.petstore.entities.User;
import com.petstore.exceptions.PetStoreExceptionMsg;
import com.petstore.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

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
    private UserRepository userRepository;

    @Override
    public void init(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService());
    }

    @Bean
    UserDetailsService userDetailsService() {
        return new UserDetailsService() {

            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                User user = userRepository.findByUsername(username);

                if(user == null) {
                    throw new UsernameNotFoundException(PetStoreExceptionMsg.INVALID_CREDENTIALS);
                }

                return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                        true, true, true, true, AuthorityUtils.createAuthorityList(user.getRole()));
            }
        };
    }
}
