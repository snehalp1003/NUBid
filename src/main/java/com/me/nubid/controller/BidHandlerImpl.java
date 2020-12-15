/**
 * 
 */
package com.me.nubid.controller;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.me.nubid.model.Bid;
import com.me.nubid.model.Product;
import com.me.nubid.model.User;
import com.me.nubid.service.DatabasePlugger;
import com.me.nubid.service.UtilityService;

/**
 * @author Snehal Patel
 */

@Controller
public class BidHandlerImpl implements BidHandler {

    private static final Logger log = LoggerFactory
            .getLogger(BidHandlerImpl.class);

    @Autowired
    DatabasePlugger databasePlugger;

    @Override
    public String placeBid(HttpServletRequest request) throws IOException {
        if (request != null) {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("currentuser");
            String uuid = user.getUserUuid();
            Bid bid = new Bid();
            bid.setBidderId(uuid);
            bid.setBidProdId(request.getParameter("prodId"));
            bid.setBidPrice(
                    Double.parseDouble(request.getParameter("newBidPrice")));
            bid.setBidDate(new Date());

            if (bid != null) {
                if (!UtilityService
                        .checkIfValidPrice(bid.getBidPrice().toString())) {
                    log.error(
                            "********** Bid Price not in correct format !! **********");
                    return "error";
                } else {
                    try {
                        Product prod = databasePlugger
                                .getProduct(request.getParameter("prodId"));
                        if(bid.getBidPrice().compareTo(prod.getProdMinPrice()) < 0) {
                            log.error(
                                    "********** Bid Price cannot be less than the minimum bid price !! **********");
                            return "error";
                        } else if(!UtilityService.checkStringNotNull(prod.getProdBuyerId())) {
                            String bidId = databasePlugger.bidPresent(
                                    bid.getBidderId(), bid.getBidProdId());
                            if (UtilityService.checkStringNotNull(bidId)) {
                                bid.setBidId(bidId);
                                Bid b = databasePlugger.placeBid(bid, true);
                            } else {
                                bid.setBidId(UtilityService.generateUuid());
                                Bid b = databasePlugger.placeBid(bid, false);
                            }
                            return "bid-offersuccessful";
                        } else {
                            return "bid-cannotacceptoffer";
                        }
                    
                    } catch (Exception e) {
                        log.error(
                                "********** Error while placing bid !! **********"
                                        + e.getMessage());
                    }
                }
            }
        }

        log.error("********** Request is empty !! **********");
        return "error";
    }

    @Override
    public String fetchBidsForProduct(HttpServletRequest request)
            throws IOException {

        if (request != null) {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("currentuser");
            String uuid = user.getUserUuid();

            String prodId = request.getParameter("prodId");

            if (UtilityService.checkStringNotNull(prodId)) {
                try {
                    Product product = databasePlugger.getProduct(prodId);
                    Map<String, Double> viewBidsForProduct = databasePlugger
                            .getAllBids(prodId);
                    if (product != null) {
                        session.setAttribute("prodId", prodId);
                        session.setAttribute("prodName", product.getProdName());
                        session.setAttribute("bidsPlaced", viewBidsForProduct);
                        if (product.getProdSellerId()
                                .equals(user.getUserUuid())) {
                            return "bids-sellerdashboard";
                        } else {
                            return "bids-buyerdashboard";
                        }
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
        log.error("********** Request is empty !! **********");
        return "error";
    }
}
