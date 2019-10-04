package com.eventz.io.eventz.controllers.v1;


import com.eventz.io.eventz.config.cache.EventzRedisCache;
import com.eventz.io.eventz.config.cache.MemoryCache;
import com.eventz.io.eventz.models.request.AuthRequest;
import com.eventz.io.eventz.models.request.CreateRequest;
import com.eventz.io.eventz.models.response.AuthResponse;
import com.eventz.io.eventz.models.response.CreateResponse;
import com.eventz.io.eventz.models.response.User;
import com.eventz.io.eventz.services.contract.UserServices;
import com.eventz.io.eventz.services.contract.ValidationService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**
 * Created by Michael.Akobundu on 3/9/2019.
 */
@RestController
@RequestMapping("/apis/v1/user")
public class UserController {

    private final UserServices userServices;
    private final ValidationService validationService;
    private final MemoryCache memoryCache;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public UserController(UserServices userServices, ValidationService validationService, EventzRedisCache memoryCache) {
        this.userServices = userServices;
        this.validationService = validationService;
        this.memoryCache = memoryCache;
    }


    @RequestMapping(name="CREATE USER", value="/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public CreateResponse create(@RequestBody @Validated User createRequest) throws Exception {
        validationService.validateEmail(createRequest.getEmail());
        validationService.validateUsername(createRequest.getUserName());
        validationService.validatePhoneNumber(createRequest.getPhoneNumber());

        return userServices.createUser(createRequest);

    }

    @RequestMapping(name="UPDATE USER", value="/update", method=RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @ApiOperation(value = "UPDATE", notes = "Lorem ipsum hdhjfhjf dhkdhdkhdkhdkhkd dkhkfhkhfkhf khdkhdkhdkhkd kdhkdhdhdhjdh " +
            "dkdhkdkdhkhf kjfkhfkfhfhf khkfhkhfkhfhf khfkfhkfkfh khfkhfkhkhf khfkhfkhfkfh")
    public void update(@RequestBody @Validated User updateRequest) throws Exception {
        userServices.updateUser(updateRequest);
    }

    @RequestMapping(name="AUTHENTICATE USER", value="/auth", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public AuthResponse authenticate(@RequestBody @Validated AuthRequest authRequest) throws Exception {
        return userServices.authenticate(authRequest);
    }

    @RequestMapping(name="TESTCACHE", value="/testCache", method = RequestMethod.GET)
    @ResponseBody
    public String testcache() throws Exception {
        memoryCache.insertToCache("Newone", "This is a new one");
        return "Inserted!!";
    }
}
