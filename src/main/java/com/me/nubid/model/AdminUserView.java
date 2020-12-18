/**
 * 
 */
package com.me.nubid.model;

/**
 * @author Snehal Patel
 */
public class AdminUserView {
    private String userEmail;
    private String userFname;
    private String userLname;
    
    public String getUserEmail() {
        return userEmail;
    }
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
    public String getUserFname() {
        return userFname;
    }
    public void setUserFname(String userFname) {
        this.userFname = userFname;
    }
    public String getUserLname() {
        return userLname;
    }
    public void setUserLname(String userLname) {
        this.userLname = userLname;
    }  
}
