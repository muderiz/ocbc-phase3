/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imi.dolphin.sdkwebservice.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author chochong
 */

@RestController
@RequestMapping("/callback")
public class Callback_webhook {
    @RequestMapping(method = RequestMethod.GET)
    String get(HttpServletRequest request) {
        String validationToken = request.getParameter("validationToken");
        return validationToken;
    }
    @RequestMapping(method = RequestMethod.POST)
    String post(@RequestBody String callback_) {
        System.out.println(callback_);
        return callback_;
    }
}
