/**
 * 
 */
package com.me.nubid.model;

import java.util.Date;

/**
 * @author Snehal Patel
 */
public class AdminBidView {
    
    private String sellerEmail;
    private String prodName;
    private String prodCategory;
    private Double bidStartPrice;
    private Date bidStartDate;
    private String buyerEmail;
    private Double bidFinalPrice;
    private Date bidEndDate;
    
    public String getSellerEmail() {
        return sellerEmail;
    }
    public void setSellerEmail(String sellerEmail) {
        this.sellerEmail = sellerEmail;
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
    public Double getBidStartPrice() {
        return bidStartPrice;
    }
    public void setBidStartPrice(Double bidStartPrice) {
        this.bidStartPrice = bidStartPrice;
    }
    public Date getBidStartDate() {
        return bidStartDate;
    }
    public void setBidStartDate(Date bidStartDate) {
        this.bidStartDate = bidStartDate;
    }
    public String getBuyerEmail() {
        return buyerEmail;
    }
    public void setBuyerEmail(String buyerEmail) {
        this.buyerEmail = buyerEmail;
    }
    public Double getBidFinalPrice() {
        return bidFinalPrice;
    }
    public void setBidFinalPrice(Double bidFinalPrice) {
        this.bidFinalPrice = bidFinalPrice;
    }
    public Date getBidEndDate() {
        return bidEndDate;
    }
    public void setBidEndDate(Date bidEndDate) {
        this.bidEndDate = bidEndDate;
    }
}
