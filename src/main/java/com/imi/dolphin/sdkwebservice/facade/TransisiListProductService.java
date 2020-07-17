/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imi.dolphin.sdkwebservice.facade;

import com.google.gson.Gson;
import com.imi.dolphin.sdkwebservice.builder.ButtonBuilder;
import com.imi.dolphin.sdkwebservice.builder.CarouselBuilder;
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
 * @author chochong
 */
@Service
public class TransisiListProductService {

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
     * Digunakan untuk menampilkan tombol berupa carousel, tombol tersebut akan
     * mengerahkan user pada tampilan antar muka webview, jenis webview yang
     * akan di tampilkan tergantung pada life goal id yang dimiliki oleh user
     *
     * @param extensionRequest digunakan untuk memanggil data-data dari response
     * user yang sudah diolah oleh bot
     * @return Json sesuai dengan format dari class ExtensionResult
     */
    public ExtensionResult transisiListProduct_webView(ExtensionRequest extensionRequest) {
        log.debug("transisiListProduct_webView() extension request: {}", new Gson().toJson(extensionRequest, ExtensionRequest.class));

        //======================================================================
        userToken = svcDolphinService.getUserToken(userToken);
        String contactId = extensionRequest.getIntent().getTicket().getContactId();
        Contact contact = svcDolphinService.getCustomer(userToken, contactId);
        String b = contact.getAdditionalField().get(0);
        DataLeads dataLeads1 = new Gson().fromJson(b, DataLeads.class);
        //======================================================================        

        int pendanaan = dataLeads1.getTransaction_Data()[0].getInvestment_Type();
        String besar_dana_investasi = dataLeads1.getTransaction_Data()[0].getInvestment_Amount() + "";
        int lamaInvestasi = dataLeads1.getTransaction_Data()[0].getTenor();
        String name = dataLeads1.getTransaction_Data()[0].getName();
        int riskProfileId = dataLeads1.getTransaction_Data()[0].getRisk_Profile_ID();
        int usia_anak = dataLeads1.getTransaction_Data()[0].getChildren_Age();
        int negaraKuliah = dataLeads1.getTransaction_Data()[0].getEC_ID();
        String judul_life_goal = dataLeads1.getTransaction_Data()[0].getFinancial_Goal();
        long dana_sekarang = dataLeads1.getTransaction_Data()[0].getInitial_Amount();
        long kebutuhan_dana = dataLeads1.getTransaction_Data()[0].getTarget_Amount();
        String pic = "";
        String title = "";
        String subTitle = "";
        String buttonName = "Lihat";
        int lifeGoal = dataLeads1.getTransaction_Data()[0].getLife_Goal_ID();
        String link = "";
        switch (lifeGoal) {
            case 1:
                pic = appProperties.getOcbc_carousel_gambar_growth();
                title = appProperties.getOcbc_carousel_title_growth();
                subTitle = appProperties.getOcbc_carousel_subtitle_growth();
                link = appProperties.OCBC_WEBVIEW_BASE_URL + "/growth/" + contactId + "/"
                        + besar_dana_investasi + "/"
                        + pendanaan + "/"
                        + lamaInvestasi + "/"
                        + name + "/"
                        + riskProfileId + "?view=widget";
                break;
            case 2:
                pic = appProperties.getOcbc_carousel_gambar_pendidikan();
                title = appProperties.getOcbc_carousel_title_pendidikan();
                subTitle = appProperties.getOcbc_carousel_subtitle_pendidikan();
                link = appProperties.OCBC_WEBVIEW_BASE_URL + "/education/" + contactId + "/"
                        + usia_anak + "/"
                        + negaraKuliah + "/"
                        + dana_sekarang + "/"
                        + name + "/"
                        + riskProfileId + "?view=widget";
                break;
            default:
                String[] data = validationMethod.split_Judul_lifegoal(judul_life_goal);
                if ("Nonton".equalsIgnoreCase(data[0])) {
                    pic = appProperties.getOcbc_carousel_gambar_lainnya_nonton();
                } else if ("Liburan".equalsIgnoreCase(data[0])) {
                    pic = appProperties.getOcbc_carousel_gambar_lainnya_liburan();
                } else if ("Beli".equalsIgnoreCase(data[0])) {
                    pic = appProperties.getOcbc_carousel_gambar_lainnya_beli();
                } else {
                    pic = appProperties.getOcbc_carousel_gambar_lainnya();
                }
                title = judul_life_goal;
                subTitle = appProperties.getOcbc_carousel_subtitle_lainnya();
                link = appProperties.OCBC_WEBVIEW_BASE_URL + "/etc/" + contactId + "/"
                        + judul_life_goal + "/"
                        + name + "/"
                        + dana_sekarang + "/"
                        + kebutuhan_dana + "/"
                        + lamaInvestasi + "/"
                        + riskProfileId + "?view=widget";
                break;
        }
        ExtensionResult extensionResult = new ExtensionResult();
        Map<String, String> output = new HashMap<>();

        extensionResult.setAgent(false);
        extensionResult.setRepeat(false);
        extensionResult.setSuccess(true);
        extensionResult.setNext(true);
        StringBuilder respBuilder = new StringBuilder();
        respBuilder.append(dialogUtil.CreateBubble("lifeGoalLainnya_validasiWebView", 2, null));
        List<String> buttonList = new ArrayList<>();
        ButtonTemplate button;
        ButtonBuilder buttonBuilder;
        for (int i = 0; i < 1; i++) {
            button = new ButtonTemplate();
            button.setPictureLink(pic);
            button.setPicturePath(pic);
            button.setTitle(title);
            button.setSubTitle(subTitle);
            List<EasyMap> actions = new ArrayList<>();
            EasyMap bookAction = new EasyMap();
            bookAction.setName(buttonName);
            bookAction.setValue(link);
            actions.add(bookAction);
            button.setButtonValues(actions);
            buttonBuilder = new ButtonBuilder(button);
            buttonList.add(buttonBuilder.build());
        }
        CarouselBuilder carouselBuilder = new CarouselBuilder(buttonList);
        output.put(OUTPUT, (respBuilder.append(CONSTANT_SPLIT_SYNTAX).append(carouselBuilder.build())).toString());
        extensionResult.setValue(output);
        return extensionResult;
    }

