package com.starterkit.springboot.brs.controller.v1.api;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.starterkit.springboot.brs.controller.v1.request.UserSignupRequest;
import com.starterkit.springboot.brs.dto.model.user.UserDto;
import com.starterkit.springboot.brs.service.MyFeinClient;
import com.starterkit.springboot.brs.service.UserService;

import io.swagger.annotations.Api;
import com.starterkit.springboot.brs.dto.response.Response;


/**
 * Created by Arpit Khandelwal.
 */
@RestController
@RequestMapping("/api/v1/user")
@Api(value = "brs-application", description = "Operations pertaining to user management in the BRS application")
public class UserController {
    @Autowired
    private UserService userService;
    
    @Autowired
    private MyFeinClient myFeinClient;

    /**
     * Handles the incoming POST API "/v1/user/signup"
     *
     * @param userSignupRequest
     * @return
     */
    @PostMapping("/signup")
    public Response signup(@RequestBody @Valid UserSignupRequest userSignupRequest) {


        return Response.ok().setPayload(registerUser(userSignupRequest, false));
    }
    
    
    @PostMapping("/signupfein")
    public void signupfein(@RequestBody @Valid UserSignupRequest userSignupRequest) {
    	
    	// Call API
    	myFeinClient.signUp(userSignupRequest);
    	
    	
    }
    
    /*
    @PostMapping("/signup")
    public Response signup(@RequestBody @Valid UserSignupRequest userSignupRequest) {

        return Response.ok().setPayload(registerUser(userSignupRequest, false));
    }*/
    
    


    /**
     * Register a new user in the database
     *
     * @param userSignupRequest
     * @return
     */
    private UserDto registerUser(UserSignupRequest userSignupRequest, boolean isAdmin) {
        UserDto userDto = new UserDto()
                .setEmail(userSignupRequest.getEmail())
                .setPassword(userSignupRequest.getPassword())
                .setFirstName(userSignupRequest.getFirstName())
                .setLastName(userSignupRequest.getLastName())
                .setMobileNumber(userSignupRequest.getMobileNumber())
                .setAdmin(isAdmin);

        return userService.signup(userDto);
    }
}
