package com.petstore.repositories;

import com.petstore.entities.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring JPARepository implementation for {@link Pet}.
 * <p>
 * User: Ionut Barau (ionutbarau)
 * Project: petstore
 * Date: 21/04/2017.
 * Time: 20:59
 */
@Repository
public interface PetRepository extends JpaRepository<Pet, Integer> {
}