    /**
     * Digunakan untuk melakukan validasi terhadap data yang di terima oleh bot
     * yang berasal dari webview jika user tidak melakukan action pada web view
     * maka bot akan menampilkan kembali webview sebelumnya
     *
     * @param extensionRequest digunakan untuk memanggil data-data dari response
     * user yang sudah diolah oleh bot
     * @return Json sesuai dengan format dari class ExtensionResult
     */
    public ExtensionResult transisiListProduct_validasiWebView(ExtensionRequest extensionRequest) {
        log.debug("transisiListProduct_validasiWebView() extension request: {}", new Gson().toJson(extensionRequest, ExtensionRequest.class));
        ExtensionResult extensionResult = new ExtensionResult();
        Map<String, String> output = new HashMap<>();
        Map<String, String> clearEntities = new HashMap<>();
        String web_view = dialogUtil.getEasyMapValueByName(extensionRequest, "web_view");
        ValidationMethod validationMethod = new ValidationMethod();
        String[] data = validationMethod.split_webView(web_view);
//        extensionResult.setAgent(false);
//        extensionResult.setRepeat(false);
//        extensionResult.setSuccess(true);
//        extensionResult.setNext(true);
        if (data.length > 1) {
            extensionResult = this.transisiListProduct_final(extensionRequest);
            clearEntities.put("validasi_web_view", "validasiWebView");
            extensionResult.setEntities(clearEntities);
        } else {
            extensionResult = this.transisiListProduct_webView(extensionRequest);
            clearEntities.put("web_view", "");
            clearEntities.put("validasi_web_view", "");
            extensionResult.setEntities(clearEntities);
        }
//        extensionResult.setValue(output);
        return extensionResult;
    }

    private String convertBulankeTahun(String tenor) {
        String jangkawaktu = "";
        String tahun = "0";
        String bulan = "0";
        double bulandouble = 0;
        double hasilpembagiantenor = 0;
        double tenordouble = Double.parseDouble(tenor);
        if (tenordouble > 12) {
            hasilpembagiantenor = tenordouble / 12;
            String hasil = Double.toString(hasilpembagiantenor);
            String[] splithasil = hasil.split("\\.");
            String splithasil1 = splithasil[0];
            String splithasil2 = "0." + splithasil[1];
            double roundedsplithasil2 = (double) Math.round(Double.parseDouble(splithasil2) * 100) / 100;
            tahun = splithasil1;
            bulandouble = roundedsplithasil2 * 12;
            bulandouble = (double) Math.round(bulandouble);
            int bulanint = (int) bulandouble;
            bulan = Integer.toString(bulanint);
        } else if (tenordouble == 12) {
            hasilpembagiantenor = tenordouble / 12;
            int tahunint = (int) hasilpembagiantenor;
            tahun = Integer.toString(tahunint);
        } else {
            bulan = tenor;
        }
        if (tahun.equalsIgnoreCase("0")) {
            jangkawaktu = bulan + " Bulan";
        } else if (bulan.equalsIgnoreCase("0")) {
            jangkawaktu = tahun + " Tahun";
        } else {
            jangkawaktu = tahun + " Tahun " + bulan + " Bulan";
        }
        return jangkawaktu;
    }

