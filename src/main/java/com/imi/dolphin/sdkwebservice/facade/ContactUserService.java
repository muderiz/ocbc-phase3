/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imi.dolphin.sdkwebservice.facade;

import com.imi.dolphin.sdkwebservice.service.IDolphinService;
import com.imi.dolphin.sdkwebservice.service.IMailService;
import com.imi.dolphin.sdkwebservice.service.ServiceImp;
import com.imi.dolphin.sdkwebservice.service.ValidationMethod;
import com.google.gson.Gson;
import com.imi.dolphin.sdkwebservice.builder.ButtonBuilder;
import com.imi.dolphin.sdkwebservice.builder.CarouselBuilder;
import com.imi.dolphin.sdkwebservice.builder.QuickReplyBuilder;
import static com.imi.dolphin.sdkwebservice.facade.RiskProfileService.OUTPUT;
import com.imi.dolphin.sdkwebservice.model.ButtonTemplate;
import com.imi.dolphin.sdkwebservice.model.Contact;
import com.imi.dolphin.sdkwebservice.model.DataLeads;
import com.imi.dolphin.sdkwebservice.model.EasyMap;
import java.util.HashMap;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.imi.dolphin.sdkwebservice.model.ExtensionRequest;
import com.imi.dolphin.sdkwebservice.model.ExtensionResult;
import com.imi.dolphin.sdkwebservice.model.Location;
import com.imi.dolphin.sdkwebservice.model.Transaction_Data;
import com.imi.dolphin.sdkwebservice.model.UserToken;
import com.imi.dolphin.sdkwebservice.param.ParamSdk;
import static com.imi.dolphin.sdkwebservice.param.ParamSdk.JSON;
import com.imi.dolphin.sdkwebservice.property.AppProperties;
import com.imi.dolphin.sdkwebservice.service.GeneratePDF;
import com.imi.dolphin.sdkwebservice.service.MailServiceImp;
import com.imi.dolphin.sdkwebservice.util.AES256Util;
import com.imi.dolphin.sdkwebservice.util.DialogUtil;
import com.imi.dolphin.sdkwebservice.util.OkHttpUtil;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 *
 * @author chochong contact number 082238946301 (leo)
 */
@Service
public class ContactUserService {

    private static final Logger log = LogManager.getLogger(ServiceImp.class);

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    public static final String OUTPUT = "output";
    private UserToken userToken;

    @Autowired
    AppProperties appProperties;

    @Autowired
    IMailService svcMailService;

    @Autowired
    IDolphinService svcDolphinService;

    @Autowired
    OkHttpUtil okHttpUtil;
    private static final String CONSTANT_SPLIT_SYNTAX = "&split&";

    @Autowired
    DialogUtil dialogUtil;

    @Autowired
    ValidationMethod validationMethod;

    @Autowired
    WPPService wppService;

    @Autowired
    GeneratePDF generatePDF;

    @Autowired
    MailServiceImp mailServiceImp;

    @Autowired
    AES256Util aesUtil;

    private String bubble = "";
    private String bubble2 = "";

    /**
     * mengembalikan extensionResult dengan nilai output sebagai jawaban dari
     * bot
     *
     * @param extensionRequest digunakan untuk memanggil data-data dari response
     * user yang sudah diolah oleh bot
     * @return extensionResult
     */
    public ExtensionResult contactUser_entitasNama(ExtensionRequest extensionRequest) {
        log.debug("contactUser_entitasNama() extension request: {}", extensionRequest);
        ExtensionResult extensionResult = new ExtensionResult();
        extensionResult.setAgent(false);
        extensionResult.setRepeat(false);
        extensionResult.setSuccess(true);
        extensionResult.setNext(true);
        Map<String, String> output = new HashMap<>();
        bubble = dialogUtil.CreateBubble("contactUser_entitasNama", 1, null);
        output.put(OUTPUT, bubble);
        extensionResult.setValue(output);
        return extensionResult;
    }

    /**
     * Digunakan untuk melakukan validasi nama terhadap response user
     *
     * @param extensionRequest digunakan untuk memanggil data-data dari response
     * user yang sudah diolah oleh bot
     * @return extensionResult berupa Json
     */
    public ExtensionResult contactUser_validasiNama(ExtensionRequest extensionRequest) {
        log.debug("contactUser_validasiNama() extension request: {}", new Gson().toJson(extensionRequest, ExtensionRequest.class));
        ExtensionResult extensionResult = new ExtensionResult();
        Map<String, String> output = new HashMap<>();
        Map<String, String> clearEntities = new HashMap<>();
        String nama = dialogUtil.getEasyMapValueByName(extensionRequest, "nama");
        ValidationMethod validationMethod = new ValidationMethod();
        boolean status = validationMethod.valName(nama);
        extensionResult.setAgent(false);
        extensionResult.setRepeat(false);
        extensionResult.setSuccess(true);
        extensionResult.setNext(true);
        StringBuilder respBuilder = new StringBuilder();
        if (!status) {
            clearEntities.put("nama", "");
            clearEntities.put("validasi_nama", "");
            extensionResult.setEntities(clearEntities);
            output.put(OUTPUT, dialogUtil.CreateBubble("contactUser_validasiNama", 1, null));
        } else {
            clearEntities.put("validasi_nama", "validasiNama");
            extensionResult.setEntities(clearEntities);
            output.put(OUTPUT, dialogUtil.CreateBubble("contactUser_validasiNama", 2, null));
        }
        extensionResult.setValue(output);
        return extensionResult;
    }

