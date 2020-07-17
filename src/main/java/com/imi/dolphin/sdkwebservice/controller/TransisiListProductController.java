/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imi.dolphin.sdkwebservice.controller;

import com.imi.dolphin.sdkwebservice.facade.TransisiListProductService;
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
public class TransisiListProductController {
    
    private static final Logger log = LogManager.getLogger(Controller.class);

    @Autowired
    private AppProperties appProperties;

    @Autowired
    TransisiListProductService svcService;
    
    /**
     * memanggil service dengan endPoint /transisiListProduct/webview
     * @param extensionRequest digunakan untuk memanggil data-data dari response user yang sudah diolah oleh bot
     * @return Json dengan format class ExtentionResult
     */
    @RequestMapping("/transisiListProduct/webview")
    @PostMapping
    public ExtensionResult transisiListProduct_webview(@RequestBody ExtensionRequest extensionRequest) {
        return svcService.transisiListProduct_webView(extensionRequest);
    }
    
    /**
     * memanggil service dengan endPoint /transisiListProduct/validasiwebview
     * @param extensionRequest digunakan untuk memanggil data-data dari response user yang sudah diolah oleh bot
     * @return Json dengan format class ExtentionResult
     */
    @RequestMapping("/transisiListProduct/validasiwebview")
    @PostMapping
    public ExtensionResult transisiListProduct_validasiWebView(@RequestBody ExtensionRequest extensionRequest) {
        return svcService.transisiListProduct_validasiWebView(extensionRequest);
    }
    
    /**
     * memanggil service dengan endPoint /transisiListProduct/final
     * @param extensionRequest digunakan untuk memanggil data-data dari response user yang sudah diolah oleh bot
     * @return Json dengan format class ExtentionResult
     */
    @RequestMapping("/transisiListProduct/final")
    @PostMapping
    public ExtensionResult transisiListProduct_final(@RequestBody ExtensionRequest extensionRequest) {
        return svcService.transisiListProduct_final(extensionRequest);
    }
    
    /**
     * memanggil service dengan endPoint /transisiListProduct/validasifinal
     * @param extensionRequest digunakan untuk memanggil data-data dari response user yang sudah diolah oleh bot
     * @return Json dengan format class ExtentionResult
     */
    @RequestMapping("/transisiListProduct/validasifinal")
    @PostMapping
    public ExtensionResult transisiListProduct_validasiFinal(@RequestBody ExtensionRequest extensionRequest) {
        return svcService.transisiListProduct_validasiFinal(extensionRequest);
    }
}
