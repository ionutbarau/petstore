package com.petstore.restcontrollers;

import com.petstore.entities.Pet;
import com.petstore.exceptions.PetStoreException;
import com.petstore.services.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
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


    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Pet> list() {
        return petService.list();
    }

    @RequestMapping(value = "/{petId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Pet find(@PathVariable Integer petId, HttpServletResponse response) {
        Pet pet = null;
        try {
            pet = petService.find(petId);
        } catch (PetStoreException e) {
            response.setStatus(e.getHttpStatusCode().getValue());

        }
        return pet;
    }
}
