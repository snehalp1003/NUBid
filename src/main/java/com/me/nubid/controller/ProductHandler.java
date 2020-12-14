/**
 * 
 */
package com.me.nubid.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
    
    @RequestMapping(value = "/v1/product/create.htm", method = RequestMethod.GET)
    public String getProductCreateForm(HttpServletRequest request)
            throws IOException;
    
    @RequestMapping(value = "/v1/product/add.htm", method = RequestMethod.POST)
    public String addNewProduct(HttpServletRequest request) throws IOException;
    
    //Pending
    @RequestMapping(value = "/v1/update/product/{prodId}", method = RequestMethod.PUT)
    public ResponseEntity<Boolean> updateProduct(
            @PathVariable(value = "prodId") String prodId,
            @RequestBody Product prod) throws IOException;
    
    //Pending
    @RequestMapping(value = "/v1/get/product/{prodId}", method = RequestMethod.GET)
    public ResponseEntity<Product> fetchProductDetails(
            @PathVariable(value = "prodId") String prodId) throws IOException;
    
    @RequestMapping(value = "/v1/view/products.htm", method = RequestMethod.GET)
    public String viewProductsForPurchase(HttpServletRequest request)
            throws IOException;
    
    @RequestMapping(value = "/v1/view/myproducts.htm", method = RequestMethod.GET)
    public String viewProductsForSelling(HttpServletRequest request)
            throws IOException;
    
    //Pending
    @RequestMapping(value = "/v1/product/delete.htm", method = RequestMethod.DELETE)
    public String deleteProduct(HttpServletRequest request)
            throws IOException;
}