    /**
     *
     * @param extensionRequest
     * @return
     */
    public ExtensionResult contactUser_entitasStatusNasabah(ExtensionRequest extensionRequest) {
        log.debug("contactUser_entitasStatusNasabah() extension request: {}", extensionRequest);
        ExtensionResult extensionResult = new ExtensionResult();
        extensionResult.setAgent(false);
        extensionResult.setRepeat(false);
        extensionResult.setSuccess(true);
        extensionResult.setNext(true);
        Map<String, String> output = new HashMap<>();
        bubble = dialogUtil.CreateBubble("contactUser_entitasStatusNasabah", 1, null);
        output.put(OUTPUT, bubble);
        extensionResult.setValue(output);
        return extensionResult;
    }

    /**
     *
     * @param extensionRequest
     * @return
     */
    public ExtensionResult contactUser_validasiStatusNasabah(ExtensionRequest extensionRequest) {
        log.debug("contactUser_validasiStatusNasabah() extension request: {}", new Gson().toJson(extensionRequest, ExtensionRequest.class));
        ExtensionResult extensionResult = new ExtensionResult();
        Map<String, String> output = new HashMap<>();
        Map<String, String> clearEntities = new HashMap<>();
        String status_nasabah = dialogUtil.getEasyMapValueByName(extensionRequest, "status_nasabah");

        extensionResult.setAgent(false);
        extensionResult.setRepeat(false);
        extensionResult.setSuccess(true);
        extensionResult.setNext(true);
        StringBuilder respBuilder = new StringBuilder();
        if (status_nasabah.equalsIgnoreCase("sudah")) {
            clearEntities.put("validasi_status_nasabah", "ETB");
            extensionResult.setEntities(clearEntities);
            bubble = dialogUtil.CreateBubble("contactUser_entitasEmail", 1, null);
            output.put(OUTPUT, bubble);
        } else if (status_nasabah.equalsIgnoreCase("belum")) {
            clearEntities.put("validasi_status_nasabah", "NTB");
            extensionResult.setEntities(clearEntities);
            bubble = dialogUtil.CreateBubble("contactUser_entitasEmail", 2, null);
            output.put(OUTPUT, bubble);
        } else {
            clearEntities.put("status_nasabah", "");
            clearEntities.put("validasi_status_nasabah", "");
            extensionResult.setEntities(clearEntities);
            bubble = dialogUtil.CreateBubble("contactUser_validasiStatusNasabah", 1, null) + CONSTANT_SPLIT_SYNTAX
                    + dialogUtil.CreateBubble("contactUser_validasiStatusNasabah", 2, null);
            output.put(OUTPUT, bubble);
        }
        extensionResult.setValue(output);
        return extensionResult;
    }

    /**
     * mengembalikan extensionResult dengan nilai output sebagai jawaban dari
     * bot json
     *
     * @param extensionRequest digunakan untuk memanggil data-data dari response
     * user yang sudah diolah oleh bot
     * @return extensionResult berupa Json
     */
    public ExtensionResult contactUser_entitasEmail(ExtensionRequest extensionRequest) {
        log.debug("contactUser_entitasEmail() extension request: {}", extensionRequest);
        ExtensionResult extensionResult = new ExtensionResult();
        extensionResult.setAgent(false);
        extensionResult.setRepeat(false);
        extensionResult.setSuccess(true);
        extensionResult.setNext(true);
        Map<String, String> output = new HashMap<>();
        String status_nasabah = dialogUtil.getEasyMapValueByName(extensionRequest, "status_nasabah");

        if (status_nasabah.equalsIgnoreCase("sudah")) {
            bubble = dialogUtil.CreateBubble("contactUser_entitasEmail", 1, null);
            output.put(OUTPUT, bubble);
        } else if (status_nasabah.equalsIgnoreCase("belum")) {
            bubble = dialogUtil.CreateBubble("contactUser_entitasEmail", 2, null);
            output.put(OUTPUT, bubble);
        }
        extensionResult.setValue(output);
        return extensionResult;
    }

    /**
     * DIgunakan untuk melakukan validasi apakah data diinput user adalah data
     * dengan format yang tepat validasi dilakukan pada function valEmail(email)
     *
     * @param extensionRequest digunakan untuk memanggil data-data dari response
     * user yang sudah diolah oleh bot
     * @return extensionResult berupa Json
     */
    public ExtensionResult contactUser_validasiEmail(ExtensionRequest extensionRequest) {
        log.debug("contactUser_validasiEmail() extension request: {}", new Gson().toJson(extensionRequest, ExtensionRequest.class));
        ExtensionResult extensionResult = new ExtensionResult();
        Map<String, String> output = new HashMap<>();
        Map<String, String> clearEntities = new HashMap<>();
        String email = dialogUtil.getEasyMapValueByName(extensionRequest, "email");
        ValidationMethod validationMethod = new ValidationMethod();
        clearEntities = validationMethod.valEmail(email);
        extensionResult.setEntities(clearEntities);
        extensionResult.setAgent(false);
        extensionResult.setRepeat(false);
        extensionResult.setSuccess(true);
        extensionResult.setNext(true);
        if (clearEntities.get("email").equals("")) {
            bubble = dialogUtil.CreateBubble("contactUser_validasiEmail", 1, null);
            output.put(OUTPUT, bubble);
        } else {
            bubble = dialogUtil.CreateBubble("contactUser_validasiEmail", 2, null);
            output.put(OUTPUT, bubble);
        }
        extensionResult.setValue(output);
        return extensionResult;
    }

    /**
     *
     * @param extensionRequest
     * @return
     */
    public ExtensionResult contactUser_entitasUsia(ExtensionRequest extensionRequest) {
        log.debug("contactUser_entitasUsia() extension request: {}", extensionRequest);
        ExtensionResult extensionResult = new ExtensionResult();
        extensionResult.setAgent(false);
        extensionResult.setRepeat(false);
        extensionResult.setSuccess(true);
        extensionResult.setNext(true);
        Map<String, String> output = new HashMap<>();
        bubble = dialogUtil.CreateBubble("contactUser_entitasUsia", 1, null);
        output.put(OUTPUT, bubble);
        extensionResult.setValue(output);
        return extensionResult;
    }

