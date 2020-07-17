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
import com.imi.dolphin.sdkwebservice.model.Country;
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
import com.imi.dolphin.sdkwebservice.service.ParamJSONService;
import com.imi.dolphin.sdkwebservice.service.ServiceImp;
import com.imi.dolphin.sdkwebservice.service.ValidationMethod;
import com.imi.dolphin.sdkwebservice.util.DialogUtil;
import com.imi.dolphin.sdkwebservice.util.OkHttpUtil;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
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
public class PendidikanService {

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
    ParamJSONService paramJSONServices;

    private String bubble = "";

    /**
     * key output digunkan untuk menampilkan response dari bot sesuai dengan
     * jawaban yang telah di set didalam file dialog
     *
     * @param extensionRequest digunakan untuk memanggil data-data dari response
     * user yang sudah diolah oleh bot
     * @return extensionResult json dengan format kelas ExtensionResult
     */
    public ExtensionResult pendidikanKuliahAnak_entitasUsiaAnak(ExtensionRequest extensionRequest) {
        log.debug("pendidikanKuliahAnak_entitasUsiaAnak() extension request: {}", extensionRequest);
        ExtensionResult extensionResult = new ExtensionResult();
        extensionResult.setAgent(false);
        extensionResult.setRepeat(false);
        extensionResult.setSuccess(true);
        extensionResult.setNext(true);
        Map<String, String> output = new HashMap<>();
        bubble = dialogUtil.CreateBubble("pendidikanKuliahAnak_entitasUsiaAnak", 1, null);
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
     * @return extensionResult json dengan format kelas ExtensionResult
     */
    public ExtensionResult pendidikanKuliahAnak_validasiUsiaAnak(ExtensionRequest extensionRequest) {
        log.debug("pendidikanKuliahAnak_validasiUsiaAnak() extension request: {}", new Gson().toJson(extensionRequest, ExtensionRequest.class));
        ExtensionResult extensionResult = new ExtensionResult();
        Map<String, String> output = new HashMap<>();
        Map<String, String> clearEntities = new HashMap<>();
        String usiaAnak = dialogUtil.getEasyMapValueByName(extensionRequest, "usia_anak");
        log.debug("pendidikanKuliahAnak_validasiUsiaAnak() usia anak: {}", usiaAnak);
        usiaAnak = usiaAnak.toLowerCase()
                .replace("tahun", "")
                .replace("thun", "")
                .replace("thn", "")
                .replace(",", "")
                .replace(".", "")
                .replace("-", "")
                .trim();
        String kalimat = usiaAnak;
        String[] kata = kalimat.split("");
        if (kata.length > 2) {
            usiaAnak = kata[0] + kata[1];
        } else {
            usiaAnak = usiaAnak;
        }
        clearEntities = validationMethod.valUsiaAnak(usiaAnak);
        extensionResult.setEntities(clearEntities);
        extensionResult.setAgent(false);
        extensionResult.setRepeat(false);
        extensionResult.setSuccess(true);
        extensionResult.setNext(true);
        StringBuilder respBuilder = new StringBuilder();
        if (clearEntities.get("usia_anak").equals("")) {
            bubble = dialogUtil.CreateBubble("pendidikanKuliahAnak_validasiUsiaAnak", 1, null);
        } else {
            Map<String, String> map = new LinkedHashMap<>();
            int counter = 1;
            List<Country> listCountry = paramJSONServices.getListCountryfromFileJson("country.json");
            for (Country country : listCountry) {
                map.put(country.text, country.value);
            }
            bubble = dialogUtil.CreateBubble("pendidikanKuliahAnak_validasiUsiaAnak", 2, null);
            QuickReplyBuilder quickReplyBuilder = new QuickReplyBuilder.Builder(bubble + "").addAll(map).build();
            bubble = quickReplyBuilder.string();
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
     * @return extensionResult json dengan format kelas ExtensionResult
     */
    public ExtensionResult pendidikanKuliahAnak_entitasNegaraKuliah(ExtensionRequest extensionRequest) {
        log.debug("pendidikanKuliahAnak_entitasNegaraKuliah() extension request: {}", extensionRequest);
        Map<String, String> output = new HashMap<>();
        Map<String, String> map = new HashMap<>();
        int counter = 1;
        List<Country> listCountry = paramJSONServices.getListCountryfromFileJson("country.json");
        for (Country country : listCountry) {
            map.put(country.text, country.value);
        }
        bubble = dialogUtil.CreateBubble("pendidikanKuliahAnak_entitasNegaraKuliah", 1, null);
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
     * @return extensionResult json dengan format kelas ExtensionResult
     */
    public ExtensionResult pendidikanKuliahAnak_validasiNegaraKuliah(ExtensionRequest extensionRequest) {
        log.debug("pendidikanKuliahAnak_validasiNegaraKuliah() extension request: {}", new Gson().toJson(extensionRequest, ExtensionRequest.class));
        ExtensionResult extensionResult = new ExtensionResult();
        Map<String, String> output = new HashMap<>();
        Map<String, String> clearEntities = new HashMap<>();
        String negaraKuliah = dialogUtil.getEasyMapValueByName(extensionRequest, "negara_kuliah");
        clearEntities = validationMethod.valNegaraKuliah(negaraKuliah);
        extensionResult.setEntities(clearEntities);
        extensionResult.setAgent(false);
        extensionResult.setRepeat(false);
        extensionResult.setSuccess(true);
        extensionResult.setNext(true);
        Map<String, String> map = new HashMap<>();
        StringBuilder respBuilder = new StringBuilder();
        if (clearEntities.get("negara_kuliah").equals("")) {
            int counter = 1;
            List<Country> listCountry = paramJSONServices.getListCountryfromFileJson("country.json");
            for (Country country : listCountry) {
                map.put(country.text, country.value);
            }
            bubble = dialogUtil.CreateBubble("pendidikanKuliahAnak_validasiNegaraKuliah", 1, null);
            QuickReplyBuilder quickReplyBuilder = new QuickReplyBuilder.Builder(bubble + "").addAll(map).build();
            output.put(OUTPUT, quickReplyBuilder.string());
        } else {
            Map<String, String> params = new HashMap<>();
            params.put("negara_kuliah", clearEntities.get("validasi_negara_kuliah"));
            bubble = dialogUtil.CreateBubble("pendidikanKuliahAnak_validasiNegaraKuliah", 2, params);
            output.put(OUTPUT, bubble);
        }
        extensionResult.setValue(output);
        return extensionResult;
    }

    /**
     * key output digunkan untuk menampilkan response dari bot sesuai dengan
     * jawaban yang telah di set didalam file dialog
     *
     * @param extensionRequest digunakan untuk memanggil data-data dari response
     * user yang sudah diolah oleh bot
     * @return extensionResult json dengan format kelas ExtensionResult
     */
    public ExtensionResult pendidikanKuliahAnak_entitasDanaSekarang(ExtensionRequest extensionRequest) {
        log.debug("pendidikanKuliahAnak_entitasDanaSekarang() extension request: {}", extensionRequest);
        String negaraKuliah = dialogUtil.getEasyMapValueByName(extensionRequest, "validasi_negara_kuliah");
        ExtensionResult extensionResult = new ExtensionResult();
        extensionResult.setAgent(false);
        extensionResult.setRepeat(false);
        extensionResult.setSuccess(true);
        extensionResult.setNext(true);
        Map<String, String> output = new HashMap<>();
        Map<String, String> params = new HashMap<>();
        params.put("negara_kuliah", negaraKuliah);
        bubble = dialogUtil.CreateBubble("pendidikanKuliahAnak_entitasDanaSekarang", 1, params);
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
     * @return extensionResult json dengan format kelas ExtensionResult
     */
//    public ExtensionResult pendidikanKuliahAnak_validasiDanaSekarang(ExtensionRequest extensionRequest) {
//
//        log.debug("pendidikanKuliahAnak_validasiDanaSekarang() extension request: {}", new Gson().toJson(extensionRequest, ExtensionRequest.class));
//        ExtensionResult extensionResult = new ExtensionResult();
//        Map<String, String> output = new HashMap<>();
//        Map<String, String> clearEntities = new HashMap<>();
//        String danaSekarang = dialogUtil.getEasyMapValueByName(extensionRequest, "dana_sekarang");
//        String negaraKuliah = dialogUtil.getEasyMapValueByName(extensionRequest, "negara_kuliah");
//        String usia_anak = dialogUtil.getEasyMapValueByName(extensionRequest, "usia_anak");
//
//        //======================================================================
//        //======================================================================
//        userToken = svcDolphinService.getUserToken(userToken);
//        String contactId = extensionRequest.getIntent().getTicket().getContactId();
//        Contact contact = svcDolphinService.getCustomer(userToken, contactId);
//        String b = contact.getAdditionalField().get(0);
//        DataLeads dataLeads1 = new Gson().fromJson(b, DataLeads.class);
//        dataLeads1.getTransaction_Data()[0].getName();
//
//        String name = dataLeads1.getTransaction_Data()[0].getName();
//        int riskProfileId = dataLeads1.getTransaction_Data()[0].getRisk_Profile_ID();
//        //======================================================================
//        //======================================================================
//
//        clearEntities = validationMethod.validasiDanaSekarang(danaSekarang);
//        extensionResult.setEntities(clearEntities);
//        extensionResult.setAgent(false);
//        extensionResult.setRepeat(false);
//        extensionResult.setSuccess(true);
//        extensionResult.setNext(true);
//        if (clearEntities.get("dana_sekarang").equals("")) {
//            Map<String, String> params = new HashMap<>();
//            params.put("negara_kuliah", negaraKuliah);
//            bubble = dialogUtil.CreateBubble("pendidikanKuliahAnak_validasiDanaSekarang", 1, params);
//        } else {
//            ButtonTemplate button = new ButtonTemplate();
//            button.setTitle(appProperties.getOcbc_carousel_title_pendidikan());
//            button.setSubTitle(appProperties.getOcbc_carousel_subtitle_pendidikan());
//            button.setPictureLink(appProperties.getOcbc_carousel_gambar_pendidikan());
//            button.setPicturePath(appProperties.getOcbc_carousel_gambar_pendidikan());
//            List<EasyMap> actions = new ArrayList<>();
//            EasyMap bookAction = new EasyMap();
//            bookAction.setName(appProperties.getOcbc_carousel_nameButton_pendidikan());
//
//            String tempResponseUser = danaSekarang.toLowerCase()
//                    .replace(" ", "")
//                    .replace(".", "")
//                    .replace(",", "")
//                    .replace("-", "")
//                    .replace("rp.", "")
//                    .replace("rp", "")
//                    .replace("jt", "000000")
//                    .replace("juta", "000000")
//                    .replace("m", "000000000")
//                    .replace("miliar", "000000000")
//                    .replace("t", "000000000000")
//                    .replace("triliun", "000000000000")
//                    .replace("ribu", "000").replace("rbu", "000").replace("rb", "000")
//                    .replace("rupiah", "")
//                    .trim();
//
////            /education/refID/1/Indonesia/10,020,000,013/Dewi
//            bookAction.setValue(appProperties.OCBC_WEBVIEW_BASE_URL + "/education/" + contactId + "/"
//                    //        bookAction.setValue(appProperties.OCBC_WEBVIEW_BASE_URL + "/education/contactID/"
//                    + usia_anak + "/"
//                    + negaraKuliah + "/"
//                    + tempResponseUser + "/"
//                    + name + "/"
//                    + riskProfileId + "?view=widget");
//            actions.add(bookAction);
//            button.setButtonValues(actions);
//            ButtonBuilder buttonBuilder = new ButtonBuilder(button);
//            String btnBuilder = buttonBuilder.build();
//            bubble = dialogUtil.CreateBubble("pendidikanKuliahAnak_validasiDanaSekarang", 2, null) + CONSTANT_SPLIT_SYNTAX + btnBuilder;
//        }
//        output.put(OUTPUT, bubble);
//
//        extensionResult.setValue(output);
//        return extensionResult;
//    }
    /**
     *
     * @param extensionRequest
     * @return
     */
    public ExtensionResult pendidikanKuliahAnak_validasiDanaSekarang(ExtensionRequest extensionRequest) {
        log.debug("pendidikanKuliahAnak_validasiDanaSekarang() extension request: {}", new Gson().toJson(extensionRequest, ExtensionRequest.class));
        ExtensionResult extensionResult = new ExtensionResult();
        Map<String, String> output = new HashMap<>();
        Map<String, String> clearEntities = new HashMap<>();
        String danaSekarang = dialogUtil.getEasyMapValueByName(extensionRequest, "dana_sekarang");
        String negaraKuliah = dialogUtil.getEasyMapValueByName(extensionRequest, "negara_kuliah");
        String usia_anak = dialogUtil.getEasyMapValueByName(extensionRequest, "usia_anak");

        //======================================================================
        //======================================================================
        userToken = svcDolphinService.getUserToken(userToken);
        String contactId = extensionRequest.getIntent().getTicket().getContactId();
        Contact contact = svcDolphinService.getCustomer(userToken, contactId);
        String b = contact.getAdditionalField().get(0);
        DataLeads dataLeads1 = new Gson().fromJson(b, DataLeads.class);
        dataLeads1.getTransaction_Data()[0].getName();

        String name = dataLeads1.getTransaction_Data()[0].getName();
        int riskProfileId = dataLeads1.getTransaction_Data()[0].getRisk_Profile_ID();
        //======================================================================
        //======================================================================

        clearEntities = validationMethod.validasiDanaSekarang(danaSekarang);
        extensionResult.setEntities(clearEntities);
        extensionResult.setAgent(false);
        extensionResult.setRepeat(false);
        extensionResult.setSuccess(true);
        extensionResult.setNext(true);
        if (clearEntities.get("dana_sekarang").equals("")) {
            Map<String, String> params = new HashMap<>();
            params.put("negara_kuliah", negaraKuliah);
            bubble = dialogUtil.CreateBubble("pendidikanKuliahAnak_validasiDanaSekarang", 1, params);
        } else {
            int tenor = 18 - Integer.parseInt(usia_anak);
            if (tenor <= 0) {
                tenor = 1;
            }
            tenor = tenor * 12;
            //======================================================================
            //======================================================================
            String Children_Age = usia_anak;
            String EC_ID = negaraKuliah;
            String Initial_Amount = clearEntities.get("dana_sekarang").replace(".00", "");
            String Target_Amount = "0"; // data[7] //untuk variabel dari API 

            //======================================================================
            //======================================================================
//            log.debug("pendidikanKuliahAnak_webView() ============== | Debug | ============== Data array 7: {}", data[7]);
            //======================================================================
            //======================================================================
            dataLeads1.getTransaction_Data()[0].getEC_ID();
            //======================================================================
            //======================================================================
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
            transaction_Data.setLife_Goal_ID(2);
            transaction_Data.setFinancial_Goal("Pendidikan Anak");
            transaction_Data.setInvestment_Type(dataLeads1.getTransaction_Data()[0].getInvestment_Type());
            transaction_Data.setTenor(tenor);
            transaction_Data.setInvestment_Amount(dataLeads1.getTransaction_Data()[0].getInvestment_Amount());

            transaction_Data.setTarget_Amount(dataLeads1.getTransaction_Data()[0].getTarget_Amount());
            transaction_Data.setInitial_Amount(Long.parseLong(Initial_Amount));
            transaction_Data.setEC_ID(Integer.parseInt(EC_ID));
            transaction_Data.setChildren_Age(Integer.parseInt(Children_Age));

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
            listData.add("" + new Gson().toJson(dataLeads, DataLeads.class) + "");
            contact.setAdditionalField(listData);
            contact = svcDolphinService.updateCustomer(userToken, contact);
            bubble = dialogUtil.CreateBubble("pendidikanKuliahAnak_validasiDanaSekarang", 2, null);
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
     * @return extensionResult json dengan format kelas ExtensionResult
     */
    private String pendidikanKuliahAnak_outputwebView(ExtensionRequest extensionRequest) {
        log.debug("pendidikanKuliahAnak_webView() extension request: {}", extensionRequest);
        String danaSekarang = dialogUtil.getEasyMapValueByName(extensionRequest, "dana_sekarang");
        String negaraKuliah = dialogUtil.getEasyMapValueByName(extensionRequest, "negara_kuliah");
        String usia_anak = dialogUtil.getEasyMapValueByName(extensionRequest, "usia_anak");

        //======================================================================
        //======================================================================
        userToken = svcDolphinService.getUserToken(userToken);
        String contactId = extensionRequest.getIntent().getTicket().getContactId();
        Contact contact = svcDolphinService.getCustomer(userToken, contactId);
        String b = contact.getAdditionalField().get(0);
        DataLeads dataLeads1 = new Gson().fromJson(b, DataLeads.class);
        dataLeads1.getTransaction_Data()[0].getName();

        String name = dataLeads1.getTransaction_Data()[0].getName();
        int riskProfileId = dataLeads1.getTransaction_Data()[0].getRisk_Profile_ID();
        //======================================================================
        //======================================================================

        ButtonTemplate button = new ButtonTemplate();
        button.setTitle(appProperties.getOcbc_carousel_title_pendidikan());
        button.setSubTitle(appProperties.getOcbc_carousel_subtitle_pendidikan());
        button.setPictureLink(appProperties.getOcbc_carousel_gambar_pendidikan());
        button.setPicturePath(appProperties.getOcbc_carousel_gambar_pendidikan());
        List<EasyMap> actions = new ArrayList<>();
        EasyMap bookAction = new EasyMap();
        bookAction.setName(appProperties.getOcbc_carousel_nameButton_pendidikan());

        String tempResponseUser = danaSekarang.toLowerCase()
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
        bookAction.setValue(appProperties.OCBC_WEBVIEW_BASE_URL + "/education/" + contactId + "/"
                + usia_anak + "/"
                + negaraKuliah + "/"
                + tempResponseUser + "/"
                + name + "/"
                + riskProfileId + "?view=widget");
        actions.add(bookAction);
        button.setButtonValues(actions);
        ButtonBuilder buttonBuilder = new ButtonBuilder(button);
        String btnBuilder = buttonBuilder.build();
        bubble = dialogUtil.CreateBubble("pendidikanKuliahAnak_webView", 1, null) + CONSTANT_SPLIT_SYNTAX + btnBuilder;
        return bubble;
    }

    /**
     * key output digunkan untuk menampilkan response dari bot sesuai dengan
     * jawaban yang telah di set didalam file dialog
     *
     * @param extensionRequest digunakan untuk memanggil data-data dari response
     * user yang sudah diolah oleh bot
     * @return extensionResult json dengan format kelas ExtensionResult
     */
    public ExtensionResult pendidikanKuliahAnak_webView(ExtensionRequest extensionRequest) {
        String strOutput = pendidikanKuliahAnak_outputwebView(extensionRequest);
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
     * @return extensionResult json dengan format kelas ExtensionResult
     */
    public ExtensionResult pendidikanKuliahAnak_validasiWebViewConfirmation(ExtensionRequest extensionRequest) {

        log.debug("pendidikanKuliahAnak_validasiWebViewConfirmation() extension request: {}", new Gson().toJson(extensionRequest, ExtensionRequest.class));
        ExtensionResult extensionResult = new ExtensionResult();
        Map<String, String> output = new HashMap<>();
        Map<String, String> clearEntities = new HashMap<>();
        String web_view = dialogUtil.getEasyMapValueByName(extensionRequest, "web_view");

        String[] data = validationMethod.split_webView(web_view);
        extensionResult.setAgent(false);
        extensionResult.setRepeat(false);
        extensionResult.setSuccess(true);
        extensionResult.setNext(true);

        int lengthdata = data.length;

        log.debug("pendidikanKuliahAnak_validasiWebViewConfirmation() data length: {}", lengthdata);

        log.debug(lengthdata);
        if (lengthdata > 1) {
            clearEntities.put("validasi_webview", "validasiWebview");
            extensionResult.setEntities(clearEntities);

            String bubbleSummarywebview = pendidikanKuliahAnak_validasiWebView(extensionRequest);
            output.put(OUTPUT, bubbleSummarywebview);
            extensionResult.setValue(output);

        } else {
            clearEntities.put("web_view", "");
            clearEntities.put("validasi_webview", "");

            String strOutput = pendidikanKuliahAnak_outputwebView(extensionRequest);
            output.put(OUTPUT, strOutput);

            extensionResult.setValue(output);
            extensionResult.setEntities(clearEntities);
        }

        return extensionResult;
    }

    /**
     * key output digunkan untuk menampilkan response dari bot sesuai dengan
     * jawaban yang telah di set didalam file dialog
     *
     * @param extensionRequest digunakan untuk memanggil data-data dari response
     * user yang sudah diolah oleh bot
     * @return extensionResult json dengan format kelas ExtensionResult
     */
    public String pendidikanKuliahAnak_validasiWebView(ExtensionRequest extensionRequest) {
        log.debug("pendidikanKuliahAnak_validasiWebView() extension request: {}", new Gson().toJson(extensionRequest, ExtensionRequest.class));
        ExtensionResult extensionResult = new ExtensionResult();
        Map<String, String> output = new HashMap<>();
        Map<String, String> clearEntities = new HashMap<>();
        String web_view = dialogUtil.getEasyMapValueByName(extensionRequest, "web_view");
        String[] data = validationMethod.split_webView(web_view);
        log.debug("pendidikanKuliahAnak_validasiWebView() data length: {}", data.length);
        extensionResult.setAgent(false);
        extensionResult.setRepeat(false);
        extensionResult.setSuccess(true);
        extensionResult.setNext(true);
        StringBuilder respBuilder = new StringBuilder();
        clearEntities.put("usia_anak", data[0]);
        clearEntities.put("negara_kuliah", data[1]);
        clearEntities.put("dana_sekarang", data[2]);

        String negara_tujuan = "";
        switch (Integer.parseInt(data[1])) {
            case 1:
                negara_tujuan = "Indonesia";
                break;
            case 2:
                negara_tujuan = "Singapura";
                break;
            case 3:
                negara_tujuan = "Britania Raya/ Inggris";
                break;
            case 4:
                negara_tujuan = "Amerika Serikat (Universitas Negeri)";
                break;
            case 5:
                negara_tujuan = "Amerika Serikat (Universitas Swasta)";
                break;
            case 6:
                negara_tujuan = "Australia";
                break;
            default:
                break;
        }

        clearEntities.put("validasi_webview", "validasiNama");
        extensionResult.setEntities(clearEntities);
        respBuilder.append("Life Goal : Pendidikan Kuliah Anak \n"
                + "Usia Anak : " + data[0] + " Tahun \n"
                + "Negara Tujuan : " + negara_tujuan + " \n"
                + "Dana Tersedia : IDR " + validationMethod.replaceSparatorRp(data[2]) + " \n"
                + "Dana bulanan investasi yang dibutuhkan hanya: IDR " + validationMethod.replaceSparatorRp(data[3]) + " \n"
                + "(est laba: " + data[5] + " /tahun)");

        //======================================================================
        //======================================================================
        int tenor = 18 - Integer.parseInt(data[0]);

        if (tenor <= 0) {
            tenor = 1;
        }
        //======================================================================
        //======================================================================
        String Children_Age = data[0];
        String EC_ID = data[1];
        String Initial_Amount = data[2];
        String Target_Amount = validationMethod.replaceSparator(data[7]); // data[7] //untuk variabel dari API 

        //======================================================================
        //======================================================================
        log.debug("pendidikanKuliahAnak_webView() ============== | Debug | ============== Data array 7: {}", data[7]);
        //======================================================================
        //======================================================================
        userToken = svcDolphinService.getUserToken(userToken);
        String contactId = extensionRequest.getIntent().getTicket().getContactId();
        Contact contact = svcDolphinService.getCustomer(userToken, contactId);
        String b = contact.getAdditionalField().get(0);
        DataLeads dataLeads1 = new Gson().fromJson(b, DataLeads.class);
        dataLeads1.getTransaction_Data()[0].getEC_ID();
        //======================================================================
        //======================================================================
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
        transaction_Data.setLife_Goal_ID(2);
        transaction_Data.setFinancial_Goal("Pendidikan Anak");
        transaction_Data.setInvestment_Type(dataLeads1.getTransaction_Data()[0].getInvestment_Type());
        transaction_Data.setTenor(tenor);
        transaction_Data.setInvestment_Amount(dataLeads1.getTransaction_Data()[0].getInvestment_Amount());

        transaction_Data.setTarget_Amount(Long.parseLong(Target_Amount));
        transaction_Data.setInitial_Amount(Long.parseLong(Initial_Amount));
        transaction_Data.setEC_ID(Integer.parseInt(EC_ID));
        transaction_Data.setChildren_Age(Integer.parseInt(Children_Age));

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
        listData.add("" + new Gson().toJson(dataLeads, DataLeads.class) + "");
        contact.setAdditionalField(listData);
        contact = svcDolphinService.updateCustomer(userToken, contact);
        //====================================================================== 
        //======================================================================

        Map<String, String> map = new HashMap<>();
        StringBuilder respBuilderJika = new StringBuilder();
        if (dataLeads1.getTransaction_Data()[0].getRisk_Profile_ID() == 0) {
            respBuilderJika.append(dialogUtil.CreateBubble("pendidikanKuliahAnak_validasiWebView", 1, null));
            QuickReplyBuilder quickReplyBuilder = new QuickReplyBuilder.Builder(respBuilderJika + "").addAll(map).build();
            respBuilder.append(CONSTANT_SPLIT_SYNTAX).append(quickReplyBuilder.string()).toString();
        } else {
            respBuilderJika.append(dialogUtil.CreateBubble("transisiListProduct_final", 1, null));
            QuickReplyBuilder quickReplyBuilder = new QuickReplyBuilder.Builder(respBuilderJika + "").addAll(map).build();
            respBuilder.append(CONSTANT_SPLIT_SYNTAX).append(quickReplyBuilder.string()).toString();
        }
        return respBuilder.toString();
    }

    /**
     * key output digunkan untuk menampilkan response dari bot sesuai dengan
     * jawaban yang telah di set didalam file dialog
     *
     * @param extensionRequest digunakan untuk memanggil data-data dari response
     * user yang sudah diolah oleh bot
     * @return extensionResult json dengan format kelas ExtensionResult
     */
    public ExtensionResult validasi_beforefinal(ExtensionRequest extensionRequest) {
        log.debug("validasi_beforefinal() extension request: {}", new Gson().toJson(extensionRequest, ExtensionRequest.class));
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
            bubble = dialogUtil.CreateBubble("pendidikanKuliahAnak_validasiDanaSekarang", 2, null);
            respBuilder.append(bubble);
            output.put(OUTPUT, respBuilder.toString());
            extensionResult.setValue(output);
        }
        extensionResult.setEntities(clearEntities);
        return extensionResult;
    }

}
