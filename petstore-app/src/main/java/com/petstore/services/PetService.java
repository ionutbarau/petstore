package com.petstore.services;

import com.petstore.entities.Pet;
import com.petstore.exceptions.PetStoreException;

import java.util.List;

/**
 * The Pet Service used for managing pet related operations.
 * <p>
 * User: Ionut Barau (ionutbarau)
 * Project: petstore
 * Date: 21/04/2017.
 * Time: 19:19
 */
public interface PetService {

    /**
     * List all the available pets in the petstore.
     *
     * @return the list of available pets
     */
    List<Pet> list();

    /**
     * Finds the pet by using the specified id.
     *
     * @param id
     * @return the found pet
     * @throws PetStoreException if the pet is not found(http 404) or the id is invalid (http 400).
     */
    Pet find(Integer id) throws PetStoreException;

    /**
     * Adds a new pet.
     *
     * @param pet
     * @return the newly added pet
     * @throws PetStoreException if the input is invalid(http 400)
     */
    Pet add(Pet pet) throws PetStoreException;

    /**
     * Deletes the specified pet.
     *
     * @param id
     * @throws PetStoreException if pet is not found (http 404) or the id is invalid (http 400)
     */
    void delete(Integer id) throws PetStoreException;
}
