package com.eventz.io.eventz.dao;

import com.eventz.io.eventz.models.request.AuthRequest;
import com.eventz.io.eventz.models.response.User;

/**
 * Created by Michael.Akobundu on 4/4/2019.
 */
public interface UserDao extends BaseDao<User> {
    User authenticate(String username, String password);
    User findUserByEmail(String email);
    User findUserByPhoneNumber(String phoneNumber);
    User findUserByUsername(String username);
}
