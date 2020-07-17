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
public class LainnyaService {

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
     * key output digunakan sebagai respon bot terhadap user (Json)
     *
     * @param extensionRequest digunakan untuk memanggil data-data dari response
     * user yang sudah diolah oleh bot
     * @return extensionResult json dengan format kelas ExtensionResult
     */
    public ExtensionResult lifeGoalLainnya_entitasJudulLifeGoal(ExtensionRequest extensionRequest) {
        log.debug("lifeGoalLainnya_entitasJudulLifeGoal() extension request: {}", extensionRequest);
        ExtensionResult extensionResult = new ExtensionResult();
        extensionResult.setAgent(false);
        extensionResult.setRepeat(false);
        extensionResult.setSuccess(true);
        extensionResult.setNext(true);
        Map<String, String> output = new HashMap<>();
        bubble = dialogUtil.CreateBubble("lifeGoalLainnya_entitasJudulLifeGoal", 1, null);
        output.put(OUTPUT, bubble);
        extensionResult.setValue(output);
        return extensionResult;
    }

    /**
     * Digunakan untuk memvalidasi value yang di inputkan oleh user
     *
     * @param extensionRequest digunakan untuk memanggil data-data dari response
     * user yang sudah diolah oleh bot
     * @return extensionResult json dengan format kelas ExtensionResult
     */
    public ExtensionResult lifeGoalLainnya_validasiJudulLifeGoal(ExtensionRequest extensionRequest) {
        log.debug("lifeGoalLainnya_validasiJudulLifeGoal(() extension request: {}", new Gson().toJson(extensionRequest, ExtensionRequest.class));
        ExtensionResult extensionResult = new ExtensionResult();
        Map<String, String> output = new HashMap<>();
        Map<String, String> clearEntities = new HashMap<>();
        String judulLifeGoal = dialogUtil.getEasyMapValueByName(extensionRequest, "judul_life_goal");

        clearEntities = validationMethod.validasiJudulLifeGoal(judulLifeGoal);
        extensionResult.setEntities(clearEntities);
        extensionResult.setAgent(false);
        extensionResult.setRepeat(false);
        extensionResult.setSuccess(true);
        extensionResult.setNext(true);
        StringBuilder respBuilder = new StringBuilder();
        if (clearEntities.get("judul_life_goal").equals("")) {
            output.put(OUTPUT, dialogUtil.CreateBubble("lifeGoalLainnya_validasiJudulLifeGoal", 1, null));
        } else {
            Map<String, String> params = new HashMap<>();
            params.put("judul_life_goal", judulLifeGoal);
            output.put(OUTPUT, dialogUtil.CreateBubble("lifeGoalLainnya_validasiJudulLifeGoal", 2, params));
        }
        extensionResult.setValue(output);
        return extensionResult;
    }

    /**
     * key output digunakan sebagai respon bot terhadap user (Json)
     *
     * @param extensionRequest digunakan untuk memanggil data-data dari response
     * user yang sudah diolah oleh bot
     * @return extensionResult json dengan format kelas ExtensionResult
     */
    public ExtensionResult lifeGoalLainnya_entitasKebutuhanDana(ExtensionRequest extensionRequest) {
        log.debug("lifeGoalLainnya_entitasKebutuhanDana() extension request: {}", extensionRequest);
        String judulLifeGoal = dialogUtil.getEasyMapValueByName(extensionRequest, "judul_life_goal");
        ExtensionResult extensionResult = new ExtensionResult();
        extensionResult.setAgent(false);
        extensionResult.setRepeat(false);
        extensionResult.setSuccess(true);
        extensionResult.setNext(true);
        Map<String, String> output = new HashMap<>();
        Map<String, String> params = new HashMap<>();
        params.put("judul_life_goal", judulLifeGoal);
        output.put(OUTPUT, dialogUtil.CreateBubble("lifeGoalLainnya_entitasKebutuhanDana", 1, null));
        extensionResult.setValue(output);
        return extensionResult;
    }

