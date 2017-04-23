package com.petstore.repositories;


import com.petstore.entities.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring JPARepository implementation for {@link Tag}.
 * <p>
 * User: Ionut Barau (ionutbarau)
 * Project: petstore
 * Date: 23/04/2017.
 * Time: 18:16
 */
public interface TagRepository extends JpaRepository<Tag, Integer> {
}
