/**
 * 
 */
package com.me.nubid.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

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
        if(request!=null && request.getSession() != null && request.getSession().getAttribute("currentuser") != null) {
            return "product-create";
        }
        log.error("********** Request is empty or Session is invalid !! **********");
        return "error";
    }

    @Override
    public String addNewProduct(HttpServletRequest request) throws IOException {
        if (request != null && request.getSession() != null
                && request.getSession().getAttribute("currentuser") != null) {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("currentuser");

            Product product = new Product();
            product.setProdSellerId(user.getUserUuid());
            product.setProdName(request.getParameter("newprodname"));
            product.setProdCategory(request.getParameter("newcategory"));
            product.setProdDesc(request.getParameter("newdescription"));
            product.setProdMinPrice(
                    Double.parseDouble(request.getParameter("newminbidprice")));
            product.setProdStartDate(new Date());

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
        log.error(
                "********** Request is empty or Session is invalid !! **********");
        return "error";
    }

    @Override
    public String updateProductOnAcceptBidOffer(HttpServletRequest request)
            throws IOException {
        if (request != null && request.getSession() != null
                && request.getSession().getAttribute("currentuser") != null) {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("currentuser");
            String prodId = request.getParameter("acceptedProdId");
            String buyerEmail = request.getParameter("acceptedBidderEmail");
            Double acceptedOfferPrice = (Double.parseDouble(request.getParameter("acceptedOfferPrice")));

            if (UtilityService.checkStringNotNull(prodId)) {
                try {
                    User buyerDetails = databasePlugger.getUserDetails(buyerEmail);
                    Product prod = databasePlugger.getProduct(prodId);
                    if(!UtilityService.checkStringNotNull(prod.getProdBuyerId())) {
                        prod.setProdBuyerId(buyerDetails.getUserUuid());
                        prod.setProdFinalPrice((acceptedOfferPrice));
                        prod.setProdEndDate(new Date());
                        boolean prodSold = databasePlugger.updateProduct(prodId,
                                prod);
                        if (prodSold) {
                            return "bid-acceptoffersuccess";
                        } else {
                            return "error";
                        }
                    } else {
                        return "bid-cannotacceptoffer";
                    }
                } catch (Exception e) {
                    log.error(
                            "********** Error while adding product !! **********"
                                    + e.getMessage());
                }
            } else {
                log.error("********** Product Id is empty !! **********");
                return "error";
            }
        }
        log.error(
                "********** Request is empty or Session is invalid !! **********");
        return "error";
    }

    @Override
    public String deleteProduct(HttpServletRequest request) throws IOException {
        if (request != null && request.getSession() != null
                && request.getSession().getAttribute("currentuser") != null) {
            String prodId = request.getParameter("prodId");
            if (UtilityService.checkStringNotNull(prodId)) {
                try {
                    if (databasePlugger.deleteProduct(prodId)) {
                        return "product-deletesuccessful";
                    }
                } catch (Exception e) {
                    log.error(
                            "********** Error while deleting product !! **********"
                                    + e.getMessage());
                }
            } else {
                log.error("********** Product Id is empty !! **********");
                return "error";
            }
        }
        log.error(
                "********** Request is empty or Session is invalid !! **********");
        return "error";
    }

    @Override
    public String viewProductsForPurchase(HttpServletRequest request)
            throws IOException {
        if (request != null && request.getSession() != null
                && request.getSession().getAttribute("currentuser") != null) {
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
        log.error(
                "********** Request is empty or Session is invalid !! **********");
        return "error";
    }

    @Override
    public String viewProductsForSelling(HttpServletRequest request)
            throws IOException {
        if (request != null && request.getSession() != null
                && request.getSession().getAttribute("currentuser") != null) {
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

        log.error(
                "********** Request is empty or Session is invalid !! **********");
        return "error";
    }

    @Override
    public String searchProducts(HttpServletRequest request)
            throws IOException {
        if(request != null && request.getSession() != null
                && request.getSession().getAttribute("currentuser") != null) {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("currentuser");
            String key = request.getParameter("category");
            if(UtilityService.checkStringNotNull(key)) {
                try {
                    List<Product> searchProductList = databasePlugger.searchProducts(key, user.getUserUuid());
                    session.setAttribute("productsForPurchase", searchProductList);
                    return "product-purchasedashboard";
                } catch (Exception e) {
                    log.error(
                            "********** Error while fetching products matching search criteria !! **********"
                                    + e.getMessage());
                }
            } else {
                log.error("********** Search critieria is empty !! **********");
                return "error";
            }
        }
        log.error(
                "********** Request is empty or Session is invalid !! **********");
        return "error";
    }
}
