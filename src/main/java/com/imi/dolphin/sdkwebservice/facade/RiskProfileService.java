/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imi.dolphin.sdkwebservice.facade;

import com.imi.dolphin.sdkwebservice.builder.ImageBuilder;
import com.imi.dolphin.sdkwebservice.model.ButtonTemplate;
import com.imi.dolphin.sdkwebservice.model.Contact;
import com.imi.dolphin.sdkwebservice.model.DataLeads;
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

import com.google.gson.Gson;
import com.imi.dolphin.sdkwebservice.builder.ButtonBuilder;
import com.imi.dolphin.sdkwebservice.builder.CarouselBuilder;
import com.imi.dolphin.sdkwebservice.builder.QuickReplyBuilder;
import com.imi.dolphin.sdkwebservice.model.CalculatorRequestBody;
import com.imi.dolphin.sdkwebservice.model.CalculatorResponseBody;
import com.imi.dolphin.sdkwebservice.model.EasyMap;
import com.imi.dolphin.sdkwebservice.service.ConvertToParam;

/**
 *
 * @author cokkyturnip
 */
@Service
public class RiskProfileService {

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

    private String bubble = "";
    private String bubble2 = "";

    public ExtensionResult riskProfile_entitasStatusInvestasi(ExtensionRequest extensionRequest) {
        log.debug("riskProfile_entitasStatusInvestasi() extension request: {}", extensionRequest);
        Map<String, String> output = new HashMap<>();
        bubble = dialogUtil.CreateBubble("riskProfile_entitasStatusInvestasi", 1, null) + CONSTANT_SPLIT_SYNTAX
                + dialogUtil.CreateBubble("riskProfile_entitasStatusInvestasi", 2, null);
        output.put(OUTPUT, bubble);
        ExtensionResult extensionResult = new ExtensionResult();
        extensionResult.setAgent(false);
        extensionResult.setRepeat(false);
        extensionResult.setSuccess(true);
        extensionResult.setNext(true);
        extensionResult.setValue(output);
        return extensionResult;
    }

    public ExtensionResult riskProfile_validasiStatusInvestasi(ExtensionRequest extensionRequest) {
        log.debug("riskProfile_validasiStatusInvestasi() extension request: {}", new Gson().toJson(extensionRequest, ExtensionRequest.class));
        ExtensionResult extensionResult = new ExtensionResult();
        Map<String, String> output = new HashMap<>();
        Map<String, String> clearEntities = new HashMap<>();
        String statusInvestasi = dialogUtil.getEasyMapValueByName(extensionRequest, "status_investasi");
        clearEntities = validationMethod.valStatusInvestasi(statusInvestasi);
        extensionResult.setEntities(clearEntities);
        extensionResult.setAgent(false);
        extensionResult.setRepeat(false);
        extensionResult.setSuccess(true);
        extensionResult.setNext(true);
        Map<String, String> map = new HashMap<>();
        if (clearEntities.get("status_investasi").equals("")) {
            bubble = dialogUtil.CreateBubble("riskProfile_validasiStatusInvestasi", 1, null) + CONSTANT_SPLIT_SYNTAX
                    + dialogUtil.CreateBubble("riskProfile_validasiStatusInvestasi", 2, null);
            output.put(OUTPUT, bubble);
            extensionResult.setValue(output);
        } else if (clearEntities.get("status_investasi").equals("0")) {
            setData(extensionRequest);
            bubble = dialogUtil.CreateBubble("riskProfile_validasiStatusInvestasi", 3, null) + CONSTANT_SPLIT_SYNTAX
                    + dialogUtil.CreateBubble("riskProfile_validasiStatusInvestasi", 4, null);
            output.put(OUTPUT, bubble);
            extensionResult.setValue(output);
        } else {
            output.put(OUTPUT, "");
            extensionResult.setValue(output);
        }
        return extensionResult;
    }

    private void setData(ExtensionRequest extensionRequest) {
        log.debug("setDataMulaiBerinvestasi() extension request: {}", new Gson().toJson(extensionRequest, ExtensionRequest.class));

        String status_investasi = dialogUtil.getEasyMapValueByName(extensionRequest, "status_investasi");
        String profile_resiko = dialogUtil.getEasyMapValueByName(extensionRequest, "profile_resiko");

        if ("Sudah".equalsIgnoreCase(status_investasi) || "1".equalsIgnoreCase(status_investasi)) {
            status_investasi = 1 + "";
        } else {
            status_investasi = 0 + "";
        }

        ConvertToParam convertToParam = new ConvertToParam();
        int convertProfileResiko = convertToParam.convertMulaiBerinvestasiRiskProfile(profile_resiko);

        //======================================================================
        //======================================================================
        String contactId = extensionRequest.getIntent().getTicket().getContactId();
        userToken = svcDolphinService.getUserToken(userToken);
        Contact contact = svcDolphinService.getCustomer(userToken, contactId);
        String b = contact.getAdditionalField().get(0);
        DataLeads dataLeads1 = new Gson().fromJson(b, DataLeads.class);
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
        transaction_Data.setIs_Already_Invest(Integer.parseInt(status_investasi));
        transaction_Data.setFinancial_Info_Income_Range_ID(dataLeads1.getTransaction_Data()[0].getFinancial_Info_Income_Range_ID());
        transaction_Data.setFinancial_Info_Saved_Income_ID(dataLeads1.getTransaction_Data()[0].getFinancial_Info_Saved_Income_ID());
        transaction_Data.setRisk_Profile_ID(convertProfileResiko);
        transaction_Data.setLife_Goal_ID(dataLeads1.getTransaction_Data()[0].getLife_Goal_ID());
        transaction_Data.setFinancial_Goal(dataLeads1.getTransaction_Data()[0].getFinancial_Goal());
        transaction_Data.setInvestment_Type(dataLeads1.getTransaction_Data()[0].getInvestment_Type());
        transaction_Data.setTenor(dataLeads1.getTransaction_Data()[0].getTenor());
        transaction_Data.setInvestment_Amount(dataLeads1.getTransaction_Data()[0].getInvestment_Amount());
        transaction_Data.setTarget_Amount(dataLeads1.getTransaction_Data()[0].getTarget_Amount());
        transaction_Data.setInitial_Amount(dataLeads1.getTransaction_Data()[0].getInitial_Amount());
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
        transaction_Data.setRating(0);
        transaction_Data.setRating_Comment("Default");
        transaction_Data.setStatus_Nasabah("Default");
//
        Transaction_Data[] arrTransaction_data = new Transaction_Data[1];
        arrTransaction_data = list_transaction_Data.toArray(arrTransaction_data);

        DataLeads dataLeads = new DataLeads();
        dataLeads.Channel_ID = "1";
        dataLeads.Ext_Reff_ID = contactId;//dataLeads.setExt_Reff_ID("ASDASDS907098");
        dataLeads.Transaction_Data = arrTransaction_data;

        List<String> listData = new ArrayList<>();
        listData.add("" + new Gson().toJson(dataLeads, DataLeads.class) + "");
        contact.setAdditionalField(listData);

        contact = svcDolphinService.updateCustomer(userToken, contact);
        //====================================================================== 
        //======================================================================
//        Map<String, String> entities = new HashMap<>();
//        Map<String, String> output = new HashMap<>();
//        output.put(OUTPUT, "Berdasarkan profil {first_name} ");
//        ExtensionResult extensionResult = new ExtensionResult();
//        extensionResult.setAgent(false);
//        extensionResult.setRepeat(false);
//        extensionResult.setSuccess(true);
//        extensionResult.setNext(true);
//
//        entities.put("set_data", "setData");
//        extensionResult.setEntities(entities);
//        extensionResult.setValue(output);
//M
//        return extensionResult;
    }

