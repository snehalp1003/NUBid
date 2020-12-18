/**
 * 
 */
package com.me.nubid.service;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.mindrot.jbcrypt.BCrypt;

/**
 * @author Snehal Patel
 */

public class UtilityService {

//    static final String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    static final String emailRegex = "^[a-zA-Z0-9_]+(@northeastern\\.edu)$";
//    static final String passwordRegex = "((?=.*[a-z])(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%!]).{8,40})";
    static final String passwordRegex = "((?=.*[a-z])(?=.*[0-9])(?=.*[A-Z])(?=.*[*@#$%!()&]).{8,40})";
    static final String phoneNumRegex = "^\\d{10}$";
    static final String priceRegex = "[0-9]+([,.][0-9]{1,2})?";

    public static boolean checkStringNotNull(String in) {
        boolean output = false;
        if (in != null && !in.isEmpty() && !in.equals("")
                && in.trim().length() != 0) {
            output = true;
        }
        return output;
    }

    public static boolean checkIfValidEmail(String email) {
        boolean output = false;

        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        output = matcher.matches();
        boolean emailLength = false;
        String[] passBreak = email.split("@", 2);
        if (passBreak[0].length() > 2 && passBreak[0].length() < 9) {
            emailLength = true;
        }

        if (output == true && emailLength == true) {
            return true;
        }
        return false;
    }

    public static boolean checkIfValidPassword(String plainTextPassword) {
        boolean output = false;

        Pattern pattern = Pattern.compile(passwordRegex);
        Matcher matcher = pattern.matcher(plainTextPassword);
        output = matcher.matches();
        return output;
    }

    public static boolean checkIfValidPhoneNum(String phoneNum) {
        boolean output = false;

        Pattern pattern = Pattern.compile(phoneNumRegex);
        Matcher matcher = pattern.matcher(phoneNum);
        output = matcher.matches();
        return output;
    }

    public static String hashPassword(String plainTextPassword) {
        return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
    }

    public static boolean checkPassword(String plainPassword,
            String hashedPassword) {
        if (BCrypt.checkpw(plainPassword, hashedPassword))
            return true;
        else
            return false;
    }

    public static String generateUuid() {
        return UUID.randomUUID().toString();
    }

    public static boolean checkIfValidPrice(String price) {
        boolean output = false;

        Pattern pattern = Pattern.compile(priceRegex);
        Matcher matcher = pattern.matcher(price);
        output = matcher.matches();
        return output;
    }

    public static void SendEmail(String buyerEmail) throws EmailException {
        Email email = new SimpleEmail();
        email.setHostName("smtp.googlemail.com");
        email.setSmtpPort(465);
        email.setAuthenticator(
                new DefaultAuthenticator("snailpatel2", "stupidhacker-_-"));
        email.setSSLOnConnect(true);
        email.setFrom("snailpatel2@gmail.com");
        email.setSubject("Congratulations ! Bid Won !");
        email.setMsg("The item has been sold to you at your bid price !");
        email.addTo("snehalp1003@gmail.com");
        email.send();
    }
}