    /**
     *
     * @param extensionRequest
     * @return
     */
    public ExtensionResult contactUser_validasiUsia(ExtensionRequest extensionRequest) {
        log.debug("contactUser_validasiUsia() extension request: {}", extensionRequest);
        ExtensionResult extensionResult = new ExtensionResult();
        Map<String, String> output = new HashMap<>();
        Map<String, String> clearEntities = new HashMap<>();
        String usia = dialogUtil.getEasyMapValueByName(extensionRequest, "usia");

        usia = usia.toLowerCase()
                .replace("tahun", "")
                .replace("thun", "")
                .replace("thn", "")
                .replace(",", "")
                .replace(".", "")
                .replace("-", "")
                .trim();

        String kalimat = usia;
        String[] kata = kalimat.split("");
        if (kata.length > 2) {
            usia = kata[0] + kata[1];
        } else {
            usia = usia;
        }

        String status = validationMethod.ValAge(usia);
        extensionResult.setAgent(false);
        extensionResult.setRepeat(false);
        extensionResult.setSuccess(true);
        extensionResult.setNext(true);
        StringBuilder respBuilder = new StringBuilder();
        Map<String, String> map = new HashMap<>();
        if ("Mengandung Angka".equals(status)) {
            clearEntities.put("usia", "");
            clearEntities.put("validasi_usia", "");
            extensionResult.setEntities(clearEntities);
            bubble = dialogUtil.CreateBubble("contactUser_validasiUsia", 1, null);
            output.put(OUTPUT, bubble);
        } else {
            clearEntities.put("usia", usia);
            clearEntities.put("validasi_usia", "validasiUsia");
            extensionResult.setEntities(clearEntities);
            bubble = dialogUtil.CreateBubble("contactUser_entitasRating", 1, null);
            output.put(OUTPUT, bubble);
        }
        extensionResult.setValue(output);
        return extensionResult;
    }

    /**
     * mengembalikan extensionResult berupa json, dan output akan digunakan
     * sebagai jawaban dari bot
     *
     * @param extensionRequest digunakan untuk memanggil data-data dari response
     * user yang sudah diolah oleh bot
     * @return extensionResult berupa Json
     */
    public ExtensionResult contactUser_entitasLokasi(ExtensionRequest extensionRequest) {
        log.debug("contactUser_entitasLokasi() extension request: {}", extensionRequest);
        ExtensionResult extensionResult = new ExtensionResult();
        extensionResult.setAgent(false);
        extensionResult.setRepeat(false);
        extensionResult.setSuccess(true);
        extensionResult.setNext(true);
        Map<String, String> output = new HashMap<>();
        bubble = dialogUtil.CreateBubble("contactUser_entitasLokasi", 1, null);
        output.put(OUTPUT, bubble);
        extensionResult.setValue(output);
        return extensionResult;
    }

    /**
     * Digunakan untuk melakukan validasi pada lokasi yang di input oleh user
     * findLoc digunakan utuk memanggil daftar location dari sebuah file dan
     * akan menampilkan daftar tersebut kedalam sebuah carousel
     *
     * @param extensionRequest digunakan untuk memanggil data-data dari response
     * user yang sudah diolah oleh bot
     * @return extensionResult berupa Json
     */
    public ExtensionResult contactUser_validasiLokasi(ExtensionRequest extensionRequest) {
        log.debug("contactUser_validasiLokasi() extension request: {}", new Gson().toJson(extensionRequest, ExtensionRequest.class));
        ExtensionResult extensionResult = new ExtensionResult();
        Map<String, String> clearEntities = new HashMap<>();
        Map<String, String> output = new HashMap<>();
        String lokasi = dialogUtil.getEasyMapValueByName(extensionRequest, "lokasi");
        String lokasi_2 = "";
        extensionResult.setAgent(false);
        extensionResult.setRepeat(false);
        extensionResult.setSuccess(true);
        extensionResult.setNext(true);
        try {
            List<Location> findLoc = dialogUtil.location(lokasi);
            if (findLoc != null) {
                List<String> buttonList = new ArrayList<>();
                ButtonTemplate button;
                ButtonBuilder buttonBuilder;
                for (Location location : findLoc) {
                    button = new ButtonTemplate();
                    button.setPictureLink(appProperties.getOcbc_carousel_gambar_kota());
                    button.setPicturePath(appProperties.getOcbc_carousel_gambar_kota());
                    button.setTitle(location.getKota());
                    button.setSubTitle(location.getKota());
                    List<EasyMap> actions = new ArrayList<>();
                    EasyMap bookAction = new EasyMap();
                    bookAction.setName("Pilih");
                    bookAction.setValue(location.getKota());
                    actions.add(bookAction);
                    button.setButtonValues(actions);
                    buttonBuilder = new ButtonBuilder(button);
                    buttonList.add(buttonBuilder.build());
                    lokasi_2 = location.getKota() + "";
                }
                StringBuilder respBuilder = new StringBuilder();
                respBuilder.append("Apakah kota yang kamu maksud sebagai berikut? Kalau bukan juga gapapa sih.. tapi tolong sebutin lagi ya kota tempat kamu tinggal... ");
                CarouselBuilder carouselBuilder = new CarouselBuilder(buttonList);
                output.put(OUTPUT, (respBuilder.append(CONSTANT_SPLIT_SYNTAX).append(carouselBuilder.build())).toString());
            } else {
                clearEntities.put("lokasi", "");
                clearEntities.put("validasi_lokasi", "");
                extensionResult.setEntities(clearEntities);
                bubble = dialogUtil.CreateBubble("contactUser_entitasLokasi", 1, null);
                output.put(OUTPUT, bubble);
            }
        } catch (Exception e) {
            clearEntities.put("lokasi", "");
            clearEntities.put("validasi_lokasi", "");
            extensionResult.setEntities(clearEntities);
            bubble = dialogUtil.CreateBubble("contactUser_entitasLokasi", 1, null);
            output.put(OUTPUT, bubble);
        }
        extensionResult.setValue(output);
        return extensionResult;
    }

