/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imi.dolphin.sdkwebservice.facade;

import com.google.gson.Gson;
import com.imi.dolphin.sdkwebservice.builder.ButtonBuilder;
import com.imi.dolphin.sdkwebservice.builder.ImageBuilder;
import com.imi.dolphin.sdkwebservice.model.ButtonTemplate;
import com.imi.dolphin.sdkwebservice.model.EasyMap;
import com.imi.dolphin.sdkwebservice.model.ExtensionRequest;
import com.imi.dolphin.sdkwebservice.model.ExtensionResult;
import com.imi.dolphin.sdkwebservice.model.UserToken;
import com.imi.dolphin.sdkwebservice.property.AppProperties;
import com.imi.dolphin.sdkwebservice.service.IDolphinService;
import com.imi.dolphin.sdkwebservice.service.MatrixParameter;
import com.imi.dolphin.sdkwebservice.service.ServiceImp;
import com.imi.dolphin.sdkwebservice.service.ValidationMethod;
import com.imi.dolphin.sdkwebservice.util.DialogUtil;
import com.imi.dolphin.sdkwebservice.util.OkHttpUtil;
import com.imi.dolphin.sdkwebservice.util.PictureUtil;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Deka
 */
@Service
public class Phase2BelajarBerinvestasiService {

    private static final Logger log = LogManager.getLogger(ServiceImp.class);

    public static final String OUTPUT = "output";
    private UserToken userToken;

    @Autowired
    AppProperties appProperties;

    @Autowired
    OkHttpUtil okHttpUtil;
    private static final String SPLIT = "&split&";

    @Autowired
    DialogUtil dialogUtil;

    @Autowired
    ValidationMethod validationMethod;

    @Autowired
    IDolphinService svcDolphinService;

    @Autowired
    WPPService wppService;

    @Autowired
    PictureUtil pictureUtil;

    @Autowired
    MatrixParameter matrixParameter;

    private String bubble = "";

    /**
     * Digunakan Untuk melakukan validasi terhadap inputan user
     *
     * @param extensionRequest digunakan untuk memanggil data-data dari response
     * user yang sudah diolah oleh bot
     * @return extensionResult mengirimkan json dengan format class
     * extensionResult
     */
    public ExtensionResult belajarBerinvestasi_validasiPertaanyaanUser(ExtensionRequest extensionRequest) {
        log.debug("belajarBerinvestasi_validasiPertaanyaanUser() extension request: {}", new Gson().toJson(extensionRequest, ExtensionRequest.class));
        ExtensionResult extensionResult = new ExtensionResult();
        extensionResult.setAgent(false);
        extensionResult.setRepeat(false);
        extensionResult.setSuccess(true);
        extensionResult.setNext(true);
        Map<String, String> clearEntities = new HashMap<>();
        String pertanyaan_user = dialogUtil.getEasyMapValueByName(extensionRequest, "pertanyaan_user").toLowerCase();

        if (pertanyaan_user.equalsIgnoreCase("apa tuh") || pertanyaan_user.equalsIgnoreCase("kasih tau")) {
        } else {
            clearEntities.put("pertanyaan_user", "");
            extensionResult.setEntities(clearEntities);
        }
        System.out.println(extensionResult);
        return extensionResult;
    }