    public ExtensionResult riskProfile_validasiProfileResiko(ExtensionRequest extensionRequest) {
        log.debug("riskProfile_validasiProfileResiko() extension request: {}", new Gson().toJson(extensionRequest, ExtensionRequest.class));
        ExtensionResult extensionResult = new ExtensionResult();
        Map<String, String> output = new HashMap<>();
        Map<String, String> clearEntities = new HashMap<>();
        String profile_resiko = dialogUtil.getEasyMapValueByName(extensionRequest, "profile_resiko");
        //        ValidationMethod validationMethod = new ValidationMethod();
        clearEntities = validationMethod.valRiskProfile(profile_resiko);
        StringBuilder respBuilder = new StringBuilder();
        if (!clearEntities.get("profile_resiko").equals("")) {
            if (clearEntities.get("validasi_profile_resiko").equalsIgnoreCase("hitung profil risiko kembali")) {
                clearEntities.put("status_investasi", "0");
                clearEntities.put("validasi_status_investasi", "validasiStatusInvestasi");
                clearEntities.put("profile_resiko", "0");
                bubble = dialogUtil.CreateBubble("riskProfile_validasiStatusInvestasi", 3, null) + CONSTANT_SPLIT_SYNTAX
                        + dialogUtil.CreateBubble("riskProfile_validasiStatusInvestasi", 4, null);
                output.put(OUTPUT, bubble);
            } else {
                setData(extensionRequest);
                //        entities.put("set_data", "setData");
                bubble = dialogUtil.CreateBubble("riskProfile_validasiProfileResiko", 1, null);
                clearEntities.put("sumber_dana", "sumberdana");
                clearEntities.put("validasi_sumber_dana", "validasisumberdana");
                clearEntities.put("pengetahuan", "pengetahuan");
                clearEntities.put("validasi_pengetahuan", "validasipengetahuan");
                clearEntities.put("pembagian_hasil", "pembagianhasil");
                clearEntities.put("validasi_pembagian_hasil", "validasipembagianhasil");
                clearEntities.put("skenario_keuntungan", "skenariokeuntungan");
                clearEntities.put("validasi_skenario_keuntungan", "validasiskenariokeuntungan");
                clearEntities.put("konfirmasi_riskprofile", "bychatbot");
                extensionResult.setEntities(clearEntities);

                String Path = "";
                String riskprofile = "";
                String depan = "";
                int risk_profile_id = 0;
                if ("Conservative".equalsIgnoreCase(profile_resiko)) {
                    risk_profile_id = 1;
                    Path = appProperties.getOCBC_PICTURES_INFOGRAFIK4();
                } else if ("Balance".equalsIgnoreCase(profile_resiko)) {
                    risk_profile_id = 2;
                    Path = appProperties.getOCBC_PICTURES_INFOGRAFIK5();
                } else if ("Growth".equalsIgnoreCase(profile_resiko)) {
                    risk_profile_id = 3;
                    Path = appProperties.getOCBC_PICTURES_INFOGRAFIK6();
                } else if ("Aggressive".equalsIgnoreCase(profile_resiko)) {
                    risk_profile_id = 4;
                    Path = appProperties.getOCBC_PICTURES_INFOGRAFIK7();
                }

                ButtonTemplate button = new ButtonTemplate();
                button.setTitle(ParamSdk.SAMPLE_TITLE);
                button.setSubTitle(ParamSdk.SAMPLE_SUBTITLE);
                button.setPictureLink(Path);
                button.setPicturePath(Path);
                ImageBuilder buttonBuilder = new ImageBuilder(button);
                String btnBuilder = buttonBuilder.build();
                String bubble2 = dialogUtil.CreateBubble("riskProfile_validasiProfileResiko", 2, null);

                respBuilder.append(bubble).append(CONSTANT_SPLIT_SYNTAX).append(btnBuilder)
                        .append(CONSTANT_SPLIT_SYNTAX).append(bubble2);
                output.put(OUTPUT, respBuilder.toString());
            }

        } else {
            output.put(OUTPUT, "");
        }
        extensionResult.setValue(output);

        extensionResult.setEntities(clearEntities);
        extensionResult.setAgent(false);
        extensionResult.setRepeat(false);
        extensionResult.setSuccess(true);
        extensionResult.setNext(true);

        return extensionResult;
    }

