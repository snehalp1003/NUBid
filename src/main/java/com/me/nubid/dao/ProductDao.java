/**
 * 
 */
package com.me.nubid.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.me.nubid.model.Product;

/**
 * @author Snehal Patel
 */
public class ProductDao extends Dao {

    private static final Logger log = LoggerFactory.getLogger(ProductDao.class);

    public Product addProduct(Product p) {
        try {
            begin();
            Product prod = new Product();
            prod.setProdId(p.getProdId());
            prod.setProdName(p.getProdName());
            prod.setProdCategory(p.getProdCategory());
            prod.setProdDesc(p.getProdDesc());
            prod.setProdSellerId(p.getProdSellerId());
            prod.setProdMinPrice(p.getProdMinPrice());
            prod.setProdStartDate(p.getProdStartDate());
            prod.setProdEndDate(p.getProdEndDate());
            prod.setProdBuyerId(p.getProdBuyerId());
            prod.setProdFinalPrice(p.getProdFinalPrice());
            getSession().save(p);
            commit();
            return prod;
        } catch (Exception e) {
            rollback();
            log.error("Error while inserting new product into the database");
        }
        return null;
    }

    public boolean updateProduct(String prodId, Product prod) {
        try {
            begin();

            Product p = (Product) getSession().load(Product.class, prodId);
            if (null != p) {
                getSession().update(prodId, prod);
                commit();
                return true;
            } else {
                rollback();
                return false;
            }
        } catch (Exception e) {
            rollback();
            log.error("Error while updating product into the database");
        }
        return false;
    }

    public Product getProduct(String prodId) {
        try {
            Query query;
            begin();
            query = getSession().createQuery("from Product where prodID=:id");
            query.setParameter("id", prodId);
            Product prod = (Product) query.uniqueResult();

            if (null != prod) {
                commit();
                return prod;
            } else {
                rollback();
                log.error("Product does not exist");
            }
        } catch (Exception e) {
            rollback();
            log.error("Error while finding the product");
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public List<Product> viewProductsForPurchase(String currentUserUuid) {
        try {
            Query query;
            begin();
            query = getSession()
                    .createQuery("from Product where prodSellerId!=:id");
            query.setParameter("id", currentUserUuid);
            List<Product> prodsForPurchase = query.list();
            commit();
            return prodsForPurchase;
        } catch (HibernateException e) {
            rollback();
            System.out.println("Could not list Products");
        }
        return new ArrayList<Product>();
    }

    @SuppressWarnings("unchecked")
    public List<Product> viewProductsForSelling(String currentUserUuid) {
        try {
            Query query;
            begin();
            query = getSession()
                    .createQuery("from Product where prodSellerId=:id");
            query.setParameter("id", currentUserUuid);
            List<Product> prodsPlacedForBidding = query.list();
            commit();
            return prodsPlacedForBidding;
        } catch (HibernateException e) {
            rollback();
            System.out.println("Could not list Products");
        }
        return new ArrayList<Product>();
    }
}