    /**
     * Digunakan untuk memvalidasi lokasi kedua yang di input oleh user, bot
     * akan menampilkan pertanyaan untuk di konfirmasi oleh user dengan jawaban
     * ya atau tidak
     *
     * @param extensionRequest digunakan untuk memanggil data-data dari response
     * user yang sudah diolah oleh bot
     * @return extensionResult berupa Json
     */
    public ExtensionResult contactUser_validasiLokasi_kedua(ExtensionRequest extensionRequest) {
        log.debug("contactUser_validasiLokasi_kedua() extension request: {}", extensionRequest);
        Map<String, String> output = new HashMap<>();
        Map<String, String> map = new HashMap<>();
        StringBuilder respBuilder = new StringBuilder();
        String lokasi = dialogUtil.getEasyMapValueByName(extensionRequest, "validasi_lokasi");
        respBuilder.append("Apakah " + lokasi + " adalah lokasi yang tepat?");
        map.put("Ya", "Ya");
        map.put("Tidak", "Tidak");
        QuickReplyBuilder quickReplyBuilder = new QuickReplyBuilder.Builder(respBuilder.toString()).addAll(map).build();
        output.put(OUTPUT, quickReplyBuilder.string());
        ExtensionResult extensionResult = new ExtensionResult();
        extensionResult.setAgent(false);
        extensionResult.setRepeat(false);
        extensionResult.setSuccess(true);
        extensionResult.setNext(true);
        extensionResult.setValue(output);
        return extensionResult;
    }

    /**
     * key output digunakan sebagai respon bot terhadap user untuk bertanya
     * nomor tlp dari user
     *
     * @param extensionRequest digunakan untuk memanggil data-data dari response
     * user yang sudah diolah oleh bot
     * @return extensionResult berupa Json
     */
    public ExtensionResult contactUser_entitasNoTlp(ExtensionRequest extensionRequest) {
        log.debug("contactUser_entitasNoTlp() extension request: {}", extensionRequest);
//        Map<String, String> clearEntities = new HashMap<>();
        ExtensionResult extensionResult = new ExtensionResult();
        extensionResult.setAgent(false);
        extensionResult.setRepeat(false);
        extensionResult.setSuccess(true);
        extensionResult.setNext(true);
        Map<String, String> output = new HashMap<>();
//        String validasi_lokasi_kedua = dialogUtil.getEasyMapValueByName(extensionRequest, "validasi_lokasi_kedua");
//        if ("Ya".equalsIgnoreCase(validasi_lokasi_kedua)) {
        bubble = dialogUtil.CreateBubble("contactUser_entitasNoTlp", 1, null);
        output.put(OUTPUT, bubble);
//        } else {
//            clearEntities.put("lokasi", "");
//            clearEntities.put("validasi_lokasi", "");
//            clearEntities.put("validasi_lokasi_kedua", "");
//            extensionResult.setEntities(clearEntities);
//            bubble = dialogUtil.CreateBubble("contactUser_entitasLokasi", 1, null);
//            output.put(OUTPUT, bubble);
//        }
        extensionResult.setValue(output);
        return extensionResult;
    }

    /**
     * Digunakan untuk validasi nomor tlp dari response user
     *
     * @param extensionRequest digunakan untuk memanggil data-data dari response
     * user yang sudah diolah oleh bot
     * @return extensionResult berupa Json
     */
    public ExtensionResult contactUser_validasiNoTlp(ExtensionRequest extensionRequest) {
        log.debug("contactUser_validasiNoTlp() extension request: {}", new Gson().toJson(extensionRequest, ExtensionRequest.class));
        ExtensionResult extensionResult = new ExtensionResult();
        Map<String, String> output = new HashMap<>();
        Map<String, String> clearEntities = new HashMap<>();
        String NoTlp = dialogUtil.getEasyMapValueByName(extensionRequest, "no_telp");
        Map<String, String> map = new HashMap<>();
        StringBuilder respBuilder = new StringBuilder();
        boolean status = validationMethod.valNoTlp(NoTlp);
        extensionResult.setAgent(false);
        extensionResult.setRepeat(false);
        extensionResult.setSuccess(true);
        extensionResult.setNext(true);
        if (status == false) {
            clearEntities.put("no_telp", "");
            clearEntities.put("validasi_no_telp", "");
            extensionResult.setEntities(clearEntities);
            bubble = dialogUtil.CreateBubble("contactUser_validasiNoTlp", 1, null);
            output.put(OUTPUT, bubble);
            extensionResult.setValue(output);
        } else {
            clearEntities.put("validasi_no_telp", "validasiNoTlp");
            extensionResult.setEntities(clearEntities);
            bubble = dialogUtil.CreateBubble("contactUser_entitasRating", 1, null);
            output.put(OUTPUT, bubble);
            extensionResult.setValue(output);
        }

        return extensionResult;
    }

