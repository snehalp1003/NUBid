/**
 * 
 */
package com.me.nubid.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.me.nubid.model.Product;
import com.me.nubid.model.ProductImage;
import com.me.nubid.model.User;
import com.me.nubid.service.DatabasePlugger;
import com.me.nubid.service.UtilityService;

/**
 * @author Snehal Patel
 */

@Controller
public class ImageHandlerImpl implements ImageHandler {

    private static final Logger log = LoggerFactory
            .getLogger(ImageHandlerImpl.class);

    @Autowired
    DatabasePlugger databasePlugger;

    @Override
    public String fetchImageForProduct(HttpServletRequest request)
            throws IOException {

        if (request != null && request.getSession() != null
                && request.getSession().getAttribute("currentuser") != null) {

            String uRole = UtilityService.getCurrentUserRole(request);
            if (uRole.equals("admin")) {
                log.error("********** Unauthorized user ! **********");
                return "error";
            }
            HttpSession session = request.getSession();

            String prodId = request.getParameter("prodId");

            if (UtilityService.checkStringNotNull(prodId)) {
                try {
                    ProductImage prodImg = databasePlugger
                            .getProductImage(prodId);
                    if (prodImg != null && UtilityService
                            .checkStringNotNull(prodImg.getImgLocation())) {
                        session.setAttribute("prodImg",
                                prodImg.getImgLocation());
                        return "product-image";
                    } else {
                        session.setAttribute("prodImg", "");
                        return "product-image";
                    }
                } catch (Exception e) {
                    log.error(
                            "********** Error while fetching bids for Product Id !! **********"
                                    + prodId);
                }
            } else {
                log.error("********** Product Id is null !! **********");
                return "error";
            }
        }
        log.error(
                "********** Request is empty or Session is invalid !! **********");
        return "error";

    }

}
