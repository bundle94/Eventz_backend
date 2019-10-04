package com.eventz.io.eventz.models.response;

/**
 * Created by Michael.Akobundu on 3/10/2019.
 */
public enum ResponseCodes {
    SUCCESS("00", "Successful"),
    BAD_REQUEST("104", "Bad Request"),
    INSUFFICIENT_PERMISSION("403", "Insufficient Permission."),
    SYSTEM_ERROR("500", "Something went wrong. Please try again later"),
    UNAUTHORIZED("401", "Unauthorized Request");


    private String code;
    private String message;

    ResponseCodes(String code, String message){
        this.code = code;
        this.message = message;
    }

    public String getCode(){
        return this.code;
    }

    public String getMessage(){
        return this.message;
    }
}