    /**
     * Digunakan Untuk melakukan validasi terhadap inputan user
     *
     * @param extensionRequest digunakan untuk memanggil data-data dari response
     * user yang sudah diolah oleh bot
     * @return extensionResult mengirimkan json dengan format class
     * extensionResult
     */
    public ExtensionResult belajarBerinvestasi_validasiTujuanInvestasi(ExtensionRequest extensionRequest) {
        log.debug("belajarBerinvestasi_validasiTujuanInvestasi() extension request: {}", new Gson().toJson(extensionRequest, ExtensionRequest.class));
        ExtensionResult extensionResult = new ExtensionResult();
        extensionResult.setAgent(false);
        extensionResult.setRepeat(false);
        extensionResult.setSuccess(true);
        extensionResult.setNext(true);
        Map<String, String> clearEntities = new HashMap<>();
        String tujuan_investasi = dialogUtil.getEasyMapValueByName(extensionRequest, "tujuan_investasi").toLowerCase();

        if (tujuan_investasi.equalsIgnoreCase("wujudin life goalsku") || tujuan_investasi.equalsIgnoreCase("persiapan masa depan")) {
            clearEntities.put("validasi_tujuan_investasi", "validasi_tujuan_investasi");
            extensionResult.setEntities(clearEntities);
        } else {
            clearEntities.put("tujuan_investasi", "");
            extensionResult.setEntities(clearEntities);
        }
        System.out.println(extensionResult);
        return extensionResult;
    }

    /**
     * Digunakan Untuk melakukan validasi terhadap inputan user
     *
     * @param extensionRequest digunakan untuk memanggil data-data dari response
     * user yang sudah diolah oleh bot
     * @return extensionResult mengirimkan json dengan format class
     * extensionResult
     */
    public ExtensionResult belajarBerinvestasi_validasiPertaanyaanUser2(ExtensionRequest extensionRequest) {
        log.debug("belajarBerinvestasi_validasiPertaanyaanUser2() extension request: {}", new Gson().toJson(extensionRequest, ExtensionRequest.class));
        ExtensionResult extensionResult = new ExtensionResult();
        extensionResult.setAgent(false);
        extensionResult.setRepeat(false);
        extensionResult.setSuccess(true);
        extensionResult.setNext(true);
        Map<String, String> clearEntities = new HashMap<>();
        String pertanyaan_user2 = dialogUtil.getEasyMapValueByName(extensionRequest, "pertanyaan_user2").toLowerCase();

        if (pertanyaan_user2.equalsIgnoreCase("gimana cara tau profil risiko")) {
            clearEntities.put("tanya_profilresiko", "gimana cara tau profil risiko");
        } else if (pertanyaan_user2.equalsIgnoreCase("mengapa gitu")) {
        } else {
            clearEntities.put("pertanyaan_user2", "");
        }

        extensionResult.setEntities(clearEntities);
        return extensionResult;
    }

    /**
     * Digunakan Untuk menampilkan key output berupa response Bot
     *
     * @param extensionRequest digunakan untuk memanggil data-data dari response
     * user yang sudah diolah oleh bot
     * @return extensionResult mengirimkan json dengan format class
     * extensionResult
     */
    public ExtensionResult belajarBerinvestasi_jenisProfilRisiko(ExtensionRequest extensionRequest) {
        log.debug("belajarBerinvestasi_jenisProfilRisiko() extension request: {}", new Gson().toJson(extensionRequest, ExtensionRequest.class));
        ExtensionResult extensionResult = new ExtensionResult();
        extensionResult.setAgent(false);
        extensionResult.setRepeat(false);
        extensionResult.setSuccess(true);
        extensionResult.setNext(true);
        Map<String, String> clearEntities = new HashMap<>();
        Map<String, String> output = new HashMap<>();
        String tanya_profilresiko = dialogUtil.getEasyMapValueByName(extensionRequest, "tanya_profilresiko").toLowerCase();

        if (tanya_profilresiko.equalsIgnoreCase("gimana cara tau profil risiko")) {
            bubble = dialogUtil.CreateBubble("belajarBerinvestasi_jenisProfilRisiko", 1, null);
            log.debug("belajarBerinvestasi_validasiJawabanProfilResiko() extension request: {}", bubble);

            StringBuilder respBuilder = new StringBuilder();
            ButtonTemplate image = new ButtonTemplate();
            image.setPictureLink(appProperties.getOCBC_PICTURES_INFOGRAFIK2());
            image.setPicturePath(appProperties.getOCBC_PICTURES_INFOGRAFIK2());
            ImageBuilder imageBuilder = new ImageBuilder(image);
//            String imgBuilder = imageBuilder.build();

            String bubble2 = dialogUtil.CreateBubble("belajarBerinvestasi_jenisProfilRisiko", 2, null);
            respBuilder.append(bubble)
                    .append(SPLIT)
                    .append(imageBuilder.build())
                    .append(SPLIT)
                    .append(bubble2);
            output.put(OUTPUT, respBuilder.toString());

            extensionResult.setValue(output);
        } else {
            clearEntities.put("tanya_profilresiko", "");
        }

        extensionResult.setEntities(clearEntities);
        return extensionResult;
    }

