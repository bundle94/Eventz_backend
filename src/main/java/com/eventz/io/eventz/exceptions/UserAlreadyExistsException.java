package com.eventz.io.eventz.exceptions;

/**
 * Created by Michael.Akobundu on 4/5/2019.
 */
public class UserAlreadyExistsException extends RuntimeException{
    private String code = null;
    public UserAlreadyExistsException(String message) {
        super(message);
    }

    public UserAlreadyExistsException(String code, String message) {
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
