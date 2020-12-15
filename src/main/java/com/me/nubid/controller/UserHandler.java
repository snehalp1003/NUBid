package com.me.nubid.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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

    @RequestMapping(value = "/v1/user/create.htm", method = RequestMethod.GET)
    public String getUserCreateForm(HttpServletRequest request)
            throws IOException;

    @RequestMapping(value = "/v1/user/register.htm", method = RequestMethod.POST)
    public String addNewUser(HttpServletRequest request) throws IOException;

    @RequestMapping(value = "/v1/user/update/create.htm", method = RequestMethod.GET)
    public String getUserUpdateForm(HttpServletRequest request)
            throws IOException;

    @RequestMapping(value = "/v1/user/update.htm", method = RequestMethod.POST)
    public String updateUser(HttpServletRequest request) throws IOException;

    @RequestMapping(value = "/v1/user/home.htm", method = RequestMethod.POST)
    public String fetchUserDetails(HttpServletRequest request)
            throws IOException;

    @RequestMapping(value = "/v1/user/logout.htm", method = RequestMethod.GET)
    public String logoutUser(HttpServletRequest request) throws IOException;

    @RequestMapping(value = "/v1/user/home.htm", method = RequestMethod.GET)
    public String goBack(HttpServletRequest request) throws IOException;
}
