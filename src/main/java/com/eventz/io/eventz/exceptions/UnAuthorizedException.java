package com.eventz.io.eventz.exceptions;

/**
 * Created by Michael.Akobundu on 4/2/2019.
 */
public class UnAuthorizedException extends RuntimeException {

    private String code = null;
    public UnAuthorizedException(String message) {
        super(message);
    }

    public UnAuthorizedException(String code, String message) {
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
