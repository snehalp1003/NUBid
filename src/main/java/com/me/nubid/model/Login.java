/**
 * 
 */
package com.me.nubid.model;

/**
 * @author Snehal Patel
 */
public class Login {
    
    private String userEmailAddress;
    private String userPassword;
    private String userRole;
    
    public String getUserEmailAddress() {
        return userEmailAddress;
    }
    public void setUserEmailAddress(String userEmailAddress) {
        this.userEmailAddress = userEmailAddress;
    }
    public String getUserPassword() {
        return userPassword;
    }
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
    public String getUserRole() {
        return userRole;
    }
    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }    
}
