/**
 * Copyright (c) 2014 InMotion Innovation Technology. All Rights Reserved. <BR>
 * <BR>
 * This software contains confidential and proprietary information of InMotion
 * Innovation Technology. ("Confidential Information").<BR>
 * <BR>
 * Such Confidential Information shall not be disclosed and it shall only be
 * used in accordance with the terms of the license agreement entered into with
 * IMI; other than in accordance with the written permission of IMI. <BR>
 *
 *
 */
package com.imi.dolphin.sdkwebservice.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.imi.dolphin.sdkwebservice.model.ExtensionRequest;
import com.imi.dolphin.sdkwebservice.model.ExtensionResult;
import com.imi.dolphin.sdkwebservice.model.RiskProfileResponse;
import com.imi.dolphin.sdkwebservice.property.AppProperties;
import com.imi.dolphin.sdkwebservice.service.GetBodyFromAPI;
import com.imi.dolphin.sdkwebservice.service.IMailService;
import com.imi.dolphin.sdkwebservice.service.IService;

/**
 *
 * @author reja
 *
 */
@RestController
public class Controller {

    private static final Logger log = LogManager.getLogger(Controller.class);

    @Autowired
    private AppProperties appProperties;

    @Autowired
    IService svcService;

    @Autowired
    IMailService svcMailService;

    /**
     * memanggil service dengan endPoint /ping
     * @param extensionRequest digunakan untuk memanggil data-data dari response user yang sudah diolah oleh bot
     * @return Json
     */
    @RequestMapping("/ping")
    @PostMapping
    public ExtensionResult doPingRequest(@RequestBody ExtensionRequest extensionRequest) {
        return svcService.getDolphinResponse(extensionRequest);
    }

    /**
     * memanggil service dengan endPoint /forms
     * Template from inMotion for get From id
     * @return String Port and form Id
     */
    @RequestMapping("/forms")
    public String getStarted() {
        log.debug("getStarted() service port: {}", appProperties.getServicePort());
        return "Hello Form, service port: " + appProperties.getServicePort() + ", " + appProperties.getFormId();
    }

    /**
     * memanggil service dengan endPoint /status/
     * @param extensionRequest digunakan untuk memanggil data-data dari response user yang sudah diolah oleh bot
     * @return Json
     */
    @RequestMapping("/status/")
    @PostMapping
    public ExtensionResult doGetSrnResult(@RequestBody ExtensionRequest extensionRequest) {
        return svcService.getSrnResult(extensionRequest);
    }

    /**
     * memanggil service dengan endPoint /customers
     * @param extensionRequest digunakan untuk memanggil data-data dari response user yang sudah diolah oleh bot
     * @return Json
     */
    @RequestMapping("/customers")
    @PostMapping
    public ExtensionResult doQueryCustomerInfo(@RequestBody ExtensionRequest extensionRequest) {
        return svcService.getCustomerInfo(extensionRequest);
    }

    /**
     * memanggil service dengan endPoint /modifycustomername
     * @param extensionRequest digunakan untuk memanggil data-data dari response user yang sudah diolah oleh bot
     * @return Json
     */
    @RequestMapping("/modifycustomername")
    @PostMapping
    public ExtensionResult doClearCustomerName(@RequestBody ExtensionRequest extensionRequest) {
        return svcService.modifyCustomerName(extensionRequest);
    }

    /**
     * memanggil service dengan endPoint /productinfo
     * @param extensionRequest digunakan untuk memanggil data-data dari response user yang sudah diolah oleh bot
     * @return Json
     */
    @RequestMapping("/productinfo")
    @PostMapping
    public ExtensionResult doQueryProductInfo(@RequestBody ExtensionRequest extensionRequest) {
        return svcService.getProductInfo(extensionRequest);
    }

    /**
     * memanggil service dengan endPoint /messages
     * @param extensionRequest digunakan untuk memanggil data-data dari response user yang sudah diolah oleh bot
     * @return Json
     */
    @RequestMapping("/messages")
    @PostMapping
    public ExtensionResult doGetMessages(@RequestBody ExtensionRequest extensionRequest) {
        return svcService.getMessageBody(extensionRequest);
    }

    /**
     * memanggil service dengan endPoint /quickreplies
     * @param extensionRequest digunakan untuk memanggil data-data dari response user yang sudah diolah oleh bot
     * @return Json
     */
    @RequestMapping("/quickreplies")
    @PostMapping
    public ExtensionResult doBuildQuickReplies(@RequestBody ExtensionRequest extensionRequest) {
        return svcService.getQuickReplies(extensionRequest);
    }

