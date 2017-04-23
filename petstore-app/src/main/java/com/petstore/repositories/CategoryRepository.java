package com.petstore.repositories;

import com.petstore.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring JPARepository implementation for {@link Category}.
 * <p>
 * User: Ionut Barau (ionutbarau)
 * Project: petstore
 * Date: 23/04/2017.
 * Time: 18:46
 */
public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
