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
import com.imi.dolphin.sdkwebservice.facade.RiskProfileService;
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
public class RiskProfileController {

    private static final Logger log = LogManager.getLogger(Controller.class);

    @Autowired
    private AppProperties appProperties;

    @Autowired
    RiskProfileService svcService;

    @RequestMapping("/riskProfile/entitasStatusInvestasi")
    @PostMapping
    public ExtensionResult riskProfile_entitasStatusInvestasi(@RequestBody ExtensionRequest extensionRequest) {
        return svcService.riskProfile_entitasStatusInvestasi(extensionRequest);
    }

    @RequestMapping("/riskProfile/validasiStatusInvestasi")
    @PostMapping
    public ExtensionResult riskProfile_validasiStatusInvestasi(@RequestBody ExtensionRequest extensionRequest) {
        return svcService.riskProfile_validasiStatusInvestasi(extensionRequest);
    }

    @RequestMapping("/riskProfile/validasiProfileResiko")
    @PostMapping
    public ExtensionResult riskProfile_validasiProfileResiko(@RequestBody ExtensionRequest extensionRequest) {
        return svcService.riskProfile_validasiProfileResiko(extensionRequest);
    }

    /**
     * memanggil service dengan endPoint /riskProfile/entitasSumberDana
     *
     * @param extensionRequest digunakan untuk memanggil data-data dari response
     * user yang sudah diolah oleh bot
     * @return Json dengan format class ExtentionResult
     */
    @RequestMapping("/riskProfile/entitasSumberDana")
    @PostMapping
    public ExtensionResult riskProfile_entitasSumberDana(@RequestBody ExtensionRequest extensionRequest) {
        return svcService.riskProfile_entitasSumberDana(extensionRequest);
    }

    /**
     * memanggil service dengan endPoint /riskProfile/validasiSumberDana
     *
     * @param extensionRequest digunakan untuk memanggil data-data dari response
     * user yang sudah diolah oleh bot
     * @return Json dengan format class ExtentionResult
     */
    @RequestMapping("/riskProfile/validasiSumberDana")
    @PostMapping
    public ExtensionResult riskProfilea_validasiSumberDana(@RequestBody ExtensionRequest extensionRequest) {
        return svcService.riskProfile_validasiSumberDana(extensionRequest);
    }

    /**
     * memanggil service dengan endPoint /riskProfile/entitasPengetahuan
     *
     * @param extensionRequest digunakan untuk memanggil data-data dari response
     * user yang sudah diolah oleh bot
     * @return Json dengan format class ExtentionResult
     */
    @RequestMapping("/riskProfile/entitasPengetahuan")
    @PostMapping
    public ExtensionResult riskProfile_entitasPengetahuan(@RequestBody ExtensionRequest extensionRequest) {
        return svcService.riskProfile_entitasPengetahuan(extensionRequest);
    }

    /**
     * memanggil service dengan endPoint /riskProfile/validasiPengetahuan
     *
     * @param extensionRequest digunakan untuk memanggil data-data dari response
     * user yang sudah diolah oleh bot
     * @return Json dengan format class ExtentionResult
     */
    @RequestMapping("/riskProfile/validasiPengetahuan")
    @PostMapping
    public ExtensionResult riskProfilea_validasiPengetahuan(@RequestBody ExtensionRequest extensionRequest) {
        return svcService.riskProfile_validasiPengetahuan(extensionRequest);
    }

    /**
     * memanggil service dengan endPoint /riskProfile/entitasPembagianHasil
     *
     * @param extensionRequest digunakan untuk memanggil data-data dari response
     * user yang sudah diolah oleh bot
     * @return Json dengan format class ExtentionResult
     */
    @RequestMapping("/riskProfile/entitasPembagianHasil")
    @PostMapping
    public ExtensionResult riskProfile_entitasPembagianHasil(@RequestBody ExtensionRequest extensionRequest) {
        return svcService.riskProfile_entitasPembagianHasil(extensionRequest);
    }

    /**
     * memanggil service dengan endPoint /riskProfile/validasiPembagianHasil
     *
     * @param extensionRequest digunakan untuk memanggil data-data dari response
     * user yang sudah diolah oleh bot
     * @return Json dengan format class ExtentionResult
     */
    @RequestMapping("/riskProfile/validasiPembagianHasil")
    @PostMapping
    public ExtensionResult riskProfilea_validasiPembagianHasil(@RequestBody ExtensionRequest extensionRequest) {
        return svcService.riskProfile_validasiPembagianHasil(extensionRequest);
    }

    /**
     * memanggil service dengan endPoint /riskProfile/entitasSkenarioKeuntungan
     *
     * @param extensionRequest digunakan untuk memanggil data-data dari response
     * user yang sudah diolah oleh bot
     * @return Json dengan format class ExtentionResult
     */
    @RequestMapping("/riskProfile/entitasSkenarioKeuntungan")
    @PostMapping
    public ExtensionResult riskProfile_entitasSkenarioKeuntungan(@RequestBody ExtensionRequest extensionRequest) {
        return svcService.riskProfile_entitasSkenarioKeuntungan(extensionRequest);
    }

    /**
     * memanggil service dengan endPoint /riskProfile/validasiSkenarioKeuntungan
     *
     * @param extensionRequest digunakan untuk memanggil data-data dari response
     * user yang sudah diolah oleh bot
     * @return Json dengan format class ExtentionResult
     */
    @RequestMapping("/riskProfile/validasiSkenarioKeuntungan")
    @PostMapping
    public ExtensionResult riskProfilea_validasiSkenarioKeuntungan(@RequestBody ExtensionRequest extensionRequest) {
        return svcService.riskProfile_validasiSkenarioKeuntungan(extensionRequest);
    }

    /**
     * memanggil service dengan endPoint /riskProfile/summary
     *
     * @param extensionRequest digunakan untuk memanggil data-data dari response
     * user yang sudah diolah oleh bot
     * @return Json dengan format class ExtentionResult
     */
    @RequestMapping("/riskProfile/summary")
    @PostMapping
    public ExtensionResult riskProfile_summary(@RequestBody ExtensionRequest extensionRequest) {
        return svcService.riskProfile_summary(extensionRequest);
    }

    /**
     * memanggil service dengan endPoint /riskProfile/validasiBeforeFinal
     *
     * @param extensionRequest digunakan untuk memanggil data-data dari response
     * user yang sudah diolah oleh bot
     * @return Json dengan format class ExtentionResult
     */
    @RequestMapping("/riskProfile/validasiBeforeFinal")
    @PostMapping
    public ExtensionResult validasi_beforefinal(@RequestBody ExtensionRequest extensionRequest) {
        return svcService.validasi_beforefinal(extensionRequest);
    }

}
