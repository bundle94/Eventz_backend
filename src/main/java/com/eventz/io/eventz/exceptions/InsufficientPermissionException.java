package com.eventz.io.eventz.exceptions;

import com.eventz.io.eventz.models.response.ResponseCodes;

/**
 * Created by Michael.Akobundu on 3/10/2019.
 */
public class InsufficientPermissionException extends RuntimeException {
    private String code = null;

    public InsufficientPermissionException(String message) {
        super(message);
    }

    public InsufficientPermissionException(String code, String message) {
        super(message);
        this.code = code;
    }

    public InsufficientPermissionException(ResponseCodes responseCode) {
        super(responseCode.getMessage());
        this.code = responseCode.getCode();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