    /**
     * Digunakan Untuk melakukan validasi terhadap inputan user
     *
     * @param extensionRequest digunakan untuk memanggil data-data dari response
     * user yang sudah diolah oleh bot
     * @return extensionResult mengirimkan json dengan format class
     * extensionResult
     */
    public ExtensionResult belajarBerinvestasi_validasiJawabanProfilResiko(ExtensionRequest extensionRequest) {
        log.debug("belajarBerinvestasi_validasiJawabanProfilResiko() extension request: {}", new Gson().toJson(extensionRequest, ExtensionRequest.class));
        ExtensionResult extensionResult = new ExtensionResult();
        extensionResult.setAgent(false);
        extensionResult.setRepeat(false);
        extensionResult.setSuccess(true);
        extensionResult.setNext(true);
        Map<String, String> clearEntities = new HashMap<>();
        Map<String, String> output = new HashMap<>();
        String jawaban_profilresiko = dialogUtil.getEasyMapValueByName(extensionRequest, "jawaban_profilresiko").toLowerCase();

        if (jawaban_profilresiko.equalsIgnoreCase("sudah tau")) {
            clearEntities.put("pertanyaan_user3", "lanjut");
        } else if (jawaban_profilresiko.equalsIgnoreCase("masih bingung")) {
        } else {
            clearEntities.put("jawaban_profilresiko", "");
            bubble = dialogUtil.CreateBubble("belajarBerinvestasi_jenisProfilRisiko", 1, null);
            log.debug("belajarBerinvestasi_validasiJawabanProfilResiko() extension request: {}", bubble);

            StringBuilder respBuilder = new StringBuilder();
            ButtonTemplate image = new ButtonTemplate();
            image.setPictureLink(appProperties.getOCBC_PICTURES_INFOGRAFIK2());
            image.setPicturePath(appProperties.getOCBC_PICTURES_INFOGRAFIK2());
            ImageBuilder imageBuilder = new ImageBuilder(image);
//            String imgBuilder = imageBuilder.build();

            String bubble2 = dialogUtil.CreateBubble("belajarBerinvestasi_jenisProfilRisiko", 2, null);
            respBuilder.append(bubble)
                    .append(SPLIT)
                    .append(imageBuilder.build())
                    .append(SPLIT)
                    .append(bubble2);
            output.put(OUTPUT, respBuilder.toString());

            extensionResult.setValue(output);
        }
        extensionResult.setEntities(clearEntities);
        return extensionResult;
    }

