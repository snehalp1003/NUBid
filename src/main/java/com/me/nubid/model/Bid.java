package com.me.nubid.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Snehal Patel
 */

@Entity
@Table(name="Bid")
public class Bid {

    @Id
    private String bidId;
    private String bidderId;
    private String bidProdId;
    
    @Column(nullable=false)
    private Double bidPrice;
    private Date bidDate;
    
    public String getBidId() {
        return bidId;
    }
    public void setBidId(String bidId) {
        this.bidId = bidId;
    }
    public String getBidderId() {
        return bidderId;
    }
    public void setBidderId(String bidderId) {
        this.bidderId = bidderId;
    }
    public String getBidProdId() {
        return bidProdId;
    }
    public void setBidProdId(String bidProdId) {
        this.bidProdId = bidProdId;
    }
    public Double getBidPrice() {
        return bidPrice;
    }
    public void setBidPrice(Double bidPrice) {
        this.bidPrice = bidPrice;
    }
    public Date getBidDate() {
        return bidDate;
    }
    public void setBidDate(Date bidDate) {
        this.bidDate = bidDate;
    }
}