    /**
     * Digunakan untuk memvalidasi value yang di inputkan oleh user
     *
     * @param extensionRequest digunakan untuk memanggil data-data dari response
     * user yang sudah diolah oleh bot
     * @return extensionResult json dengan format kelas ExtensionResult
     */
    public ExtensionResult lifeGoalLainnya_validasiKebutuhanDana(ExtensionRequest extensionRequest) {
        log.debug("lifeGoalLainnya_validasiKebutuhanDana() extension request: {}", new Gson().toJson(extensionRequest, ExtensionRequest.class));
        String judulLifeGoal = dialogUtil.getEasyMapValueByName(extensionRequest, "judul_life_goal");
        ExtensionResult extensionResult = new ExtensionResult();
        Map<String, String> output = new HashMap<>();
        Map<String, String> clearEntities = new HashMap<>();
        String kebutuhan_dana = dialogUtil.getEasyMapValueByName(extensionRequest, "kebutuhan_dana");
        clearEntities = validationMethod.validasiKebutuhanDana(kebutuhan_dana);
        extensionResult.setEntities(clearEntities);
        extensionResult.setAgent(false);
        extensionResult.setRepeat(false);
        extensionResult.setSuccess(true);
        extensionResult.setNext(true);
        Map<String, String> params = new HashMap<>();
        params.put("judul_life_goal", judulLifeGoal);
        if (clearEntities.get("kebutuhan_dana").equals("")) {
            output.put(OUTPUT, dialogUtil.CreateBubble("lifeGoalLainnya_validasiKebutuhanDana", 1, null));
        } else {
            output.put(OUTPUT, dialogUtil.CreateBubble("lifeGoalLainnya_validasiKebutuhanDana", 2, null));
        }
        extensionResult.setValue(output);
        return extensionResult;
    }

    /**
     * key output digunakan sebagai respon bot terhadap user (Json)
     *
     * @param extensionRequest digunakan untuk memanggil data-data dari response
     * user yang sudah diolah oleh bot
     * @return extensionResult json dengan format kelas ExtensionResult
     */
    public ExtensionResult lifeGoalLainnya_entitasDanaSekarang(ExtensionRequest extensionRequest) {
        log.debug("lifeGoalLainnya_entitasDanaSekarang() extension request: {}", extensionRequest);
        String judulLifeGoal = dialogUtil.getEasyMapValueByName(extensionRequest, "judul_life_goal");
        ExtensionResult extensionResult = new ExtensionResult();
        extensionResult.setAgent(false);
        extensionResult.setRepeat(false);
        extensionResult.setSuccess(true);
        extensionResult.setNext(true);
        Map<String, String> output = new HashMap<>();
        Map<String, String> params = new HashMap<>();
        params.put("judul_life_goal", judulLifeGoal);
        output.put(OUTPUT, dialogUtil.CreateBubble("lifeGoalLainnya_entitasDanaSekarang", 1, params));
        extensionResult.setValue(output);
        return extensionResult;
    }

    /**
     * Digunakan untuk memvalidasi value yang di inputkan oleh user
     *
     * @param extensionRequest digunakan untuk memanggil data-data dari response
     * user yang sudah diolah oleh bot
     * @return extensionResult json dengan format kelas ExtensionResult
     */
    public ExtensionResult lifeGoalLainnya_validasiDanaSekarang(ExtensionRequest extensionRequest) {
        log.debug("lifeGoalLainnya_validasiDanaSekarang() extension request: {}", new Gson().toJson(extensionRequest, ExtensionRequest.class));
        String judulLifeGoal = dialogUtil.getEasyMapValueByName(extensionRequest, "judul_life_goal");
        ExtensionResult extensionResult = new ExtensionResult();
        Map<String, String> output = new HashMap<>();
        Map<String, String> clearEntities = new HashMap<>();
        String danaSekarang = dialogUtil.getEasyMapValueByName(extensionRequest, "dana_sekarang");

        clearEntities = validationMethod.validasiDanaSekarang(danaSekarang);
        extensionResult.setEntities(clearEntities);
        extensionResult.setAgent(false);
        extensionResult.setRepeat(false);
        extensionResult.setSuccess(true);
        extensionResult.setNext(true);
        StringBuilder respBuilder = new StringBuilder();

        String[] splitjudullifegoal = judulLifeGoal.split(" ");
        String judul_lifegoal = splitjudullifegoal[0];

        if (clearEntities.get("dana_sekarang").equals("")) {
            output.put(OUTPUT, dialogUtil.CreateBubble("lifeGoalLainnya_validasiDanaSekarang", 1, null));
        } else if (judul_lifegoal.equalsIgnoreCase("Liburan")) {
            output.put(OUTPUT, dialogUtil.CreateBubble("lifeGoalLainnya_validasiDanaSekarang", 3, null));
        } else {
            output.put(OUTPUT, dialogUtil.CreateBubble("lifeGoalLainnya_validasiDanaSekarang", 2, null));
        }
        extensionResult.setValue(output);
        return extensionResult;
    }

