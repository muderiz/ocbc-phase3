/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imi.dolphin.sdkwebservice.controller;

import com.imi.dolphin.sdkwebservice.facade.PendidikanService;
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
public class PendidikanController {

    private static final Logger log = LogManager.getLogger(Controller.class);

    @Autowired
    private AppProperties appProperties;

    @Autowired
    PendidikanService svcService;

    /**
     * memanggil service dengan endPoint /pendidikanKuliahAnak/entitasUsiaAnak
     * @param extensionRequest digunakan untuk memanggil data-data dari response user yang sudah diolah oleh bot
     * @return Json dengan format class ExtentionResult
     */
    @RequestMapping("/pendidikanKuliahAnak/entitasUsiaAnak")
    @PostMapping
    public ExtensionResult pendidikanKuliahAnak_entitasUsiaAnak(@RequestBody ExtensionRequest extensionRequest) {
        return svcService.pendidikanKuliahAnak_entitasUsiaAnak(extensionRequest);
    }

    /**
     * memanggil service dengan endPoint /pendidikanKuliahAnak/validasiUsiaAnak
     * @param extensionRequest digunakan untuk memanggil data-data dari response user yang sudah diolah oleh bot
     * @return Json dengan format class ExtentionResult
     */
    @RequestMapping("/pendidikanKuliahAnak/validasiUsiaAnak")
    @PostMapping
    public ExtensionResult pendidikanKuliahAnak_validasiUsiaAnak(@RequestBody ExtensionRequest extensionRequest) {
        return svcService.pendidikanKuliahAnak_validasiUsiaAnak(extensionRequest);
    }

    /**
     * memanggil service dengan endPoint /pendidikanKuliahAnak/entitasNegaraKuliah
     * @param extensionRequest digunakan untuk memanggil data-data dari response user yang sudah diolah oleh bot
     * @return Json dengan format class ExtentionResult
     */
    @RequestMapping("/pendidikanKuliahAnak/entitasNegaraKuliah")
    @PostMapping
    public ExtensionResult pendidikanKuliahAnak_entitasNegaraKuliah(@RequestBody ExtensionRequest extensionRequest) {
        return svcService.pendidikanKuliahAnak_entitasNegaraKuliah(extensionRequest);
    }

    /**
     * memanggil service dengan endPoint /pendidikanKuliahAnak/validasiNegaraKuliah
     * @param extensionRequest digunakan untuk memanggil data-data dari response user yang sudah diolah oleh bot
     * @return Json dengan format class ExtentionResult
     */
    @RequestMapping("/pendidikanKuliahAnak/validasiNegaraKuliah")
    @PostMapping
    public ExtensionResult pendidikanKuliahAnak_validasiNegaraKuliah(@RequestBody ExtensionRequest extensionRequest) {
        return svcService.pendidikanKuliahAnak_validasiNegaraKuliah(extensionRequest);
    }

    /**
     * memanggil service dengan endPoint /pendidikanKuliahAnak/entitasDanaSekarang
     * @param extensionRequest digunakan untuk memanggil data-data dari response user yang sudah diolah oleh bot
     * @return Json dengan format class ExtentionResult
     */
    @RequestMapping("/pendidikanKuliahAnak/entitasDanaSekarang")
    @PostMapping
    public ExtensionResult pendidikanKuliahAnak_entitasDanaSekarang(@RequestBody ExtensionRequest extensionRequest) {
        return svcService.pendidikanKuliahAnak_entitasDanaSekarang(extensionRequest);
    }

    /**
     * memanggil service dengan endPoint /pendidikanKuliahAnak/validasiDanaSekarang
     * @param extensionRequest digunakan untuk memanggil data-data dari response user yang sudah diolah oleh bot
     * @return Json dengan format class ExtentionResult
     */
    @RequestMapping("/pendidikanKuliahAnak/validasiDanaSekarang")
    @PostMapping
    public ExtensionResult pendidikanKuliahAnak_validasiDanaSekarang(@RequestBody ExtensionRequest extensionRequest) {
        return svcService.pendidikanKuliahAnak_validasiDanaSekarang(extensionRequest);
    }

    /**
     * memanggil service dengan endPoint /pendidikanKuliahAnak/webView
     * @param extensionRequest digunakan untuk memanggil data-data dari response user yang sudah diolah oleh bot
     * @return Json dengan format class ExtentionResult
     */
    @RequestMapping("/pendidikanKuliahAnak/webView")
    @PostMapping
    public ExtensionResult pendidikanKuliahAnak_webView(@RequestBody ExtensionRequest extensionRequest) {
        return svcService.pendidikanKuliahAnak_webView(extensionRequest);
    }

    /**
     * memanggil service dengan endPoint /pendidikanKuliahAnak/validasiWebViewConfirmation
     * @param extensionRequest digunakan untuk memanggil data-data dari response user yang sudah diolah oleh bot
     * @return Json dengan format class ExtentionResult
     */
    @RequestMapping("/pendidikanKuliahAnak/validasiWebViewConfirmation")
    @PostMapping
    public ExtensionResult pendidikanKuliahAnak_validasiWebViewConfirmation(@RequestBody ExtensionRequest extensionRequest) {
        return svcService.pendidikanKuliahAnak_validasiWebViewConfirmation(extensionRequest);
    }

    /**
     * memanggil service dengan endPoint /pendidikanKuliahAnak/validasiBeforeFinal
     * @param extensionRequest digunakan untuk memanggil data-data dari response user yang sudah diolah oleh bot
     * @return Json dengan format class ExtentionResult
     */
    @RequestMapping("/pendidikanKuliahAnak/validasiBeforeFinal")
    @PostMapping
    public ExtensionResult validasi_beforefinal(@RequestBody ExtensionRequest extensionRequest) {
        return svcService.validasi_beforefinal(extensionRequest);
    }

}
