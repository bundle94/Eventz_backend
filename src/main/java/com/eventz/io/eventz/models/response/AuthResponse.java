package com.eventz.io.eventz.models.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Michael.Akobundu on 4/4/2019.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AuthResponse {
    private String responseCode;
    private String responseMessage;
    private User userdetails;

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public User getUserdetails() {
        return userdetails;
    }

    public void setUserdetails(User userdetails) {
        this.userdetails = userdetails;
    }
}
