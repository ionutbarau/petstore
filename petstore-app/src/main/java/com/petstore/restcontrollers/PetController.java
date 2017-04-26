package com.petstore.restcontrollers;

import com.petstore.entities.Pet;
import com.petstore.entities.User;
import com.petstore.exceptions.PetStoreException;
import com.petstore.exceptions.PetStoreExceptionInformation;
import com.petstore.exceptions.PetStoreExceptionMsg;
import com.petstore.repositories.UserRepository;
import com.petstore.services.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * User: Ionut Barau (ionutbarau)
 * Project: petstore
 * Date: 21/04/2017.
 * Time: 21:02
 */
@RestController
@RequestMapping(value = "/pet")
public class PetController {

    @Autowired
    private PetService petService;

    @Autowired
    private UserRepository userRepository;


    @RequestMapping(value = "/users", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> listUsers() {
        return userRepository.findAll();
    }

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Pet> list() {
        return petService.list();
    }


    @RequestMapping(value = "/{petId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Pet find(@PathVariable Integer petId) throws PetStoreException {
        return petService.find(petId);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Pet add(@RequestBody Pet pet) throws PetStoreException {
        return petService.add(pet);
    }

    @RequestMapping(value = "/{petId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void delete(@PathVariable Integer petId) throws PetStoreException {
        petService.delete(petId);
    }

    @ExceptionHandler(PetStoreException.class)
    /**
     * Exception handler for the pet rest api
     */
    public ResponseEntity<PetStoreExceptionInformation> handleExceptions(HttpServletRequest req, PetStoreException e) {
        String message = e.getMessage();
        PetStoreExceptionInformation info = new PetStoreExceptionInformation(message);
        HttpStatus status;
        switch (message) {
            case PetStoreExceptionMsg.INVALID_ID:
            case PetStoreExceptionMsg.INVALID_INPUT:
            case PetStoreExceptionMsg.VALIDATION_EXCEPTION:
                status = HttpStatus.BAD_REQUEST;
                break;
            case PetStoreExceptionMsg.PET_NOT_FOUND:
                status = HttpStatus.NOT_FOUND;
                break;
            default:
                status = HttpStatus.INTERNAL_SERVER_ERROR;

        }
        return new ResponseEntity<>(info, status);
    }
}
