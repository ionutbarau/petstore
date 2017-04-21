package com.petstore.enums;

/**
 * Enum that holds http status codes.
 * <p>
 * User: Ionut Barau (ionutbarau)
 * Project: petstore
 * Date: 21/04/2017.
 * Time: 19:34
 */
public enum HttpStatusCode {

    HTTP_200(200), HTTP_400(400), HTTP_404(404), HTTP_405(405);

    private final int value;

    HttpStatusCode(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
