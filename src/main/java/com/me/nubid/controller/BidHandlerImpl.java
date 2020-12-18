/**
 * 
 */
package com.me.nubid.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.me.nubid.model.AdminBidView;
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
        if (request != null && request.getSession() != null
                && request.getSession().getAttribute("currentuser") != null) {
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
                        if (bid.getBidPrice()
                                .compareTo(prod.getProdMinPrice()) < 0) {
                            log.error(
                                    "********** Bid Price cannot be less than the minimum bid price !! **********");
                            return "error";
                        } else if (!UtilityService
                                .checkStringNotNull(prod.getProdBuyerId())) {
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

        log.error(
                "********** Request is empty or Session is invalid !! **********");
        return "error";
    }

    @Override
    public String fetchBidsForProduct(HttpServletRequest request)
            throws IOException {

        if (request != null && request.getSession() != null
                && request.getSession().getAttribute("currentuser") != null) {
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
        log.error(
                "********** Request is empty or Session is invalid !! **********");
        return "error";
    }

    @Override
    public String viewAllOpenBids(HttpServletRequest request)
            throws IOException {
        List<AdminBidView> allOpenBids = new ArrayList<AdminBidView>();
        if (request != null && request.getSession() != null
                && request.getSession().getAttribute("currentuser") != null) {
            HttpSession session = request.getSession();
            try {
                allOpenBids = databasePlugger.viewAllOpenBids();
                session.setAttribute("allopenbids", allOpenBids);
                return "admin-allopenbids";
            } catch (Exception e) {
                log.error(
                        "********** Error while fetching all open bids !! **********"
                                + e.getMessage());
            }
        }
        log.error(
                "********** Request is empty or Session is invalid !! **********");
        return "error";
    }

    @Override
    public String viewAllClosedBids(HttpServletRequest request)
            throws IOException {
        List<AdminBidView> allClosedBids = new ArrayList<AdminBidView>();
        if (request != null && request.getSession() != null
                && request.getSession().getAttribute("currentuser") != null) {
            HttpSession session = request.getSession();
            try {
                allClosedBids = databasePlugger.viewAllClosedBids();
                session.setAttribute("allclosedbids", allClosedBids);
                return "admin-allclosedbids";
            } catch (Exception e) {
                log.error(
                        "********** Error while fetching all closed bids !! **********"
                                + e.getMessage());
            }
        }
        log.error(
                "********** Request is empty or Session is invalid !! **********");
        return "error";
    }

    @Override
    public String bidSearch(HttpServletRequest request) throws IOException {
        List<AdminBidView> bidSearchList = new ArrayList<AdminBidView>();
        if (request != null && request.getSession() != null
                && request.getSession().getAttribute("currentuser") != null) {
            HttpSession session = request.getSession();
            String category = request.getParameter("searchcategory");
            String bidStatus = request.getParameter("searchbidstatus");
            try {
                if(!UtilityService.checkStringNotNull(category) || !UtilityService.checkStringNotNull(bidStatus)) {
                    log.error(
                            "********** Product Category or Bid Status Null !! **********");
                    return "error";
                } else {
                    bidSearchList = databasePlugger.searchForBids(category, bidStatus);
                    session.setAttribute("searchbidlist", bidSearchList);
                    if(bidStatus.equals("open")) {
                        return "admin-searchopenbids";
                    }
                    return "admin-searchclosedbids";
                }
            } catch (Exception e) {
                log.error(
                        "********** Error while searching for bids !! **********"
                                + e.getMessage());
            }
        }
        log.error(
                "********** Request is empty or Session is invalid !! **********");
        return "error";
    
    }
}
