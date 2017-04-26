package com.petstore.services;

import com.petstore.entities.User;
import com.petstore.exceptions.PetStoreException;

/**
 * The User Service used for managing user related operations.
 * <p>
 * User: Ionut Barau (ionutbarau)
 * Project: petstore
 * Date: 23/04/2017.
 * Time: 21:45
 */
public interface UserService {

    User findByUsername(String username) throws PetStoreException;

}
