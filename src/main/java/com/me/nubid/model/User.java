package com.me.nubid.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author Snehal Patel
 */

@Entity
@Table(name="User")
public class User {
    
    @Id
    private String userUuid;
    private String userFirstName;
    private String userLastName;
    
    @Column(nullable=false, unique = true)
    private String userEmailAddress;
    
    @Column(nullable=false)
    private String userPassword;
    
    private String userAddress;
    private String userPhoneNum;
    private String userCollege;
    private String userDept;
    
    @Column(nullable=false) 
    private String userRole;
    
    @OneToMany(targetEntity = Product.class, cascade=CascadeType.ALL)
    @JoinColumn(name="prodSellerId" , referencedColumnName = "userUuid")
    private List<Product> prodsSold;
    
    @OneToMany(targetEntity = Product.class, cascade=CascadeType.ALL)
    @JoinColumn(name="prodBuyerId" , referencedColumnName = "userUuid")
    private List<Product> prodsBought;
    
    @OneToMany(targetEntity = Bid.class, cascade=CascadeType.ALL)
    @JoinColumn(name="bidderId" , referencedColumnName = "userUuid")
    private List<Bid> bidsPlaced;
    
    public String getUserUuid() {
        return userUuid;
    }
    public void setUserUuid(String userUuid) {
        this.userUuid = userUuid;
    }
    public String getUserFirstName() {
        return userFirstName;
    }
    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }
    public String getUserLastName() {
        return userLastName;
    }
    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }
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
    public String getUserAddress() {
        return userAddress;
    }
    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }
    public String getUserPhoneNum() {
        return userPhoneNum;
    }
    public void setUserPhoneNum(String userPhoneNum) {
        this.userPhoneNum = userPhoneNum;
    }
    public String getUserCollege() {
        return userCollege;
    }
    public void setUserCollege(String userCollege) {
        this.userCollege = userCollege;
    }
    public String getUserDept() {
        return userDept;
    }
    public void setUserDept(String userDept) {
        this.userDept = userDept;
    }
    public String getUserRole() {
        return userRole;
    }
    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }    
}
