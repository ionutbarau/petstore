package com.petstore.exceptions;

import com.petstore.enums.HttpStatusCode;

/**
 * Class used for business related exceptions regarding the petstore.
 * User: Ionut Barau (ionutbarau)
 * Project: petstore
 * Date: 21/04/2017.
 * Time: 19:23
 */
public class PetStoreException extends RuntimeException {

    private HttpStatusCode httpStatusCode;

    private String exceptionMsg;

    public PetStoreException(HttpStatusCode httpStatusCode, String exceptionMsg) {
        this.exceptionMsg = exceptionMsg;
        this.httpStatusCode = httpStatusCode;
        //HttpStatus.S
    }

    @Override
    public String getMessage() {
        return exceptionMsg;
    }

    @Override
    public String getLocalizedMessage() {
        return exceptionMsg;
    }

    public HttpStatusCode getHttpStatusCode() {
        return httpStatusCode;
    }
}