    /**
     * key output akan di gunakan sebagai respon dari bot terhadap user untuk
     * menanyakan rating yang di berikan oleh user
     *
     * @param extensionRequest digunakan untuk memanggil data-data dari response
     * user yang sudah diolah oleh bot
     * @return extensionResult berupa Json
     */
    public ExtensionResult contactUser_entitasRating(ExtensionRequest extensionRequest) {
        log.debug("contactUser_rating() extension request: {}", extensionRequest);
        Map<String, String> output = new HashMap<>();
        bubble = dialogUtil.CreateBubble("contactUser_entitasRating", 1, null);
        output.put(OUTPUT, bubble);
        ExtensionResult extensionResult = new ExtensionResult();
        extensionResult.setAgent(false);
        extensionResult.setRepeat(false);
        extensionResult.setSuccess(true);
        extensionResult.setNext(true);
        extensionResult.setValue(output);
        return extensionResult;
    }

    /**
     * Digunakan untuk melakukan validasi rating yang diinputkan oleh user
     *
     * @param extensionRequest digunakan untuk memanggil data-data dari response
     * user yang sudah diolah oleh bot
     * @return extensionResult berupa Json
     */
    public ExtensionResult contactUser_validasiRating(ExtensionRequest extensionRequest) {
        log.debug("contactUser_entitasNoTlp() extension request: {}", extensionRequest);
        Map<String, String> clearEntities = new HashMap<>();
        ExtensionResult extensionResult = new ExtensionResult();
        extensionResult.setAgent(false);
        extensionResult.setRepeat(false);
        extensionResult.setSuccess(true);
        extensionResult.setNext(true);
        Map<String, String> output = new HashMap<>();
        String rating = dialogUtil.getEasyMapValueByName(extensionRequest, "rating");
        boolean validasi_reting = validationMethod.valAngka(rating);
        StringBuilder respBuilder = new StringBuilder();
        if (validasi_reting == false) {
            clearEntities.put("rating", "");
            clearEntities.put("validasi_rating", "");
            extensionResult.setEntities(clearEntities);
            Map<String, String> map = new HashMap<>();
            bubble = dialogUtil.CreateBubble("contactUser_validasiRating", 1, null);
            output.put(OUTPUT, bubble);
        } else {
            clearEntities.put("validasi_rating", "validasiRating");
            extensionResult.setEntities(clearEntities);
            if ("0".equalsIgnoreCase(rating) || "1".equalsIgnoreCase(rating)) {
                bubble = dialogUtil.CreateBubble("contactUser_validasiRating", 2, null);
                output.put(OUTPUT, bubble);
            } else {
                bubble = dialogUtil.CreateBubble("contactUser_validasiRating", 3, null);
                output.put(OUTPUT, bubble);
            }
        }
        extensionResult.setValue(output);
        return extensionResult;
    }

    /**
     * key output akan digunakan sebagai jawaban dari user pada kasus ini
     * memiliki jawaban yang berbeda tergantung jawaban dari user memerikan
     * rating dengan nilai berapa
     *
     * @param extensionRequest digunakan untuk memanggil data-data dari response
     * user yang sudah diolah oleh bot
     * @return extensionResult berupa Json
     */
    public ExtensionResult contactUser_entitasRatingComment(ExtensionRequest extensionRequest) {
        log.debug("contactUser_rating() extension request: {}", extensionRequest);
        Map<String, String> output = new HashMap<>();
        StringBuilder respBuilder = new StringBuilder();
        String rating = dialogUtil.getEasyMapValueByName(extensionRequest, "rating");

        if ("1".equalsIgnoreCase(rating) || "0".equalsIgnoreCase(rating)) {
            bubble = dialogUtil.CreateBubble("contactUser_entitasRatingComment", 1, null);
        } else {
            bubble = dialogUtil.CreateBubble("contactUser_entitasRatingComment", 2, null);
        }
        output.put(OUTPUT, bubble);
        ExtensionResult extensionResult = new ExtensionResult();
        extensionResult.setAgent(false);
        extensionResult.setRepeat(false);
        extensionResult.setSuccess(true);
        extensionResult.setNext(true);
        extensionResult.setValue(output);
        return extensionResult;
    }

    /**
     * DIgunakan untuk memvalidasi hasil dari komentar user
     *
     * @param extensionRequest digunakan untuk memanggil data-data dari response
     * user yang sudah diolah oleh bot
     * @return extensionResult berupa Json
     */
    public ExtensionResult contactUser_validasiRatingComment(ExtensionRequest extensionRequest) {
        log.debug("contactUser_entitasNoTlp() extension request: {}", extensionRequest);
        Map<String, String> clearEntities = new HashMap<>();
        ExtensionResult extensionResult = new ExtensionResult();
        extensionResult.setAgent(false);
        extensionResult.setRepeat(false);
        extensionResult.setSuccess(true);
        extensionResult.setNext(true);
        Map<String, String> output = new HashMap<>();
        StringBuilder respBuilder = new StringBuilder();
        String rating = dialogUtil.getEasyMapValueByName(extensionRequest, "rating");
        boolean validasi_rating = validationMethod.valAngka(rating);
        if (validasi_rating == false) {
            clearEntities.put("rating_comment", "");
            clearEntities.put("validasi_rating_comment", "");
            extensionResult.setEntities(clearEntities);
            if ("1".equalsIgnoreCase(rating) || "0".equalsIgnoreCase(rating)) {
                bubble = dialogUtil.CreateBubble("contactUser_validasiRatingComment", 1, null);
            } else {
                bubble = dialogUtil.CreateBubble("contactUser_validasiRatingComment", 2, null);
            }
            output.put(OUTPUT, bubble);
            extensionResult.setValue(output);
        } else {
            clearEntities.put("validasi_rating_comment", "validasiRatingComment");
            extensionResult.setEntities(clearEntities);
        }
        return extensionResult;
    }

