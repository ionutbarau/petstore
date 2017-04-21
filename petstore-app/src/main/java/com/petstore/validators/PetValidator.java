package com.petstore.validators;

import com.petstore.entities.Pet;
import com.petstore.enums.HttpStatusCode;
import com.petstore.exceptions.PetStoreException;
import com.petstore.exceptions.PetStoreExceptionMsg;

/**
 * Validator class for adding pets.
 * <p>
 * User: Ionut Barau (ionutbarau)
 * Project: petstore
 * Date: 21/04/2017.
 * Time: 21:42
 */
public class PetValidator {

    public static void validate(Pet pet) {
        if (pet == null) {
            throw new PetStoreException(HttpStatusCode.HTTP_400, PetStoreExceptionMsg.INVALID_INPUT);
        }
        boolean isPetNameValid = pet.getName() == null || pet.getName().isEmpty();
        if (!isPetNameValid) {
            throw new PetStoreException(HttpStatusCode.HTTP_400, PetStoreExceptionMsg.INVALID_INPUT);
        }
    }
}
