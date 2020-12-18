/**
 * 
 */
package com.me.nubid.dao;

import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.me.nubid.model.Product;
import com.me.nubid.model.ProductImage;

/**
 * @author Snehal Patel
 */
public class ProductImageDao extends Dao {
    
    private static final Logger log = LoggerFactory.getLogger(ProductImageDao.class);
    
    public ProductImage addProductImage(ProductImage i) {
        try {
            begin();
            ProductImage img = new ProductImage();
            img.setImageId(i.getImageId());
            img.setProdId(i.getProdId());
            img.setImgLocation(i.getImgLocation());
            getSession().save(i);
            commit();
            close();
            return img;
        } catch (Exception e) {
            rollback();
            log.error("Error while inserting new product image into the database");
        }
        return null;
    }
    
    public ProductImage getProductImage(String prodId) {
        try {
            Query query;
            begin();
            query = getSession().createQuery("from ProductImage where prodID=:id");
            query.setParameter("id", prodId);
            ProductImage img = (ProductImage) query.uniqueResult();

            if (null != img) {
                commit();
                close();
                return img;
            } else {
                rollback();
                log.error("Product Image does not exist");
            }
        } catch (Exception e) {
            rollback();
            log.error("Error while finding the image for product");
        }
        return null;
    }
}
