/**
 * 
 */
package com.me.nubid.controller;

import java.io.IOException;
import java.util.Map;

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
    public ResponseEntity<Bid> placeBid(@RequestBody Bid bid)
            throws IOException {
        if (bid != null) {
            if (!UtilityService
                    .checkIfValidPrice(bid.getBidPrice().toString())) {
                log.error(
                        "********** Bid Price not in correct format !! **********");
                return new ResponseEntity("Bid Price not in correct format !!",
                        HttpStatus.BAD_REQUEST);
            } else {
                try {
                    bid.setBidId(UtilityService.generateUuid());
                    return new ResponseEntity<Bid>(
                            databasePlugger.placeBid(bid), HttpStatus.OK);
                } catch (Exception e) {
                    log.error("********** Error while placing bid !! **********"
                            + e.getMessage());
                }
            }
        } else {
            log.error("********** Bid is null !! **********");
            return new ResponseEntity("Error while placing bid !!",
                    HttpStatus.BAD_REQUEST);
        }
        return null;
    }

    @Override
    public ResponseEntity<Map<String, Double>> fetchBidsForProduct(
            @PathVariable(value = "prodId") String prodId) throws IOException {
        if(UtilityService.checkStringNotNull(prodId)) {
            try {
                return new ResponseEntity<Map<String, Double>>(
                        databasePlugger.getAllBids(prodId), HttpStatus.OK);
            } catch(Exception e) {
                log.error("********** Error while fetching bids for Prodduct Id !! **********"
                        + prodId);
            }
        } else {
            log.error("********** Product Id is null !! **********");
            return new ResponseEntity("Error when fetching bids for product !!",
                    HttpStatus.BAD_REQUEST);
        }
        return null;
    }

}