    /**
     * Digunakan Untuk melakukan validasi terhadap inputan user
     *
     * @param extensionRequest digunakan untuk memanggil data-data dari response
     * user yang sudah diolah oleh bot
     * @return extensionResult mengirimkan json dengan format class
     * extensionResult
     */
    public ExtensionResult belajarBerinvestasi_validasiPertanyaanUser3(ExtensionRequest extensionRequest) {
        log.debug("belajarBerinvestasi_validasiPertanyaanUser3() extension request: {}", new Gson().toJson(extensionRequest, ExtensionRequest.class));
        ExtensionResult extensionResult = new ExtensionResult();
        extensionResult.setAgent(false);
        extensionResult.setRepeat(false);
        extensionResult.setSuccess(true);
        extensionResult.setNext(true);
        Map<String, String> clearEntities = new HashMap<>();
        Map<String, String> output = new HashMap<>();
        String pertanyaan_user3 = dialogUtil.getEasyMapValueByName(extensionRequest, "pertanyaan_user3").toLowerCase();

        if (pertanyaan_user3.equalsIgnoreCase("lanjut")) {
            ButtonTemplate image = new ButtonTemplate();
            image.setPictureLink(appProperties.getOCBC_PICTURES_INFOGRAFIK1());
            image.setPicturePath(appProperties.getOCBC_PICTURES_INFOGRAFIK1());
            ImageBuilder imageBuilder = new ImageBuilder(image);

//            ButtonTemplate image2 = new ButtonTemplate();
//            image2.setPictureLink("");
//            image2.setPicturePath("");
//            ImageBuilder imageBuilder2 = new ImageBuilder(image2);
//            output.put(OUTPUT, imageBuilder.build() + SPLIT + imageBuilder2.build());
            output.put(OUTPUT, imageBuilder.build());

            extensionResult.setValue(output);
        } else {
            clearEntities.put("pertanyaan_user3", "");
        }

        extensionResult.setEntities(clearEntities);
        return extensionResult;
    }