    /**
     * Digunakan untuk menampilkan summary user terhadap data yang sudah di
     * inputkan, dan akan melakukan update data user berdasarkan data yang
     * sebelumnya sudah di inputkan oleh user
     *
     * @param extensionRequest digunakan untuk memanggil data-data dari response
     * user yang sudah diolah oleh bot
     * @return Json sesuai dengan format dari class ExtensionResult
     */
    public ExtensionResult transisiListProduct_final(ExtensionRequest extensionRequest) {
        log.debug("transisiListProduct_final() extension request: {}", new Gson().toJson(extensionRequest, ExtensionRequest.class));
        //======================================================================
        //======================================================================
        userToken = svcDolphinService.getUserToken(userToken);
        String contactId = extensionRequest.getIntent().getTicket().getContactId();
        Contact contact = svcDolphinService.getCustomer(userToken, contactId);
        String b = contact.getAdditionalField().get(0);
        DataLeads dataLeads1 = new Gson().fromJson(b, DataLeads.class);
        //======================================================================     
        //======================================================================     
        String Children_Age = "";
        String EC_ID = "";
        String Initial_Amount = "";
        String Target_Amount = "";
        int tenor = 0;
        String investment_amount = "";
        int investment_type = 0;
        String finantcial_goal = "";
        //======================================================================     
        //======================================================================     

        int pendanaan = dataLeads1.getTransaction_Data()[0].getInvestment_Type();
        int life_goal_id = dataLeads1.getTransaction_Data()[0].getLife_Goal_ID();
        String mode = "";

        if (pendanaan == 0) {
            mode = "Bulanan";
        } else {
            mode = "Lumpsum";
        }

        ExtensionResult extensionResult = new ExtensionResult();
        Map<String, String> output = new HashMap<>();
        String web_view = dialogUtil.getEasyMapValueByName(extensionRequest, "web_view");
        ValidationMethod validationMethod = new ValidationMethod();
        String[] data = validationMethod.split_webView(web_view);
        extensionResult.setAgent(false);
        extensionResult.setRepeat(false);
        extensionResult.setSuccess(true);
        extensionResult.setNext(true);
        StringBuilder respBuilder = new StringBuilder();

        switch (life_goal_id) {
            case 1:

                //======================================================================
                //======================================================================
                investment_amount = validationMethod.replaceSparator(data[0]);
                tenor = Integer.parseInt(data[1]);
                investment_type = pendanaan;
                String jangkawaktugrowth = convertBulankeTahun(data[1]);
                //======================================================================
                //======================================================================
                respBuilder.append("Life Goal : Tumbuhkan Uang \n"
                        + "Mode : " + mode + " \n"
                        + "Dana Investasi : IDR " + validationMethod.replaceSparatorRp(data[0]) + " \n"
                        + "Jangka Waktu : " + jangkawaktugrowth + " \n"
                        + "Estimasi Pertumbuhan Hingga : IDR " + validationMethod.replaceSparatorRp(data[2]) + " \n"
                        + "(estimasi return " + data[4] + " /tahun)");
                break;
            case 2:
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

                Children_Age = data[0];
                EC_ID = data[1];
                Initial_Amount = validationMethod.replaceSparator(data[2]);
                Target_Amount = validationMethod.replaceSparator(data[7]);

                tenor = 18 - Integer.parseInt(data[0]);

                if (tenor <= 0) {
                    tenor = 1;
                }
                tenor = tenor * 12;
                //===========================================================================================
                log.debug("TransisiListProduct() Tenorrrrrrrr Integer.parseInt(data[0]) |=======|||debug|||=======| Gson : {}", Integer.parseInt(data[0]));
                //===========================================================================================

                //===========================================================================================
                log.debug("TransisiListProduct() Tenorrrrrrrr |=======|||debug|||=======| Gson : {}", tenor);
                //===========================================================================================

                respBuilder.append("Life Goal : Pendidikan Kuliah Anak \n"
                        + "Usia Anak : " + data[0] + " Tahun \n"
                        + "Negara Tujuan : " + negara_tujuan + " \n"
                        + "Dana Tersedia : IDR " + validationMethod.replaceSparatorRp(data[2]) + " \n"
                        + "Dana bulanan investasi yang dibutuhkan hanya: IDR " + validationMethod.replaceSparatorRp(data[3]) + " \n"
                        + "(estimasi return: " + data[5] + " /tahun)");
                break;
            default:
                String judul_life_goal = dataLeads1.getTransaction_Data()[0].getFinancial_Goal();
                Target_Amount = validationMethod.replaceSparator(data[0]);
                Initial_Amount = validationMethod.replaceSparator(data[1]);
                tenor = Integer.parseInt(data[2]);
                finantcial_goal = judul_life_goal;
                String jangkawaktuetc = convertBulankeTahun(data[2]);
                respBuilder.append("Life Goal : " + judul_life_goal + " \n"
                        + "Target Dana : IDR " + validationMethod.replaceSparatorRp(data[0]) + " \n"
                        + "Dana Terkumpul : IDR " + validationMethod.replaceSparatorRp(data[1]) + " \n"
                        + "Jangka Waktu : " + jangkawaktuetc + " \n"
                        + "Dana investasi bulanan yang dibutuhkan : IDR " + validationMethod.replaceSparatorRp(data[3]) + " \n"
                        + "(estimasi return: " + data[5] + " /tahun)");
                break;
        }

        int lifeGoal = dataLeads1.getTransaction_Data()[0].getLife_Goal_ID();
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
        transaction_Data.setRisk_Profile_ID(dataLeads1.getTransaction_Data()[0].getRisk_Profile_ID());

        transaction_Data.setInvestment_Amount(dataLeads1.getTransaction_Data()[0].getInvestment_Amount());
        switch (life_goal_id) {
            case 1:
                transaction_Data.setLife_Goal_ID(1);
                transaction_Data.setFinancial_Goal("Pertumbuhan Aset");
                transaction_Data.setInvestment_Type(investment_type);
                transaction_Data.setTenor(tenor);
                transaction_Data.setInvestment_Amount(Integer.parseInt(investment_amount));
                transaction_Data.setInitial_Amount(0);
                break;
            case 2:
                transaction_Data.setLife_Goal_ID(2);
                transaction_Data.setFinancial_Goal("Pendidikan Anak");
                transaction_Data.setTenor(tenor);
                transaction_Data.setTarget_Amount(Long.parseLong(Target_Amount));
                transaction_Data.setInitial_Amount(Integer.parseInt(Initial_Amount));
                transaction_Data.setEC_ID(Integer.parseInt(EC_ID));
                transaction_Data.setChildren_Age(Integer.parseInt(Children_Age));
                break;
            case 3:
                transaction_Data.setLife_Goal_ID(3);
                transaction_Data.setFinancial_Goal(finantcial_goal);
                transaction_Data.setTenor(tenor);
                transaction_Data.setInvestment_Amount(dataLeads1.getTransaction_Data()[0].getInvestment_Amount());
                transaction_Data.setTarget_Amount(Long.parseLong(Target_Amount));
                transaction_Data.setInitial_Amount(Long.parseLong(Initial_Amount));
                break;
            default:
                transaction_Data.setFinancial_Goal(dataLeads1.getTransaction_Data()[0].getFinancial_Goal());
                transaction_Data.setTenor(dataLeads1.getTransaction_Data()[0].getTenor());
                transaction_Data.setInvestment_Amount(dataLeads1.getTransaction_Data()[0].getInvestment_Amount());
                transaction_Data.setTarget_Amount(dataLeads1.getTransaction_Data()[0].getTarget_Amount());
                transaction_Data.setInitial_Amount(dataLeads1.getTransaction_Data()[0].getInitial_Amount());
                transaction_Data.setLife_Goal_ID(dataLeads1.getTransaction_Data()[0].getLife_Goal_ID());
                transaction_Data.setInvestment_Type(dataLeads1.getTransaction_Data()[0].getInvestment_Type());
                transaction_Data.setEC_ID(dataLeads1.getTransaction_Data()[0].getEC_ID());
                transaction_Data.setChildren_Age(dataLeads1.getTransaction_Data()[0].getChildren_Age());
                break;
        }

        transaction_Data.setProduct_ID(dataLeads1.getTransaction_Data()[0].getProduct_ID());
        transaction_Data.setDatetime_Start_Process(dataLeads1.getTransaction_Data()[0].getDatetime_Start_Process());
        transaction_Data.setDatetime_End_Process(dataLeads1.getTransaction_Data()[0].getDatetime_Start_Process());
        transaction_Data.setTime_Horizon(dataLeads1.getTransaction_Data()[0].getTime_Horizon());
        transaction_Data.setSource_of_Income(dataLeads1.getTransaction_Data()[0].getSource_of_Income());
        transaction_Data.setInvestment_Knowledge(dataLeads1.getTransaction_Data()[0].getInvestment_Knowledge());
        transaction_Data.setIncome_Usage(dataLeads1.getTransaction_Data()[0].getIncome_Usage());
        transaction_Data.setPotential_Loss(dataLeads1.getTransaction_Data()[0].getPotential_Loss());
        transaction_Data.setPlatform_Name("OCBC NISP Website");
        list_transaction_Data.add(transaction_Data);
        Transaction_Data[] arrTransaction_data = new Transaction_Data[1];
        arrTransaction_data = list_transaction_Data.toArray(arrTransaction_data);
        DataLeads dataLeads = new DataLeads();
        dataLeads.setChannel_ID("1");
        dataLeads.setExt_Reff_ID(contactId);
        dataLeads.setTransaction_Data(arrTransaction_data);
        List<String> listData = new ArrayList<>();
        listData.add("" + new Gson().toJson(dataLeads, DataLeads.class) + "");
        contact.setAdditionalField(listData);
        contact = svcDolphinService.updateCustomer(userToken, contact);

        Map<String, String> map = new HashMap<>();
        StringBuilder respBuilderJika = new StringBuilder();
//        respBuilderJika.append("Selangkah lebih dekat untuk mewujudkan Life Goal {first_name} dapat dicapai dengan berinvestasi di produk pilihan {first_name} sendiri.  ");
//        respBuilderJika.append("Berikut merupakan beberapa pilihan produk investasi yang sesuai dengan profil dan prioritas {first_name}");
//        map.put("Lanjut", "rekomendasi product");

        respBuilderJika.append(dialogUtil.CreateBubble("transisiListProduct_final", 1, null));

        QuickReplyBuilder quickReplyBuilder = new QuickReplyBuilder.Builder(respBuilderJika + "").addAll(map).build();
        output.put(OUTPUT, (respBuilder.append(CONSTANT_SPLIT_SYNTAX).append(quickReplyBuilder.string())).toString());

        extensionResult.setValue(output);
        return extensionResult;
    }

