/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imi.dolphin.sdkwebservice.facade;

import com.google.gson.Gson;
import com.imi.dolphin.sdkwebservice.builder.ButtonBuilder;
import com.imi.dolphin.sdkwebservice.builder.QuickReplyBuilder;
import com.imi.dolphin.sdkwebservice.model.ButtonTemplate;
import com.imi.dolphin.sdkwebservice.model.Contact;
import com.imi.dolphin.sdkwebservice.model.DataLeads;
import com.imi.dolphin.sdkwebservice.model.EasyMap;
import com.imi.dolphin.sdkwebservice.model.ExtensionRequest;
import com.imi.dolphin.sdkwebservice.model.ExtensionResult;
import com.imi.dolphin.sdkwebservice.model.Transaction_Data;
import com.imi.dolphin.sdkwebservice.model.UserToken;
import com.imi.dolphin.sdkwebservice.param.ParamSdk;
import com.imi.dolphin.sdkwebservice.property.AppProperties;
import com.imi.dolphin.sdkwebservice.service.IDolphinService;
import com.imi.dolphin.sdkwebservice.service.IMailService;
import com.imi.dolphin.sdkwebservice.service.ServiceImp;
import com.imi.dolphin.sdkwebservice.service.ValidationMethod;
import com.imi.dolphin.sdkwebservice.util.DialogUtil;
import com.imi.dolphin.sdkwebservice.util.OkHttpUtil;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author cokkyturnip
 */
@Service
public class PertumbuhanAsetService {

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

    private String bubble = "";

