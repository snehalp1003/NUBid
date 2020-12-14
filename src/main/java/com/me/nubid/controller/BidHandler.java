/**
 * 
 */
package com.me.nubid.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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

    @RequestMapping(value = "/v1/bid/offer.htm", method = RequestMethod.POST)
    public String placeBid(HttpServletRequest request)
            throws IOException;

    @RequestMapping(value = "/v1/bids/view.htm", method = RequestMethod.GET)
    public String fetchBidsForProduct(HttpServletRequest request)
            throws IOException;

}
