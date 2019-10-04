package com.eventz.io.eventz.exceptions;

/**
 * Created by Michael.Akobundu on 3/10/2019.
 */
public class RequestException extends RuntimeException{
    private String code;

    public RequestException(String message) {
        super(message);
    }

    public RequestException(String code, String message) {
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
