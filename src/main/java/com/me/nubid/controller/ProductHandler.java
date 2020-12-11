/**
 * 
 */
package com.me.nubid.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.me.nubid.model.Product;

/**
 * @author Snehal Patel
 */

@Controller
public interface ProductHandler {
    
    @RequestMapping(value = "/v1/add/product", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> addNewProduct(
            @RequestBody Product prod) throws IOException;
    
    @RequestMapping(value = "/v1/update/product/{prodId}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> updateProduct(
            @PathVariable(value = "prodId") String prodId,
            @RequestBody Product prod) throws IOException;
    
    @RequestMapping(value = "/v1/get/product/{prodId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> fetchProductDetails(
            @PathVariable(value = "prodId") String prodId) throws IOException;
    
    @RequestMapping(value = "/v1/view/buy", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Product>> viewProductsForPurchase(
            @PathVariable(value = "uuid") String uuid)
            throws IOException;
    
    @RequestMapping(value = "/v1/view/sell", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Product>> viewProductsForSelling(
            @PathVariable(value = "uuid") String uuid)
            throws IOException;
}
