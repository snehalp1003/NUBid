/**
 * 
 */
package com.me.nubid.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Snehal Patel
 */

@Controller
public interface ImageHandler {

    @RequestMapping(value = "/v1/image/view.htm", method = RequestMethod.GET)
    public String fetchImageForProduct(HttpServletRequest request)
            throws IOException;
}