    /**
     * memanggil service dengan endPoint /form
     * @param extensionRequest digunakan untuk memanggil data-data dari response user yang sudah diolah oleh bot
     * @return Json
     */
    @RequestMapping("/form")
    @PostMapping
    public ExtensionResult doBuildForm(@RequestBody ExtensionRequest extensionRequest) {
        return svcService.getForms(extensionRequest);
    }

    /**
     * memanggil service dengan endPoint /button
     * @param extensionRequest digunakan untuk memanggil data-data dari response user yang sudah diolah oleh bot
     * @return Json
     */
    @RequestMapping("/button")
    @PostMapping
    public ExtensionResult doBuildButton(@RequestBody ExtensionRequest extensionRequest) {
        return svcService.getButtons(extensionRequest);
    }

    /**
     * memanggil service dengan endPoint /carousel
     * @param extensionRequest digunakan untuk memanggil data-data dari response user yang sudah diolah oleh bot
     * @return Json
     */
    @RequestMapping("/carousel")
    @PostMapping
    public ExtensionResult doBuildCarousel(@RequestBody ExtensionRequest extensionRequest) {
        return svcService.getCarousel(extensionRequest);
    }

    /**
     * memanggil service dengan endPoint /transferAgent
     * @param extensionRequest digunakan untuk memanggil data-data dari response user yang sudah diolah oleh bot
     * @return Json
     */
    @RequestMapping("/transferAgent")
    @PostMapping
    public ExtensionResult doTransferToAgent(@RequestBody ExtensionRequest extensionRequest) {
        return svcService.doTransferToAgent(extensionRequest);
    }

    /**
     * memanggil service dengan endPoint /sendLocation
     * @param extensionRequest digunakan untuk memanggil data-data dari response user yang sudah diolah oleh bot
     * @return Json
     */
    @RequestMapping("/sendLocation")
    @PostMapping
    public ExtensionResult doBuildSendLocation(@RequestBody ExtensionRequest extensionRequest) {
        return svcService.doSendLocation(extensionRequest);
    }

    /**
     * memanggil service dengan endPoint /image
     * @param extensionRequest digunakan untuk memanggil data-data dari response user yang sudah diolah oleh bot
     * @return Json
     */
    @RequestMapping("/image")
    @PostMapping
    public ExtensionResult doBuildImage(@RequestBody ExtensionRequest extensionRequest) {
        return svcService.getImage(extensionRequest);
    }

    /**
     * memanggil service dengan endPoint /sendMail
     * @param extensionRequest digunakan untuk memanggil data-data dari response user yang sudah diolah oleh bot
     * @return Json
     */
    @RequestMapping("/sendMail")
    @PostMapping
    public ExtensionResult doSendMail(@RequestBody ExtensionRequest extensionRequest) {
        return svcService.doSendMail(extensionRequest);
    }

    /**
     * memanggil service dengan endPoint /setName
     * @param extensionRequest digunakan untuk memanggil data-data dari response user yang sudah diolah oleh bot
     * @return Json
     */
    @RequestMapping("/setName")
    @PostMapping
    public ExtensionResult setName(@RequestBody ExtensionRequest extensionRequest) {
        return svcService.setName(extensionRequest);
    }

    /**
     * memanggil service dengan endPoint /validasiNama
     * @param extensionRequest digunakan untuk memanggil data-data dari response user yang sudah diolah oleh bot
     * @return Json
     */
    @RequestMapping("/validasiNama")
    @PostMapping
    public ExtensionResult validasiNama(@RequestBody ExtensionRequest extensionRequest) {
        return svcService.validasiNama(extensionRequest);
    }

    /**
     * memanggil service dengan endPoint /entitasNama
     * @param extensionRequest digunakan untuk memanggil data-data dari response user yang sudah diolah oleh bot
     * @return Json
     */
    @RequestMapping("/entitasNama")
    @PostMapping
    public ExtensionResult entitasNama(@RequestBody ExtensionRequest extensionRequest) {
        return svcService.entitasNama(extensionRequest);
    }

    /**
     * memanggil service dengan endPoint /getAdditionalField
     * @param extensionRequest digunakan untuk memanggil data-data dari response user yang sudah diolah oleh bot
     * @return Json
     */
    @RequestMapping("/getAdditionalField")
    @PostMapping
    public ExtensionResult getAdditionalField(@RequestBody ExtensionRequest extensionRequest) {
        return svcService.getAdditionalField(extensionRequest);
    }