    /**
     * Digunakan untuk menyimpan data kedalam varabel aditionalField
     *
     * @param extensionRequest digunakan untuk memanggil data-data dari response
     * user yang sudah diolah oleh bot
     * @return extensionResult berupa Json
     */
    public ExtensionResult contactUser_final(ExtensionRequest extensionRequest) {
        String report_code = "";
        String path_file = "";
        String nama_lengkap = dialogUtil.getEasyMapValueByName(extensionRequest, "nama");
        String email = dialogUtil.getEasyMapValueByName(extensionRequest, "email");
        String status_nasabah = dialogUtil.getEasyMapValueByName(extensionRequest, "status_nasabah");
        String validasi_status_nasabah = dialogUtil.getEasyMapValueByName(extensionRequest, "validasi_status_nasabah");
        String no_telp = dialogUtil.getEasyMapValueByName(extensionRequest, "no_telp");
        int usia = 0;
        String lokasi = "null";
        String no_tlp = no_telp;
        String rating = dialogUtil.getEasyMapValueByName(extensionRequest, "rating");
        String rating_comment = dialogUtil.getEasyMapValueByName(extensionRequest, "rating_comment");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        userToken = svcDolphinService.getUserToken(userToken);
        String contactId = extensionRequest.getIntent().getTicket().getContactId();
        Contact contact = svcDolphinService.getCustomer(userToken, contactId);
        String strAddiontalfield;
        DataLeads dataLeads1;
        Transaction_Data transaction_Data;
        if (appProperties.APP_MODE.equals("debug")) {
////<editor-fold>
            dataLeads1 = new DataLeads();
            dataLeads1.Channel_ID = "1";
            dataLeads1.Ext_Reff_ID = contactId;
            transaction_Data = new Transaction_Data();
            transaction_Data.Name = "Dewi Test";
            transaction_Data.Age = 25;
            transaction_Data.Email = "cokkyturnip@gmail.com";
            transaction_Data.Phone_Number = "085956006683";
            transaction_Data.Location = "Jakarta Selatan";
            transaction_Data.Is_Married = 1;
            transaction_Data.Is_Already_Invest = 1;
            transaction_Data.Financial_Info_Income_Range_ID = 1;
            transaction_Data.Financial_Info_Saved_Income_ID = 1;
            transaction_Data.Risk_Profile_ID = 4;
            transaction_Data.Life_Goal_ID = 1;
            transaction_Data.Financial_Goal = "Pertumbuhan Aset";
            transaction_Data.Investment_Type = 0;
            transaction_Data.Tenor = 10;
            transaction_Data.Investment_Amount = 500000;
            transaction_Data.Target_Amount = 3000000;
            transaction_Data.Initial_Amount = 3000000;
            transaction_Data.EC_ID = 0;
            transaction_Data.Children_Age = 0;
            transaction_Data.Product_ID = 10;
            transaction_Data.Datetime_Start_Process = "2019-01-18 08:12:12";
            transaction_Data.Datetime_End_Process = dateFormat.format(date);
            transaction_Data.Time_Horizon = "1.a";
            transaction_Data.Source_of_Income = "2.a";
            transaction_Data.Investment_Knowledge = "3.a";
            transaction_Data.Income_Usage = "4.a";
            transaction_Data.Potential_Loss = "5.a";
            transaction_Data.Platform_Name = "OCBC NISP Website";
            List<Transaction_Data> listTransaction = new ArrayList<>();
            listTransaction.add(transaction_Data);
            Transaction_Data[] arrTransaction = new Transaction_Data[1];
            dataLeads1.Transaction_Data = listTransaction.toArray(arrTransaction);
////</editor-fold>
        } else {
            contact = svcDolphinService.getCustomer(userToken, contactId);
            strAddiontalfield = contact.getAdditionalField().get(0);
            dataLeads1 = new Gson().fromJson(strAddiontalfield, DataLeads.class);
            int pendanaan = dataLeads1.getTransaction_Data()[0].getInvestment_Type();
            int Tenor = dataLeads1.getTransaction_Data()[0].getTenor();
            String TimeHorizon = "";
            if (Tenor <= 1) {
                TimeHorizon = "1.a";
            } else if (Tenor >= 2 && Tenor <= 5) {
                TimeHorizon = "1.b";
            } else if (Tenor >= 6 && Tenor <= 10) {
                TimeHorizon = "1.c";
            } else {
                TimeHorizon = "1.d";
            }
            dataLeads1.Channel_ID = "1";
            dataLeads1.Ext_Reff_ID = contactId;
            transaction_Data = new Transaction_Data();
            transaction_Data.Name = nama_lengkap;
            transaction_Data.Age = usia;
            transaction_Data.Email = email;
            transaction_Data.Phone_Number = no_tlp;
            transaction_Data.Location = lokasi;
            transaction_Data.Is_Married = dataLeads1.getTransaction_Data()[0].getIs_Married();
            transaction_Data.Is_Already_Invest = dataLeads1.getTransaction_Data()[0].getIs_Already_Invest();
            transaction_Data.Financial_Info_Income_Range_ID = dataLeads1.getTransaction_Data()[0].getFinancial_Info_Income_Range_ID();
            transaction_Data.Financial_Info_Saved_Income_ID = dataLeads1.getTransaction_Data()[0].getFinancial_Info_Saved_Income_ID();
            transaction_Data.Risk_Profile_ID = dataLeads1.getTransaction_Data()[0].getRisk_Profile_ID();
            transaction_Data.Life_Goal_ID = dataLeads1.getTransaction_Data()[0].getLife_Goal_ID();
            transaction_Data.Financial_Goal = dataLeads1.getTransaction_Data()[0].getFinancial_Goal();
            transaction_Data.Investment_Type = dataLeads1.getTransaction_Data()[0].getInvestment_Type();
            transaction_Data.Tenor = dataLeads1.getTransaction_Data()[0].getTenor();
            transaction_Data.Investment_Amount = dataLeads1.getTransaction_Data()[0].getInvestment_Amount();
            transaction_Data.Target_Amount = dataLeads1.getTransaction_Data()[0].getTarget_Amount();
            transaction_Data.Initial_Amount = dataLeads1.getTransaction_Data()[0].getInitial_Amount();
            transaction_Data.EC_ID = dataLeads1.getTransaction_Data()[0].getEC_ID();
            transaction_Data.Children_Age = dataLeads1.getTransaction_Data()[0].getChildren_Age();
            transaction_Data.Product_ID = dataLeads1.getTransaction_Data()[0].getProduct_ID();
            transaction_Data.Datetime_Start_Process = dataLeads1.getTransaction_Data()[0].getDatetime_Start_Process();
            transaction_Data.Datetime_End_Process = dateFormat.format(date);
            transaction_Data.Time_Horizon = TimeHorizon;
            transaction_Data.Source_of_Income = dataLeads1.getTransaction_Data()[0].getSource_of_Income();
            transaction_Data.Investment_Knowledge = dataLeads1.getTransaction_Data()[0].getInvestment_Knowledge();
            transaction_Data.Income_Usage = dataLeads1.getTransaction_Data()[0].getIncome_Usage();
            transaction_Data.Potential_Loss = dataLeads1.getTransaction_Data()[0].getPotential_Loss();
            transaction_Data.Platform_Name = "OCBC NISP Website";
            transaction_Data.Rating = Integer.parseInt(rating);
            transaction_Data.Rating_Comment = rating_comment;
            transaction_Data.Status_Nasabah = validasi_status_nasabah;
            List<Transaction_Data> listTransaction = new ArrayList<>();
            listTransaction.add(transaction_Data);
            Transaction_Data[] arrTransaction = new Transaction_Data[1];
            dataLeads1.Transaction_Data = listTransaction.toArray(arrTransaction);
            String name = dataLeads1.getTransaction_Data()[0].getName();
            log.debug("contactUser_final() test data dari solr: {}", name);
            List<String> listData = new ArrayList<>();
            listData.add("" + new Gson().toJson(dataLeads1, DataLeads.class) + "");
            contact.setAdditionalField(listData);
            contact = svcDolphinService.updateCustomer(userToken, contact);
        }
        OkHttpUtil okHttpUtil = new OkHttpUtil();
        okHttpUtil.init(true);
        Gson gson = new Gson();
        log.debug("contactUser_final() hasil response hasil dari post data leads 1 : {}", new Gson().toJson(dataLeads1));
        RequestBody body = RequestBody.create(JSON, gson.toJson(dataLeads1));
        log.debug("contactUser_final() hasil response hasil dari post data leads 2 : {}", new Gson().toJson(body));
        Request request = new Request.Builder().url(appProperties.OCBC_WPP_BASE_URL + appProperties.OCBC_WPP_PATH_DATA_LEADS).addHeader("Content-Type", "application/json; charset=utf-8")
                .post(body).build();
        try {
            Response response = okHttpUtil.getClient().newCall(request).execute();

            JSONObject jsonObjek = new JSONObject(response.body().string());
            report_code = jsonObjek.getString("Report_Code");
            log.debug("contactUser_final() report_code hasil dari post data leads: {}", report_code);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //==============================================================================================
        //==============================================================================================
        //2. generate pdf
        path_file = generatePDF.generate(report_code);
        log.debug("contactUser_final() Path_file hasil generate: {}", path_file);
        DateTime dtToken = DateTime.now();
        org.joda.time.format.DateTimeFormatter dtf = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
        String token = dtToken.toString(dtf);
        token = token + "&split&" + report_code + ".pdf";
        token = aesUtil.encrypt(token).replace("/", "&");
        System.out.println(token);
        //4. send bubble pdf
        StringBuilder respBuilder = new StringBuilder();
        ButtonTemplate buttonTemplate = new ButtonTemplate();
        buttonTemplate.setTitle(appProperties.getOcbc_carousel_title_download());
//        buttonTemplate.setSubTitle(report_code + ".pdf");
        buttonTemplate.setSubTitle(bubble = dialogUtil.CreateBubble("Ringkasan_send_PDF", 3, null));
        Map<String, String> output = new HashMap<>();
        buttonTemplate.setPictureLink(appProperties.getOcbc_carousel_gambar_download());
        buttonTemplate.setPicturePath(appProperties.getOcbc_carousel_gambar_download());
        List<EasyMap> actions = new ArrayList<>();
        EasyMap bookAction = new EasyMap();
        bookAction.setName("Download");
        bookAction.setValue(appProperties.OCBC_WEBVIEW_BASE_URL
                + appProperties.OCBC_WEBVIEW_BASE_URL_PDF
                + token);
        actions.add(bookAction);
        buttonTemplate.setButtonValues(actions);
        ButtonBuilder buttonBuilder = new ButtonBuilder(buttonTemplate);

        bubble = dialogUtil.CreateBubble("Ringkasan_send_PDF", 1, null);
        bubble2 = dialogUtil.CreateBubble("Ringkasan_send_PDF", 2, null);
        String bubble3 = dialogUtil.CreateBubble("Ringkasan_send_PDF", 4, null);

        respBuilder.append(bubble)
                .append(CONSTANT_SPLIT_SYNTAX)
                .append(bubble2)
                .append(CONSTANT_SPLIT_SYNTAX)
                .append(buttonBuilder.build())
                .append(CONSTANT_SPLIT_SYNTAX)
                .append(bubble3);
        output.put(OUTPUT, respBuilder.toString());

//        output.put(OUTPUT, buttonBuilder.build());
        ExtensionResult extensionResult = new ExtensionResult();
        extensionResult.setAgent(false);
        extensionResult.setRepeat(false);
        extensionResult.setSuccess(true);
        extensionResult.setNext(true);
        extensionResult.setValue(output);
        log.debug("contactUser_final() Path_file hasil generate: {}", output);
        return extensionResult;
    }

    public ExtensionResult validasi_contactUser_final(ExtensionRequest extensionRequest) {
        String report_pdf = dialogUtil.getEasyMapValueByName(extensionRequest, "report_pdf");
        Map<String, String> output = new HashMap<>();
        Map<String, String> clearEntitas = new HashMap<>();
        ExtensionResult extensionResult = new ExtensionResult();

        if (report_pdf.equalsIgnoreCase("what next")) {
            clearEntitas.put("before_final", "SKIP");
            clearEntitas.put("after_report", "SKIP");
            extensionResult.setEntities(clearEntitas);
        } else {
            bubble = dialogUtil.CreateBubble("validasi_send_PDF", 1, null);
            output.put(OUTPUT, bubble);
            clearEntitas.put("report_pdf", "");
            extensionResult.setValue(output);
            extensionResult.setEntities(clearEntitas);
        }
        extensionResult.setAgent(false);
        extensionResult.setRepeat(false);
        extensionResult.setSuccess(true);
        extensionResult.setNext(true);

        log.debug("validasi_contactUser_final(): {}", output);
        return extensionResult;
    }

    public ExtensionResult contactUser_afterFinal(ExtensionRequest extensionRequest) {
        String status_nasabah = dialogUtil.getEasyMapValueByName(extensionRequest, "status_nasabah");
        Map<String, String> output = new HashMap<>();
        Map<String, String> clearEntitas = new HashMap<>();
        ExtensionResult extensionResult = new ExtensionResult();
        StringBuilder respBuilder = new StringBuilder();

        if (status_nasabah.equalsIgnoreCase("sudah")) {
            bubble = dialogUtil.CreateBubble("contactUser_AfterReport", 1, null) + CONSTANT_SPLIT_SYNTAX
                    + dialogUtil.CreateBubble("contactUser_AfterReportSudahNasabah", 1, null) + CONSTANT_SPLIT_SYNTAX
                    + dialogUtil.CreateBubble("contactUser_AfterReportSudahNasabah", 2, null);
            log.debug("contactUser_afterFinal() extension request: {}", bubble);
            String subtitle = dialogUtil.CreateBubble("contactUser_AfterReportSudahNasabah", 3, null);
            ButtonTemplate button = new ButtonTemplate();
            button.setTitle("Download ONeMobile");
            button.setSubTitle(subtitle);
            button.setPictureLink(appProperties.getOCBC_PICTURES_ONEMOBILE());
            button.setPicturePath(appProperties.getOCBC_PICTURES_ONEMOBILE());
            List<EasyMap> actions = new ArrayList<>();
            EasyMap bookAction = new EasyMap();
            bookAction.setName("Download Here!");
            bookAction.setValue(appProperties.getOCBC_LINK_ONEMOBILE());
            actions.add(bookAction);
            button.setButtonValues(actions);
            ButtonBuilder buttonBuilder = new ButtonBuilder(button);
            String bubble2 = dialogUtil.CreateBubble("contactUser_AfterReportSudahNasabah", 4, null);
            respBuilder.append(bubble)
                    .append(CONSTANT_SPLIT_SYNTAX)
                    .append(buttonBuilder.build())
                    .append(CONSTANT_SPLIT_SYNTAX)
                    .append(bubble2);
            output.put(OUTPUT, respBuilder.toString());
            extensionResult.setValue(output);
        } else if (status_nasabah.equalsIgnoreCase("belum")) {
            bubble = dialogUtil.CreateBubble("contactUser_AfterReport", 1, null) + CONSTANT_SPLIT_SYNTAX
                    + dialogUtil.CreateBubble("contactUser_AfterReportBelumNasabah", 1, null) + CONSTANT_SPLIT_SYNTAX
                    + dialogUtil.CreateBubble("contactUser_AfterReportBelumNasabah", 2, null) + CONSTANT_SPLIT_SYNTAX
                    + dialogUtil.CreateBubble("contactUser_AfterReportBelumNasabah", 3, null) + CONSTANT_SPLIT_SYNTAX
                    + dialogUtil.CreateBubble("contactUser_AfterReportBelumNasabah", 4, null);
            log.debug("contactUser_afterFinal() extension request: {}", bubble);
            String subtitle = dialogUtil.CreateBubble("contactUser_AfterReportBelumNasabah", 5, null);
            ButtonTemplate button = new ButtonTemplate();
            button.setTitle("Daftar Nasabah OCBC NISP");
            button.setSubTitle(subtitle);
            button.setPictureLink(appProperties.getOCBC_PICTURES_DAFTARNASABAH());
            button.setPicturePath(appProperties.getOCBC_PICTURES_DAFTARNASABAH());
            List<EasyMap> actions = new ArrayList<>();
            EasyMap bookAction = new EasyMap();
            bookAction.setName("Join Here!");
            bookAction.setValue(appProperties.getOCBC_LINK_DAFTARNASABAH());
            actions.add(bookAction);
            button.setButtonValues(actions);
            ButtonBuilder buttonBuilder = new ButtonBuilder(button);
            String bubble2 = dialogUtil.CreateBubble("contactUser_AfterReportBelumNasabah", 6, null);
            respBuilder.append(bubble)
                    .append(CONSTANT_SPLIT_SYNTAX)
                    .append(buttonBuilder.build())
                    .append(CONSTANT_SPLIT_SYNTAX)
                    .append(bubble2);
            output.put(OUTPUT, respBuilder.toString());
            extensionResult.setValue(output);
        }
        extensionResult.setAgent(false);
        extensionResult.setRepeat(false);
        extensionResult.setSuccess(true);
        extensionResult.setNext(true);

        log.debug("validasi_contactUser_final(): {}", output);
        return extensionResult;
    }

}