    /**
     * Digunakan untuk menampilkan pertanyaan terhadap user tentang sumber dana
     * yang dimiliki oleh user
     *
     * @param extensionRequest digunakan untuk memanggil data-data dari response
     * user yang sudah diolah oleh bot
     * @return Json sesuai dengan format dari class ExtensionResult
     */
    public ExtensionResult riskProfile_entitasSumberDana(ExtensionRequest extensionRequest) {
        log.debug("riskProfile_entitasSumberDana() extension request: {}", extensionRequest);
        Map<String, String> output = new HashMap<>();

        bubble = dialogUtil.CreateBubble("riskProfile_entitasSumberDana", 1, null);
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
     * Digunakan untuk melakukan validasi terhadap sumber dana yang di inputkan
     * oleh user
     *
     * @param extensionRequest digunakan untuk memanggil data-data dari response
     * user yang sudah diolah oleh bot
     * @return Json sesuai dengan format dari class ExtensionResult
     */
    public ExtensionResult riskProfile_validasiSumberDana(ExtensionRequest extensionRequest) {
        log.debug("riskProfile_validasiSumberDana() extension request: {}", new Gson().toJson(extensionRequest, ExtensionRequest.class));
        ExtensionResult extensionResult = new ExtensionResult();
        Map<String, String> output = new HashMap<>();
        Map<String, String> clearEntities = new HashMap<>();
        String sumberDana = dialogUtil.getEasyMapValueByName(extensionRequest, "sumber_dana");
        String validasi_profile_resiko = dialogUtil.getEasyMapValueByName(extensionRequest, "validasi_profile_resiko");
        clearEntities = validationMethod.valSumberDana(sumberDana);
        extensionResult.setEntities(clearEntities);
        if (validasi_profile_resiko.equalsIgnoreCase("hitung profil risiko kembali")) {
            setData(extensionRequest);

        }
        extensionResult.setAgent(false);
        extensionResult.setRepeat(false);
        extensionResult.setSuccess(true);
        extensionResult.setNext(true);
        if (clearEntities.get("sumber_dana").equals("")) {
            bubble = dialogUtil.CreateBubble("riskProfile_validasiSumberDana", 1, null) + CONSTANT_SPLIT_SYNTAX
                    + dialogUtil.CreateBubble("riskProfile_validasiSumberDana", 2, null);
            output.put(OUTPUT, bubble);
        } else {
            bubble = dialogUtil.CreateBubble("riskProfile_validasiSumberDana", 3, null) + CONSTANT_SPLIT_SYNTAX
                    + dialogUtil.CreateBubble("riskProfile_validasiSumberDana", 4, null);
            output.put(OUTPUT, bubble);
        }
        extensionResult.setValue(output);
        return extensionResult;
    }

    /**
     * Digunakan untuk menampilkan pertanyaan terhadap user tentang seberapa
     * besar pengetahuan yang dimiliki user terhadap investasi
     *
     * @param extensionRequest digunakan untuk memanggil data-data dari response
     * user yang sudah diolah oleh bot
     * @return Json sesuai dengan format dari class ExtensionResult
     */
    public ExtensionResult riskProfile_entitasPengetahuan(ExtensionRequest extensionRequest) {
        log.debug("riskProfile_entitasPengetahuan() extension request: {}", extensionRequest);
        Map<String, String> output = new HashMap<>();
        bubble = dialogUtil.CreateBubble("riskProfile_entitasPengetahuan", 1, null);
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
     * digunakan untuk melakukan validasi terhadap pengetahuan yang diinputkan
     * oleh user
     *
     * @param extensionRequest digunakan untuk memanggil data-data dari response
     * user yang sudah diolah oleh bot
     * @return Json sesuai dengan format dari class ExtensionResult
     */
    public ExtensionResult riskProfile_validasiPengetahuan(ExtensionRequest extensionRequest) {
        log.debug("riskProfile_validasiPengetahuan() extension request: {}", new Gson().toJson(extensionRequest, ExtensionRequest.class));
        ExtensionResult extensionResult = new ExtensionResult();
        Map<String, String> output = new HashMap<>();
        Map<String, String> clearEntities = new HashMap<>();
        String sumberDana = dialogUtil.getEasyMapValueByName(extensionRequest, "pengetahuan");
        clearEntities = validationMethod.valPengetahuan(sumberDana);
        extensionResult.setEntities(clearEntities);
        extensionResult.setAgent(false);
        extensionResult.setRepeat(false);
        extensionResult.setSuccess(true);
        extensionResult.setNext(true);
        if (clearEntities.get("pengetahuan").equals("")) {
            bubble = dialogUtil.CreateBubble("riskProfile_validasiPengetahuan", 1, null) + CONSTANT_SPLIT_SYNTAX
                    + dialogUtil.CreateBubble("riskProfile_validasiPengetahuan", 2, null);
            output.put(OUTPUT, bubble);
        } else {
            bubble = dialogUtil.CreateBubble("riskProfile_validasiPengetahuan", 3, null);
//            output.put(OUTPUT, bubble);
            String[] risk_profile_pic = {appProperties.getOcbc_carousel_investratio1(), appProperties.getOcbc_carousel_investratio2(), appProperties.getOcbc_carousel_investratio3(), appProperties.getOcbc_carousel_investratio4(), appProperties.getOcbc_carousel_investratio5()};
            String[] risk_profile_title = {"Ambil semua", "Mayoritas Ambil", "50:50 Lah...", "Ambil dikit aja", "Puter aja semua!"};
            String[] risk_profile_payload = {"Pilih A", "Pilih B", "Pilih C", "Pilih D", "Pilih E"};
            String[] risk_profile_subtitle = {"Karena banyak keperluan", "Sisanya puterin lagi", "Setengah diambil, setengah puterin lagi", "Mayoritas puter lagi demi cuan!", "Biar cepet jadi kaya!"};
            String[] risk_profile_button = {"Pilih", "Pilih", "Pilih", "Pilih", "Pilih"};
            List<String> buttonList = new ArrayList<>();
            ButtonTemplate button;
            ButtonBuilder buttonBuilder;
            StringBuilder respBuilder = new StringBuilder();
            for (int i = 0; i < 5; i++) {
                button = new ButtonTemplate();
                button.setPictureLink(risk_profile_pic[i]);
                button.setPicturePath(risk_profile_pic[i]);
                button.setTitle(risk_profile_title[i] + "");
                button.setSubTitle(risk_profile_subtitle[i] + "");
                List<EasyMap> actions = new ArrayList<>();
                EasyMap bookAction = new EasyMap();
                bookAction.setName(risk_profile_button[i] + "");
                bookAction.setValue(risk_profile_payload[i] + "");
                actions.add(bookAction);
                button.setButtonValues(actions);
                buttonBuilder = new ButtonBuilder(button);
                buttonList.add(buttonBuilder.build());
            }
//            StringBuilder respBuilder = new StringBuilder();
//            bubble = dialogUtil.CreateBubble("riskProfile_validasiPembagianHasil", 1, null);
            respBuilder.append(bubble);
            CarouselBuilder carouselBuilder = new CarouselBuilder(buttonList);
            output.put(OUTPUT, (respBuilder.append(CONSTANT_SPLIT_SYNTAX).append(carouselBuilder.build())).toString());

        }
        extensionResult.setValue(output);
        return extensionResult;
    }

    /**
     * digunakan untuk menampilkan pesan terhadap user tentang pembagian hasil
     * yang ingin diberikan oleh user
     *
     * @param extensionRequest digunakan untuk memanggil data-data dari response
     * user yang sudah diolah oleh bot
     * @return Json sesuai dengan format dari class ExtensionResult
     */
    public ExtensionResult riskProfile_entitasPembagianHasil(ExtensionRequest extensionRequest) {
        log.debug("riskProfile_entitasPembagianHasil() extension request: {}", extensionRequest);
//        ExtensionResult extensionResult = new ExtensionResult();
//        extensionResult.setAgent(false);
//        extensionResult.setRepeat(false);
//        extensionResult.setSuccess(true);csd
//        extensionResult.setNext(true);
//        Map<String, String> output = new HashMap<>();
//        bubble = dialogUtil.CreateBubble("riskProfile_entitasPembagianHasil", 1, null);
//        output.put(OUTPUT, bubble);
//        extensionResult.setValue(output);
//        return extensionResult;   
        log.debug("getCarousel() extension request: {}", extensionRequest);
        Map<String, String> output = new HashMap<>();
        String[] risk_profile_pic = {appProperties.getOcbc_carousel_investratio1(), appProperties.getOcbc_carousel_investratio2(), appProperties.getOcbc_carousel_investratio3(), appProperties.getOcbc_carousel_investratio4(), appProperties.getOcbc_carousel_investratio5()};
        String[] risk_profile_title = {"Ambil semua", "Mayoritas Ambil", "50:50 Lah...", "Ambil dikit aja", "Puter aja semua!"};
        String[] risk_profile_payload = {"Pilih A", "Pilih B", "Pilih C", "Pilih D", "Pilih E"};
        String[] risk_profile_subtitle = {"Karena banyak keperluan", "Sisanya puterin lagi", "Setengah diambil, setengah puterin lagi", "Mayoritas puter lagi demi cuan!", "Biar cepet jadi kaya!"};
        String[] risk_profile_button = {"Pilih", "Pilih", "Pilih", "Pilih", "Pilih"};
        List<String> buttonList = new ArrayList<>();
        ButtonTemplate button;
        ButtonBuilder buttonBuilder;
        for (int i = 0; i < 5; i++) {
            button = new ButtonTemplate();
            button.setPictureLink(risk_profile_pic[i]);
            button.setPicturePath(risk_profile_pic[i]);
            button.setTitle(risk_profile_title[i] + "");
            button.setSubTitle(risk_profile_subtitle[i] + "");
            List<EasyMap> actions = new ArrayList<>();
            EasyMap bookAction = new EasyMap();
            bookAction.setName(risk_profile_button[i] + "");
            bookAction.setValue(risk_profile_payload[i] + "");
            actions.add(bookAction);
            button.setButtonValues(actions);
            buttonBuilder = new ButtonBuilder(button);
            buttonList.add(buttonBuilder.build());
        }
        StringBuilder respBuilder = new StringBuilder();
        bubble = dialogUtil.CreateBubble("riskProfile_entitasPembagianHasil", 1, null);
        respBuilder.append(bubble);
        CarouselBuilder carouselBuilder = new CarouselBuilder(buttonList);
        output.put(OUTPUT, (respBuilder.append(CONSTANT_SPLIT_SYNTAX).append(carouselBuilder.build())).toString());

        ExtensionResult extensionResult = new ExtensionResult();
        extensionResult.setAgent(false);
        extensionResult.setRepeat(false);
        extensionResult.setSuccess(true);
        extensionResult.setNext(true);
        extensionResult.setValue(output);
        return extensionResult;
    }

    /**
     * digunakan untuk melakukan validasi terhadap pembagian hasil yang
     * diinputkan oleh user
     *
     * @param extensionRequest digunakan untuk memanggil data-data dari response
     * user yang sudah diolah oleh bot
     * @return Json sesuai dengan format dari class ExtensionResult
     */
    public ExtensionResult riskProfile_validasiPembagianHasil(ExtensionRequest extensionRequest) {
        log.debug("riskProfile_validasiPembagianHasil() extension request: {}", new Gson().toJson(extensionRequest, ExtensionRequest.class));
        ExtensionResult extensionResult = new ExtensionResult();
        Map<String, String> output = new HashMap<>();
        Map<String, String> clearEntities = new HashMap<>();
        String pembagianHasil = dialogUtil.getEasyMapValueByName(extensionRequest, "pembagian_hasil");
        clearEntities = validationMethod.validasiPembagianHasil(pembagianHasil);
        extensionResult.setEntities(clearEntities);
        extensionResult.setAgent(false);
        extensionResult.setRepeat(false);
        extensionResult.setSuccess(true);
        extensionResult.setNext(true);
        StringBuilder respBuilder = new StringBuilder();
        if (clearEntities.get("pembagian_hasil").equals("")) {
//            bubble = dialogUtil.CreateBubble("riskProfile_validasiPembagianHasil", 1, null);
//            output.put(OUTPUT, bubble);

            String[] risk_profile_pic = {appProperties.getOcbc_carousel_investratio1(), appProperties.getOcbc_carousel_investratio2(), appProperties.getOcbc_carousel_investratio3(), appProperties.getOcbc_carousel_investratio4(), appProperties.getOcbc_carousel_investratio5()};
            String[] risk_profile_title = {"Ambil semua", "Mayoritas Ambil", "50:50 Lah...", "Ambil dikit aja", "Puter aja semua!"};
            String[] risk_profile_payload = {"Pilih A", "Pilih B", "Pilih C", "Pilih D", "Pilih E"};
            String[] risk_profile_subtitle = {"Karena banyak keperluan", "Sisanya puterin lagi", "Setengah diambil, setengah puterin lagi", "Mayoritas puter lagi demi cuan!", "Biar cepet jadi kaya!"};
            String[] risk_profile_button = {"Pilih", "Pilih", "Pilih", "Pilih", "Pilih"};
            List<String> buttonList = new ArrayList<>();
            ButtonTemplate button;
            ButtonBuilder buttonBuilder;
            for (int i = 0; i < 5; i++) {
                button = new ButtonTemplate();
                button.setPictureLink(risk_profile_pic[i]);
                button.setPicturePath(risk_profile_pic[i]);
                button.setTitle(risk_profile_title[i] + "");
                button.setSubTitle(risk_profile_subtitle[i] + "");
                List<EasyMap> actions = new ArrayList<>();
                EasyMap bookAction = new EasyMap();
                bookAction.setName(risk_profile_button[i] + "");
                bookAction.setValue(risk_profile_payload[i] + "");
                actions.add(bookAction);
                button.setButtonValues(actions);
                buttonBuilder = new ButtonBuilder(button);
                buttonList.add(buttonBuilder.build());
            }
//            StringBuilder respBuilder = new StringBuilder();
            bubble = dialogUtil.CreateBubble("riskProfile_validasiPembagianHasil", 1, null);
            respBuilder.append(bubble);
            CarouselBuilder carouselBuilder = new CarouselBuilder(buttonList);
            output.put(OUTPUT, (respBuilder.append(CONSTANT_SPLIT_SYNTAX).append(carouselBuilder.build())).toString());

        } else {
            bubble = dialogUtil.CreateBubble("riskProfile_validasiPembagianHasil", 2, null);
            String bubble2 = dialogUtil.CreateBubble("riskProfile_validasiPembagianHasil", 3, null);
            ButtonTemplate button = new ButtonTemplate();
            button.setTitle(appProperties.getOcbc_carousel_title_riskprofile());
            button.setSubTitle(appProperties.getOcbc_carousel_subtitle_riskprofile());
            button.setPictureLink(appProperties.getOcbc_carousel_gambar_riskprofile());
            button.setPicturePath(appProperties.getOcbc_carousel_gambar_riskprofile());
            ImageBuilder buttonBuilder = new ImageBuilder(button);
            String btnBuilder = buttonBuilder.build();
            respBuilder.append(bubble)
                    .append(CONSTANT_SPLIT_SYNTAX)
                    .append(btnBuilder)
                    .append(CONSTANT_SPLIT_SYNTAX)
                    .append(bubble2);
            output.put(OUTPUT, respBuilder.toString());
        }
        extensionResult.setValue(output);
        return extensionResult;
    }

    /**
     * Digunakan untuk melakukan pembagian skenario keuntungan yang akan
     * diberikan oleh user
     *
     * @param extensionRequest digunakan untuk memanggil data-data dari response
     * user yang sudah diolah oleh bot
     * @return Json sesuai dengan format dari class ExtensionResult
     */
    public ExtensionResult riskProfile_entitasSkenarioKeuntungan(ExtensionRequest extensionRequest) {
        log.debug("riskProfile_entitasSkenarioKeuntungan() extension request: {}", extensionRequest);
        ExtensionResult extensionResult = new ExtensionResult();
        extensionResult.setAgent(false);
        extensionResult.setRepeat(false);
        extensionResult.setSuccess(true);
        extensionResult.setNext(true);
        Map<String, String> output = new HashMap<>();
        StringBuilder respBuilder = new StringBuilder();
        ButtonTemplate button = new ButtonTemplate();
        button.setTitle(appProperties.getOcbc_carousel_title_riskprofile());
        button.setSubTitle(appProperties.getOcbc_carousel_subtitle_riskprofile());
        button.setPictureLink(appProperties.getOcbc_carousel_gambar_riskprofile());
        button.setPicturePath(appProperties.getOcbc_carousel_gambar_riskprofile());
        ImageBuilder buttonBuilder = new ImageBuilder(button);
        String btnBuilder = buttonBuilder.build();
        bubble = dialogUtil.CreateBubble("riskProfile_entitasSkenarioKeuntungan", 1, null);
        String bubble2 = dialogUtil.CreateBubble("riskProfile_entitasSkenarioKeuntungan", 2, null);
        respBuilder.append(bubble)
                .append(CONSTANT_SPLIT_SYNTAX)
                .append(btnBuilder)
                .append(CONSTANT_SPLIT_SYNTAX)
                .append(bubble2);
        output.put(OUTPUT, respBuilder.toString());
        extensionResult.setValue(output);
        return extensionResult;
    }

    /**
     * digunakan untuk melakukan validasi terhadap skenario keuntungan yang
     * diberikan oleh user
     *
     * @param extensionRequest digunakan untuk memanggil data-data dari response
     * user yang sudah diolah oleh bot
     * @return Json sesuai dengan format dari class ExtensionResult
     */
    public ExtensionResult riskProfile_validasiSkenarioKeuntungan(ExtensionRequest extensionRequest) {
        log.debug("riskProfile_validasiSkenarioKeuntungan() extension request: {}", new Gson().toJson(extensionRequest, ExtensionRequest.class));
        ExtensionResult extensionResult = new ExtensionResult();
        Map<String, String> output = new HashMap<>();
        Map<String, String> clearEntities = new HashMap<>();
        String SkenarioKeuntungan = dialogUtil.getEasyMapValueByName(extensionRequest, "skenario_keuntungan");
        clearEntities = validationMethod.validasiSkenarioKeuntungan(SkenarioKeuntungan);
        extensionResult.setEntities(clearEntities);
        extensionResult.setAgent(false);
        extensionResult.setRepeat(false);
        extensionResult.setSuccess(true);
        extensionResult.setNext(true);
        StringBuilder respBuilder = new StringBuilder();
        if (clearEntities.get("skenario_keuntungan").equals("")) {
            ButtonTemplate button = new ButtonTemplate();
            button.setTitle(appProperties.getOcbc_carousel_title_riskprofile());
            button.setSubTitle(appProperties.getOcbc_carousel_subtitle_riskprofile());
            button.setPictureLink(appProperties.getOcbc_carousel_gambar_riskprofile());
            button.setPicturePath(appProperties.getOcbc_carousel_gambar_riskprofile());
            ImageBuilder buttonBuilder = new ImageBuilder(button);
            String btnBuilder = buttonBuilder.build();
            bubble = dialogUtil.CreateBubble("riskProfile_validasiSkenarioKeuntungan", 1, null);
            String bubble2 = dialogUtil.CreateBubble("riskProfile_validasiSkenarioKeuntungan", 2, null);
            respBuilder.append(bubble)
                    .append(CONSTANT_SPLIT_SYNTAX)
                    .append(btnBuilder)
                    .append(CONSTANT_SPLIT_SYNTAX)
                    .append(bubble2);
            output.put(OUTPUT, respBuilder.toString());
        } else {
            clearEntities.put("validasi_skenario_keuntungan", "validasiSkenarioKeuntungan");
//            extensionResult.setEntities(clearEntities);
            bubble = generateRiskProfile(extensionRequest);
            respBuilder.append(bubble);
            output.put(OUTPUT, respBuilder.toString());

        }
        extensionResult.setValue(output);
        return extensionResult;
    }

    /**
     * Digunakan untuk melakukan hit terhadap API riskProfile
     *
     * @param extensionRequest digunakan untuk memanggil data-data dari response
     * user yang sudah diolah oleh bot
     * @return Json sesuai dengan format dari class ExtensionResult
     */
    private String generateRiskProfile(ExtensionRequest extensionRequest) {
        String status_investasi = dialogUtil.getEasyMapValueByName(extensionRequest, "status_investasi");
        String sumber_dana = dialogUtil.getEasyMapValueByName(extensionRequest, "sumber_dana");
        String pengetahuan = dialogUtil.getEasyMapValueByName(extensionRequest, "pengetahuan");
        String pembagian_hasil = dialogUtil.getEasyMapValueByName(extensionRequest, "pembagian_hasil");
        String skenario_keuntungan = dialogUtil.getEasyMapValueByName(extensionRequest, "skenario_keuntungan");

        //===========================================================================================
        log.debug("generateRiskProfile() pesan risk profile |=======|||debug|||=======| skenario_keuntungan : {}", skenario_keuntungan);
        //===========================================================================================
        String hasil = "";

        String konvert_skenario = skenario_keuntungan
                .toLowerCase()
                .replace("pilih", "")
                .replace("opsi", "")
                .replace("satu", "1")
                .replace("dua", "2")
                .replace("tiga", "3")
                .replace("empat", "4")
                .replace("lima", "5")
                .trim();

        if ("1".equalsIgnoreCase(konvert_skenario) || "a".equalsIgnoreCase(konvert_skenario)) {
            konvert_skenario = "5.a";
        } else if ("2".equalsIgnoreCase(konvert_skenario) || "b".equalsIgnoreCase(konvert_skenario)) {
            konvert_skenario = "5.b";
        } else if ("3".equalsIgnoreCase(konvert_skenario) || "c".equalsIgnoreCase(konvert_skenario)) {
            konvert_skenario = "5.c";
        } else if ("4".equalsIgnoreCase(konvert_skenario) || "d".equalsIgnoreCase(konvert_skenario)) {
            konvert_skenario = "5.d";
        } else if ("5".equalsIgnoreCase(konvert_skenario) || "e".equalsIgnoreCase(konvert_skenario)) {
            konvert_skenario = "5.e";
        }
        StringBuilder respBuilder = new StringBuilder();
        String contactId = extensionRequest.getIntent().getTicket().getContactId();
        userToken = svcDolphinService.getUserToken(userToken);
        Contact contact = svcDolphinService.getCustomer(userToken, contactId);
        String b = contact.getAdditionalField().get(0);
        DataLeads dataLeads1 = new Gson().fromJson(b, DataLeads.class);
        dataLeads1.getTransaction_Data()[0].getEC_ID();
        int tenorrrrrrr = dataLeads1.getTransaction_Data()[0].getTenor();
        String konvert_time_horizon = "";

        //===========================================================================================
        log.debug("generateRiskProfile() Tenorrrrrrrr |=======|||debug|||=======| Gson : {}", tenorrrrrrr);
        //===========================================================================================
        if (tenorrrrrrr <= 1) {
            konvert_time_horizon = "1.a";
        } else if (tenorrrrrrr >= 2 && tenorrrrrrr <= 5) {
            konvert_time_horizon = "1.b";
        } else if (tenorrrrrrr >= 6 && tenorrrrrrr <= 10) {
            konvert_time_horizon = "1.c";
        } else {
            konvert_time_horizon = "1.d";
        }
        CalculatorRequestBody riskProfileRequest = new CalculatorRequestBody();
        riskProfileRequest.Channel_ID = 1;
        riskProfileRequest.Ext_Reff_ID = contactId;
        riskProfileRequest.Risk_Profile_Channel = "WPP";
        riskProfileRequest.Time_Horizon = konvert_time_horizon;
        riskProfileRequest.Source_of_Income = sumber_dana;
        riskProfileRequest.Income_Usage = pembagian_hasil;
        riskProfileRequest.Investment_Knowledge = pengetahuan;
        riskProfileRequest.Potential_Loss = konvert_skenario;
        riskProfileRequest.Financial_Condition = null;
        riskProfileRequest.Investment_Instrument = null;
        riskProfileRequest.Time_Horizon_Structured_Product = "";
        riskProfileRequest.Return_Characteristic = "";
        riskProfileRequest.Avg_Placement_Fund = "";
        riskProfileRequest.Placement_Frequency = "";
        CalculatorResponseBody riskProfileResponse = wppService.POST_Calculator(
                appProperties.OCBC_WPP_PATH_RISK_PROFILE,
                riskProfileRequest);
        hasil = riskProfileResponse.Risk_Profile_Type;

        //===========================================================================================
        log.debug("generateRiskProfile() pesan risk profile |=======|||debug|||=======| Gson : {}", new Gson().toJson(riskProfileRequest, CalculatorRequestBody.class));
        //===========================================================================================

        log.debug("riskProfile_final() hasil dari api adalah : {}", hasil);
        String Path = "";
        String riskprofile = "";
        String depan = "";
        int risk_profile_id = 0;
        if ("Conservative".equalsIgnoreCase(hasil)) {
            depan = "Okee! Udah semua nih untuk risk profile.";
            riskprofile = "Ternyata kamu itu tipe CONSERVATIVE ";
            risk_profile_id = 1;
            Path = appProperties.getOCBC_PICTURES_INFOGRAFIK4();
        } else if ("Balance".equalsIgnoreCase(hasil)) {
            depan = "Okee! Udah semua nih untuk risk profile.";
            riskprofile = "Ternyata kamu itu tipe BALANCE";
            risk_profile_id = 2;
            Path = appProperties.getOCBC_PICTURES_INFOGRAFIK5();
        } else if ("Growth".equalsIgnoreCase(hasil)) {
            depan = "Okee! Udah semua nih untuk risk profile.";
            riskprofile = "Ternyata kamu itu tipe GROWTH";
            risk_profile_id = 3;
            Path = appProperties.getOCBC_PICTURES_INFOGRAFIK6();
        } else if ("Aggressive".equalsIgnoreCase(hasil)) {
            depan = "Okee! Udah semua nih untuk risk profile.";
            riskprofile = "Ternyata kamu itu tipe AGGRESIVE";
            risk_profile_id = 4;
            Path = appProperties.getOCBC_PICTURES_INFOGRAFIK7();
        }
        switch (riskProfileResponse.RC) {
            case 0:
                //when RC = 0 (valid), set additional field
                //..
                //..
                SaveToAdditionalField additionalField = new SaveToAdditionalField();
                ArrayList<Transaction_Data> list_transaction_Data = new ArrayList<>();
                Transaction_Data transaction_Data = new Transaction_Data();
                transaction_Data.setName(dataLeads1.getTransaction_Data()[0].getName());
                transaction_Data.setAge(dataLeads1.getTransaction_Data()[0].getAge());
                transaction_Data.setEmail(dataLeads1.getTransaction_Data()[0].getEmail());
                transaction_Data.setPhone_Number(dataLeads1.getTransaction_Data()[0].getPhone_Number());
                transaction_Data.setLocation(dataLeads1.getTransaction_Data()[0].getLocation());
                transaction_Data.setIs_Married(dataLeads1.getTransaction_Data()[0].getIs_Married());
                transaction_Data.setIs_Already_Invest(dataLeads1.getTransaction_Data()[0].getIs_Already_Invest());
                transaction_Data.setFinancial_Info_Income_Range_ID(dataLeads1.getTransaction_Data()[0].getFinancial_Info_Income_Range_ID());
                transaction_Data.setFinancial_Info_Saved_Income_ID(dataLeads1.getTransaction_Data()[0].getFinancial_Info_Saved_Income_ID());
                transaction_Data.setRisk_Profile_ID(risk_profile_id);
                transaction_Data.setLife_Goal_ID(dataLeads1.getTransaction_Data()[0].getLife_Goal_ID());
                transaction_Data.setFinancial_Goal(dataLeads1.getTransaction_Data()[0].getFinancial_Goal());
                transaction_Data.setInvestment_Type(dataLeads1.getTransaction_Data()[0].getInvestment_Type());
                transaction_Data.setTenor(tenorrrrrrr);
                transaction_Data.setInvestment_Amount(dataLeads1.getTransaction_Data()[0].getInvestment_Amount());
                transaction_Data.setTarget_Amount(dataLeads1.getTransaction_Data()[0].getTarget_Amount());
                transaction_Data.setInitial_Amount(dataLeads1.getTransaction_Data()[0].getInitial_Amount());
                transaction_Data.setEC_ID(dataLeads1.getTransaction_Data()[0].getEC_ID());
                transaction_Data.setChildren_Age(dataLeads1.getTransaction_Data()[0].getChildren_Age());
                transaction_Data.setProduct_ID(dataLeads1.getTransaction_Data()[0].getProduct_ID());
                transaction_Data.setDatetime_Start_Process(dataLeads1.getTransaction_Data()[0].getDatetime_Start_Process());
                transaction_Data.setDatetime_End_Process(dataLeads1.getTransaction_Data()[0].getDatetime_Start_Process());
                transaction_Data.setTime_Horizon(konvert_time_horizon);
                transaction_Data.setSource_of_Income(sumber_dana);
                transaction_Data.setInvestment_Knowledge(pengetahuan);
                transaction_Data.setIncome_Usage(pembagian_hasil);
                transaction_Data.setPotential_Loss(konvert_skenario);
                transaction_Data.setPlatform_Name("OCBC NISP Website");
                list_transaction_Data.add(transaction_Data);
                Transaction_Data[] arrTransaction_data = new Transaction_Data[1];
                arrTransaction_data = list_transaction_Data.toArray(arrTransaction_data);
                DataLeads dataLeads = new DataLeads();
                dataLeads.setChannel_ID("1");
                dataLeads.setExt_Reff_ID("ASDASDS907098");
                dataLeads.setTransaction_Data(arrTransaction_data);
                List<String> listData = new ArrayList<>();
                listData.add("" + new Gson().toJson(dataLeads, DataLeads.class) + "");
                contact.setAdditionalField(listData);
                contact = svcDolphinService.updateCustomer(userToken, contact);

                ButtonTemplate button = new ButtonTemplate();
                button.setTitle(ParamSdk.SAMPLE_TITLE);
                button.setSubTitle(ParamSdk.SAMPLE_SUBTITLE);
                button.setPictureLink(Path);
                button.setPicturePath(Path);
                ImageBuilder buttonBuilder = new ImageBuilder(button);
                String btnBuilder = buttonBuilder.build();
                bubble = dialogUtil.CreateBubble("riskProfile_validasiSkenarioKeuntungan", 3, null);
                respBuilder.append(depan).append(CONSTANT_SPLIT_SYNTAX).append(riskprofile)
                        .append(CONSTANT_SPLIT_SYNTAX).append(btnBuilder)
                        .append(CONSTANT_SPLIT_SYNTAX).append(bubble);
                break;
            default:
                //do something when RC != 0 (invalid)
                //..
                //..
                bubble = dialogUtil.CreateBubble("riskProfile_final", 2, null);
                respBuilder.append(bubble);
        }
        return respBuilder.toString();

    }

    /**
     * Digunakan untuk menampilkan summary
     *
     * @param extensionRequest digunakan untuk memanggil data-data dari response
     * user yang sudah diolah oleh bot
     * @return Json sesuai dengan format dari class ExtensionResult
     */
    public ExtensionResult riskProfile_summary(ExtensionRequest extensionRequest) {
        log.debug("riskProfile_final() extension request: {}", new Gson().toJson(extensionRequest, ExtensionRequest.class));
        ExtensionResult extensionResult = new ExtensionResult();
        Map<String, String> output = new HashMap<>();
        Map<String, String> clearEntities = new HashMap<>();
        StringBuilder respBuilder = new StringBuilder();
        extensionResult.setAgent(false);
        extensionResult.setRepeat(false);
        extensionResult.setSuccess(true);
        extensionResult.setNext(true);
        bubble = generateRiskProfile(extensionRequest);
        respBuilder.append(bubble);
        Map<String, String> map = new HashMap<>();
        map.put("Lanjut", "lihat ringkasan saya");

        QuickReplyBuilder quickReplyBuilder = new QuickReplyBuilder.Builder("Klik lanjut untuk melihat ringkasan data kamu").addAll(map).build();
        output.put(OUTPUT, respBuilder.toString());
        extensionResult.setValue(output);
        clearEntities.put("konfirmasi_riskprofile", "bychatbot");
        extensionResult.setEntities(clearEntities);
        return extensionResult;
    }

    /**
     * digunakan untuk melakukan validasi terhadap response user
     *
     * @param extensionRequest digunakan untuk memanggil data-data dari response
     * user yang sudah diolah oleh bot
     * @return Json sesuai dengan format dari class ExtensionResult
     */
    public ExtensionResult validasi_beforefinal(ExtensionRequest extensionRequest) {
        log.debug("validasi_beforefinal() extension request: {}", new Gson().toJson(extensionRequest, ExtensionRequest.class));
        ExtensionResult extensionResult = new ExtensionResult();
        extensionResult.setAgent(false);
        extensionResult.setRepeat(false);
        extensionResult.setSuccess(true);
        extensionResult.setNext(true);
        Map<String, String> clearEntities = new HashMap<>();
        String konfirmasiRiskprofile = dialogUtil.getEasyMapValueByName(extensionRequest, "konfirmasi_riskprofile").toLowerCase();
        switch (konfirmasiRiskprofile) {
            case "bychatbot":
                String summaryriskprofile = dialogUtil.getEasyMapValueByName(extensionRequest, "riskprofile_summary").toLowerCase();
                if (summaryriskprofile.equals("lihat ringkasan saya")) {
                    clearEntities.put("before_final", "Done");
                } else {
                    clearEntities.put("konfirmasi_riskprofile", "");
                }
                break;
            case "lihat ringkasan saya":
                clearEntities.put("before_final", "Done");
                break;
            default:
                clearEntities.put("konfirmasi_riskprofile", "");
                break;
        }
        extensionResult.setEntities(clearEntities);
        return extensionResult;
    }
}
