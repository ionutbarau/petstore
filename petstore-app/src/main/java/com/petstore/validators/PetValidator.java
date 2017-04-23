package com.petstore.validators;

import com.petstore.entities.Pet;
import com.petstore.exceptions.PetStoreException;
import com.petstore.exceptions.PetStoreExceptionMsg;
import org.springframework.stereotype.Component;

/**
 * Validator class for adding pets.
 * <p>
 * User: Ionut Barau (ionutbarau)
 * Project: petstore
 * Date: 21/04/2017.
 * Time: 21:42
 */
@Component
public class PetValidator {

    /**
     * Validates input for creating a new pet.
     *
     * @param pet
     * @throws PetStoreException
     */
    public void validate(Pet pet) throws PetStoreException {
        if (pet == null) {
            throw new PetStoreException(PetStoreExceptionMsg.INVALID_INPUT);
        }
        boolean isPetNameValid = pet.getName() != null && !pet.getName().isEmpty();
        boolean isPetCategoryValid = pet.getCategory() != null;
        boolean isPetStatusValid = pet.getStatus() != null;
        boolean isPetTagsValid = pet.getTags() != null && !pet.getTags().isEmpty();
        boolean isPetPhotosUrlValid = pet.getPhotoUrls() != null && !pet.getPhotoUrls().isEmpty();
        if (!isPetNameValid || !isPetCategoryValid || !isPetPhotosUrlValid || !isPetTagsValid || !isPetStatusValid) {
            throw new PetStoreException(PetStoreExceptionMsg.INVALID_INPUT);
        }
    }
}
