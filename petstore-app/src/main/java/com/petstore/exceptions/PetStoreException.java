package com.petstore.exceptions;

/**
 * Class used for business related exceptions.
 *
 * User: Ionut Barau (ionutbarau)
 * Project: petstore
 * Date: 21/04/2017.
 * Time: 19:23
 */
public class PetStoreException extends RuntimeException {


    private String exceptionMsg;

    public PetStoreException(String exceptionMsg) {
        this.exceptionMsg = exceptionMsg;
    }

    @Override
    public String getMessage() {
        return exceptionMsg;
    }

    @Override
    public String getLocalizedMessage() {
        return exceptionMsg;
    }

}
