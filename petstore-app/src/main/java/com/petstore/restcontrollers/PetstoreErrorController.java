package com.petstore.restcontrollers;

import com.petstore.exceptions.PetStoreExceptionInformation;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * User: Ionut Barau (ionutbarau)
 * Project: petstore
 * Date: 24/04/2017.
 * Time: 22:12
 */
@RestController
public class PetstoreErrorController implements ErrorController {

    @RequestMapping(value = "/error")
    public ResponseEntity<PetStoreExceptionInformation> error() {
        String message = "An unknown error has occurred...Please contact de administrator.";
        PetStoreExceptionInformation info = new PetStoreExceptionInformation(message);
        return new ResponseEntity<>(info, HttpStatus.BAD_REQUEST);
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