    /**
     * key output digunakan sebagai respon bot terhadap user (Json)
     *
     * @param extensionRequest digunakan untuk memanggil data-data dari response
     * user yang sudah diolah oleh bot
     * @return ExtensionResult json dengan format kelas ExtensionResult
     */
    public ExtensionResult lifeGoalLainnya_entitasLamaBerinvestasi(ExtensionRequest extensionRequest) {
        log.debug("lifeGoalLainnya_entitasLamaBerinvestasi() extension request: {}", extensionRequest);
        String judulLifeGoal = dialogUtil.getEasyMapValueByName(extensionRequest, "judul_life_goal");

        ExtensionResult extensionResult = new ExtensionResult();
        extensionResult.setAgent(false);
        extensionResult.setRepeat(false);
        extensionResult.setSuccess(true);
        extensionResult.setNext(true);
        Map<String, String> output = new HashMap<>();
        String[] splitjudullifegoal = judulLifeGoal.split(" ");
        String judul_lifegoal = splitjudullifegoal[0];
        if (judul_lifegoal.equalsIgnoreCase("Liburan")) {
            output.put(OUTPUT, dialogUtil.CreateBubble("lifeGoalLainnya_entitasLamaBerinvestasi", 2, null));
        } else {
            output.put(OUTPUT, dialogUtil.CreateBubble("lifeGoalLainnya_entitasLamaBerinvestasi", 1, null));

        }

        extensionResult.setValue(output);
        return extensionResult;
    }

