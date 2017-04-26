package com.petstore.services;

import com.petstore.entities.Category;
import com.petstore.entities.Pet;
import com.petstore.entities.Tag;
import com.petstore.exceptions.PetStoreException;
import com.petstore.exceptions.PetStoreExceptionMsg;
import com.petstore.repositories.CategoryRepository;
import com.petstore.repositories.PetRepository;
import com.petstore.repositories.TagRepository;
import com.petstore.security.PetStoreSecurityRoles;
import com.petstore.validators.PetValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    private final Logger logger = LoggerFactory.getLogger(PetServiceImpl.class);

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private PetValidator petValidator;

    @Secured({PetStoreSecurityRoles.MANAGER, PetStoreSecurityRoles.GUEST})
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    @Override
    public List<Pet> list() {
        logger.debug("Entering list()");
        List<Pet> pets = petRepository.findAll();
        logger.debug("Leaving list() with pets = " + pets);
        return pets;
    }

    @Secured({PetStoreSecurityRoles.MANAGER, PetStoreSecurityRoles.GUEST})
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    @Override
    public Pet find(Integer id) throws PetStoreException {
        logger.debug("Entering find() with id = " + id);
        if (id < 0) {
            logger.error("Exception caught in find() : " + PetStoreExceptionMsg.INVALID_ID);
            throw new PetStoreException(PetStoreExceptionMsg.INVALID_ID);
        }
        Pet pet = petRepository.findOne(id);
        if (pet == null) {
            logger.error("Exception caught in find() : " + PetStoreExceptionMsg.PET_NOT_FOUND);
            throw new PetStoreException(PetStoreExceptionMsg.PET_NOT_FOUND);
        }
        logger.debug("Leaving find() with pet = " + pet);
        return pet;
    }

    @Secured({PetStoreSecurityRoles.MANAGER})
    @Override
    public Pet add(Pet pet) throws PetStoreException {
        logger.debug("Entering add() with pet = " + pet);
        petValidator.validate(pet);

        //0 as id represents a new pet. @Id field has it's value generated by sequence.
        Integer petId = pet.getId();
        if (petId != null && petId.equals(0)) {
            pet.setId(null);
        }

        //persist relationship
        persistRelationships(pet);
        //save the pet
        Pet savedPet = petRepository.save(pet);
        logger.debug("Leaving add() with savedPet = " + savedPet);
        return savedPet;
    }

    /**
     * Persist/merge category and tags
     *
     * @param pet
     */
    private void persistRelationships(Pet pet) {

        logger.debug("Entering persistRelationships() with pet = " + pet);
        //handle category persistence
        Category category = pet.getCategory();
        Integer catId = category.getId();
        //0 as id represents a new category. @Id field has it's value generated by sequence.
        if (catId != null && catId.equals(0)) {
            category.setId(null);
        }
        pet.setCategory(categoryRepository.save(category));

        //handle tags persistence
        Set<Tag> mergedTags = new HashSet<>();
        Set<Tag> tags = pet.getTags();
        tags.stream().forEach(tag -> {
            //0 as id represents a new tag. @Id field has it's value generated by sequence.
            if (tag.getId() != null && tag.getId().equals(0)) {
                tag.setId(null);
            }
            mergedTags.add(tagRepository.save(tag));
        });
        pet.setTags(mergedTags);
        logger.debug("Leaving persistRelationships() with pet = " + pet);
    }

    @Secured({PetStoreSecurityRoles.MANAGER})
    @Override
    public void delete(Integer id) throws PetStoreException {
        logger.debug("Entering delete() with id = " + id);
        Pet pet = find(id);
        petRepository.delete(pet.getId());
        logger.debug("Leaving delete()");
    }
}
