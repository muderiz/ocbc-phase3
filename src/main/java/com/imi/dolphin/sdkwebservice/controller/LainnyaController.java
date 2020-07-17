/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imi.dolphin.sdkwebservice.controller;

import com.imi.dolphin.sdkwebservice.facade.LainnyaService;
import com.imi.dolphin.sdkwebservice.facade.LifeGoalBeliBarangService;
import com.imi.dolphin.sdkwebservice.facade.LifeGoalLiburanService;
import com.imi.dolphin.sdkwebservice.facade.LifeGoalNontonKonserService;
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
 * @author cokkyturnip
 */
@RestController
public class LainnyaController {

    private static final Logger log = LogManager.getLogger(Controller.class);

    @Autowired
    private AppProperties appProperties;

    @Autowired
    LainnyaService svcService;

    @Autowired
    LifeGoalBeliBarangService barangService;

    @Autowired
    LifeGoalLiburanService liburanService;

    @Autowired
    LifeGoalNontonKonserService konserService;

    /**
     * memanggil service dengan endPoint /lifeGoalLainnya/entitasJudulLifeGoal
     *
     * @param extensionRequest digunakan untuk memanggil data-data dari response
     * user yang sudah diolah oleh bot
     * @return Json dengan format class ExtentionResult
     */
    @RequestMapping("/lifeGoalLainnya/entitasJudulLifeGoal")
    @PostMapping
    public ExtensionResult lifeGoalLainnya_entitasJudulLifeGoal(@RequestBody ExtensionRequest extensionRequest) {
        return svcService.lifeGoalLainnya_entitasJudulLifeGoal(extensionRequest);
    }

    /**
     * memanggil service dengan endPoint /lifeGoalLainnya/validasiJudulLifeGoal
     *
     * @param extensionRequest digunakan untuk memanggil data-data dari response
     * user yang sudah diolah oleh bot
     * @return Json dengan format class ExtentionResult
     */
    @RequestMapping("/lifeGoalLainnya/validasiJudulLifeGoal")
    @PostMapping
    public ExtensionResult lifeGoalLainnya_validasiJudulLifeGoal(@RequestBody ExtensionRequest extensionRequest) {
        return svcService.lifeGoalLainnya_validasiJudulLifeGoal(extensionRequest);
    }

    /**
     * memanggil service dengan endPoint /lifeGoalLainnya/entitasKebutuhanDana
     *
     * @param extensionRequest digunakan untuk memanggil data-data dari response
     * user yang sudah diolah oleh bot
     * @return Json dengan format class ExtentionResult
     */
    @RequestMapping("/lifeGoalLainnya/entitasKebutuhanDana")
    @PostMapping
    public ExtensionResult lifeGoalLainnya_entitasKebutuhanDana(@RequestBody ExtensionRequest extensionRequest) {
        return svcService.lifeGoalLainnya_entitasKebutuhanDana(extensionRequest);
    }

    /**
     * memanggil service dengan endPoint /lifeGoalLainnya/validasiKebutuhanDana
     *
     * @param extensionRequest digunakan untuk memanggil data-data dari response
     * user yang sudah diolah oleh bot
     * @return Json dengan format class ExtentionResult
     */
    @RequestMapping("/lifeGoalLainnya/validasiKebutuhanDana")
    @PostMapping
    public ExtensionResult lifeGoalLainnya_validasiKebutuhanDana(@RequestBody ExtensionRequest extensionRequest) {
        return svcService.lifeGoalLainnya_validasiKebutuhanDana(extensionRequest);
    }

    /**
     * memanggil service dengan endPoint /lifeGoalLainnya/entitasDanaSekarang
     *
     * @param extensionRequest digunakan untuk memanggil data-data dari response
     * user yang sudah diolah oleh bot
     * @return Json dengan format class ExtentionResult
     */
    @RequestMapping("/lifeGoalLainnya/entitasDanaSekarang")
    @PostMapping
    public ExtensionResult lifeGoalLainnya_entitasDanaSekarang(@RequestBody ExtensionRequest extensionRequest) {
        return svcService.lifeGoalLainnya_entitasDanaSekarang(extensionRequest);
    }

    /**
     * memanggil service dengan endPoint /lifeGoalLainnya/validasiDanaSekarang
     *
     * @param extensionRequest digunakan untuk memanggil data-data dari response
     * user yang sudah diolah oleh bot
     * @return Json dengan format class ExtentionResult
     */
    @RequestMapping("/lifeGoalLainnya/validasiDanaSekarang")
    @PostMapping
    public ExtensionResult lifeGoalLainnya_validasiDanaSekarang(@RequestBody ExtensionRequest extensionRequest) {
        return svcService.lifeGoalLainnya_validasiDanaSekarang(extensionRequest);
    }