    /**
     * Digunakan untuk memvalidasi value yang di inputkan oleh user
     *
     * @param extensionRequest digunakan untuk memanggil data-data dari response
     * user yang sudah diolah oleh bot
     * @return ExtensionResult json dengan format kelas ExtensionResult
     */
//    public ExtensionResult lifeGoalLainnya_validasiLamaBerinvestasi(ExtensionRequest extensionRequest) {
//        log.debug("lifeGoalLainnya_validasiLamaBerinvestasi() extension request: {}", new Gson().toJson(extensionRequest, ExtensionRequest.class));
//        ExtensionResult extensionResult = new ExtensionResult();
//        Map<String, String> output = new HashMap<>();
//        Map<String, String> clearEntities = new HashMap<>();
//
//        String lama_investasi = dialogUtil.getEasyMapValueByName(extensionRequest, "lama_berinvestasi");
//        String judul_life_goal = dialogUtil.getEasyMapValueByName(extensionRequest, "judul_life_goal");
//        String dana_sekarang = dialogUtil.getEasyMapValueByName(extensionRequest, "dana_sekarang");
//        String kebutuhan_dana = dialogUtil.getEasyMapValueByName(extensionRequest, "kebutuhan_dana");
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
//        lama_investasi = lama_investasi.toLowerCase()
//                .replace("tahun", "")
//                .replace("thun", "")
//                .replace("thn", "")
//                .replace(",", "")
//                .replace(".", "")
//                .replace("-", "")
//                .trim();
//
//        String kalimat = lama_investasi;
//        String[] kata = kalimat.split("");
//        if (kata.length > 2) {
//            lama_investasi = kata[0] + kata[1];
//        } else {
//            lama_investasi = lama_investasi;
//        }
//
//        clearEntities = validationMethod.valLamaInvestasi(lama_investasi);
//        extensionResult.setEntities(clearEntities);
//        extensionResult.setAgent(false);
//        extensionResult.setRepeat(false);
//        extensionResult.setSuccess(true);
//        extensionResult.setNext(true);
//        if (clearEntities.get("lama_berinvestasi").equals("")) {
//            output.put(OUTPUT, dialogUtil.CreateBubble("lifeGoalLainnya_validasiLamaBerinvestasi", 1, null));
//        } else {
//            if (Integer.parseInt(lama_investasi.replace(" ", "").trim()) > 50) {
//                lama_investasi = "50";
//            }
//            String[] data = validationMethod.split_Judul_lifegoal(judul_life_goal);
//            String gambar = "";
//            if ("Nonton".equalsIgnoreCase(data[0])) {
//                gambar = appProperties.getOcbc_carousel_gambar_lainnya_nonton();
//            } else if ("Liburan".equalsIgnoreCase(data[0])) {
//                gambar = appProperties.getOcbc_carousel_gambar_lainnya_liburan();
//            } else if ("Beli".equalsIgnoreCase(data[0])) {
//                gambar = appProperties.getOcbc_carousel_gambar_lainnya_beli();
//            } else {
//                gambar = appProperties.getOcbc_carousel_gambar_lainnya();
//            }
//
//            ButtonTemplate button = new ButtonTemplate();
//            button.setTitle(judul_life_goal);
//            button.setSubTitle(appProperties.getOcbc_carousel_subtitle_lainnya());
//            button.setPictureLink(gambar);
//            button.setPicturePath(gambar);
//            List<EasyMap> actions = new ArrayList<>();
//            EasyMap bookAction = new EasyMap();
//            bookAction.setName(appProperties.getOcbc_carousel_nameButton_lainnya());
//            bookAction.setValue(appProperties.OCBC_WEBVIEW_BASE_URL + "/etc/" + contactId + "/"
//                    + judul_life_goal + "/"
//                    + name + "/"
//                    + dana_sekarang + "/"
//                    + kebutuhan_dana + "/"
//                    + lama_investasi + "/"
//                    + riskProfileId + "?view=widget");
//            actions.add(bookAction);
//            button.setButtonValues(actions);
//            ButtonBuilder buttonBuilder = new ButtonBuilder(button);
//            String btnBuilder = buttonBuilder.build();
//            bubble = dialogUtil.CreateBubble("lifeGoalLainnya_validasiLamaBerinvestasi", 2, null) + CONSTANT_SPLIT_SYNTAX + btnBuilder;
//            output.put(OUTPUT, bubble);
//        }
//        extensionResult.setValue(output);
//        return extensionResult;
//    }
//   
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