    /**
     * di bagian ini akan melakukan validasi kembali terhadap action user, jika
     * user tidak melakukan action klik tombol pada quick reply maka bot akan
     * melakukan perulangan untuk menampilkan button quick reply tersebut
     *
     * @param extensionRequest digunakan untuk memanggil data-data dari response
     * user yang sudah diolah oleh bot
     * @return Json sesuai dengan format dari class ExtensionResult
     */
    public ExtensionResult transisiListProduct_validasiFinal(ExtensionRequest extensionRequest) {
        log.debug("validasi_beforefinal() extension request: {}", new Gson().toJson(extensionRequest, ExtensionRequest.class));
        ExtensionResult extensionResult = new ExtensionResult();
        extensionResult.setAgent(false);
        extensionResult.setRepeat(false);
        extensionResult.setSuccess(true);
        extensionResult.setNext(true);
        Map<String, String> clearEntities = new HashMap<>();

        String validasiwebview = dialogUtil.getEasyMapValueByName(extensionRequest, "validasi_webviewfinal").toLowerCase();
        if (validasiwebview.equalsIgnoreCase("Risk Profile") || validasiwebview.equalsIgnoreCase("rekomendasi product")) {
            clearEntities.put("validasi_final", "Done");
        } else {
            clearEntities.put("final", "");
            clearEntities.put("validasi_final", "");
            userToken = svcDolphinService.getUserToken(userToken);
            String contactId = extensionRequest.getIntent().getTicket().getContactId();
            Contact contact = svcDolphinService.getCustomer(userToken, contactId);
            String b = contact.getAdditionalField().get(0);
            DataLeads dataLeads1 = new Gson().fromJson(b, DataLeads.class);
            Map<String, String> output = new HashMap<>();
            StringBuilder respBuilder = new StringBuilder();
            Map<String, String> map = new HashMap<>();
            StringBuilder respBuilderJika = new StringBuilder();
            respBuilderJika.append(dialogUtil.CreateBubble("transisiListProduct_final", 1, null));
            QuickReplyBuilder quickReplyBuilder = new QuickReplyBuilder.Builder(respBuilderJika + "").addAll(map).build();
            output.put(OUTPUT, (respBuilder.append(CONSTANT_SPLIT_SYNTAX).append(quickReplyBuilder.string())).toString());
            extensionResult.setValue(output);
        }
        extensionResult.setEntities(clearEntities);
        return extensionResult;
    }

}
