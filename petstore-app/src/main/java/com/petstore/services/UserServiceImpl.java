package com.petstore.services;

import com.petstore.entities.User;
import com.petstore.exceptions.PetStoreException;
import com.petstore.exceptions.PetStoreExceptionMsg;
import com.petstore.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


/**
 * Spring service implementation class for {@link UserService}.
 * <p>
 * User: Ionut Barau (ionutbarau)
 * Project: petstore
 * Date: 23/04/2017.
 * Time: 21:45
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
public class UserServiceImpl implements UserService {

    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findByUsername(String username) throws PetStoreException {
        logger.debug("Entering findByUsername() with username = " + username);
        User user = userRepository.findByUsername(username);
        if (user == null) {
            logger.error("Exception caught in findByUsername() : " + PetStoreExceptionMsg.INVALID_CREDENTIALS);
            throw new PetStoreException(PetStoreExceptionMsg.INVALID_CREDENTIALS);
        }
        logger.debug("Leaving findByUsername() with user = " + user);
        return user;
    }

}