    public ExtensionResult lifeGoalLainnya_validasiLamaBerinvestasi(ExtensionRequest extensionRequest) {
        log.debug("lifeGoalLainnya_validasiLamaBerinvestasi() extension request: {}", new Gson().toJson(extensionRequest, ExtensionRequest.class));
        ExtensionResult extensionResult = new ExtensionResult();
        Map<String, String> output = new HashMap<>();
        Map<String, String> clearEntities = new HashMap<>();

        String lama_investasi = dialogUtil.getEasyMapValueByName(extensionRequest, "lama_berinvestasi");
        String judul_life_goal = dialogUtil.getEasyMapValueByName(extensionRequest, "judul_life_goal");
        String dana_sekarang = dialogUtil.getEasyMapValueByName(extensionRequest, "dana_sekarang");
        String kebutuhan_dana = dialogUtil.getEasyMapValueByName(extensionRequest, "kebutuhan_dana");
        dana_sekarang = dana_sekarang
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

        kebutuhan_dana = kebutuhan_dana
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

        lama_investasi = konverterTahun(lama_investasi);
        if (lama_investasi.equalsIgnoreCase("")) {
            clearEntities.put("lama_berinvestasi", "");
            clearEntities.put("validasi_lama_berinvestasi", "");
            bubble = dialogUtil.CreateBubble("lifeGoalLainnya_validasiLamaBerinvestasi", 1, null);
            output.put(OUTPUT, bubble);
            extensionResult.setValue(output);
        } else {
            clearEntities = validationMethod.valLamaInvestasi(lama_investasi);

            StringBuilder respBuilder = new StringBuilder();

            if (clearEntities.get("lama_berinvestasi").equals("")) {
                String[] splitjudullifegoal = judul_life_goal.split(" ");
                String judul_lifegoal = splitjudullifegoal[0];
                if (judul_lifegoal.equalsIgnoreCase("Liburan")) {
                    output.put(OUTPUT, dialogUtil.CreateBubble("lifeGoalLainnya_validasiLamaBerinvestasi", 2, null));
                } else {
                    output.put(OUTPUT, dialogUtil.CreateBubble("lifeGoalLainnya_validasiLamaBerinvestasi", 1, null));
                }
            } else {

                dataLeads1.getTransaction_Data()[0].getEC_ID();
                //======================================================================
                //======================================================================

                long investment_amount = dataLeads1.getTransaction_Data()[0].getInvestment_Amount();
                String target_amount = kebutuhan_dana;
                String initial_amount = dana_sekarang;
                String tenor = lama_investasi;
                String finantcial_goal = judul_life_goal;

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
                transaction_Data.setLife_Goal_ID(3);
                transaction_Data.setFinancial_Goal(finantcial_goal);
                transaction_Data.setInvestment_Type(dataLeads1.getTransaction_Data()[0].getInvestment_Type());
                transaction_Data.setTenor(Integer.parseInt(tenor.replace(" ", "")));
                transaction_Data.setInvestment_Amount(investment_amount);
                transaction_Data.setTarget_Amount(Long.parseLong(target_amount));
                transaction_Data.setInitial_Amount(Long.parseLong(initial_amount));
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
                listData.add("" + new Gson().toJson(dataLeads, DataLeads.class) + "");
                contact.setAdditionalField(listData);
                contact = svcDolphinService.updateCustomer(userToken, contact);

                bubble = dialogUtil.CreateBubble("lifeGoalLainnya_validasiLamaBerinvestasi", 3, null);
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
     * Digunakan untuk menampilkan carousel dinamis berdasarkan syarat tertentu
     *
     * @param extensionRequest digunakan untuk memanggil data-data dari response
     * user yang sudah diolah oleh bot
     * @return hasil berupa string jawaban yang sudah di set di dalam file
     * dialog
     */
    private String lifeGoalLainnya_outputwebView(ExtensionRequest extensionRequest) {
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
        log.debug("lifeGoalLainnya_webView() extension request: {}", extensionRequest);

        String lamaInvestasi = dialogUtil.getEasyMapValueByName(extensionRequest, "lama_berinvestasi");
        String judul_life_goal = dialogUtil.getEasyMapValueByName(extensionRequest, "judul_life_goal");
        String dana_sekarang = dialogUtil.getEasyMapValueByName(extensionRequest, "dana_sekarang");
        String kebutuhan_dana = dialogUtil.getEasyMapValueByName(extensionRequest, "kebutuhan_dana");
        String nama = "";

        if (appProperties.APP_MODE.equals("debug")) {
            nama = "Bambang";
        } else {
            nama = contact.getContactFirstName();
        }

        String[] data = validationMethod.split_Judul_lifegoal(judul_life_goal);
        String gambar = "";
        if ("Nonton".equalsIgnoreCase(data[0])) {
            gambar = appProperties.getOcbc_carousel_gambar_lainnya_nonton();
        } else if ("Liburan".equalsIgnoreCase(data[0])) {
            gambar = appProperties.getOcbc_carousel_gambar_lainnya_liburan();
        } else if ("Beli".equalsIgnoreCase(data[0])) {
            gambar = appProperties.getOcbc_carousel_gambar_lainnya_beli();
        } else {
            gambar = appProperties.getOcbc_carousel_gambar_lainnya();
        }

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

        ButtonTemplate button = new ButtonTemplate();
        button.setTitle(judul_life_goal);
        button.setSubTitle(appProperties.getOcbc_carousel_subtitle_lainnya());
        button.setPictureLink(gambar);
        button.setPicturePath(gambar);
        List<EasyMap> actions = new ArrayList<>();
        EasyMap bookAction = new EasyMap();
        bookAction.setName(appProperties.getOcbc_carousel_nameButton_lainnya());
        bookAction.setValue(appProperties.OCBC_WEBVIEW_BASE_URL + "/etc/" + contactId + "/"
                + judul_life_goal + "/"
                + name + "/"
                + dana_sekarang + "/"
                + kebutuhan_dana + "/"
                + lamaInvestasi + "/"
                + riskProfileId + "?view=widget");
        actions.add(bookAction);
        button.setButtonValues(actions);
        ButtonBuilder buttonBuilder = new ButtonBuilder(button);
        String btnBuilder = buttonBuilder.build();
        String hasil = dialogUtil.CreateBubble("lifeGoalLainnya_webView", 1, null) + CONSTANT_SPLIT_SYNTAX + btnBuilder;
        return hasil;
    }

    /**
     * key output digunakan sebagai respon bot terhadap user (Json)
     *
     * @param extensionRequest digunakan untuk memanggil data-data dari response
     * user yang sudah diolah oleh bot
     * @return extensionResult json dengan format kelas ExtensionResult
     */
    public ExtensionResult lifeGoalLainnya_webView(ExtensionRequest extensionRequest) {
        //======================================================================
        //======================================================================
        String strOutput = lifeGoalLainnya_outputwebView(extensionRequest);
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
     * key output digunakan sebagai respon bot terhadap user (Json)
     *
     * @param extensionRequest digunakan untuk memanggil data-data dari response
     * user yang sudah diolah oleh bot
     * @return extensionResult json dengan format kelas ExtensionResult
     */
    public ExtensionResult lifeGoalLainnya_validasiWebViewConfirmation(ExtensionRequest extensionRequest) {
        log.debug("lifeGoalLainnya_validasiWebViewConfirmation() extension request: {}", new Gson().toJson(extensionRequest, ExtensionRequest.class));
        ExtensionResult extensionResult = new ExtensionResult();
        Map<String, String> output = new HashMap<>();
        Map<String, String> clearEntities = new HashMap<>();
        String web_view = dialogUtil.getEasyMapValueByName(extensionRequest, "web_view");
        String[] data = validationMethod.split_webView(web_view);
        extensionResult.setAgent(false);
        extensionResult.setRepeat(false);
        extensionResult.setSuccess(true);
        extensionResult.setNext(true);

        //======================================================================
        //======================================================================
        int lengthdata = data.length;
        log.debug(lengthdata);
        if (lengthdata > 1) {
            clearEntities.put("validasi_webview", "validasiWebview");
            extensionResult.setEntities(clearEntities);
            String bubbleSummarywebview = lifeGoalLainnya_validasiWebView(extensionRequest);
            output.put(OUTPUT, bubbleSummarywebview);
            extensionResult.setValue(output);
        } else {
            clearEntities.put("web_view", "");
            clearEntities.put("validasi_webview", "");
            String strOutput = lifeGoalLainnya_outputwebView(extensionRequest);
            output.put(OUTPUT, strOutput);
            extensionResult.setValue(output);
            extensionResult.setEntities(clearEntities);
        }
        return extensionResult;
    }

    /**
     * Digunakan untuk memvalidasi value yang di inputkan oleh user
     *
     * @param extensionRequest digunakan untuk memanggil data-data dari response
     * user yang sudah diolah oleh bot
     * @return extensionResult json dengan format kelas ExtensionResult
     */
    public String lifeGoalLainnya_validasiWebView(ExtensionRequest extensionRequest) {
        log.debug("lifeGoalLainnya_validasiWebView() extension request: {}", new Gson().toJson(extensionRequest, ExtensionRequest.class));
        ExtensionResult extensionResult = new ExtensionResult();
        Map<String, String> output = new HashMap<>();
        Map<String, String> clearEntities = new HashMap<>();
        String web_view = dialogUtil.getEasyMapValueByName(extensionRequest, "web_view");
        String judul_life_goal = dialogUtil.getEasyMapValueByName(extensionRequest, "judul_life_goal");
        String[] data = validationMethod.split_webView(web_view);
        extensionResult.setAgent(false);
        extensionResult.setRepeat(false);
        extensionResult.setSuccess(true);
        extensionResult.setNext(true);
        StringBuilder respBuilder = new StringBuilder();
        clearEntities.put("kebutuhan_dana", data[0]);
        clearEntities.put("dana_sekarang", data[1]);
        clearEntities.put("lama_berinvestasi", data[2]);
        clearEntities.put("validasi_webview", "validasiNama");
        extensionResult.setEntities(clearEntities);
        respBuilder.append("Life Goal : " + judul_life_goal + " \n"
                + "Target Dana : IDR " + validationMethod.replaceSparatorRp(data[0]) + " \n"
                + "Dana Terkumpul : IDR " + validationMethod.replaceSparatorRp(data[1]) + " \n"
                + "Jangka Waktu : " + data[2] + " Tahun \n"
                + "Dana investasi bulanan yang dibutuhkan : IDR " + validationMethod.replaceSparatorRp(data[3]) + " \n"
                + "(est. laba: " + data[5] + " /tahun)");
        Map<String, String> params = new HashMap<>();
        params.put("judul_life_goal", judul_life_goal);
        params.put("kebutuhan_dana", data[0]);
        params.put("dana_sekarang", data[1]);
        params.put("lama_berinvestasi", data[2]);
        params.put("investasi_result", data[3]);
        params.put("tabungan_result", data[4]);
        params.put("investasi_rate", data[5]);
        params.put("tabungan_rate", data[6]);

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

        long investment_amount = dataLeads1.getTransaction_Data()[0].getInvestment_Amount();
        String target_amount = data[0];
        String initial_amount = data[1];
        String tenor = data[2];
        String finantcial_goal = judul_life_goal;

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
        transaction_Data.setLife_Goal_ID(3);
        transaction_Data.setFinancial_Goal(finantcial_goal);
        transaction_Data.setInvestment_Type(dataLeads1.getTransaction_Data()[0].getInvestment_Type());
        transaction_Data.setTenor(Integer.parseInt(tenor.replace(" ", "")));
        transaction_Data.setInvestment_Amount(investment_amount);
        transaction_Data.setTarget_Amount(Long.parseLong(target_amount));
        transaction_Data.setInitial_Amount(Long.parseLong(initial_amount));
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
        listData.add("" + new Gson().toJson(dataLeads, DataLeads.class) + "");
        contact.setAdditionalField(listData);
        contact = svcDolphinService.updateCustomer(userToken, contact);

        Map<String, String> map = new HashMap<>();
        StringBuilder respBuilderJika = new StringBuilder();
        if (dataLeads1.getTransaction_Data()[0].getRisk_Profile_ID() == 0) {
            respBuilderJika.append(dialogUtil.CreateBubble("lifeGoalLainnya_validasiWebView", 1, null));
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
     * key output digunakan sebagai respon bot terhadap user (Json)
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
            bubble = dialogUtil.CreateBubble("lifeGoalLainnya_validasiLamaBerinvestasi", 3, null);
            respBuilder.append(bubble);
            output.put(OUTPUT, respBuilder.toString());
            extensionResult.setValue(output);
        }
        extensionResult.setEntities(clearEntities);
        return extensionResult;
    }

}
