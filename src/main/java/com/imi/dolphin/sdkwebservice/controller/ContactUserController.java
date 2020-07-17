/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imi.dolphin.sdkwebservice.controller;

import com.imi.dolphin.sdkwebservice.facade.ContactUserService;
import com.imi.dolphin.sdkwebservice.facade.LainnyaService;
import com.imi.dolphin.sdkwebservice.model.ExtensionRequest;
import com.imi.dolphin.sdkwebservice.model.ExtensionResult;
import com.imi.dolphin.sdkwebservice.property.AppProperties;
import java.awt.BorderLayout;
import javax.servlet.http.HttpServletRequest;
import okhttp3.Callback;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author chochong
 */
@RestController
public class ContactUserController {

    private static final Logger log = LogManager.getLogger(Controller.class);

    @Autowired
    private AppProperties appProperties;

    @Autowired
    ContactUserService svcService;

    /**
     * memanggil service dengan endPoint /contactUser/entitasNama
     *
     * @param extensionRequest digunakan untuk memanggil data-data dari response
     * user yang sudah diolah oleh bot
     * @return Json Sesuai dengan format yang sudah di tentukan
     */
    @RequestMapping("/contactUser/entitasNama")
    @PostMapping
    public ExtensionResult contactUser_entitasNama(@RequestBody ExtensionRequest extensionRequest) {
        return svcService.contactUser_entitasNama(extensionRequest);
    }

    /**
     * memanggil service dengan endPoint /contactUser/validasiNama
     *
     * @param extensionRequest digunakan untuk memanggil data-data dari response
     * user yang sudah diolah oleh bot
     * @return Json Sesuai dengan format yang sudah di tentukan
     */
    @RequestMapping("/contactUser/validasiNama")
    @PostMapping
    public ExtensionResult contactUser_validasiNama(@RequestBody ExtensionRequest extensionRequest) {
        return svcService.contactUser_validasiNama(extensionRequest);
    }

    /**
     *
     * @param extensionRequest
     * @return
     */
    @RequestMapping("/contactUser/entitasStatusNasabah")
    @PostMapping
    public ExtensionResult contactUser_entitasStatusNasabah(@RequestBody ExtensionRequest extensionRequest) {
        return svcService.contactUser_entitasStatusNasabah(extensionRequest);
    }

    /**
     *
     * @param extensionRequest
     * @return
     */
    @RequestMapping("/contactUser/validasiStatusNasabah")
    @PostMapping
    public ExtensionResult contactUser_validasiStatusNasabah(@RequestBody ExtensionRequest extensionRequest) {
        return svcService.contactUser_validasiStatusNasabah(extensionRequest);
    }

    /**
     * memanggil service dengan endPoint /contactUser/entitasEmail
     *
     * @param extensionRequest digunakan untuk memanggil data-data dari response
     * user yang sudah diolah oleh bot
     * @return Json Sesuai dengan format yang sudah di tentukan
     */
    @RequestMapping("/contactUser/entitasEmail")
    @PostMapping
    public ExtensionResult contactUser_entitasEmail(@RequestBody ExtensionRequest extensionRequest) {
        return svcService.contactUser_entitasEmail(extensionRequest);
    }

    /**
     * memanggil service dengan endPoint /contactUser/validasiEmail
     *
     * @param extensionRequest digunakan untuk memanggil data-data dari response
     * user yang sudah diolah oleh bot
     * @return Json Sesuai dengan format yang sudah di tentukan
     */
    @RequestMapping("/contactUser/validasiEmail")
    @PostMapping
    public ExtensionResult contactUser_validasiEmail(@RequestBody ExtensionRequest extensionRequest) {
        return svcService.contactUser_validasiEmail(extensionRequest);
    }

    /**
     *
     * @param extensionRequest
     * @return
     */
    @RequestMapping("/contactUser/entitasUsia")
    @PostMapping
    public ExtensionResult contactUser_entitasUsia(@RequestBody ExtensionRequest extensionRequest) {
        return svcService.contactUser_entitasUsia(extensionRequest);
    }

    /**
     *
     * @param extensionRequest
     * @return
     */
    @RequestMapping("/contactUser/validasiUsia")
    @PostMapping
    public ExtensionResult contactUser_validasiUsia(@RequestBody ExtensionRequest extensionRequest) {
        return svcService.contactUser_validasiUsia(extensionRequest);
    }

    /**
     * memanggil service dengan endPoint /contactUser/entitasLokasi
     *
     * @param extensionRequest digunakan untuk memanggil data-data dari response
     * user yang sudah diolah oleh bot
     * @return Json Sesuai dengan format yang sudah di tentukan
     */
    @RequestMapping("/contactUser/entitasLokasi")
    @PostMapping
    public ExtensionResult contactUser_entitasLokasi(@RequestBody ExtensionRequest extensionRequest) {
        return svcService.contactUser_entitasLokasi(extensionRequest);
    }

