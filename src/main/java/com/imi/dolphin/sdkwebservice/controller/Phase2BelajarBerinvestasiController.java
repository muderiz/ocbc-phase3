/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imi.dolphin.sdkwebservice.controller;

import com.imi.dolphin.sdkwebservice.facade.Phase2BelajarBerinvestasiService;
import com.imi.dolphin.sdkwebservice.model.ExtensionRequest;
import com.imi.dolphin.sdkwebservice.model.ExtensionResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Deka
 */
@RestController
public class Phase2BelajarBerinvestasiController {

    @Autowired
    Phase2BelajarBerinvestasiService belajarBerinvestasiService;

    @RequestMapping("/belajarBerinvestasi/validasiPertaanyaanUser")
    @PostMapping
    public ExtensionResult belajarBerinvestasi_validasiPertaanyaanUser(@RequestBody ExtensionRequest extensionRequest) {
        return belajarBerinvestasiService.belajarBerinvestasi_validasiPertaanyaanUser(extensionRequest);
    }

    @RequestMapping("/belajarBerinvestasi/validasiTujuanInvestasi")
    @PostMapping
    public ExtensionResult belajarBerinvestasi_validasiTujuanInvestasi(@RequestBody ExtensionRequest extensionRequest) {
        return belajarBerinvestasiService.belajarBerinvestasi_validasiTujuanInvestasi(extensionRequest);
    }

    @RequestMapping("/belajarBerinvestasi/validasiPertaanyaanUser2")
    @PostMapping
    public ExtensionResult belajarBerinvestasi_validasiPertaanyaanUser2(@RequestBody ExtensionRequest extensionRequest) {
        return belajarBerinvestasiService.belajarBerinvestasi_validasiPertaanyaanUser2(extensionRequest);
    }

    @RequestMapping("/belajarBerinvestasi/jenisProfilRisiko")
    @PostMapping
    public ExtensionResult belajarBerinvestasi_jenisProfilRisiko(@RequestBody ExtensionRequest extensionRequest) {
        return belajarBerinvestasiService.belajarBerinvestasi_jenisProfilRisiko(extensionRequest);
    }

    @RequestMapping("/belajarBerinvestasi/validasiJawabanProfilResiko")
    @PostMapping
    public ExtensionResult belajarBerinvestasi_validasiJawabanProfilResiko(@RequestBody ExtensionRequest extensionRequest) {
        return belajarBerinvestasiService.belajarBerinvestasi_validasiJawabanProfilResiko(extensionRequest);
    }

    @RequestMapping("/belajarBerinvestasi/validasiPertanyaanUser3")
    @PostMapping
    public ExtensionResult belajarBerinvestasi_validasiPertanyaanUser3(@RequestBody ExtensionRequest extensionRequest) {
        return belajarBerinvestasiService.belajarBerinvestasi_validasiPertanyaanUser3(extensionRequest);
    }

    @RequestMapping("/belajarBerinvestasi/validasiJawabanBerani")
    @PostMapping
    public ExtensionResult belajarBerinvestasi_validasiJawabanBerani(@RequestBody ExtensionRequest extensionRequest) {
        return belajarBerinvestasiService.belajarBerinvestasi_validasiJawabanBerani(extensionRequest);
    }

    @RequestMapping("/belajarBerinvestasi/validasiFinalBelajarBerinvestasiSatu")
    @PostMapping
    public ExtensionResult belajarBerinvestasi_validasiFinalBelajarBerinvestasiSatu(@RequestBody ExtensionRequest extensionRequest) {
        return belajarBerinvestasiService.belajarBerinvestasi_validasiFinalBelajarBerinvestasiSatu(extensionRequest);
    }

    @RequestMapping("/belajarBerinvestasi/pertanyaanUser4")
    @PostMapping
    public ExtensionResult belajarBerinvestasi_pertanyaanUser4(@RequestBody ExtensionRequest extensionRequest) {
        return belajarBerinvestasiService.belajarBerinvestasi_pertanyaanUser4(extensionRequest);
    }

    @RequestMapping("/belajarBerinvestasi/validasiPertanyaanUser4")
    @PostMapping
    public ExtensionResult belajarBerinvestasi_validasiPertanyaanUser4(@RequestBody ExtensionRequest extensionRequest) {
        return belajarBerinvestasiService.belajarBerinvestasi_validasiPertanyaanUser4(extensionRequest);
    }

    @RequestMapping("/belajarBerinvestasi/validasiFinalBelajarBerinvestasiDua")
    @PostMapping
    public ExtensionResult belajarBerinvestasi_validasiFinalBelajarBerinvestasiDua(@RequestBody ExtensionRequest extensionRequest) {
        return belajarBerinvestasiService.belajarBerinvestasi_validasiFinalBelajarBerinvestasiDua(extensionRequest);
    }

    @RequestMapping("/belajarBerinvestasi/validasiPertanyaanUser5")
    @PostMapping
    public ExtensionResult belajarBerinvestasi_validasiPertanyaanUser5(@RequestBody ExtensionRequest extensionRequest) {
        return belajarBerinvestasiService.belajarBerinvestasi_validasiPertanyaanUser5(extensionRequest);
    }

    @RequestMapping("/belajarBerinvestasi/validasiFinalBelajarBerinvestasiTiga")
    @PostMapping
    public ExtensionResult belajarBerinvestasi_validasiFinalBelajarBerinvestasiTiga(@RequestBody ExtensionRequest extensionRequest) {
        return belajarBerinvestasiService.belajarBerinvestasi_validasiFinalBelajarBerinvestasiTiga(extensionRequest);
    }
}
