/**
 * 
 */
package com.me.nubid.controller;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.me.nubid.model.Login;
import com.me.nubid.model.User;
import com.me.nubid.service.DatabasePlugger;
import com.me.nubid.service.UtilityService;

/**
 * @author Snehal Patel
 */

@Controller
public class UserHandlerImpl implements UserHandler {

    private static final Logger log = LoggerFactory
            .getLogger(UserHandlerImpl.class);

    @Autowired
    DatabasePlugger databasePlugger;

    @Override
    public ResponseEntity<User> addNewUser(
            @RequestBody User user) throws IOException {
        if (UtilityService.checkStringNotNull(user.getUserEmailAddress())
                && UtilityService.checkStringNotNull(user.getUserPassword())) {
            if (!UtilityService.checkIfValidEmail(user.getUserEmailAddress())) {
                log.error(
                        "********** Email address not in correct format !! **********");
                return new ResponseEntity(
                        "Email address not in correct format !!",
                        HttpStatus.BAD_REQUEST);
            } else if (!UtilityService
                    .checkIfValidPassword(user.getUserPassword())) {
                log.error(
                        "********** Password not in correct format !! **********");
                return new ResponseEntity("Password not in correct format !!",
                        HttpStatus.BAD_REQUEST);
            } else if (!UtilityService
                    .checkIfValidPhoneNum(user.getUserPhoneNum())) {
                log.error(
                        "********** Phone number not in correct format !! **********");
                return new ResponseEntity(
                        "Phone number not in correct format !!",
                        HttpStatus.BAD_REQUEST);
            } else {
                try {
                    user.setUserUuid(UtilityService.generateUuid());
                    user.setUserPassword(UtilityService
                            .hashPassword(user.getUserPassword()));
                    return new ResponseEntity<User>(
                            databasePlugger.addNewUser(user), HttpStatus.OK);
                } catch (Exception e) {
                    log.error(
                            "********** Error while registering user !! **********"
                                    + e.getMessage());
                }
            }
        } else {
            log.error(
                    "********** Email address or password empty !! **********");
            return new ResponseEntity("Email address or password empty !!",
                    HttpStatus.BAD_REQUEST);
        }
        return null;
    }

    @Override
    public ResponseEntity<Boolean> updateUser(
            @RequestBody User user) throws IOException {
        if (UtilityService.checkStringNotNull(user.getUserEmailAddress())) {
            if (!UtilityService.checkIfValidEmail(user.getUserEmailAddress())) {
                log.error(
                        "********** Email address not in correct format !! **********");
                return new ResponseEntity(
                        "Email address not in correct format !!",
                        HttpStatus.BAD_REQUEST);
            } else if (!UtilityService
                    .checkIfValidPhoneNum(user.getUserPhoneNum())) {
                log.error(
                        "********** Phone number not in correct format !! **********");
                return new ResponseEntity(
                        "Phone number not in correct format !!",
                        HttpStatus.BAD_REQUEST);
            } else {
                try {
                    return new ResponseEntity<Boolean>(
                            databasePlugger.updateUser(user.getUserUuid(), user),
                            HttpStatus.OK);
                } catch (Exception e) {
                    log.error(
                            "********** Error while updating user details !! **********"
                                    + e.getMessage());
                }
            }
        } else {
            log.error("********** Email address is empty !! **********");
            return new ResponseEntity("Email address is empty !!",
                    HttpStatus.BAD_REQUEST);
        }
        return null;
    }

    @Override
    public ResponseEntity<User> fetchUserDetails(@RequestBody Login login)
            throws IOException {
        if (UtilityService.checkStringNotNull(login.getUserEmailAddress())
                && UtilityService.checkStringNotNull(login.getUserPassword())) {
            if (!UtilityService
                    .checkIfValidEmail(login.getUserEmailAddress())) {
                log.error(
                        "********** Email address not in correct format !! **********");
                return new ResponseEntity(
                        "Email address not in correct format !!",
                        HttpStatus.BAD_REQUEST);
            } else {
                try {
                    User u = databasePlugger.getUserDetails(login);
                    if (UtilityService.checkPassword(login.getUserPassword(),
                            u.getUserPassword())) {
                        return new ResponseEntity<User>(u, HttpStatus.OK);
                    } else {
                        return new ResponseEntity(
                                "***** Password Incorrect *****",
                                HttpStatus.BAD_REQUEST);
                    }
                } catch (Exception e) {
                    log.error(
                            "********** Error while fetching user details !! **********"
                                    + e.getMessage());
                }
            }
        } else {
            log.error(
                    "********** Email address or password is empty !! **********");
            return new ResponseEntity("Email address or password is empty !!",
                    HttpStatus.BAD_REQUEST);
        }
        return null;
    }

}