    /** 
     * memanggil service dengan endPoint /mulaiBerinvestasi/validasiSyaratDanKetentuan
     * @param extensionRequest digunakan untuk memanggil data-data dari response user yang sudah diolah oleh bot
     * @return Json
     */
    @RequestMapping("/mulaiBerinvestasi/validasiSyaratDanKetentuan")
    @PostMapping
    public ExtensionResult mulaiBerinvestasi_validasiSyaratKetentuan(@RequestBody ExtensionRequest extensionRequest) {
        return svcService.mulaiBerinvestasi_validasiSyaratKetentuan(extensionRequest);
    }

    /**
     * memanggil service dengan endPoint /mulaiBerinvestasi/entitasSyaratDanKetentuan
     * @param extensionRequest digunakan untuk memanggil data-data dari response user yang sudah diolah oleh bot
     * @return Json
     */
    @RequestMapping("/mulaiBerinvestasi/entitasSyaratDanKetentuan")
    @PostMapping
    public ExtensionResult mulaiBerinvestasi_entitasSyaratDanKetentuan(@RequestBody ExtensionRequest extensionRequest) {
        return svcService.mulaiBerinvestasi_entitasSyaratDanKetentuan(extensionRequest);
    }

    /**
     * memanggil service dengan endPoint /mulaiBerinvestasi/entitasUmur
     * @param extensionRequest digunakan untuk memanggil data-data dari response user yang sudah diolah oleh bot
     * @return Json
     */
    @RequestMapping("/mulaiBerinvestasi/entitasUmur")
    @PostMapping
    public ExtensionResult mulaiBerinvestasi_entitasUmur(@RequestBody ExtensionRequest extensionRequest) {
        return svcService.mulaiBerinvestasi_entitasUmur(extensionRequest);
    }

    /**
     * memanggil service dengan endPoint /mulaiBerinvestasi/validasiUmur
     * @param extensionRequest digunakan untuk memanggil data-data dari response user yang sudah diolah oleh bot
     * @return Json
     */
    @RequestMapping("/mulaiBerinvestasi/validasiUmur")
    @PostMapping
    public ExtensionResult mulaiBerinvestasi_validasiUmur(@RequestBody ExtensionRequest extensionRequest) {
        return svcService.mulaiBerinvestasi_validasiUmur(extensionRequest);
    }

    /**
     * memanggil service dengan endPoint /mulaiBerinvestasi/entitasStatusPerkawinan
     * @param extensionRequest digunakan untuk memanggil data-data dari response user yang sudah diolah oleh bot
     * @return Json
     */
    @RequestMapping("/mulaiBerinvestasi/entitasStatusPerkawinan")
    @PostMapping
    public ExtensionResult mulaiBerinvestasi_entitasStatusPerkawinan(@RequestBody ExtensionRequest extensionRequest) {
        return svcService.mulaiBerinvestasi_entitasStatusPerkawinan(extensionRequest);
    }

    /**
     * memanggil service dengan endPoint /mulaiBerinvestasi/validasiStatusPerkawinan
     * @param extensionRequest digunakan untuk memanggil data-data dari response user yang sudah diolah oleh bot
     * @return Json
     */
    @RequestMapping("/mulaiBerinvestasi/validasiStatusPerkawinan")
    @PostMapping
    public ExtensionResult mulaiBerinvestasi_validasiStatusPerkawinan(@RequestBody ExtensionRequest extensionRequest) {
        return svcService.mulaiBerinvestasi_validasiStatusPerkawinan(extensionRequest);
    }

    /**
     * memanggil service dengan endPoint /mulaiBerinvestasi/entitasPendapatan
     * @param extensionRequest digunakan untuk memanggil data-data dari response user yang sudah diolah oleh bot
     * @return Json
     */
    @RequestMapping("/mulaiBerinvestasi/entitasPendapatan")
    @PostMapping
    public ExtensionResult mulaiBerinvestasi_entitasPendapatan(@RequestBody ExtensionRequest extensionRequest) {
        return svcService.mulaiBerinvestasi_entitasPendapatan(extensionRequest);
    }

    /**
     * memanggil service dengan endPoint /mulaiBerinvestasi/validasiPendapatan
     * @param extensionRequest digunakan untuk memanggil data-data dari response user yang sudah diolah oleh bot
     * @return Json
     */
    @RequestMapping("/mulaiBerinvestasi/validasiPendapatan")
    @PostMapping
    public ExtensionResult mulaiBerinvestasi_validasiPendapatan(@RequestBody ExtensionRequest extensionRequest) {
        return svcService.mulaiBerinvestasi_validasiPendapatan(extensionRequest);
    }

    /**
     * memanggil service dengan endPoint /mulaiBerinvestasi/entitasTabungan
     * @param extensionRequest digunakan untuk memanggil data-data dari response user yang sudah diolah oleh bot
     * @return Json
     */
    @RequestMapping("/mulaiBerinvestasi/entitasTabungan")
    @PostMapping
    public ExtensionResult mulaiBerinvestasi_entitasTabungan(@RequestBody ExtensionRequest extensionRequest) {
        return svcService.mulaiBerinvestasi_entitasTabungan(extensionRequest);
    }

