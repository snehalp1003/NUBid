/**
 * 
 */
package com.me.nubid.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.me.nubid.model.Product;
import com.me.nubid.model.User;
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
    public String getProductCreateForm(HttpServletRequest request)
            throws IOException {
        return "product-create";
    }

    @Override
    public String addNewProduct(HttpServletRequest request) throws IOException {
        if (request != null) {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("currentuser");

            Product product = new Product();
            product.setProdSellerId(user.getUserUuid());
            product.setProdName(request.getParameter("newprodname"));
            product.setProdCategory(request.getParameter("newcategory"));
            product.setProdDesc(request.getParameter("newdescription"));
            product.setProdMinPrice(
                    Double.parseDouble(request.getParameter("newminbidprice")));

            if (product != null) {
                if (!UtilityService.checkIfValidPrice(
                        product.getProdMinPrice().toString())) {
                    log.error(
                            "********** Product Price not in correct format !! **********");
                    return "error";
                } else if (!UtilityService
                        .checkStringNotNull(product.getProdSellerId())) {
                    log.error(
                            "********** Product Seller Id is null !! **********");
                    return "error";
                } else {
                    try {
                        product.setProdId(UtilityService.generateUuid());
                        Product p = databasePlugger.addNewProduct(product);
                        if (p != null) {
                            return "product-createsuccessful";
                        }
                    } catch (Exception e) {
                        log.error(
                                "********** Error while adding product !! **********"
                                        + e.getMessage());
                    }
                }
            }
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
    public String viewProductsForPurchase(HttpServletRequest request) throws IOException {
        if (request != null) {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("currentuser");
            String uuid = user.getUserUuid();
            if (UtilityService.checkStringNotNull(uuid)) {
                try {
                    List<Product> products = databasePlugger
                            .viewProductsForPurchase(uuid);
                    session.setAttribute("productsForPurchase", products);
                    return "product-purchasedashboard";
                } catch (Exception e) {
                    log.error(
                            "********** Error while fetching products for purchase !! **********"
                                    + e.getMessage());
                }
            } else {
                log.error("********** User Id is empty !! **********");
                return "error";
            }
        }
        return null;
    }

    @Override
    public String viewProductsForSelling(HttpServletRequest request)
            throws IOException {
        if (request != null) {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("currentuser");
            String uuid = user.getUserUuid();

            if (UtilityService.checkStringNotNull(uuid)) {
                try {
                    List<Product> products = databasePlugger
                            .viewProductsForSelling(uuid);
                    session.setAttribute("productsForSelling", products);
                    return "product-selldashboard";
                } catch (Exception e) {
                    log.error(
                            "********** Error while fetching products for selling !! **********"
                                    + e.getMessage());
                }
            } else {
                log.error("********** User Id is empty !! **********");
                return "error";
            }
        }

        return null;
    }

    @Override
    public String deleteProduct(HttpServletRequest request) throws IOException {
        // TODO Auto-generated method stub
        return null;
    }
}
