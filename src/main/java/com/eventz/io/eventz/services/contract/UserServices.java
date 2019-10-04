package com.eventz.io.eventz.services.contract;

import com.eventz.io.eventz.models.request.AuthRequest;
import com.eventz.io.eventz.models.request.CreateRequest;
import com.eventz.io.eventz.models.response.AuthResponse;
import com.eventz.io.eventz.models.response.User;
import com.eventz.io.eventz.models.response.CreateResponse;

/**
 * Created by Michael.Akobundu on 3/10/2019.
 */
public interface UserServices {
    CreateResponse createUser(User request);
    void updateUser(User updateRequest);
    AuthResponse authenticate(AuthRequest authRequest);
}
