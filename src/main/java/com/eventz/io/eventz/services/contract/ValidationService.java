package com.eventz.io.eventz.services.contract;

/**
 * Created by Michael.Akobundu on 4/5/2019.
 */
public interface ValidationService {
    boolean validateEmail(String email);
    boolean validatePhoneNumber(String phoneNumber);
    boolean validateUsername(String username);
}