    /**
     * key output digunkan untuk menampilkan response dari bot sesuai dengan
     * jawaban yang telah di set didalam file dialog
     *
     * @param extensionRequest digunakan untuk memanggil data-data dari response
     * user yang sudah diolah oleh bot
     * @return extensionResult mengirimkan json dengan format class
     * extensionResult
     */
    public ExtensionResult pertumbuhanAset_entitasPendanaan(ExtensionRequest extensionRequest) {
        log.debug("pertumbuhanAset_entitasPendanaan() extension request: {}", extensionRequest);
        Map<String, String> output = new HashMap<>();
        bubble = dialogUtil.CreateBubble("pertumbuhanAset_entitasPendanaan", 1, null) + CONSTANT_SPLIT_SYNTAX
                + dialogUtil.CreateBubble("pertumbuhanAset_entitasPendanaan", 2, null);
        log.debug("pertumbuhanAset_entitasPendanaan() extension request: {}", bubble);
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
     * key output digunkan untuk menampilkan response dari bot sesuai dengan
     * jawaban yang telah di set didalam file dialog
     *
     * @param extensionRequest digunakan untuk memanggil data-data dari response
     * user yang sudah diolah oleh bot
     * @return extensionResult mengirimkan json dengan format class
     * extensionResult
     */
    public ExtensionResult pertumbuhanAset_ValidasiPendanaan(ExtensionRequest extensionRequest) {
        log.debug("pertumbuhanAset_ValidasiPendanaan() extension request: {}", new Gson().toJson(extensionRequest, ExtensionRequest.class));
        ExtensionResult extensionResult = new ExtensionResult();
        Map<String, String> output = new HashMap<>();
        Map<String, String> clearEntities = new HashMap<>();
        String pendanaan = dialogUtil.getEasyMapValueByName(extensionRequest, "pendanaan");
        clearEntities = validationMethod.valPendanaan(pendanaan);
        extensionResult.setEntities(clearEntities);
        extensionResult.setAgent(false);
        extensionResult.setRepeat(false);
        extensionResult.setSuccess(true);
        extensionResult.setNext(true);
        if (clearEntities.get("pendanaan").equals("")) {
            bubble = dialogUtil.CreateBubble("pertumbuhanAset_ValidasiPendanaan", 1, null) + CONSTANT_SPLIT_SYNTAX
                    + dialogUtil.CreateBubble("pertumbuhanAset_ValidasiPendanaan", 2, null);
        } else {
            Map<String, String> param = new HashMap<>();
            if (clearEntities.get("pendanaan").equals("0")) {
                param.put("pendanaan", "tiap bulannya");
            } else if (clearEntities.get("pendanaan").equals("1")) {
                param.put("pendanaan", "di awal");
            }
            bubble = dialogUtil.CreateBubble("pertumbuhanAset_ValidasiPendanaan", 3, param);
        }
        output.put(OUTPUT, bubble);
        extensionResult.setValue(output);
        return extensionResult;
    }

    /**
     * key output digunkan untuk menampilkan response dari bot sesuai dengan
     * jawaban yang telah di set didalam file dialog
     *
     * @param extensionRequest digunakan untuk memanggil data-data dari response
     * user yang sudah diolah oleh bot
     * @return extensionResult mengirimkan json dengan format class
     * extensionResult
     */
    public ExtensionResult pertumbuhanAset_entitasBesarDanaInvestasi(ExtensionRequest extensionRequest) {
        log.debug("pertumbuhanAset_entitasBesarDanaInvestasi() extension request: {}", extensionRequest);
        String pendanaan = dialogUtil.getEasyMapValueByName(extensionRequest, "pendanaan");
        ExtensionResult extensionResult = new ExtensionResult();
        extensionResult.setAgent(false);
        extensionResult.setRepeat(false);
        extensionResult.setSuccess(true);
        extensionResult.setNext(true);
        Map<String, String> output = new HashMap<>();
        Map<String, String> param = new HashMap<>();
        if (pendanaan.equals("0")) {
            param.put("pendanaan", "tiap bulannya");
        } else if (pendanaan.equals("1")) {
            param.put("pendanaan", "di awal");
        }
        bubble = dialogUtil.CreateBubble("pertumbuhanAset_entitasBesarDanaInvestasi", 1, param);
        output.put(OUTPUT, bubble);
        extensionResult.setValue(output);
        return extensionResult;
    }

    /**
     * key output digunkan untuk menampilkan response dari bot sesuai dengan
     * jawaban yang telah di set didalam file dialog
     *
     * @param extensionRequest digunakan untuk memanggil data-data dari response
     * user yang sudah diolah oleh bot
     * @return extensionResult mengirimkan json dengan format class
     * extensionResult
     */
    public ExtensionResult pertumbuhanAset_ValidasiBesarDanaInvestasi(ExtensionRequest extensionRequest) {
        log.debug("pertumbuhanAset_ValidasiBesarDanaInvestasi() extension request: {}", new Gson().toJson(extensionRequest, ExtensionRequest.class));
        ExtensionResult extensionResult = new ExtensionResult();
        Map<String, String> output = new HashMap<>();
        Map<String, String> clearEntities = new HashMap<>();
        String besar_dana_investasi = dialogUtil.getEasyMapValueByName(extensionRequest, "besar_dana_invesatasi");
        String pendanaan = dialogUtil.getEasyMapValueByName(extensionRequest, "pendanaan");
        log.debug("pertumbuhanAset_ValidasiBesarDanaInvestasi() besar_dana_investasi : {}", besar_dana_investasi);
        clearEntities = validationMethod.valBesarDanaInvestasi(besar_dana_investasi);
        extensionResult.setEntities(clearEntities);
        extensionResult.setAgent(false);
        extensionResult.setRepeat(false);
        extensionResult.setSuccess(true);
        extensionResult.setNext(true);
        if (clearEntities.get("besar_dana_invesatasi").equals("")) {
            Map<String, String> param = new HashMap<>();
            if (pendanaan.equals("0")) {
                param.put("pendanaan", "tiap bulannya");
            } else {
                param.put("pendanaan", "di awal");
            }
            bubble = dialogUtil.CreateBubble("pertumbuhanAset_ValidasiBesarDanaInvestasi", 1, param);
        } else {
            bubble = dialogUtil.CreateBubble("pertumbuhanAset_ValidasiBesarDanaInvestasi", 2, null);
        }
        output.put(OUTPUT, bubble);
        extensionResult.setValue(output);
        return extensionResult;
    }

    /**
     * key output digunkan untuk menampilkan response dari bot sesuai dengan
     * jawaban yang telah di set didalam file dialog
     *
     * @param extensionRequest digunakan untuk memanggil data-data dari response
     * user yang sudah diolah oleh bot
     * @return extensionResult mengirimkan json dengan format class
     * extensionResult
     */
    public ExtensionResult pertumbuhanAset_entitasLamaInvestasi(ExtensionRequest extensionRequest) {
        log.debug("pertumbuhanAset_entitasLamaInvestasi() extension request: {}", extensionRequest);
        ExtensionResult extensionResult = new ExtensionResult();
        extensionResult.setAgent(false);
        extensionResult.setRepeat(false);
        extensionResult.setSuccess(true);
        extensionResult.setNext(true);
        Map<String, String> output = new HashMap<>();
        bubble = dialogUtil.CreateBubble("pertumbuhanAset_entitasLamaInvestasi", 1, null);
        output.put(OUTPUT, bubble);
        extensionResult.setValue(output);
        return extensionResult;
    }

    /**
     * key output digunkan untuk menampilkan response dari bot sesuai dengan
     * jawaban yang telah di set didalam file dialog
     *
     * @param extensionRequest digunakan untuk memanggil data-data dari response
     * user yang sudah diolah oleh bot
     * @return extensionResult mengirimkan json dengan format class
     * extensionResult
     */
//    public ExtensionResult pertumbuhanAset_ValidasiLamaInvestasi(ExtensionRequest extensionRequest) {
//        userToken = svcDolphinService.getUserToken(userToken);
//        String contactId = extensionRequest.getIntent().getTicket().getContactId();
//        Contact contact = svcDolphinService.getCustomer(userToken, contactId);
//        String b = contact.getAdditionalField().get(0);
//        DataLeads dataLeads1 = new Gson().fromJson(b, DataLeads.class);
//        int RiskProfileId = dataLeads1.getTransaction_Data()[0].getRisk_Profile_ID();
//        String name = dataLeads1.getTransaction_Data()[0].getName();
//        log.debug("pertumbuhanAset_ValidasiLamaInvestasi() extension request: {}", new Gson().toJson(extensionRequest, ExtensionRequest.class));
//        ExtensionResult extensionResult = new ExtensionResult();
//        Map<String, String> output = new HashMap<>();
//        Map<String, String> clearEntities = new HashMap<>();
//        String lamaInvestasi = dialogUtil.getEasyMapValueByName(extensionRequest, "lama_investasi");
//        String besar_dana_investasi = dialogUtil.getEasyMapValueByName(extensionRequest, "besar_dana_invesatasi");
//        String pendanaan = dialogUtil.getEasyMapValueByName(extensionRequest, "pendanaan");
//        lamaInvestasi = lamaInvestasi.toLowerCase()
//                .replace("tahun", "")
//                .replace("thun", "")
//                .replace("thn", "")
//                .replace(",", "")
//                .replace(".", "")
//                .replace("-", "")
//                .trim();
//        String kalimat = lamaInvestasi;
//        String[] kata = kalimat.split("");
//        if (kata.length > 2) {
//            lamaInvestasi = kata[0] + kata[1];
//        } else {
//            lamaInvestasi = lamaInvestasi;
//        }
//        clearEntities = validationMethod.valLamaInvestasi_pertumbuhanAset(lamaInvestasi);
//
//        extensionResult.setEntities(clearEntities);
//        extensionResult.setAgent(false);
//        extensionResult.setRepeat(false);
//        extensionResult.setSuccess(true);
//        extensionResult.setNext(true);
//        StringBuilder respBuilder = new StringBuilder();
//        if (clearEntities.get("lama_investasi").equals("")) {
//            bubble = dialogUtil.CreateBubble("pertumbuhanAset_ValidasiLamaInvestasi", 1, null);
//            output.put(OUTPUT, bubble);
//        } else {
//            if (Integer.parseInt(lamaInvestasi.replace(" ", "").trim()) > 50) {
//                lamaInvestasi = "50";
//            }
//            log.debug("pertumbuhanAset_webView() extension request: {}", extensionRequest);
//            ButtonTemplate button = new ButtonTemplate();
//            button.setTitle(appProperties.getOcbc_carousel_title_growth());
//            button.setSubTitle(appProperties.getOcbc_carousel_subtitle_growth());
//            button.setPictureLink(appProperties.getOcbc_carousel_gambar_growth());
//            button.setPicturePath(appProperties.getOcbc_carousel_gambar_growth());
//            List<EasyMap> actions = new ArrayList<>();
//            EasyMap bookAction = new EasyMap();
//            bookAction.setName(appProperties.getOcbc_carousel_nameButton_growth());
//            bookAction.setValue(appProperties.OCBC_WEBVIEW_BASE_URL + "/growth/" + contactId + "/"
//                    + besar_dana_investasi + "/"
//                    + pendanaan + "/"
//                    + lamaInvestasi + "/"
//                    + name + "/"
//                    + RiskProfileId + "?view=widget");
//            actions.add(bookAction);
//            button.setButtonValues(actions);
//            ButtonBuilder buttonBuilder = new ButtonBuilder(button);
//            String btnBuilder = buttonBuilder.build();
//            bubble = dialogUtil.CreateBubble("pertumbuhanAset_ValidasiLamaInvestasi", 2, null);
//            respBuilder.append(bubble).append(CONSTANT_SPLIT_SYNTAX).append(btnBuilder);
//            output.put(OUTPUT, respBuilder.toString());
//        }
//        extensionResult.setValue(output);
//        return extensionResult;
//    }
    /**
     * mengembalikan hasil String berupa validasi Lama Investasi
     *
     * @param inputUser berisi tahun yang diketikan user
     * @return String konverterTahun
     */
    public String konverterTahun(String inputUser) {

        String inputanUser = inputUser.toLowerCase();

        int separatorTahunLength = 5;
        int separatorBulanLength = 5;

        int indexTahun = 0;
        int indexBulan = 0;
        int tahun = 0;
        int bulan = 0;
        String hasil = "";

        indexTahun = inputanUser.indexOf("tahun");
        if (indexTahun < 0) {
            indexTahun = inputanUser.indexOf("thn");
            if (indexTahun < 0) {
//                indexTahun = 0;
                separatorTahunLength = 0;

            } else {
                separatorTahunLength = 3;

            }

        }

        indexBulan = inputanUser.indexOf("bulan");
        if (indexBulan < 0) {
            indexBulan = inputanUser.indexOf("bln");
            separatorBulanLength = 3;
        }

        //cek masih ada kata "bulan" atau "bln" ga...
        if (indexBulan > 0) {
            String newInput = inputanUser.substring(indexBulan + 1, inputUser.length());
            int newIndexBulan = 0;
            newIndexBulan = newInput.indexOf("bulan");
            if (newIndexBulan < 0) {
                newIndexBulan = newInput.indexOf("bln");
            }

            if (newIndexBulan > 0) {
                return "";
            }
        }

        if (indexTahun > indexBulan && indexBulan > 0) {
            return hasil;
        } else {
            int hitungantahun = 0;
            if (indexTahun > 0) {
                try {
                    tahun = Integer.parseInt(inputanUser.substring(0, indexTahun).trim()) * 12;
                    hitungantahun = Integer.parseInt(inputanUser.substring(0, indexTahun).trim()) + separatorTahunLength;
                } catch (Exception e) {
                    return hasil;
                }
            }
            if (indexBulan < 0 && Integer.toString(tahun).length() + separatorTahunLength != inputanUser.trim().length()) {

                if (inputanUser.trim().length() > hitungantahun) {
                    return hasil;
                }
            } else if (indexBulan > 0 && indexTahun != 0) {
                int start = indexTahun + separatorTahunLength;
                if (start < 0) {
                    start = 0;
                }
                try {
                    bulan = Integer.parseInt(inputanUser.substring(start, indexBulan).trim());

                    //cek dibelakang bulan masih ada karakter ga...
                    if (inputanUser.substring(indexBulan + separatorBulanLength, inputanUser.length()).length() > 0) {
                        return "";
                    }
                } catch (Exception e) {
                    return "";
                }
            }
        }

        if (indexTahun < 0 && indexBulan < 0) {
            try {
                tahun = Integer.parseInt(inputanUser) * 12;
            } catch (Exception e) {
                return hasil;
            }
        }
        hasil = tahun + bulan + "";
        if (hasil.equals("0")) {
            hasil = "";
        }
        return hasil;

    }

    /**
     * key output digunkan untuk menampilkan response dari bot sesuai dengan
     * jawaban yang telah di set didalam file dialog
     *
     * @param extensionRequest digunakan untuk memanggil data-data dari response
     * user yang sudah diolah oleh bot
     * @return extensionResult mengirimkan json dengan format class
     * extensionResult
     */
    public ExtensionResult pertumbuhanAset_ValidasiLamaInvestasi(ExtensionRequest extensionRequest) {
        userToken = svcDolphinService.getUserToken(userToken);
        String contactId = extensionRequest.getIntent().getTicket().getContactId();
        Contact contact = svcDolphinService.getCustomer(userToken, contactId);
        String b = contact.getAdditionalField().get(0);
        DataLeads dataLeads1 = new Gson().fromJson(b, DataLeads.class
        );
        int RiskProfileId = dataLeads1.getTransaction_Data()[0].getRisk_Profile_ID();
        String name = dataLeads1.getTransaction_Data()[0].getName();
        log.debug("pertumbuhanAset_ValidasiLamaInvestasi() extension request: {}", new Gson().toJson(extensionRequest, ExtensionRequest.class
        ));
        ExtensionResult extensionResult = new ExtensionResult();
        Map<String, String> output = new HashMap<>();
        Map<String, String> clearEntities = new HashMap<>();
        String lamaInvestasi = dialogUtil.getEasyMapValueByName(extensionRequest, "lama_investasi");
        System.out.println("Inputan User Lama Investasi :" + lamaInvestasi);
        String besar_dana_investasi = dialogUtil.getEasyMapValueByName(extensionRequest, "besar_dana_invesatasi");
        String pendanaan = dialogUtil.getEasyMapValueByName(extensionRequest, "pendanaan");
        lamaInvestasi = konverterTahun(lamaInvestasi);
        System.out.println("hasil konversi : " + lamaInvestasi);

        if (lamaInvestasi.equalsIgnoreCase("")) {
            clearEntities.put("lama_investasi", "");
            clearEntities.put("validasi_lama_investasi", "");
            bubble = dialogUtil.CreateBubble("pertumbuhanAset_ValidasiLamaInvestasi", 1, null);
            output.put(OUTPUT, bubble);
            extensionResult.setValue(output);
        } else {
            besar_dana_investasi = besar_dana_investasi
                    .replace(" ", "")
                    .replace(".00", "")
                    .replace(".0", "")
                    .replace(".", "")
                    .replace(",", "")
                    .replace("-", "")
                    .replace("rp.", "")
                    .replace("rp", "")
                    .replace("jt", "000000")
                    .replace("juta", "000000")
                    .replace("m", "000000000")
                    .replace("miliar", "000000000")
                    .replace("t", "000000000000")
                    .replace("triliun", "000000000000")
                    .replace("ribu", "000").replace("rbu", "000").replace("rb", "000")
                    .replace("rupiah", "")
                    .trim();

//        int besardanainvestasi = Integer.parseInt(besar_dana_investasi);
            clearEntities = validationMethod.valLamaInvestasi_pertumbuhanAset(lamaInvestasi);

            StringBuilder respBuilder = new StringBuilder();
            if (clearEntities.get("lama_investasi").equals("")) {
                bubble = dialogUtil.CreateBubble("pertumbuhanAset_ValidasiLamaInvestasi", 1, null);
                output.put(OUTPUT, bubble);
            } else {
                if (Integer.parseInt(lamaInvestasi.replace(" ", "").trim()) > 600) {
                    lamaInvestasi = "600";
                }
                log.debug("pertumbuhanAset_webView() extension request: {}", extensionRequest);
                String investment_amount = besar_dana_investasi;
                String tenor = lamaInvestasi;
                String investment_type = pendanaan;
                //======================================================================
                //======================================================================
                dataLeads1.getTransaction_Data()[0].getEC_ID();
                ArrayList<Transaction_Data> list_transaction_Data = new ArrayList<>();
                Transaction_Data transaction_Data = new Transaction_Data();
                list_transaction_Data.add(transaction_Data);
                transaction_Data.setName(dataLeads1.getTransaction_Data()[0].getName());
                transaction_Data.setAge(dataLeads1.getTransaction_Data()[0].getAge());
                transaction_Data.setEmail(dataLeads1.getTransaction_Data()[0].getEmail());
                transaction_Data.setPhone_Number(dataLeads1.getTransaction_Data()[0].getPhone_Number());
                transaction_Data.setLocation(dataLeads1.getTransaction_Data()[0].getLocation());
                transaction_Data.setIs_Married(dataLeads1.getTransaction_Data()[0].getIs_Married());
                transaction_Data.setIs_Already_Invest(dataLeads1.getTransaction_Data()[0].getIs_Already_Invest());
                transaction_Data.setFinancial_Info_Income_Range_ID(dataLeads1.getTransaction_Data()[0].getFinancial_Info_Income_Range_ID());
                transaction_Data.setFinancial_Info_Saved_Income_ID(dataLeads1.getTransaction_Data()[0].getFinancial_Info_Saved_Income_ID());
                transaction_Data.setRisk_Profile_ID(dataLeads1.getTransaction_Data()[0].getRisk_Profile_ID());
                transaction_Data.setLife_Goal_ID(1);
                transaction_Data.setFinancial_Goal("Pertumbuhan Aset");
                transaction_Data.setInvestment_Type(Integer.parseInt(investment_type));
                transaction_Data.setTenor(Integer.parseInt(tenor.replace(" ", "").trim()));
                transaction_Data.setInvestment_Amount(Long.parseLong(investment_amount));
                transaction_Data.setTarget_Amount(dataLeads1.getTransaction_Data()[0].getTarget_Amount());
                transaction_Data.setInitial_Amount(0);
                transaction_Data.setEC_ID(dataLeads1.getTransaction_Data()[0].getEC_ID());
                transaction_Data.setChildren_Age(dataLeads1.getTransaction_Data()[0].getChildren_Age());
                transaction_Data.setProduct_ID(dataLeads1.getTransaction_Data()[0].getProduct_ID());
                transaction_Data.setDatetime_Start_Process(dataLeads1.getTransaction_Data()[0].getDatetime_Start_Process());
                transaction_Data.setDatetime_End_Process(dataLeads1.getTransaction_Data()[0].getDatetime_Start_Process());
                transaction_Data.setTime_Horizon(dataLeads1.getTransaction_Data()[0].getTime_Horizon());
                transaction_Data.setSource_of_Income(dataLeads1.getTransaction_Data()[0].getSource_of_Income());
                transaction_Data.setInvestment_Knowledge(dataLeads1.getTransaction_Data()[0].getInvestment_Knowledge());
                transaction_Data.setIncome_Usage(dataLeads1.getTransaction_Data()[0].getIncome_Usage());
                transaction_Data.setPotential_Loss(dataLeads1.getTransaction_Data()[0].getPotential_Loss());
                transaction_Data.setPlatform_Name("OCBC NISP Website");
                transaction_Data.setRating(dataLeads1.getTransaction_Data()[0].getRating());
                transaction_Data.setRating_Comment(dataLeads1.getTransaction_Data()[0].getRating_Comment());
                transaction_Data.setStatus_Nasabah(dataLeads1.getTransaction_Data()[0].getStatus_Nasabah());
                Transaction_Data[] arrTransaction_data = new Transaction_Data[1];
                arrTransaction_data = list_transaction_Data.toArray(arrTransaction_data);
                DataLeads dataLeads = new DataLeads();
                dataLeads.Channel_ID = "1";
                dataLeads.Ext_Reff_ID = contactId;//dataLeads.setExt_Reff_ID("ASDASDS907098");
                dataLeads.Transaction_Data = arrTransaction_data;
                List<String> listData = new ArrayList<>();
                listData.add("" + new Gson().toJson(dataLeads, DataLeads.class
                ) + "");
                contact.setAdditionalField(listData);
                contact = svcDolphinService.updateCustomer(userToken, contact);

                bubble = dialogUtil.CreateBubble("pertumbuhanAset_ValidasiLamaInvestasi", 2, null);
                respBuilder.append(bubble);
                output.put(OUTPUT, respBuilder.toString());
            }
            extensionResult.setValue(output);

        }
        extensionResult.setEntities(clearEntities);
        extensionResult.setAgent(false);
        extensionResult.setRepeat(false);
        extensionResult.setSuccess(true);
        extensionResult.setNext(true);
        return extensionResult;
    }

    /**
     * Digunakan untuk mengembalikan response bot yang sudah di set didalam
     * dialog
     *
     * @param extensionRequest digunakan untuk memanggil data-data dari response
     * user yang sudah diolah oleh bot
     * @return respBuilder String
     */
    private String pertumbuhanAset_outputWebView(ExtensionRequest extensionRequest) {
        String lamaInvestasi = dialogUtil.getEasyMapValueByName(extensionRequest, "lama_investasi");
        String besar_dana_investasi = dialogUtil.getEasyMapValueByName(extensionRequest, "besar_dana_invesatasi");
        String pendanaan = dialogUtil.getEasyMapValueByName(extensionRequest, "pendanaan");
        //======================================================================
        //======================================================================
        userToken = svcDolphinService.getUserToken(userToken);
        String contactId = extensionRequest.getIntent().getTicket().getContactId();
        Contact contact = svcDolphinService.getCustomer(userToken, contactId);
        String b = contact.getAdditionalField().get(0);
        DataLeads dataLeads1 = new Gson().fromJson(b, DataLeads.class
        );
        int RiskProfileId = dataLeads1.getTransaction_Data()[0].getRisk_Profile_ID();
        String name = dataLeads1.getTransaction_Data()[0].getName();
        //======================================================================
        //======================================================================

        lamaInvestasi = lamaInvestasi.toLowerCase()
                .replace("tahun", "")
                .replace("thun", "")
                .replace("thn", "")
                .replace(",", "")
                .replace(".", "")
                .replace("-", "")
                .trim();
        String kalimat = lamaInvestasi;
        String[] kata = kalimat.split("");
        if (kata.length > 2) {
            lamaInvestasi = kata[0] + kata[1];
        } else {
            lamaInvestasi = lamaInvestasi;
        }
//        clearEntities = validationMethod.valLamaInvestasi_pertumbuhanAset(lamaInvestasi);
        if (Integer.parseInt(lamaInvestasi) <= 50) {

        } else {
            lamaInvestasi = "50";
        }

        log.debug("pertumbuhanAset_webView() extension request: {}", extensionRequest);
        StringBuilder respBuilder = new StringBuilder();
        ButtonTemplate button = new ButtonTemplate();
        button.setTitle(appProperties.getOcbc_carousel_title_growth());
        button.setSubTitle(appProperties.getOcbc_carousel_subtitle_growth());
        button.setPictureLink(appProperties.getOcbc_carousel_gambar_growth());
        button.setPicturePath(appProperties.getOcbc_carousel_gambar_growth());
        List<EasyMap> actions = new ArrayList<>();
        EasyMap bookAction = new EasyMap();
        bookAction.setName(appProperties.getOcbc_carousel_nameButton_growth());
        bookAction.setValue(appProperties.OCBC_WEBVIEW_BASE_URL + "/growth/" + contactId + "/"
                + besar_dana_investasi + "/"
                + pendanaan + "/"
                + lamaInvestasi + "/"
                + name + "/"
                + RiskProfileId + "?view=widget");
        actions.add(bookAction);
        button.setButtonValues(actions);
        ButtonBuilder buttonBuilder = new ButtonBuilder(button);
        String btnBuilder = buttonBuilder.build();
        bubble = dialogUtil.CreateBubble("pertumbuhanAset_webView", 1, null);
        respBuilder.append(bubble).append(CONSTANT_SPLIT_SYNTAX).append(btnBuilder);

        return respBuilder.toString();
    }

    /**
     * key output digunkan untuk menampilkan response dari bot sesuai dengan
     * jawaban yang telah di set didalam file dialog
     *
     * @param extensionRequest digunakan untuk memanggil data-data dari response
     * user yang sudah diolah oleh bot
     * @return extensionResult mengirimkan json dengan format class
     * extensionResult
     */
    public ExtensionResult pertumbuhanAset_webView(ExtensionRequest extensionRequest) {
        String strOutput = pertumbuhanAset_outputWebView(extensionRequest);
        Map<String, String> output = new HashMap<>();
        output.put(OUTPUT, strOutput);
        ExtensionResult extensionResult = new ExtensionResult();
        extensionResult.setAgent(false);
        extensionResult.setRepeat(false);
        extensionResult.setSuccess(true);
        extensionResult.setNext(true);
        extensionResult.setValue(output);
        return extensionResult;
    }

    /**
     * key output digunkan untuk menampilkan response dari bot sesuai dengan
     * jawaban yang telah di set didalam file dialog
     *
     * @param extensionRequest digunakan untuk memanggil data-data dari response
     * user yang sudah diolah oleh bot
     * @return extensionResult mengirimkan json dengan format class
     * extensionResult
     */
    public ExtensionResult pertumbuhanAset_validasiWebViewConfirmation(ExtensionRequest extensionRequest) {
        log.debug("pertumbuhanAset_validasiWebViewConfirmation() extension request: {}", new Gson().toJson(extensionRequest, ExtensionRequest.class
        ));
        ExtensionResult extensionResult = new ExtensionResult();
        Map<String, String> clearEntities = new HashMap<>();
        String web_view = dialogUtil.getEasyMapValueByName(extensionRequest, "web_view");
        String[] data = validationMethod.split_webView(web_view);
        extensionResult.setAgent(false);
        extensionResult.setRepeat(false);
        extensionResult.setSuccess(true);
        extensionResult.setNext(true);
        Map<String, String> output = new HashMap<>();
        int lengthdata = data.length;
        if (lengthdata > 1) {
            clearEntities.put("validasi_webview", "validasiWebview");
            clearEntities.put("besar_dana_invesatasi", data[0]);
            clearEntities.put("lama_investasi", data[1]);
            extensionResult.setEntities(clearEntities);
            String bubbleSummarywebview = pertumbuhanAset_SummaryWebView(extensionRequest);
            output.put(OUTPUT, bubbleSummarywebview);
            extensionResult.setValue(output);
        } else {
            clearEntities.put("web_view", "");
            clearEntities.put("validasi_webview", "");
            String strOutput = pertumbuhanAset_outputWebView(extensionRequest);
            output.put(OUTPUT, strOutput);
            extensionResult.setValue(output);
            extensionResult.setEntities(clearEntities);
        }
        return extensionResult;
    }

    /**
     * Digunakan untuk mengembalikan response bot yang sudah di set didalam
     * dialog
     *
     * @param extensionRequest digunakan untuk memanggil data-data dari response
     * user yang sudah diolah oleh bot
     * @return extensionResult mengirimkan json dengan format class
     * extensionResult
     */
    private String pertumbuhanAset_SummaryWebView(ExtensionRequest extensionRequest) {
        log.debug("pertumbuhanAset_validasiWebView() extension request: {}", new Gson().toJson(extensionRequest, ExtensionRequest.class
        ));
        Map<String, String> output = new HashMap<>();
        String web_view = dialogUtil.getEasyMapValueByName(extensionRequest, "web_view");
        String pendanaan = dialogUtil.getEasyMapValueByName(extensionRequest, "pendanaan");
        String[] data = validationMethod.split_webView(web_view);
        StringBuilder respBuilder = new StringBuilder();
        respBuilder.append("Life Goal : Tumbuhkan Uang \n"
                + "Mode : " + (pendanaan.equals("0") ? "Monthly" : "Lumpsum") + " \n"
                + "Dana Investasi : IDR " + validationMethod.replaceSparatorRp(data[0]) + " \n"
                + "Jangka Waktu : " + data[1] + " Tahun \n"
                + "Estimasi Pertumbuhan Hingga : IDR " + validationMethod.replaceSparatorRp(data[2]) + " \n"
                + "(est. laba " + data[4] + " /tahun)");
        //======================================================================
        //======================================================================
        String investment_amount = data[0];
        String tenor = data[1];
        String investment_type = pendanaan;
        //======================================================================
        //======================================================================
        userToken = svcDolphinService.getUserToken(userToken);
        String contactId = extensionRequest.getIntent().getTicket().getContactId();
        Contact contact = svcDolphinService.getCustomer(userToken, contactId);
        String b = contact.getAdditionalField().get(0);
        DataLeads dataLeads1 = new Gson().fromJson(b, DataLeads.class
        );
        dataLeads1.getTransaction_Data()[0].getEC_ID();
        ArrayList<Transaction_Data> list_transaction_Data = new ArrayList<>();
        Transaction_Data transaction_Data = new Transaction_Data();
        list_transaction_Data.add(transaction_Data);
        transaction_Data.setName(dataLeads1.getTransaction_Data()[0].getName());
        transaction_Data.setAge(dataLeads1.getTransaction_Data()[0].getAge());
        transaction_Data.setEmail(dataLeads1.getTransaction_Data()[0].getEmail());
        transaction_Data.setPhone_Number(dataLeads1.getTransaction_Data()[0].getPhone_Number());
        transaction_Data.setLocation(dataLeads1.getTransaction_Data()[0].getLocation());
        transaction_Data.setIs_Married(dataLeads1.getTransaction_Data()[0].getIs_Married());
        transaction_Data.setIs_Already_Invest(dataLeads1.getTransaction_Data()[0].getIs_Already_Invest());
        transaction_Data.setFinancial_Info_Income_Range_ID(dataLeads1.getTransaction_Data()[0].getFinancial_Info_Income_Range_ID());
        transaction_Data.setFinancial_Info_Saved_Income_ID(dataLeads1.getTransaction_Data()[0].getFinancial_Info_Saved_Income_ID());
        transaction_Data.setRisk_Profile_ID(dataLeads1.getTransaction_Data()[0].getRisk_Profile_ID());
        transaction_Data.setLife_Goal_ID(1);
        transaction_Data.setFinancial_Goal("Pertumbuhan Aset");
        transaction_Data.setInvestment_Type(Integer.parseInt(investment_type));
        transaction_Data.setTenor(Integer.parseInt(tenor.replace(" ", "").trim()));
        transaction_Data.setInvestment_Amount(Long.parseLong(investment_amount));
        transaction_Data.setTarget_Amount(dataLeads1.getTransaction_Data()[0].getTarget_Amount());
        transaction_Data.setInitial_Amount(0);
        transaction_Data.setEC_ID(dataLeads1.getTransaction_Data()[0].getEC_ID());
        transaction_Data.setChildren_Age(dataLeads1.getTransaction_Data()[0].getChildren_Age());
        transaction_Data.setProduct_ID(dataLeads1.getTransaction_Data()[0].getProduct_ID());
        transaction_Data.setDatetime_Start_Process(dataLeads1.getTransaction_Data()[0].getDatetime_Start_Process());
        transaction_Data.setDatetime_End_Process(dataLeads1.getTransaction_Data()[0].getDatetime_Start_Process());
        transaction_Data.setTime_Horizon(dataLeads1.getTransaction_Data()[0].getTime_Horizon());
        transaction_Data.setSource_of_Income(dataLeads1.getTransaction_Data()[0].getSource_of_Income());
        transaction_Data.setInvestment_Knowledge(dataLeads1.getTransaction_Data()[0].getInvestment_Knowledge());
        transaction_Data.setIncome_Usage(dataLeads1.getTransaction_Data()[0].getIncome_Usage());
        transaction_Data.setPotential_Loss(dataLeads1.getTransaction_Data()[0].getPotential_Loss());
        transaction_Data.setPlatform_Name("OCBC NISP Website");
        transaction_Data.setRating(dataLeads1.getTransaction_Data()[0].getRating());
        transaction_Data.setRating_Comment(dataLeads1.getTransaction_Data()[0].getRating_Comment());
        transaction_Data.setStatus_Nasabah(dataLeads1.getTransaction_Data()[0].getStatus_Nasabah());
        Transaction_Data[] arrTransaction_data = new Transaction_Data[1];
        arrTransaction_data = list_transaction_Data.toArray(arrTransaction_data);
        DataLeads dataLeads = new DataLeads();
        dataLeads.Channel_ID = "1";
        dataLeads.Ext_Reff_ID = contactId;//dataLeads.setExt_Reff_ID("ASDASDS907098");
        dataLeads.Transaction_Data = arrTransaction_data;
        List<String> listData = new ArrayList<>();
        listData.add("" + new Gson().toJson(dataLeads, DataLeads.class
        ) + "");
        contact.setAdditionalField(listData);
        contact = svcDolphinService.updateCustomer(userToken, contact);
        //====================================================================== 
        //======================================================================
        Map<String, String> map = new HashMap<>();
        StringBuilder respBuilderJika = new StringBuilder();
        if (dataLeads1.getTransaction_Data()[0].getRisk_Profile_ID() == 0) {
            respBuilderJika.append(dialogUtil.CreateBubble("pertumbuhanAset_validasiWebView", 1, null));
            QuickReplyBuilder quickReplyBuilder = new QuickReplyBuilder.Builder(respBuilderJika.toString()).addAll(map).build();
            respBuilder.append(CONSTANT_SPLIT_SYNTAX).append(quickReplyBuilder.string()).toString();
        } else {
            respBuilderJika.append(dialogUtil.CreateBubble("transisiListProduct_final", 1, null));
            QuickReplyBuilder quickReplyBuilder = new QuickReplyBuilder.Builder(respBuilderJika.toString()).addAll(map).build();
            respBuilder.append(CONSTANT_SPLIT_SYNTAX).append(quickReplyBuilder.string()).toString();
        }
        log.debug(respBuilder.toString());
        return respBuilder.toString();
    }

    /**
     * Digunakan untuk mengembalikan response bot yang sudah di set didalam
     * dialog
     *
     * @param extensionRequest digunakan untuk memanggil data-data dari response
     * user yang sudah diolah oleh bot
     * @return extensionResult mengirimkan json dengan format class
     * extensionResult
     */
    public ExtensionResult validasi_beforefinal(ExtensionRequest extensionRequest) {
        log.debug("validasi_beforefinal() extension request: {}", new Gson().toJson(extensionRequest, ExtensionRequest.class
        ));
        ExtensionResult extensionResult = new ExtensionResult();
        extensionResult.setAgent(false);
        extensionResult.setRepeat(false);
        extensionResult.setSuccess(true);
        extensionResult.setNext(true);
        Map<String, String> output = new HashMap<>();
        Map<String, String> clearEntities = new HashMap<>();
        StringBuilder respBuilder = new StringBuilder();
        String pertanyaan_beforefinal = dialogUtil.getEasyMapValueByName(extensionRequest, "pertanyaan_beforefinal").toLowerCase();
        if (pertanyaan_beforefinal.equalsIgnoreCase("Risk Profile")) {
            clearEntities.put("before_final", "Done");
        } else {
            clearEntities.put("pertanyaan_beforefinal", "");
            bubble = dialogUtil.CreateBubble("pertumbuhanAset_ValidasiLamaInvestasi", 2, null);
            respBuilder.append(bubble);
            output.put(OUTPUT, respBuilder.toString());
            extensionResult.setValue(output);
        }
        extensionResult.setEntities(clearEntities);
        return extensionResult;
    }

}
