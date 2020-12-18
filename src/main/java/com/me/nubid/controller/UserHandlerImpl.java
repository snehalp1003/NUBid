/**
 * 
 */
package com.me.nubid.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.me.nubid.model.AdminUserView;
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
    public String getUserCreateForm(HttpServletRequest request) {
        return "user-create";
    }

    @Override
    public String addNewUser(HttpServletRequest request) throws IOException {
        if (request != null) {
            User user = new User();
            user.setUserEmailAddress(request.getParameter("newusername"));
            user.setUserPassword(request.getParameter("newpassword"));
            user.setUserFirstName(request.getParameter("newfname"));
            user.setUserLastName(request.getParameter("newlname"));
            user.setUserPhoneNum(request.getParameter("newphonenum"));
            user.setUserAddress(request.getParameter("newaddress"));
            user.setUserCollege(request.getParameter("newcollege"));
            user.setUserDept(request.getParameter("newdept"));
            user.setUserRole(request.getParameter("newrole"));
            if (UtilityService.checkStringNotNull(user.getUserEmailAddress())
                    && UtilityService
                            .checkStringNotNull(user.getUserPassword())) {
                if (!UtilityService
                        .checkIfValidEmail(user.getUserEmailAddress())) {
                    log.error(
                            "********** Email address not in correct format !! **********");
                    return "error-create";
                } else if (!UtilityService
                        .checkIfValidPassword(user.getUserPassword())) {
                    log.error(
                            "********** Password not in correct format !! **********");
                    return "error-create";
                } else if (!UtilityService
                        .checkIfValidPhoneNum(user.getUserPhoneNum())) {
                    log.error(
                            "********** Phone number not in correct format !! **********");
                    return "error-create";
                } else if (!UtilityService
                        .checkStringNotNull(user.getUserRole())) {
                    log.error("********** User Role is missing !! **********");
                } else {
                    try {
                        User userExists = databasePlugger
                                .getUserDetails(user.getUserEmailAddress());
                        if (userExists != null
                                && userExists.getUserEmailAddress()
                                        .equals(user.getUserEmailAddress())) {
                            return "error-create";
                        } else {
                            user.setUserUuid(UtilityService.generateUuid());
                            user.setUserPassword(UtilityService
                                    .hashPassword(user.getUserPassword()));
                            User newUser = databasePlugger.addNewUser(user);
                            if (newUser != null) {
                                return "user-createsuccessful";
                            }
                        }
                    } catch (Exception e) {
                        log.error(
                                "********** Error while registering user !! **********"
                                        + e.getMessage());
                    }
                }
            } else {
                log.error(
                        "********** Email address or password empty !! **********");
                return "error-create";
            }
        }

        log.error("********** Request is empty !! **********");
        return "error-create";
    }

    @Override
    public String getUserUpdateForm(HttpServletRequest request)
            throws IOException {
        if (request != null && request.getSession() != null
                && request.getSession().getAttribute("currentuser") != null) {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("currentuser");
            if (user.getUserRole().equals("admin")) {
                return "admin-update";
            }
            return "user-update";
        }
        log.error(
                "********** Request is empty or Session is invalid !! **********");
        return "error";
    }

    @Override
    public String updateUser(HttpServletRequest request) throws IOException {
        if (request != null && request.getSession() != null
                && request.getSession().getAttribute("currentuser") != null) {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("currentuser");
            user.setUserFirstName(request.getParameter("updatefname"));
            user.setUserLastName(request.getParameter("updatelname"));
            user.setUserPhoneNum(request.getParameter("updatephonenum"));
            user.setUserAddress(request.getParameter("updateaddress"));
            user.setUserCollege(request.getParameter("updatecollege"));
            user.setUserDept(request.getParameter("updatedept"));

            if (UtilityService.checkStringNotNull(user.getUserEmailAddress())) {
                if (!UtilityService
                        .checkIfValidEmail(user.getUserEmailAddress())) {
                    log.error(
                            "********** Email address not in correct format !! **********");
                    return "error";
                } else if (!UtilityService
                        .checkIfValidPhoneNum(user.getUserPhoneNum())) {
                    log.error(
                            "********** Phone number not in correct format !! **********");
                    return "error";
                } else {
                    try {
                        Boolean b = databasePlugger
                                .updateUser(user.getUserUuid(), user);
                        if (b) {
                            session.setAttribute("currentuser", user);
                            return "user-updatesuccessful";
                        } else {
                            return "error";
                        }
                    } catch (Exception e) {
                        log.error(
                                "********** Error while updating user details !! **********"
                                        + e.getMessage());
                    }
                }
            } else {
                log.error("********** Email address is empty !! **********");
                return "error";
            }
        }
        log.error(
                "********** Request is empty or Session is invalid !! **********");
        return "error";
    }

    @Override
    public String fetchUserDetails(HttpServletRequest request)
            throws IOException {
        if (request != null) {
            Login login = new Login();
            login.setUserEmailAddress(request.getParameter("username"));
            login.setUserPassword(request.getParameter("password"));
            login.setUserRole(request.getParameter("role"));

            if (UtilityService.checkStringNotNull(login.getUserEmailAddress())
                    && UtilityService
                            .checkStringNotNull(login.getUserPassword())
                    && UtilityService.checkStringNotNull(login.getUserRole())) {
                if (!UtilityService
                        .checkIfValidEmail(login.getUserEmailAddress())) {
                    log.error(
                            "********** Email address not in correct format !! **********");
                    return "error-login";
                } else {
                    try {
                        User u = databasePlugger.getUserDetails(login);
                        if (u != null
                                && UtilityService.checkPassword(
                                        login.getUserPassword(),
                                        u.getUserPassword())
                                && u.getUserRole()
                                        .equals(login.getUserRole())) {
                            HttpSession session = request.getSession(true);
                            session.setAttribute("currentuser", u);
                            if (u.getUserRole().equals("admin")) {
                                return "admin-dashboard";
                            }
                            return "user-dashboard";
                        } else if (u == null) {
                            log.error(
                                    "********** User not found !! **********");
                            return "error-login";
                        } else {
                            log.error(
                                    "********** Incorrect Role Selection or Password !! **********");
                            return "error-login";
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
                return "error-login";
            }
        }

        log.error(
                "********** Request is empty or Session is invalid !! **********");
        return "error-login";
    }

    @Override
    public String logoutUser(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "user-login";
    }

    @Override
    public String goBack(HttpServletRequest request) throws IOException {
        if (request != null && request.getSession() != null
                && request.getSession().getAttribute("currentuser") != null) {
            HttpSession session = request.getSession();
            User u = (User) session.getAttribute("currentuser");
            if (u.getUserRole().equals("admin")) {
                return "admin-dashboard";
            }
            return "user-dashboard";
        }
        log.error(
                "********** Request is empty or Session is invalid !! **********");
        return "error";
    }

    @Override
    public String getAllUsers(HttpServletRequest request) throws IOException {
        List<AdminUserView> allUsers = new ArrayList<AdminUserView>();
        if (request != null && request.getSession() != null
                && request.getSession().getAttribute("currentuser") != null) {
            HttpSession session = request.getSession();
            try {
                allUsers = databasePlugger.getAllUsers();
                session.setAttribute("allusers", allUsers);
                return "admin-allusers";
            } catch (Exception e) {
                log.error(
                        "********** Error while fetching all users !! **********"
                                + e.getMessage());
            }
        }
        log.error(
                "********** Request is empty or Session is invalid !! **********");
        return "error";
    }
}
