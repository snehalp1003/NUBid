/**
 * 
 */
package com.me.nubid.controller;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.me.nubid.model.Product;
import com.me.nubid.service.DatabasePlugger;
import com.me.nubid.service.UtilityService;

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
    public ResponseEntity<Product> addNewProduct(@RequestBody Product product)
            throws IOException {
        if (product != null) {
            if (!UtilityService
                    .checkIfValidPrice(product.getProdMinPrice().toString())) {
                log.error(
                        "********** Product Price not in correct format !! **********");
                return new ResponseEntity(
                        "Product Price not in correct format !!",
                        HttpStatus.BAD_REQUEST);
            } else if (!UtilityService
                    .checkStringNotNull(product.getProdSellerId())) {
                log.error("********** Product Seller Id is null !! **********");
                return new ResponseEntity("Product Seller Id is null !!",
                        HttpStatus.BAD_REQUEST);
            } else {
                try {
                    product.setProdId(UtilityService.generateUuid());
                    return new ResponseEntity<Product>(
                            databasePlugger.addNewProduct(product),
                            HttpStatus.OK);
                } catch (Exception e) {
                    log.error(
                            "********** Error while adding product !! **********"
                                    + e.getMessage());
                }
            }
        } else {
            log.error("********** Product is null !! **********");
            return new ResponseEntity("Error while adding product !!",
                    HttpStatus.BAD_REQUEST);
        }
        return null;
    }

    @Override
    public ResponseEntity<Boolean> updateProduct(
            @PathVariable(value = "prodId") String prodId,
            @RequestBody Product prod) throws IOException {
        if (UtilityService.checkStringNotNull(prodId) && prod != null) {
            if (!UtilityService
                    .checkIfValidPrice(prod.getProdMinPrice().toString())) {
                log.error(
                        "********** Product Price not in correct format !! **********");
                return new ResponseEntity(
                        "Product Price not in correct format !!",
                        HttpStatus.BAD_REQUEST);
            } else if (!UtilityService
                    .checkStringNotNull(prod.getProdSellerId())) {
                log.error("********** Product Seller Id is null !! **********");
                return new ResponseEntity("Product Seller Id is null !!",
                        HttpStatus.BAD_REQUEST);
            } else {
                try {
                    return new ResponseEntity<Boolean>(
                            databasePlugger.updateProduct(prodId, prod),
                            HttpStatus.OK);
                } catch (Exception e) {
                    log.error(
                            "********** Error while adding product !! **********"
                                    + e.getMessage());
                }
            }
        } else {
            log.error(
                    "********** Product Id or Product Details empty !! **********");
            return new ResponseEntity("Product Id or Product Details empty !!",
                    HttpStatus.BAD_REQUEST);
        }
        return null;
    }

    @Override
    public ResponseEntity<Product> fetchProductDetails(
            @PathVariable(value = "prodId") String prodId) throws IOException {
        if (UtilityService.checkStringNotNull(prodId)) {
            try {
                return new ResponseEntity<Product>(
                        databasePlugger.getProduct(prodId), HttpStatus.OK);
            } catch (Exception e) {
                log.error(
                        "********** Error while fetching product details !! **********"
                                + e.getMessage());
            }
        } else {
            log.error("********** Product Id is empty !! **********");
            return new ResponseEntity("Product Id is empty !!",
                    HttpStatus.BAD_REQUEST);
        }
        return null;
    }

    @Override
    public ResponseEntity<List<Product>> viewProductsForPurchase(
            @PathVariable(value = "uuid") String uuid) throws IOException {
        if (UtilityService.checkStringNotNull(uuid)) {
            try {
                return new ResponseEntity<List<Product>>(
                        databasePlugger.viewProductsForPurchase(uuid),
                        HttpStatus.OK);
            } catch (Exception e) {
                log.error(
                        "********** Error while fetching products for purchase !! **********"
                                + e.getMessage());
            }
        } else {
            log.error("********** User Id is empty !! **********");
            return new ResponseEntity("Product Id is empty !!",
                    HttpStatus.BAD_REQUEST);
        }
        return null;
    }

    @Override
    public ResponseEntity<List<Product>> viewProductsForSelling(
            @PathVariable(value = "uuid") String uuid) throws IOException {
        if (UtilityService.checkStringNotNull(uuid)) {
            try {
                return new ResponseEntity<List<Product>>(
                        databasePlugger.viewProductsForSelling(uuid),
                        HttpStatus.OK);
            } catch (Exception e) {
                log.error(
                        "********** Error while fetching products for selling !! **********"
                                + e.getMessage());
            }
        } else {
            log.error("********** User Id is empty !! **********");
            return new ResponseEntity("Product Id is empty !!",
                    HttpStatus.BAD_REQUEST);
        }
        return null;
    }

}
