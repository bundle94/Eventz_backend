package com.eventz.io.eventz.exceptions;

/**
 * Created by Michael.Akobundu on 4/4/2019.
 */
public class UserNotFoundException extends RuntimeException {
    private String code = null;
    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(String code, String message) {
        super(message);
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
