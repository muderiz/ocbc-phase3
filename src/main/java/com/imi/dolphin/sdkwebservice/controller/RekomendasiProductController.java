/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imi.dolphin.sdkwebservice.controller;

import com.imi.dolphin.sdkwebservice.facade.RekomendasiProductService;
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
public class RekomendasiProductController {

    private static final Logger log = LogManager.getLogger(Controller.class);

    @Autowired
    private AppProperties appProperties;

    @Autowired
    RekomendasiProductService svcService;

    /**
     *
     * @param extensionRequest
     * @return
     */
    @RequestMapping("/rekomendasiProduct/belumtauReksadana")
    @PostMapping
    public ExtensionResult rekomendasiProduct_belumtauReksadana(@RequestBody ExtensionRequest extensionRequest) {
        return svcService.rekomendasiProduct_belumtauReksadana(extensionRequest);
    }

    /**
     * memanggil service dengan endPoint /rekomendasiProduct/carousel
     *
     * @param extensionRequest digunakan untuk memanggil data-data dari response
     * user yang sudah diolah oleh bot
     * @return Json dengan format class ExtentionResult
     */
    @RequestMapping("/rekomendasiProduct/carousel")
    @PostMapping
    public ExtensionResult rekomendasiProduct_carousel(@RequestBody ExtensionRequest extensionRequest) {
        return svcService.rekomendasiProduct_carousel(extensionRequest);
    }

    /**
     * memanggil service dengan endPoint
     *
     * @param extensionRequest digunakan untuk memanggil data-data dari response
     * user yang sudah diolah oleh bot
     * @return Json dengan format class ExtentionResult
     */
    @RequestMapping("/rekomendasiProduct/carouselConfirmation")
    @PostMapping
    public ExtensionResult rekomendasiProduct_carouselConfirmation(@RequestBody ExtensionRequest extensionRequest) {
        return svcService.rekomendasiProduct_carouselConfirmation(extensionRequest);
    }

    /**
     * memanggil service dengan endPoint
     *
     * @param extensionRequest digunakan untuk memanggil data-data dari response
     * user yang sudah diolah oleh bot
     * @return Json dengan format class ExtentionResult
     */
    @RequestMapping("/rekomendasiProduct/validasiBeforeFinal")
    @PostMapping
    public ExtensionResult validasi_beforefinal(@RequestBody ExtensionRequest extensionRequest) {
        return svcService.validasi_beforefinal(extensionRequest);
    }

    /**
     *
     * @param extensionRequest
     * @return
     */
    @RequestMapping("/rekomendasiProduct/konfirmasiRekomendasiProduk")
    @PostMapping
    public ExtensionResult validasi_konfirmasiRekomendasiProduk(@RequestBody ExtensionRequest extensionRequest) {
        return svcService.validasi_konfirmasiRekomendasiProduk(extensionRequest);
    }

    /**
     *
     * @param extensionRequest
     * @return
     */
    @RequestMapping("/ulangRekomendasi/beforefinalRekomendasiProuk")
    @PostMapping
    public ExtensionResult beforefinal_konfirmasi_rekomendasiproduk(@RequestBody ExtensionRequest extensionRequest) {
        return svcService.beforefinal_konfirmasi_rekomendasiproduk(extensionRequest);
    }
}
