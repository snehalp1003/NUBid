package com.me.nubid.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Snehal Patel
 */

@Entity
@Table(name="ProductImage")
public class ProductImage {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String imageId;
    private String prodId;
    
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
}
