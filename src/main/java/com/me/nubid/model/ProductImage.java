package com.me.nubid.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Snehal Patel
 */

@Entity
@Table(name="ProductImage")
public class ProductImage {

    @Id
    private String imageId;
    
    @Column(nullable=false)
    private String prodId;
    
    @Column(nullable=false)
    private String imgLocation;
    
    public String getImageId() {
        return imageId;
    }
    public void setImageId(String imageId) {
        this.imageId = imageId;
    }
    public String getProdId() {
        return prodId;
    }
    public void setProdId(String prodId) {
        this.prodId = prodId;
    }
    public String getImgLocation() {
        return imgLocation;
    }
    public void setImgLocation(String imgLocation) {
        this.imgLocation = imgLocation;
    }
}
