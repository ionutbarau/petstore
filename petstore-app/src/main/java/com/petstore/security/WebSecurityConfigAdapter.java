package com.petstore.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * User: Ionut Barau (ionutbarau)
 * Project: petstore
 * Date: 23/04/2017.
 * Time: 23:00
 */
@Configuration
@EnableWebSecurity
class WebSecurityConfigAdapter extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /*http.authorizeRequests().antMatchers("/pet").fullyAuthenticated().anyRequest().permitAll().and().
                httpBasic().and().csrf().disable();*/
        http.authorizeRequests().anyRequest().permitAll().and().
                httpBasic().and().csrf().disable();

    }

}
