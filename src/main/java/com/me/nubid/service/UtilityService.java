/**
 * 
 */
package com.me.nubid.service;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.context.annotation.Configuration;

/**
 * @author Snehal Patel
 */

public class UtilityService {
    
    static final String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    static final String passwordRegex = "((?=.*[a-z])(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%!]).{8,40})";
    static final String phoneNumRegex = "^\\d{10}$";
    
    public static boolean checkStringNotNull(String in) {
        boolean output = false;
        if (in != null && !in.isEmpty() && !in.equals("") && in.trim().length() != 0) {
            output = true;
        }
        return output;
    }
    
    public static boolean checkIfValidEmail(String email) {
//        boolean output = false;
//        
//        EmailValidator validator = EmailValidator.getInstance();
//        
//        Pattern pattern = Pattern.compile(emailRegex);
//        Matcher matcher = pattern.matcher(email);
//        output = matcher.matches();
        return true;
    }
    
    public static boolean checkIfValidPassword(String plainTextPassword) {
//        boolean output = false;
//        
//        Pattern pattern = Pattern.compile(passwordRegex);
//        Matcher matcher = pattern.matcher(plainTextPassword);
//        output = matcher.matches();
        return true;
    }
    
    public static boolean checkIfValidPhoneNum(String phoneNum) {
        boolean output = false;
        
        Pattern pattern = Pattern.compile(phoneNumRegex);
        Matcher matcher = pattern.matcher(phoneNum);
        output = matcher.matches();
        return output;
    }
    
    public static String hashPassword(String plainTextPassword){
        return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
    }
    
    public static boolean checkPassword(String plainPassword, String hashedPassword) {
        if (BCrypt.checkpw(plainPassword, hashedPassword))
            return true;
        else
            return false;
    }
    
    public static String generateUuid() {
        return UUID.randomUUID().toString();
    }
}
