package com.eventz.io.eventz.services;

import com.eventz.io.eventz.dao.UserDao;
import com.eventz.io.eventz.exceptions.InvalidParameterException;
import com.eventz.io.eventz.exceptions.UserAlreadyExistsException;
import com.eventz.io.eventz.models.response.User;
import com.eventz.io.eventz.services.contract.ValidationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * Created by Michael.Akobundu on 4/5/2019.
 */
@Service
public class ValidationServiceImpl implements ValidationService {
    private final UserDao userDao;
    private final Logger logger = LoggerFactory.getLogger(ValidationServiceImpl.class);

    @Autowired
    public ValidationServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    public boolean validateEmail(String email) {
        if(email != null && StringUtils.hasText(email)) {
            User user = userDao.findUserByEmail(email);
            if(user != null) throw new UserAlreadyExistsException(String.format("User already exists with email: %s", email));
            return true;
        }
        throw new InvalidParameterException("Email address cannot be empty");
    }

    public boolean validatePhoneNumber(String phoneNumber) {
        if(phoneNumber != null && StringUtils.hasText(phoneNumber)) {
            User user = userDao.findUserByPhoneNumber(phoneNumber);
            if(user != null) throw new UserAlreadyExistsException(String.format("User already exists with phone number: %s", phoneNumber));
            return true;
        }
        throw new InvalidParameterException("Phone number cannot be empty");
    }

    public boolean validateUsername(String username) {
        if(username != null && StringUtils.hasText(username)) {
            User user = userDao.findUserByUsername(username);
            if(user != null) throw new UserAlreadyExistsException(String.format("User already exists with username: %s", username));
            return true;
        }
        throw new InvalidParameterException("Username cannot be empty");
    }
}
