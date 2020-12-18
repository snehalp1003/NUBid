package com.me.nubid.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Snehal Patel
 */

@Entity
@Table(name="Product")
public class Product {
    
    @Id
    private String prodId;
    
    @Column(nullable=false)
    private String prodName;
    
    @Column(nullable=false)
    private String prodCategory;
    private String prodDesc;
    private String prodSellerId;
    
    @Column(nullable=false)
    private Double prodMinPrice;
    private Date prodStartDate;
    private Date prodEndDate;
    private String prodBuyerId;
    private Double prodFinalPrice;
    private String prodImgPath;
    
    public String getProdId() {
        return prodId;
    }
    public void setProdId(String prodId) {
        this.prodId = prodId;
    }
    public String getProdName() {
        return prodName;
    }
    public void setProdName(String prodName) {
        this.prodName = prodName;
    }
    public String getProdCategory() {
        return prodCategory;
    }
    public void setProdCategory(String prodCategory) {
        this.prodCategory = prodCategory;
    }
    public String getProdDesc() {
        return prodDesc;
    }
    public void setProdDesc(String prodDesc) {
        this.prodDesc = prodDesc;
    }
    public String getProdSellerId() {
        return prodSellerId;
    }
    public void setProdSellerId(String prodSellerId) {
        this.prodSellerId = prodSellerId;
    }
    public Double getProdMinPrice() {
        return prodMinPrice;
    }
    public void setProdMinPrice(Double prodMinPrice) {
        this.prodMinPrice = prodMinPrice;
    }
    public Date getProdStartDate() {
        return prodStartDate;
    }
    public void setProdStartDate(Date prodStartDate) {
        this.prodStartDate = prodStartDate;
    }
    public Date getProdEndDate() {
        return prodEndDate;
    }
    public void setProdEndDate(Date prodEndDate) {
        this.prodEndDate = prodEndDate;
    }
    public String getProdBuyerId() {
        return prodBuyerId;
    }
    public void setProdBuyerId(String prodBuyerId) {
        this.prodBuyerId = prodBuyerId;
    }
    public Double getProdFinalPrice() {
        return prodFinalPrice;
    }
    public void setProdFinalPrice(Double prodFinalPrice) {
        this.prodFinalPrice = prodFinalPrice;
    }
    public String getProdImgPath() {
        return prodImgPath;
    }
    public void setProdImgPath(String prodImgPath) {
        this.prodImgPath = prodImgPath;
    }
}