    /**
     * Digunakan untuk memperoleh rekomendasi product yang sesuai dengan data
     * yang dimiliki oleh user
     *
     * @return CarouselBuilder berupa sting untuk menampilkan rekomendasi
     * product berdasarkan data yang dimiliki oleh user
     */
    public ButtonBuilder belajarBerinvestasi_getCarouselQuotes() {
//        String DIR = "/var/www/html/pictures/do_not_delete";
        String DIR = appProperties.getOCBC_PICTURES_QUOTES();
        File file = new File(DIR);

        File[] myFiles = file.listFiles();
        System.out.println("List file dari direktori " + DIR + " adalah:");
        //foreach loop
        int jumlah = 0;
        for (File files : myFiles) {
            try {
                System.out.println(files.getCanonicalFile());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            jumlah++;
        }
        String imagename = hitungRandomStringDesktop(jumlah);
        System.out.println("jumlah file = " + jumlah);
        System.out.println("gambar yang muncul adalah = " + imagename);
        String imagecarousel = appProperties.getOCBC_PICTURES_CAROUSELQUOTES();

        ButtonTemplate button = new ButtonTemplate();
        button.setTitle(dialogUtil.CreateBubble("belajarBerinvestasi_getCarouselQuotes", 1, null));
        button.setSubTitle(dialogUtil.CreateBubble("belajarBerinvestasi_getCarouselQuotes", 2, null));
        button.setPictureLink(imagecarousel);
        button.setPicturePath(imagecarousel);
        List<EasyMap> actions = new ArrayList<>();
        EasyMap bookAction = new EasyMap();
        bookAction.setName(dialogUtil.CreateBubble("belajarBerinvestasi_getCarouselQuotes", 3, null));
        bookAction.setValue(appProperties.OCBC_WEBVIEW_BASE_URL + "/tnc/quotes/" + imagename + "?view=widget");
        actions.add(bookAction);
//        EasyMap bookAction2 = new EasyMap();
//        bookAction2.setName("Lanjut");
//        bookAction2.setValue("bikin rencana investasi");
//        actions.add(bookAction2);

        button.setButtonValues(actions);
        ButtonBuilder buttonBuilder = new ButtonBuilder(button);
        log.debug("belajarBerinvestasi_getCarouselQuotes() extension request: {}", buttonBuilder.toString());

        return buttonBuilder;
    }

    /**
     * mengembalikan hasil String berupa Judul file
     *
     *
     * @param banyak berisi jumlah file
     * @return String judul quotes
     */
    public static String hitungRandomStringDesktop(int banyak) {
        String gambar = "";
        int judul = 0;
        Random angkaRandom = new Random();
        judul = angkaRandom.nextInt(banyak) + 1;

        gambar = judul + "";

        return gambar;
    }

    /**
     * mengembalikan hasil String berupa Judul file
     *
     *
     * @param banyak berisi jumlah file
     * @return String judul quotes
     */
    public static String hitungRandomStringMobile(int banyak) {
        String gambar = "";
        int judul = 0;
        Random angkaRandom = new Random();
        judul = angkaRandom.nextInt(banyak) + 1;

        gambar = "mquote" + judul + ".jpg";

        return gambar;
    }

    /**
     * Digunakan Untuk melakukan validasi terhadap inputan user
     *
     * @param extensionRequest digunakan untuk memanggil data-data dari response
     * user yang sudah diolah oleh bot
     * @return extensionResult mengirimkan json dengan format class
     * extensionResult
     */
    public ExtensionResult belajarBerinvestasi_validasiJawabanBerani(ExtensionRequest extensionRequest) {
        log.debug("belajarBerinvestasi_validasiJawabanBerani() extension request: {}", new Gson().toJson(extensionRequest, ExtensionRequest.class));
        ExtensionResult extensionResult = new ExtensionResult();
        extensionResult.setAgent(false);
        extensionResult.setRepeat(false);
        extensionResult.setSuccess(true);
        extensionResult.setNext(true);
        Map<String, String> clearEntities = new HashMap<>();
        Map<String, String> output = new HashMap<>();

        String jawaban_berani = dialogUtil.getEasyMapValueByName(extensionRequest, "jawaban_berani").toLowerCase();

        if (jawaban_berani.equalsIgnoreCase("berani")) {
            ButtonBuilder buttonBuilder = belajarBerinvestasi_getCarouselQuotes();
            bubble = dialogUtil.CreateBubble("belajarBerinvestasi_bikinRencanaInvestasi", 1, null);
            output.put(OUTPUT, buttonBuilder.build() + SPLIT + bubble);

            extensionResult.setValue(output);
        } else if (jawaban_berani.equalsIgnoreCase("masih ragu")) {
            clearEntities.put("final_belajarberinvestasisatu", "masih ragu");
            clearEntities.put("validasi_finalbelajarberinvestasisatu", "masih ragu");
        } else {
            clearEntities.put("jawaban_berani", "");
        }

        extensionResult.setEntities(clearEntities);
        return extensionResult;
    }

    /**
     * Digunakan Untuk melakukan validasi terhadap inputan user
     *
     * @param extensionRequest digunakan untuk memanggil data-data dari response
     * user yang sudah diolah oleh bot
     * @return extensionResult mengirimkan json dengan format class
     * extensionResult
     */
    public ExtensionResult belajarBerinvestasi_validasiFinalBelajarBerinvestasiSatu(ExtensionRequest extensionRequest) {
        log.debug("belajarBerinvestasi_validasiFinalBelajarBerinvestasiSatu() extension request: {}", new Gson().toJson(extensionRequest, ExtensionRequest.class));
        ExtensionResult extensionResult = new ExtensionResult();
        extensionResult.setAgent(false);
        extensionResult.setRepeat(false);
        extensionResult.setSuccess(true);
        extensionResult.setNext(true);
        Map<String, String> clearEntities = new HashMap<>();
        Map<String, String> output = new HashMap<>();

        clearEntities.put("final_belajarberinvestasisatu", "");
        ButtonBuilder buttonBuilder = belajarBerinvestasi_getCarouselQuotes();
        bubble = dialogUtil.CreateBubble("belajarBerinvestasi_bikinRencanaInvestasi", 1, null);
        output.put(OUTPUT, buttonBuilder.build() + SPLIT + bubble);

        extensionResult.setValue(output);
        extensionResult.setEntities(clearEntities);
        return extensionResult;
    }

    /**
     * Digunakan Untuk menampilkan response bot
     *
     * @param extensionRequest digunakan untuk memanggil data-data dari response
     * user yang sudah diolah oleh bot
     * @return extensionResult mengirimkan json dengan format class
     * extensionResult
     */
    public ExtensionResult belajarBerinvestasi_pertanyaanUser4(ExtensionRequest extensionRequest) {
        log.debug("belajarBerinvestasi_pertanyaanUser4() extension request: {}", new Gson().toJson(extensionRequest, ExtensionRequest.class));
        ExtensionResult extensionResult = new ExtensionResult();
        extensionResult.setAgent(false);
        extensionResult.setRepeat(false);
        extensionResult.setSuccess(true);
        extensionResult.setNext(true);
        Map<String, String> clearEntities = new HashMap<>();
        Map<String, String> output = new HashMap<>();

        String jawaban_ragu = dialogUtil.getEasyMapValueByName(extensionRequest, "jawaban_ragu").toLowerCase();

        if (jawaban_ragu.equalsIgnoreCase("investasi katanya mahal")) {
            bubble = dialogUtil.CreateBubble("belajarBerinvestasi_pertanyaanUser4", 1, null) + SPLIT
                    + dialogUtil.CreateBubble("belajarBerinvestasi_pertanyaanUser4", 2, null);
            output.put(OUTPUT, bubble);
            extensionResult.setValue(output);

        } else if (jawaban_ragu.equalsIgnoreCase("risiko tinggi nanti rugi")) {
            bubble = dialogUtil.CreateBubble("belajarBerinvestasi_pertanyaanUser4", 3, null) + SPLIT
                    + dialogUtil.CreateBubble("belajarBerinvestasi_pertanyaanUser4", 4, null);
            output.put(OUTPUT, bubble);
            extensionResult.setValue(output);

        } else {
            clearEntities.put("jawaban_ragu", "");
            clearEntities.put("pertanyaan_user4", "");
        }
        extensionResult.setEntities(clearEntities);
        return extensionResult;
    }

    /**
     * Digunakan Untuk melakukan validasi terhadap inputan user
     *
     * @param extensionRequest digunakan untuk memanggil data-data dari response
     * user yang sudah diolah oleh bot
     * @return extensionResult mengirimkan json dengan format class
     * extensionResult
     */
    public ExtensionResult belajarBerinvestasi_validasiPertanyaanUser4(ExtensionRequest extensionRequest) {
        log.debug("belajarBerinvestasi_validasiPertanyaanUser4() extension request: {}", new Gson().toJson(extensionRequest, ExtensionRequest.class));
        ExtensionResult extensionResult = new ExtensionResult();
        extensionResult.setAgent(false);
        extensionResult.setRepeat(false);
        extensionResult.setSuccess(true);
        extensionResult.setNext(true);
        Map<String, String> clearEntities = new HashMap<>();
        Map<String, String> output = new HashMap<>();

        String pertanyaan_user4 = dialogUtil.getEasyMapValueByName(extensionRequest, "pertanyaan_user4").toLowerCase();

        if (pertanyaan_user4.equalsIgnoreCase("aku siap")) {
            ButtonBuilder buttonBuilder = belajarBerinvestasi_getCarouselQuotes();
            bubble = dialogUtil.CreateBubble("belajarBerinvestasi_bikinRencanaInvestasi", 1, null);
            output.put(OUTPUT, buttonBuilder.build() + SPLIT + bubble);

            extensionResult.setValue(output);
        } else if (pertanyaan_user4.equalsIgnoreCase("terus apa lagi tipsnya")) {
            clearEntities.put("final_belajarberinvestasidua", "terus apa lagi tipsnya");
            clearEntities.put("validasi_finalbelajarberinvestasidua", "terus apa lagi tipsnya");
        } else {
            clearEntities.put("pertanyaan_user4", "");
        }

        extensionResult.setEntities(clearEntities);
        return extensionResult;
    }

    /**
     * Digunakan Untuk melakukan validasi terhadap inputan user
     *
     * @param extensionRequest digunakan untuk memanggil data-data dari response
     * user yang sudah diolah oleh bot
     * @return extensionResult mengirimkan json dengan format class
     * extensionResult
     */
    public ExtensionResult belajarBerinvestasi_validasiFinalBelajarBerinvestasiDua(ExtensionRequest extensionRequest) {
        log.debug("belajarBerinvestasi_validasiFinalBelajarBerinvestasiDua() extension request: {}", new Gson().toJson(extensionRequest, ExtensionRequest.class));
        ExtensionResult extensionResult = new ExtensionResult();
        extensionResult.setAgent(false);
        extensionResult.setRepeat(false);
        extensionResult.setSuccess(true);
        extensionResult.setNext(true);
        Map<String, String> clearEntities = new HashMap<>();
        Map<String, String> output = new HashMap<>();

        clearEntities.put("final_belajarberinvestasidua", "");
        ButtonBuilder buttonBuilder = belajarBerinvestasi_getCarouselQuotes();
        bubble = dialogUtil.CreateBubble("belajarBerinvestasi_bikinRencanaInvestasi", 1, null);
        output.put(OUTPUT, buttonBuilder.build() + SPLIT + bubble);

        extensionResult.setValue(output);
        extensionResult.setEntities(clearEntities);
        return extensionResult;
    }

    /**
     * Digunakan Untuk melakukan validasi terhadap inputan user
     *
     * @param extensionRequest digunakan untuk memanggil data-data dari response
     * user yang sudah diolah oleh bot
     * @return extensionResult mengirimkan json dengan format class
     * extensionResult
     */
    public ExtensionResult belajarBerinvestasi_validasiPertanyaanUser5(ExtensionRequest extensionRequest) {
        log.debug("belajarBerinvestasi_validasiPertanyaanUser5() extension request: {}", new Gson().toJson(extensionRequest, ExtensionRequest.class));
        ExtensionResult extensionResult = new ExtensionResult();
        extensionResult.setAgent(false);
        extensionResult.setRepeat(false);
        extensionResult.setSuccess(true);
        extensionResult.setNext(true);
        Map<String, String> clearEntities = new HashMap<>();
        Map<String, String> output = new HashMap<>();

        String pertanyaan_user5 = dialogUtil.getEasyMapValueByName(extensionRequest, "pertanyaan_user5").toLowerCase();

        if (pertanyaan_user5.equalsIgnoreCase("oke")) {
            ButtonBuilder buttonBuilder = belajarBerinvestasi_getCarouselQuotes();
            bubble = dialogUtil.CreateBubble("belajarBerinvestasi_bikinRencanaInvestasi", 1, null);
            output.put(OUTPUT, buttonBuilder.build() + SPLIT + bubble);

            extensionResult.setValue(output);
        } else {
            clearEntities.put("pertanyaan_user5", "");
        }

        extensionResult.setEntities(clearEntities);
        return extensionResult;
    }

    /**
     * Digunakan Untuk melakukan validasi terhadap inputan user
     *
     * @param extensionRequest digunakan untuk memanggil data-data dari response
     * user yang sudah diolah oleh bot
     * @return extensionResult mengirimkan json dengan format class
     * extensionResult
     */
    public ExtensionResult belajarBerinvestasi_validasiFinalBelajarBerinvestasiTiga(ExtensionRequest extensionRequest) {
        log.debug("belajarBerinvestasi_validasiFinalBelajarBerinvestasiTiga() extension request: {}", new Gson().toJson(extensionRequest, ExtensionRequest.class));
        ExtensionResult extensionResult = new ExtensionResult();
        extensionResult.setAgent(false);
        extensionResult.setRepeat(false);
        extensionResult.setSuccess(true);
        extensionResult.setNext(true);
        Map<String, String> clearEntities = new HashMap<>();
        Map<String, String> output = new HashMap<>();

        clearEntities.put("final_belajarberinvestasitiga", "");
        ButtonBuilder buttonBuilder = belajarBerinvestasi_getCarouselQuotes();
        bubble = dialogUtil.CreateBubble("belajarBerinvestasi_bikinRencanaInvestasi", 1, null);
        output.put(OUTPUT, buttonBuilder.build() + SPLIT + bubble);

        extensionResult.setValue(output);
        extensionResult.setEntities(clearEntities);
        return extensionResult;
    }

}