    /**
     * memanggil service dengan endPoint /mulaiBerinvestasi/validasiTabungan
     * @param extensionRequest digunakan untuk memanggil data-data dari response user yang sudah diolah oleh bot
     * @return Json
     */
    @RequestMapping("/mulaiBerinvestasi/validasiTabungan")
    @PostMapping
    public ExtensionResult mulaiBerinvestasi_validasiTabungan(@RequestBody ExtensionRequest extensionRequest) {
        return svcService.mulaiBerinvestasi_validasiTabungan(extensionRequest);
    }

    /**
     * memanggil service dengan endPoint /mulaiBerinvestasi/entitasStatusInvestasi
     * @param extensionRequest digunakan untuk memanggil data-data dari response user yang sudah diolah oleh bot
     * @return Json
     */
    @RequestMapping("/mulaiBerinvestasi/entitasStatusInvestasi")
    @PostMapping
    public ExtensionResult mulaiBerinvestasi_entitasStatusInvestasi(@RequestBody ExtensionRequest extensionRequest) {
        return svcService.mulaiBerinvestasi_entitasStatusInvestasi(extensionRequest);
    }

    /**
     * memanggil service dengan endPoint /mulaiBerinvestasi/validasiStatusInvestasi
     * @param extensionRequest digunakan untuk memanggil data-data dari response user yang sudah diolah oleh bot
     * @return Json
     */
    @RequestMapping("/mulaiBerinvestasi/validasiStatusInvestasi")
    @PostMapping
    public ExtensionResult mulaiBerinvestasi_validasiStatusInvestasi(@RequestBody ExtensionRequest extensionRequest) {
        return svcService.mulaiBerinvestasi_validasiStatusInvestasi(extensionRequest);
    }
    
    /**
     * memanggil service dengan endPoint /mulaiBerinvestasi/validasiRiskProfile
     * @param extensionRequest digunakan untuk memanggil data-data dari response user yang sudah diolah oleh bot
     * @return Json
     */
    @RequestMapping("/mulaiBerinvestasi/validasiRiskProfile")
    @PostMapping
    public ExtensionResult mulaiBerinvestasi_validasiRiskProfile(@RequestBody ExtensionRequest extensionRequest) {
        return svcService.mulaiBerinvestasi_validasiRiskProfile(extensionRequest);
    }

    /**
     * memanggil service dengan endPoint setAdditionalField
     * @param extensionRequest digunakan untuk memanggil data-data dari response user yang sudah diolah oleh bot
     * @return Json
     */
    @RequestMapping("setAdditionalField")
    @PostMapping
    public ExtensionResult setAdditionalField(@RequestBody ExtensionRequest extensionRequest) {
        return svcService.setAdditionalField(extensionRequest);
    }

    /**
     * memanggil service dengan endPoint /getCarouselGambarDinamis
     * @param extensionRequest digunakan untuk memanggil data-data dari response user yang sudah diolah oleh bot
     * @return Json
     */
    @RequestMapping("/getCarouselGambarDinamis")
    @PostMapping
    public ExtensionResult getCarouselGambarDinamis(@RequestBody ExtensionRequest extensionRequest) {
        return svcService.getCarouselGambarDinamis(extensionRequest);
    }
    
    /**
     * memanggil service dengan endPoint /coba_video
     * @param extensionRequest digunakan untuk memanggil data-data dari response user yang sudah diolah oleh bot
     * @return Json
     */
    @RequestMapping("/coba_video")
    @PostMapping
    public ExtensionResult coba_video(@RequestBody ExtensionRequest extensionRequest) {
        return svcService.coba_video(extensionRequest);
    }

    /**
     * memanggil service dengan endPoint /contoh/getAPI
     * @return Json
     */
    @RequestMapping("/contoh/getAPI")
    @PostMapping
    public String getBodyFromApia() {
        return svcService.getBodyFromApia();
    }

    /**
     * memanggil service dengan endPoint /contoh/getAPIb
     * @return Json
     */
    @RequestMapping("/contoh/getAPIb")
    @PostMapping
    public int getBodyFromApib() {
        return svcService.getBodyFormApib();
    }

    /**
     * memanggil service dengan endPoint /contoh/getAPIc
     * @return Json
     */
    @RequestMapping("/contoh/getAPIc")
    @PostMapping
    public GetBodyFromAPI getBodyFromApic() {
        return svcService.getBodyObject();
    }
    
}