    /**
     * memanggil service dengan endPoint
     * /lifeGoalLainnya/entitasLamaBerinvestasi
     *
     * @param extensionRequest digunakan untuk memanggil data-data dari response
     * user yang sudah diolah oleh bot
     * @return Json dengan format class ExtentionResult
     */
    @RequestMapping("/lifeGoalLainnya/entitasLamaBerinvestasi")
    @PostMapping
    public ExtensionResult lifeGoalLainnya_entitasLamaBerinvestasi(@RequestBody ExtensionRequest extensionRequest) {
        return svcService.lifeGoalLainnya_entitasLamaBerinvestasi(extensionRequest);
    }

    /**
     * memanggil service dengan endPoint
     * /lifeGoalLainnya/validasiLamaBerinvestasi
     *
     * @param extensionRequest digunakan untuk memanggil data-data dari response
     * user yang sudah diolah oleh bot
     * @return Json dengan format class ExtentionResult
     */
    @RequestMapping("/lifeGoalLainnya/validasiLamaBerinvestasi")
    @PostMapping
    public ExtensionResult lifeGoalLainnya_validasiLamaBerinvestasi(@RequestBody ExtensionRequest extensionRequest) {
        return svcService.lifeGoalLainnya_validasiLamaBerinvestasi(extensionRequest);
    }

    /**
     * memanggil service dengan endPoint /lifeGoalLainnya/webView
     *
     * @param extensionRequest digunakan untuk memanggil data-data dari response
     * user yang sudah diolah oleh bot
     * @return Json dengan format class ExtentionResult
     */
    @RequestMapping("/lifeGoalLainnya/webView")
    @PostMapping
    public ExtensionResult lifeGoalLainnya_webView(@RequestBody ExtensionRequest extensionRequest) {
        return svcService.lifeGoalLainnya_webView(extensionRequest);
    }

    /**
     * memanggil service dengan endPoint
     * /lifeGoalLainnya/validasiWebViewConfirmation
     *
     * @param extensionRequest digunakan untuk memanggil data-data dari response
     * user yang sudah diolah oleh bot
     * @return Json dengan format class ExtentionResult
     */
    @RequestMapping("/lifeGoalLainnya/validasiWebViewConfirmation")
    @PostMapping
    public ExtensionResult lifeGoalLainnya_validasiWebViewConfirmation(@RequestBody ExtensionRequest extensionRequest) {
        return svcService.lifeGoalLainnya_validasiWebViewConfirmation(extensionRequest);
    }

    /**
     * memanggil service dengan endPoint /lifeGoalLainnya/validasiBeforeFinal
     *
     * @param extensionRequest digunakan untuk memanggil data-data dari response
     * user yang sudah diolah oleh bot
     * @return Json dengan format class ExtentionResult
     */
    @RequestMapping("/lifeGoalLainnya/validasiBeforeFinal")
    @PostMapping
    public ExtensionResult validasi_beforefinal(@RequestBody ExtensionRequest extensionRequest) {
        return svcService.validasi_beforefinal(extensionRequest);
    }

    @RequestMapping("/lifeGoalBeliBarang/validasiJudulLifeGoal")
    @PostMapping
    public ExtensionResult lifeGoalBeliBarang_validasiJudulLifeGoal(@RequestBody ExtensionRequest extensionRequest) {
        return barangService.lifeGoalBeliBarang_validasiJudulLifeGoal(extensionRequest);
    }
    
    @RequestMapping("/lifeGoalLiburan/validasiJudulLifeGoal")
    @PostMapping
    public ExtensionResult lifeGoalLiburan_validasiJudulLifeGoal(@RequestBody ExtensionRequest extensionRequest) {
        return liburanService.lifeGoalLiburan_validasiJudulLifeGoal(extensionRequest);
    }
    
    @RequestMapping("/lifeGoalNontonKonser/validasiJudulLifeGoal")
    @PostMapping
    public ExtensionResult lifeGoalNontonKonser_validasiJudulLifeGoal(@RequestBody ExtensionRequest extensionRequest) {
        return konserService.lifeGoalNontonKonser_validasiJudulLifeGoal(extensionRequest);
    }
}
