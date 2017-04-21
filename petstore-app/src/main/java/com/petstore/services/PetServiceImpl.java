package com.petstore.services;

import com.petstore.entities.Pet;
import com.petstore.enums.HttpStatusCode;
import com.petstore.exceptions.PetStoreException;
import com.petstore.exceptions.PetStoreExceptionMsg;
import com.petstore.repositories.PetRepository;
import com.petstore.validators.PetValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Spring service implementation class for {@link PetService}.
 * <p>
 * User: Ionut Barau (ionutbarau)
 * Project: petstore
 * Date: 21/04/2017.
 * Time: 19:19
 */
@Service
@Transactional(propagation = Propagation.REQUIRED)
public class PetServiceImpl implements PetService {

    @Autowired
    private PetRepository petRepository;

    @Override
    public List<Pet> list() {
        return petRepository.findAll();
    }

    @Override
    public Pet find(Integer id) throws PetStoreException {
        if (id < 0) {
            throw new PetStoreException(HttpStatusCode.HTTP_400, PetStoreExceptionMsg.INVALID_ID);
        }
        Pet pet = petRepository.findOne(id);
        if (pet == null) {
            throw new PetStoreException(HttpStatusCode.HTTP_404, PetStoreExceptionMsg.PET_NOT_FOUND);
        }
        return pet;
    }

    @Override
    public Pet add(Pet pet) throws PetStoreException {
        PetValidator.validate(pet);
        return petRepository.save(pet);
    }

    @Override
    public void delete(Integer id) throws PetStoreException {
        Pet pet = find(id);
        petRepository.delete(pet.getId());
    }
}
