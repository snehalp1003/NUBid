/**
 * 
 */
package com.me.nubid.controller;

import java.io.IOException;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.me.nubid.model.Bid;

/**
 * @author Snehal Patel
 */

@Controller
public interface BidHandler {
    
    @RequestMapping(value = "/v1/placeBid/bid/{bidId}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Bid> addNewUser(
            @PathVariable(value = "bidId") String bidId,
            @RequestBody Bid bid) throws IOException;
    
    @RequestMapping(value = "/v1/fetchBids/prodId/{prodId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String,String>> fetchBidsForProduct(
            @PathVariable(value = "prodId") String prodId)
            throws IOException;

}
