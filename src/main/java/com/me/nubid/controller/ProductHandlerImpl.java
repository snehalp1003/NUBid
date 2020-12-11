/**
 * 
 */
package com.me.nubid.controller;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.me.nubid.model.Product;
import com.me.nubid.model.User;
import com.me.nubid.service.DatabasePlugger;

/**
 * @author Snehal Patel
 */

@Controller
public class ProductHandlerImpl implements ProductHandler {
    
    private static final Logger log = LoggerFactory
            .getLogger(ProductHandlerImpl.class);

    @Autowired
    DatabasePlugger databasePlugger;

    @Override
    public ResponseEntity<Product> addNewProduct(
            @RequestBody Product product)
            throws IOException {
        
        return null;
    }

    @Override
    public ResponseEntity<Boolean> updateProduct(String prodId, Product prod)
            throws IOException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ResponseEntity<Product> fetchProductDetails(String prodId)
            throws IOException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ResponseEntity<List<Product>> viewProductsForPurchase(String uuid)
            throws IOException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ResponseEntity<List<Product>> viewProductsForSelling(String uuid)
            throws IOException {
        // TODO Auto-generated method stub
        return null;
    }

}
