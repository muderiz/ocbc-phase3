/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imi.dolphin.sdkwebservice.controller;

import com.imi.dolphin.sdkwebservice.facade.ContactUserService;
import com.imi.dolphin.sdkwebservice.facade.KantorCabangTerdekatService;
import com.imi.dolphin.sdkwebservice.model.ExtensionRequest;
import com.imi.dolphin.sdkwebservice.model.ExtensionResult;
import com.imi.dolphin.sdkwebservice.property.AppProperties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author chochong
 */
@RestController
public class KantorCabangTerdekat {
    
    private static final Logger log = LogManager.getLogger(Controller.class);

    @Autowired
    private AppProperties appProperties;

    @Autowired
    KantorCabangTerdekatService svcService;
    
    /**
     * memanggil service dengan endPoint /kantorCabangTerdekat/final
     * @param extensionRequest digunakan untuk memanggil data-data dari response user yang sudah diolah oleh bot
     * @return Json
     */
    @RequestMapping("/kantorCabangTerdekat/final")
    @PostMapping
    public ExtensionResult kantorCabangTerdekat_final(@RequestBody ExtensionRequest extensionRequest) {
        return svcService.kantorCabangTerdekat_final(extensionRequest);
    }
}
