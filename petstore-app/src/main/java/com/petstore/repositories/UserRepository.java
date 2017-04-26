package com.petstore.repositories;

import com.petstore.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring JPARepository implementation for {@link User}.
 * <p>
 * User: Ionut Barau (ionutbarau)
 * Project: petstore
 * Date: 23/04/2017.
 * Time: 21:42
 */
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsernameAndPassword(String username, String password);

    User findByUsername(String username);
}
