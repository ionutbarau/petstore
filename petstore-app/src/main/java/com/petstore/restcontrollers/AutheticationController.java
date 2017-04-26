package com.petstore.restcontrollers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * User: Ionut Barau (ionutbarau)
 * Project: petstore
 * Date: 25/04/2017.
 * Time: 00:40
 */
@RestController
public class AutheticationController {

    @RequestMapping("/user")
    public Principal user(Principal user) {
        return user;
    }
}
