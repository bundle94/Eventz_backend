package com.eventz.io.eventz.models.response;

/**
 * Created by Michael.Akobundu on 3/10/2019.
 */
public class CreateResponse {
    private String code;
    private String message;
    private User user;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
