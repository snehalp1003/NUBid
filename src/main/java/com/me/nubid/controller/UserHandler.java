package com.me.nubid.controller;

import java.io.IOException;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.me.nubid.model.Login;
import com.me.nubid.model.User;

/**
 * @author Snehal Patel
 */

@Controller
public interface UserHandler {

    @RequestMapping(value = "/v1/register/user/{userEmailAddress}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> addNewUser(
            @PathVariable(value = "userEmailAddress") String userEmailAddress,
            @RequestBody User user) throws IOException;

    @RequestMapping(value = "/v1/update/user", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> updateUser(
            @RequestBody User user) throws IOException;

    @RequestMapping(value = "/v1/get/user", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> fetchUserDetails(
            @RequestBody Login login)
            throws IOException;
}
