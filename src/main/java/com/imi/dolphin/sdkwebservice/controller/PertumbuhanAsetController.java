/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imi.dolphin.sdkwebservice.controller;

import com.imi.dolphin.sdkwebservice.model.ExtensionRequest;
import com.imi.dolphin.sdkwebservice.model.ExtensionResult;
import com.imi.dolphin.sdkwebservice.property.AppProperties;
import com.imi.dolphin.sdkwebservice.facade.PertumbuhanAsetService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author cokkyturnip
 */
@RestController
public class PertumbuhanAsetController {

    private static final Logger log = LogManager.getLogger(Controller.class);

    @Autowired
    private AppProperties appProperties;

    @Autowired
    PertumbuhanAsetService svcService;

    /**
     * memanggil service dengan endPoint /pertumbuhanAset/entitasPendanaan
     * @param extensionRequest digunakan untuk memanggil data-data dari response user yang sudah diolah oleh bot
     * @return Json dengan format class ExtentionResult
     */
    @RequestMapping("/pertumbuhanAset/entitasPendanaan")
    @PostMapping
    public ExtensionResult pertumbuhanAset_entitasPendanaan(@RequestBody ExtensionRequest extensionRequest) {
        return svcService.pertumbuhanAset_entitasPendanaan(extensionRequest);
    }

    /**
     * memanggil service dengan endPoint /pertumbuhanAset/validsaiPendanaan
     * @param extensionRequest digunakan untuk memanggil data-data dari response user yang sudah diolah oleh bot
     * @return Json dengan format class ExtentionResult
     */
    @RequestMapping("/pertumbuhanAset/validasiPendanaan")
    @PostMapping
    public ExtensionResult pertumbuhanAset_ValidasiPendanaan(@RequestBody ExtensionRequest extensionRequest) {
        return svcService.pertumbuhanAset_ValidasiPendanaan(extensionRequest);
    }

    /**
     * memanggil service dengan endPoint /pertumbuhanAset/entitasBesarDanaInvestasi
     * @param extensionRequest digunakan untuk memanggil data-data dari response user yang sudah diolah oleh bot
     * @return Json dengan format class ExtentionResult
     */
    @RequestMapping("/pertumbuhanAset/entitasBesarDanaInvestasi")
    @PostMapping
    public ExtensionResult pertumbuhanAset_entitasBesarDanaInvestasi(@RequestBody ExtensionRequest extensionRequest) {
        return svcService.pertumbuhanAset_entitasBesarDanaInvestasi(extensionRequest);
    }

    /**
     * memanggil service dengan endPoint /pertumbuhanAset/validasiBesarDanaInvestasi
     * @param extensionRequest digunakan untuk memanggil data-data dari response user yang sudah diolah oleh bot
     * @return Json dengan format class ExtentionResult
     */
    @RequestMapping("/pertumbuhanAset/validasiBesarDanaInvestasi")
    @PostMapping
    public ExtensionResult pertumbuhanAset_ValidasiBesarDanaInvestasi(@RequestBody ExtensionRequest extensionRequest) {
        return svcService.pertumbuhanAset_ValidasiBesarDanaInvestasi(extensionRequest);
    }

    /**
     * memanggil service dengan endPoint /pertumbuhanAset/entitasLamaInvestasi
     * @param extensionRequest digunakan untuk memanggil data-data dari response user yang sudah diolah oleh bot
     * @return Json dengan format class ExtentionResult
     */
    @RequestMapping("/pertumbuhanAset/entitasLamaInvestasi")
    @PostMapping
    public ExtensionResult pertumbuhanAset_entitasLamaInvestasi(@RequestBody ExtensionRequest extensionRequest) {
        return svcService.pertumbuhanAset_entitasLamaInvestasi(extensionRequest);
    }

    /**
     * memanggil service dengan endPoint /pertumbuhanAset/validasiLamaInvestasi
     * @param extensionRequest digunakan untuk memanggil data-data dari response user yang sudah diolah oleh bot
     * @return Json dengan format class ExtentionResult
     */
    @RequestMapping("/pertumbuhanAset/validasiLamaInvestasi")
    @PostMapping
    public ExtensionResult pertumbuhanAset_ValidasiLamaInvestasi(@RequestBody ExtensionRequest extensionRequest) {
        return svcService.pertumbuhanAset_ValidasiLamaInvestasi(extensionRequest);
    }

    /**
     * memanggil service dengan endPoint /pertumbuhanAset/webView
     * @param extensionRequest digunakan untuk memanggil data-data dari response user yang sudah diolah oleh bot
     * @return Json dengan format class ExtentionResult
     */
    @RequestMapping("/pertumbuhanAset/webView")
    @PostMapping
    public ExtensionResult pertumbuhanAset_webView(@RequestBody ExtensionRequest extensionRequest) {
        return svcService.pertumbuhanAset_webView(extensionRequest);
    }

    /**
     * memanggil service dengan endPoint /pertumbuhanAset/validasiWebViewConfirmation
     * @param extensionRequest digunakan untuk memanggil data-data dari response user yang sudah diolah oleh bot
     * @return Json dengan format class ExtentionResult
     */
    @RequestMapping("/pertumbuhanAset/validasiWebViewConfirmation")
    @PostMapping
    public ExtensionResult pertumbuhanAset_validasiWebViewConfirmation(@RequestBody ExtensionRequest extensionRequest) {
        return svcService.pertumbuhanAset_validasiWebViewConfirmation(extensionRequest);
    }

    /**
     * memanggil service dengan endPoint /pertumbuhanAset/validasiBeforeFinal
     * @param extensionRequest digunakan untuk memanggil data-data dari response user yang sudah diolah oleh bot
     * @return Json dengan format class ExtentionResult
     */
    @RequestMapping("/pertumbuhanAset/validasiBeforeFinal")
    @PostMapping
    public ExtensionResult validasi_beforefinal(@RequestBody ExtensionRequest extensionRequest) {
        return svcService.validasi_beforefinal(extensionRequest);
    }

}