    /**
     * memanggil service dengan endPoint /contactUser/validasiLokasi
     *
     * @param extensionRequest digunakan untuk memanggil data-data dari response
     * user yang sudah diolah oleh bot
     * @return Json Sesuai dengan format yang sudah di tentukan
     */
    @RequestMapping("/contactUser/validasiLokasi")
    @PostMapping
    public ExtensionResult contactUser_validasiLokasi(@RequestBody ExtensionRequest extensionRequest) {
        return svcService.contactUser_validasiLokasi(extensionRequest);
    }

    /**
     * memanggil service dengan endPoint /contactUser/validasiLokasiKedua
     *
     * @param extensionRequest digunakan untuk memanggil data-data dari response
     * user yang sudah diolah oleh bot
     * @return Json Sesuai dengan format yang sudah di tentukan
     */
    @RequestMapping("/contactUser/validasiLokasiKedua")
    @PostMapping
    public ExtensionResult contactUser_validasiLokasi_kedua(@RequestBody ExtensionRequest extensionRequest) {
        return svcService.contactUser_validasiLokasi_kedua(extensionRequest);
    }

    /**
     * memanggil service dengan endPoint /contactUser/entitasNoTlp
     *
     * @param extensionRequest digunakan untuk memanggil data-data dari response
     * user yang sudah diolah oleh bot
     * @return Json Sesuai dengan format yang sudah di tentukan
     */
    @RequestMapping("/contactUser/entitasNoTlp")
    @PostMapping
    public ExtensionResult contactUser_entitasNoTlp(@RequestBody ExtensionRequest extensionRequest) {
        return svcService.contactUser_entitasNoTlp(extensionRequest);
    }

    /**
     * memanggil service dengan endPoint /contactUser/validasiNoTlp
     *
     * @param extensionRequest digunakan untuk memanggil data-data dari response
     * user yang sudah diolah oleh bot
     * @return Json Sesuai dengan format yang sudah di tentukan
     */
    @RequestMapping("/contactUser/validasiNoTlp")
    @PostMapping
    public ExtensionResult contactUser_validasiNoTlp(@RequestBody ExtensionRequest extensionRequest) {
        return svcService.contactUser_validasiNoTlp(extensionRequest);
    }

    /**
     * memanggil service dengan endPoint /contactUser/rating
     *
     * @param extensionRequest digunakan untuk memanggil data-data dari response
     * user yang sudah diolah oleh bot
     * @return Json Sesuai dengan format yang sudah di tentukan
     */
    @RequestMapping("/contactUser/rating")
    @PostMapping
    public ExtensionResult contactUser_entitasRating(@RequestBody ExtensionRequest extensionRequest) {
        return svcService.contactUser_entitasRating(extensionRequest);
    }

    /**
     * memanggil service dengan endPoint /contactUser/validasiRating
     *
     * @param extensionRequest digunakan untuk memanggil data-data dari response
     * user yang sudah diolah oleh bot
     * @return Json Sesuai dengan format yang sudah di tentukan
     */
    @RequestMapping("/contactUser/validasiRating")
    @PostMapping
    public ExtensionResult contactUser_validasiRating(@RequestBody ExtensionRequest extensionRequest) {
        return svcService.contactUser_validasiRating(extensionRequest);
    }

    /**
     * memanggil service dengan endPoint /contactUser/comment
     *
     * @param extensionRequest digunakan untuk memanggil data-data dari response
     * user yang sudah diolah oleh bot
     * @return Json Sesuai dengan format yang sudah di tentukan
     */
    @RequestMapping("/contactUser/comment")
    @PostMapping
    public ExtensionResult contactUser_entitasComment(@RequestBody ExtensionRequest extensionRequest) {
        return svcService.contactUser_entitasRatingComment(extensionRequest);
    }

    /**
     * memanggil service dengan endPoint /contactUser/validasiRatingComment
     *
     * @param extensionRequest digunakan untuk memanggil data-data dari response
     * user yang sudah diolah oleh bot
     * @return Json Sesuai dengan format yang sudah di tentukan
     */
    @RequestMapping("/contactUser/validasiRatingComment")
    @PostMapping
    public ExtensionResult contactUser_validasiRatingComment(@RequestBody ExtensionRequest extensionRequest) {
        return svcService.contactUser_validasiRatingComment(extensionRequest);
    }

    /**
     * memanggil service dengan endPoint /contactUser/final
     *
     * @param extensionRequest digunakan untuk memanggil data-data dari response
     * user yang sudah diolah oleh bot
     * @return Json Sesuai dengan format yang sudah di tentukan
     */
    @RequestMapping("/contactUser/final")
    @PostMapping
    public ExtensionResult contactUser_final(@RequestBody ExtensionRequest extensionRequest) {
        return svcService.contactUser_final(extensionRequest);
    }

    /**
     *
     * @param extensionRequest
     * @return
     */
    @RequestMapping("/contactUser/validasifinal")
    @PostMapping
    public ExtensionResult validasi_contactUser_final(@RequestBody ExtensionRequest extensionRequest) {
        return svcService.validasi_contactUser_final(extensionRequest);
    }

    /**
     *
     * @param extensionRequest
     * @return
     */
    @RequestMapping("/contactUser/afterFinal")
    @PostMapping
    public ExtensionResult contactUser_afterFinal(@RequestBody ExtensionRequest extensionRequest) {
        return svcService.contactUser_afterFinal(extensionRequest);
    }
}
