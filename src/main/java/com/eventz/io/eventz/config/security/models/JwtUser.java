package com.eventz.io.eventz.config.security.models;

/**
 * Created by Michael.Akobundu on 4/2/2019.
 */
public class JwtUser {
    private String userName;
    private long id;
    private String role;

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUserName() {
        return userName;
    }

    public long getId() {
        return id;
    }

    public String getRole() {
        return role;
    }
}
