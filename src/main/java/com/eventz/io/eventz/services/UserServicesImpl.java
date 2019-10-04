package com.eventz.io.eventz.services;

import com.eventz.io.eventz.dao.UserDao;
import com.eventz.io.eventz.exceptions.InvalidParameterException;
import com.eventz.io.eventz.exceptions.UserNotFoundException;
import com.eventz.io.eventz.models.request.AuthRequest;
import com.eventz.io.eventz.models.response.AuthResponse;
import com.eventz.io.eventz.models.response.ResponseCodes;
import com.eventz.io.eventz.models.response.User;
import com.eventz.io.eventz.models.response.CreateResponse;
import com.eventz.io.eventz.services.contract.UserServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;



/**
 * Created by Michael.Akobundu on 3/10/2019.
 */
@Service
public class UserServicesImpl implements UserServices{

    private final UserDao userDao;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public UserServicesImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public CreateResponse createUser(User request) {

        CreateResponse response = new CreateResponse();
        this.checkUserCredentials(request);
        if(request.getPassword() == null || !StringUtils.hasText(request.getPassword())) {
            throw new InvalidParameterException("Password must not be empty");
        }
        request.setPassword(new BCryptPasswordEncoder().encode(request.getPassword()));
        User createdUser = userDao.create(request);

        response.setCode(ResponseCodes.SUCCESS.getCode());
        response.setMessage(ResponseCodes.SUCCESS.getMessage());
        response.setUser(createdUser);
        return response;
    }

    public AuthResponse authenticate(AuthRequest authRequest) {
        if(authRequest.getUsername() != null && authRequest.getPassword() != null) {
           logger.info("Encrypted password: "+ new BCryptPasswordEncoder().encode(authRequest.getPassword()));
           User userDetails = userDao.authenticate(authRequest.getUsername(), new BCryptPasswordEncoder().encode(authRequest.getPassword()));
           if(userDetails != null) {
               AuthResponse response = new AuthResponse();
               response.setResponseCode(ResponseCodes.SUCCESS.getCode());
               response.setResponseMessage(ResponseCodes.SUCCESS.getMessage());
               response.setUserdetails(userDetails);
               return response;
           }
           throw new UserNotFoundException("Invalid username or password");
        }
        throw new InvalidParameterException("Username and password is required for this request");
    }

    public void updateUser(User updateRequest) {
        this.checkUserCredentials(updateRequest);
        userDao.update(updateRequest);
    }

    private void checkUserCredentials(User request) {
        if(request.getFirstName() == null || !StringUtils.hasText(request.getFirstName())) {
            throw new InvalidParameterException("Firstname must not be empty");
        }
        if(request.getLastName() == null || !StringUtils.hasText(request.getLastName())) {
            throw new InvalidParameterException("Lastname must not be empty");
        }
        if(request.getEmail() == null || !StringUtils.hasText(request.getEmail())) {
            throw new InvalidParameterException("Email address must not be empty");
        }
        if(request.getUserName() == null || !StringUtils.hasText(request.getUserName())) {
            throw new InvalidParameterException("Username must not be empty");
        }
        if(request.getPhoneNumber() == null || !StringUtils.hasText(request.getPhoneNumber())) {
            throw new InvalidParameterException("Phone number must not be empty");
        }
    }

}
