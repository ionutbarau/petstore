package com.petstore.exceptions;

import java.io.Serializable;

/**
 * User: Ionut Barau (ionutbarau)
 * Project: petstore
 * Date: 22/04/2017.
 * Time: 13:14
 */
public class PetStoreExceptionInformation implements Serializable {

    private String msg;

    public PetStoreExceptionInformation(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
