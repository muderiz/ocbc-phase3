/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imi.dolphin.sdkwebservice.facade;

import com.google.gson.Gson;
import com.imi.dolphin.sdkwebservice.builder.ButtonBuilder;
import com.imi.dolphin.sdkwebservice.builder.CarouselBuilder;
import com.imi.dolphin.sdkwebservice.builder.ImageBuilder;
import com.imi.dolphin.sdkwebservice.model.ButtonTemplate;
import com.imi.dolphin.sdkwebservice.model.CalculatorRequestBody;
import com.imi.dolphin.sdkwebservice.model.CalculatorResponseBody;
import com.imi.dolphin.sdkwebservice.model.Contact;
import com.imi.dolphin.sdkwebservice.model.DataLeads;
import com.imi.dolphin.sdkwebservice.model.EasyMap;
import com.imi.dolphin.sdkwebservice.model.ExtensionRequest;
import com.imi.dolphin.sdkwebservice.model.ExtensionResult;
import com.imi.dolphin.sdkwebservice.model.Product;
import com.imi.dolphin.sdkwebservice.model.Transaction_Data;
import com.imi.dolphin.sdkwebservice.model.UserToken;
import com.imi.dolphin.sdkwebservice.param.ParamSdk;
import com.imi.dolphin.sdkwebservice.property.AppProperties;
import com.imi.dolphin.sdkwebservice.service.IDolphinService;
import com.imi.dolphin.sdkwebservice.service.MatrixParameter;
import com.imi.dolphin.sdkwebservice.service.ServiceImp;
import com.imi.dolphin.sdkwebservice.service.ValidationMethod;
import com.imi.dolphin.sdkwebservice.util.DialogUtil;
import com.imi.dolphin.sdkwebservice.util.OkHttpUtil;
import com.imi.dolphin.sdkwebservice.util.PictureUtil;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
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
public class RekomendasiProductService {

    private static final Logger log = LogManager.getLogger(ServiceImp.class);

    public static final String OUTPUT = "output";
    private UserToken userToken;

    @Autowired
    AppProperties appProperties;

    @Autowired
    OkHttpUtil okHttpUtil;
    private static final String CONSTANT_SPLIT_SYNTAX = "&split&";

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
     * Untuk memvalidasi inputan user dan membedakan apakah user sudah tau
     * Reksadana atau belum
     *
     * @param extensionRequest digunakan untuk memanggil data-data dari response
     * user yang sudah diolah oleh bot
     * @return json dengan format class extensionResult
     */
    public ExtensionResult rekomendasiProduct_belumtauReksadana(ExtensionRequest extensionRequest) {
        Map<String, String> output = new HashMap<>();
        Map<String, String> clearEntities = new HashMap<>();
        ExtensionResult extensionResult = new ExtensionResult();
        StringBuilder respBuilder = new StringBuilder();
        String pertanyaan_reksadana = dialogUtil.getEasyMapValueByName(extensionRequest, "pertanyaan_reksadana");
        String intention = dialogUtil.getEasyMapValueByName(extensionRequest, "intention");
        clearEntities = validationMethod.valStatusReksadana(pertanyaan_reksadana);
        extensionResult.setAgent(false);
        extensionResult.setRepeat(false);
        extensionResult.setSuccess(true);
        extensionResult.setNext(true);
        if (clearEntities.get("pertanyaan_reksadana").equalsIgnoreCase("sudah")) {
            clearEntities.put("belumtau_reksadana", "lanjut");
            clearEntities.put("belumtau_reksadana2", "lanjut");
            CarouselBuilder carouselBuilder = rekomendasiProduct_outputCarousel(extensionRequest);
            bubble = dialogUtil.CreateBubble("rekomendasiProduct_belumtauReksadana", 2, null);

            respBuilder.append(bubble)
                    .append(CONSTANT_SPLIT_SYNTAX)
                    .append(carouselBuilder.build());
            output.put(OUTPUT, respBuilder.toString());
            extensionResult.setValue(output);
        } else if (clearEntities.get("pertanyaan_reksadana").equalsIgnoreCase("belum")) {

            ButtonTemplate button = new ButtonTemplate();
            button.setTitle(ParamSdk.SAMPLE_TITLE);
            button.setSubTitle(ParamSdk.SAMPLE_SUBTITLE);
            button.setPictureLink(appProperties.getOCBC_PICTURES_INFOGRAFIK3());
            button.setPicturePath(appProperties.getOCBC_PICTURES_INFOGRAFIK3());
            ImageBuilder buttonBuilder = new ImageBuilder(button);
            String btnBuilder = buttonBuilder.build();

            bubble = dialogUtil.CreateBubble("rekomendasiProduct_belumtauReksadana", 1, null);

            respBuilder.append(btnBuilder)
                    .append(CONSTANT_SPLIT_SYNTAX)
                    .append(bubble);
            output.put(OUTPUT, respBuilder.toString());
            extensionResult.setValue(output);
        } else if (clearEntities.get("pertanyaan_reksadana").equals("")) {
            output.put(OUTPUT, "");
            extensionResult.setValue(output);
        }
        extensionResult.setEntities(clearEntities);

        return extensionResult;
    }

    /**
     * Digunakan untuk memperoleh rekomendasi product yang sesuai dengan data
     * yang dimiliki oleh user
     *
     * @param extensionRequest digunakan untuk memanggil data-data dari response
     * user yang sudah diolah oleh bot
     * @return CarouselBuilder berupa sting untuk menampilkan rekomendasi
     * product berdasarkan data yang dimiliki oleh user
     */
    private CarouselBuilder rekomendasiProduct_outputCarousel(ExtensionRequest extensionRequest) {
        log.debug("rekomendasiProduct_carousel() extension request: {}", new Gson().toJson(extensionRequest, ExtensionRequest.class));
        userToken = svcDolphinService.getUserToken(userToken);
        String contactId = extensionRequest.getIntent().getTicket().getContactId();
        Contact contact = svcDolphinService.getCustomer(userToken, contactId);
        String dataLeadsJSON;
        DataLeads dataLeads1;
        Transaction_Data transaction_Data;
        contactId = extensionRequest.getIntent().getTicket().getContactId();
        contact = svcDolphinService.getCustomer(userToken, contactId);
        String b = contact.getAdditionalField().get(0);
        dataLeads1 = new Gson().fromJson(b, DataLeads.class);
        int pendidikansdfs = dataLeads1.getTransaction_Data()[0].getEC_ID();
        log.debug("rekomendasiProduct_carousel() EC_ID: {}", pendidikansdfs);
        transaction_Data = dataLeads1.getTransaction_Data()[0];
        long nominal_amount = 0;
        switch (transaction_Data.Life_Goal_ID) {
            case 1:
                nominal_amount = transaction_Data.Investment_Amount;
                break;
            default:
                nominal_amount = transaction_Data.Target_Amount;
                break;
        }

        //=========================================================================================================
        log.debug("rekomendasiProduct_carousel() nominal_amount |=======|||debug|||=======| nominal_amount: {}", nominal_amount);
        //=========================================================================================================

        Map<String, String> data;

        if (transaction_Data.Life_Goal_ID == 1) {
            data = matrixParameter.listProduct(transaction_Data.Life_Goal_ID,
                    String.valueOf(transaction_Data.Investment_Type),
                    transaction_Data.Tenor,
                    nominal_amount);

        } else {
            data = matrixParameter.listProduct(transaction_Data.Life_Goal_ID,
                    String.valueOf(transaction_Data.Investment_Type),
                    transaction_Data.Tenor,
                    transaction_Data.Target_Amount - transaction_Data.Initial_Amount);

        }

        String[] list_product_type = data.get("List_Product_Type").split(",");
        CalculatorRequestBody objListProductRequest = new CalculatorRequestBody();
        objListProductRequest.Channel_ID = 1;
        objListProductRequest.Ext_Reff_ID = contactId;
        objListProductRequest.List_Product_Type = list_product_type;
        objListProductRequest.MFA_List = data.get("MFA_List");
        objListProductRequest.Risk_Profile_ID = transaction_Data.getRisk_Profile_ID();
        objListProductRequest.Tipe = transaction_Data.getLife_Goal_ID() == 1 ? "2" : "1";
        objListProductRequest.Nominal_Amount = nominal_amount;

        CalculatorResponseBody objListProductResponse = wppService.POST_Calculator(
                appProperties.OCBC_WPP_PATH_LIST_PRODUCT,
                objListProductRequest);
        log.debug("rekomendasiProduct_carousel() body dari product yang di tangkap: {}", new Gson().toJson(objListProductResponse, CalculatorResponseBody.class));

        Product[] arrProduct = objListProductResponse.List_Product;
        int RC_id = objListProductResponse.RC;

        //=========================================================================================================
        log.debug("rekomendasiProduct_carousel() RC_id |=======|||debug|||=======| RC_id: {}", RC_id);
        //=========================================================================================================

        List<String> buttonList = new ArrayList<>();
        ButtonTemplate button;
        ButtonBuilder buttonBuilder;

        if (RC_id == 0) {
            for (Product product : arrProduct) {

                List<String> listPathCalculator = matrixParameter.getCalculatorPath(transaction_Data.Life_Goal_ID, product.Product_Type);
                //=========================================================================================================
                log.debug("rekomendasiProduct_carousel() list product size |=======|||debug|||=======| listPathCalculator: {}", listPathCalculator.size());
                //=========================================================================================================
                for (String pathCalculator : listPathCalculator) {
                    String pesan = "";

                    if (pathCalculator.equalsIgnoreCase(appProperties.OCBC_WPP_PATH_FUTURE_VALUE)) {//pertumbuhan aset
                        pesan = "Estimasi pertumbuhan aset <br>"
                                + "hingga <br>"
                                + "<b>#result#<b><br><br>"
                                + "Estimasi return <b>#rate#<b>/thn <br>";
                        CalculatorRequestBody futureValueRequest = new CalculatorRequestBody();
                        futureValueRequest.Ext_Reff_ID = contactId;
                        futureValueRequest.Channel_ID = 1;
                        futureValueRequest.Investment_Type = transaction_Data.Investment_Type;
                        futureValueRequest.Future_Value_Type = matrixParameter.getFutureValueType("FV", product.Product_Type);
                        futureValueRequest.Yearly_Return_Code = matrixParameter.getYearlyReturnCode("FV", product.Product_Type);
                        futureValueRequest.Tenor = transaction_Data.Tenor;
                        futureValueRequest.Investment_Amount = transaction_Data.Investment_Amount;
                        futureValueRequest.Due_Date = 0;
                        futureValueRequest.Product_ID = product.Product_ID;
                        futureValueRequest.Risk_Profile_ID = transaction_Data.Risk_Profile_ID;

                        //===========================================================================================
                        log.debug("rekomendasiProduct_carousel() futureValueRequest |=======|||debug|||=======| body futureValueRequest : {}", new Gson().toJson(futureValueRequest, CalculatorRequestBody.class));
                        //===========================================================================================

                        CalculatorResponseBody futureValueResponse = wppService.POST_Calculator(
                                appProperties.OCBC_WPP_PATH_FUTURE_VALUE,
                                futureValueRequest);

                        if (futureValueResponse.RC == 0) {
                            //===========================================================================================
                            log.debug("rekomendasiProduct_carousel() futureValueResponse |=======|||debug|||=======| body futureValueResponse : {}", new Gson().toJson(futureValueResponse, CalculatorResponseBody.class));
                            //===========================================================================================

                            pesan = pesan.replace("#result#", "<b>" + new DecimalFormat("IDR ###,###", DecimalFormatSymbols.getInstance(Locale.ENGLISH)).format(futureValueResponse.Result) + "</b>")
                                    .replace("#rate#", futureValueResponse.Rate);

                            //===========================================================================================
                            log.debug("rekomendasiProduct_carousel() futureValueResponse |=======|||debug|||=======| pesan : {}", pesan);
                            //===========================================================================================

                            String pathImg = pictureUtil.generatePic(pesan, contactId, product.Product_Type, futureValueResponse.Investment_Type);
                            pathImg = pathImg.replace("/var/www/html", "").trim();
                            button = new ButtonTemplate();
                            button.setPictureLink(appProperties.OCBC_WEBVIEW_BASE_URL + pathImg);
                            button.setPicturePath(appProperties.OCBC_WEBVIEW_BASE_URL + pathImg);
                            String title = "";

                            //===========================================================================================
                            log.debug("rekomendasiProduct_carousel() product.Product_Type |=======|||debug|||=======| product.Product_Type : {}", futureValueResponse);
                            //===========================================================================================
                            String subtitle = "";
                            switch (product.Product_Type) {
                                case "TK":
                                    title = "Taka";
                                    subtitle = "TAKA Bunga Pasti";
                                    break;
                                case "DP":
                                    title = "Deposito";
                                    subtitle = "";
                                    break;
                                case "MFA":
                                case "MFB":
                                    title = "Reksa Dana";
                                    subtitle = product.Product_Name;
                                    break;
                            }
                            button.setTitle(title);
                            button.setSubTitle(subtitle);
                            List<EasyMap> actions = new ArrayList<>();
                            EasyMap bookAction = new EasyMap();
                            bookAction.setName("Pilih");
                            bookAction.setValue(product.Product_ID
                                    + " & " + product.Product_Name
                                    + " & " + product.Average_Rate
                                    + " & " + product.Bad_Rate
                                    + " & " + product.Good_Rate
                                    + " & " + futureValueResponse.Investment_Type
                                    + " & " + futureValueResponse.Result
                                    + " & " + product.Product_Type
                                    + " & " + futureValueResponse.Rate);
                            actions.add(bookAction);
                            button.setButtonValues(actions);
                            buttonBuilder = new ButtonBuilder(button);
                            buttonList.add(buttonBuilder.build());
                        } else {

                        }
                    } else if (pathCalculator.equalsIgnoreCase(appProperties.OCBC_WPP_PATH_FUTURE_VALUE_DEPOSITO)) {//pertumbuhan aset
                        pesan = "Estimasi pertumbuhan aset <br> "
                                + "hingga <br>"
                                + "<b>#result#<b><br><br>"
                                + "Estimasi return <b>#rate#<b>/thn <br>";
                        CalculatorRequestBody futureValueRequest = new CalculatorRequestBody();
                        futureValueRequest.Ext_Reff_ID = contactId;
                        futureValueRequest.Channel_ID = 1;
                        futureValueRequest.Tenor = transaction_Data.Tenor;
                        futureValueRequest.Investment_Amount = transaction_Data.Investment_Amount;
                        futureValueRequest.Tax = 0.2;

                        //===========================================================================================
                        log.debug("rekomendasiProduct_carousel() futureValueRequest |=======|||debug|||=======| body futureValueRequest : {}", new Gson().toJson(futureValueRequest, CalculatorRequestBody.class));
                        //===========================================================================================

                        CalculatorResponseBody futureValueResponse = wppService.POST_Calculator(
                                appProperties.OCBC_WPP_PATH_FUTURE_VALUE_DEPOSITO,
                                futureValueRequest);

                        if (futureValueResponse.RC == 0) {
                            //===========================================================================================
                            log.debug("rekomendasiProduct_carousel() futureValueResponse |=======|||debug|||=======| body futureValueResponse : {}", new Gson().toJson(futureValueResponse, CalculatorResponseBody.class));
                            //===========================================================================================

                            pesan = pesan.replace("#first_name#", transaction_Data.Name)
                                    .replace("#result#", new DecimalFormat(
                                            "IDR ###,###",
                                            DecimalFormatSymbols.getInstance(Locale.ENGLISH)).
                                            format(futureValueResponse.Result))
                                    .replace("#rate#", futureValueResponse.Rate);

                            //===========================================================================================
                            log.debug("rekomendasiProduct_carousel() pesan |=======|||debug|||=======| pesan : {}", pesan);
                            //===========================================================================================

                            String pathImg = pictureUtil.generatePic(pesan, contactId, product.Product_Type, futureValueResponse.Investment_Type);
                            pathImg = pathImg.replace("/var/www/html", "").trim();
                            button = new ButtonTemplate();
                            button.setPictureLink(appProperties.OCBC_WEBVIEW_BASE_URL + pathImg);
                            button.setPicturePath(appProperties.OCBC_WEBVIEW_BASE_URL + pathImg);
                            String title = "";

                            //===========================================================================================
                            log.debug("rekomendasiProduct_carousel() product.Product_Type |=======|||debug|||=======| product.Product_Type : {}", product.Product_Type);
                            //===========================================================================================

                            String subtitle = "";
                            switch (product.Product_Type) {
                                case "TK":
                                    title = "Taka";
                                    subtitle = "TAKA Bunga Pasti";
                                    break;
                                case "DP":
                                    title = "Deposito";
                                    subtitle = "";
                                    break;
                                case "MFA":
                                case "MFB":
                                    title = "Reksa Dana";
                                    subtitle = product.Product_Name;
                                    break;
                            }
                            button.setTitle(title);
                            button.setSubTitle(subtitle);
                            List<EasyMap> actions = new ArrayList<>();
                            EasyMap bookAction = new EasyMap();
                            bookAction.setName("Pilih");
                            bookAction.setValue(product.Product_ID
                                    + " & " + product.Product_Name
                                    + " & " + product.Average_Rate
                                    + " & " + product.Bad_Rate
                                    + " & " + product.Good_Rate
                                    + " & " + futureValueResponse.Investment_Type
                                    + " & " + futureValueResponse.Result
                                    + " & " + product.Product_Type
                                    + " & " + futureValueResponse.Rate);
                            actions.add(bookAction);
                            button.setButtonValues(actions);
                            buttonBuilder = new ButtonBuilder(button);
                            buttonList.add(buttonBuilder.build());
                        } else {

                        }

                    } else if (pathCalculator.equalsIgnoreCase(appProperties.OCBC_WPP_PATH_PRESENT_VALUE)) {// 

                        if ("MFA".equalsIgnoreCase(product.Product_Type)) {
                            pesan = "Kamu perlu investasi<br>"
                                    + "<b>#result#<b>"
                                    + "<br>sekali di awal <br><br>"
                                    + "Estimasi return <b>#rate#<b>/thn <br>";

                        } else if ("DP".equalsIgnoreCase(product.Product_Type)) {
                            pesan = "Kamu perlu menabung<br>"
                                    + "<b>#result#<b>"
                                    + "<br>sekali di awal<br><br>"
                                    + "Estimasi return <b>#rate#<b>/thn <br>";
                        } else {
                            pesan = "Kamu perlu menabung<br>"
                                    + "<b>#result#<b>"
                                    + "<br>sekali di awal <br><br>"
                                    + "Estimasi return <b>#rate#<b>/thn <br>";
                        }

                        CalculatorRequestBody presentValueRequest = new CalculatorRequestBody();
                        presentValueRequest.Ext_Reff_ID = contactId;
                        presentValueRequest.Channel_ID = 1;
                        presentValueRequest.Present_Value_Type = product.Product_Type.contains("MF") ? "MF" : "DP";
                        presentValueRequest.Payment = 0;
                        presentValueRequest.Target_Amount = nominal_amount - transaction_Data.Initial_Amount;
                        presentValueRequest.Yearly_Return_Code = matrixParameter.getYearlyReturnCode("PV", product.Product_Type);
                        presentValueRequest.Tenor = transaction_Data.Tenor;
                        presentValueRequest.Due_Date = 0;
                        presentValueRequest.Product_ID = product.Product_ID;

                        //===========================================================================================
                        log.debug("rekomendasiProduct_carousel() presentValueRequest |=======|||debug|||=======| Body presentValueRequest : {}", new Gson().toJson(presentValueRequest, CalculatorRequestBody.class));
                        //===========================================================================================

                        CalculatorResponseBody presentValueResponse = wppService.POST_Calculator(
                                appProperties.OCBC_WPP_PATH_PRESENT_VALUE,
                                presentValueRequest);

                        if (presentValueResponse.RC == 0) {
                            //===========================================================================================
                            log.debug("rekomendasiProduct_carousel() presentValueResponse |=======|||debug|||=======| Body presentValueResponse : {}", new Gson().toJson(presentValueResponse, CalculatorResponseBody.class));
                            //===========================================================================================

                            pesan = pesan.replace("#result#", new DecimalFormat("IDR ###,###",
                                    DecimalFormatSymbols.getInstance(Locale.ENGLISH)).format(presentValueResponse.Result))
                                    .replace("#rate#", presentValueResponse.Rate);

                            //===========================================================================================
                            log.debug("rekomendasiProduct_carousel() pesan |=======|||debug|||=======| pesan : {}", pesan);
                            //===========================================================================================

                            String pathImg = pictureUtil.generatePic(pesan, contactId, product.Product_Type, presentValueResponse.Investment_Type);
                            pathImg = pathImg.replace("/var/www/html", "").trim();
                            button = new ButtonTemplate();
                            button.setPictureLink(appProperties.OCBC_WEBVIEW_BASE_URL + pathImg);
                            button.setPicturePath(appProperties.OCBC_WEBVIEW_BASE_URL + pathImg);
                            String title = "";

                            //===========================================================================================
                            log.debug("rekomendasiProduct_carousel() product.Product_Type |=======|||debug|||=======| product.Product_Type : {}", product.Product_Type);
                            //===========================================================================================

                            String subtitle = "";
                            switch (product.Product_Type) {
                                case "TK":
                                    title = "Taka";
                                    subtitle = "TAKA Bunga Pasti";
                                    break;
                                case "DP":
                                    title = "Deposito";
                                    subtitle = "";
                                    break;
                                case "MFA":
                                case "MFB":
                                    title = "Reksa Dana";
                                    subtitle = product.Product_Name;
                                    break;
                            }
                            button.setTitle(title);
                            button.setSubTitle(subtitle);
                            List<EasyMap> actions = new ArrayList<>();
                            EasyMap bookAction = new EasyMap();
                            bookAction.setName("Pilih");
                            bookAction.setValue(product.Product_ID
                                    + " & " + product.Product_Name
                                    + " & " + product.Average_Rate
                                    + " & " + product.Bad_Rate
                                    + " & " + product.Good_Rate
                                    //                                    + " & " + transaction_Data.Investment_Type
                                    + " & " + presentValueResponse.Investment_Type
                                    + " & " + presentValueResponse.Result
                                    + " & " + product.Product_Type
                                    + " & " + presentValueResponse.Rate);
                            actions.add(bookAction);
                            button.setButtonValues(actions);
                            buttonBuilder = new ButtonBuilder(button);
                            buttonList.add(buttonBuilder.build());
                        } else {

                        }
                    } else if (pathCalculator.equalsIgnoreCase(appProperties.OCBC_WPP_PATH_TARGET_VALUE)) {
                        if ("MFA".equalsIgnoreCase(product.Product_Type)) {
                            pesan = "Kamu perlu investasi<br>"
                                    + "<b>#result#<b><br>setiap bulan <br><br>"
                                    + "Estimasi return <b>#rate#<b>/thn <br>";
                        } else {
                            pesan = "Kamu perlu menabung<br>"
                                    + "<b>#result#<b><br>setiap bulan <br><br>"
                                    + "Estimasi return <b>#rate#<b>/thn <br>";
                        }
                        CalculatorRequestBody targetValueRequest = new CalculatorRequestBody();
                        targetValueRequest.Ext_Reff_ID = contactId;
                        targetValueRequest.Channel_ID = 1;
                        targetValueRequest.Payment_Type = matrixParameter.getFutureValueType("TV", product.Product_Type);
                        targetValueRequest.Yearly_Return_Code = matrixParameter.getYearlyReturnCode("TV", product.Product_Type);
                        targetValueRequest.Tenor = transaction_Data.Tenor;
                        targetValueRequest.Initial_Amount = transaction_Data.Initial_Amount;
                        targetValueRequest.Pre_Calculated_Future_Value = transaction_Data.Target_Amount;
                        targetValueRequest.Future_Value_Code = matrixParameter.getFutureValueCode("TV", product.Product_Type);
                        if (transaction_Data.Life_Goal_ID == 2) {
                            targetValueRequest.EC_ID = transaction_Data.EC_ID;
                            targetValueRequest.Children_Age = transaction_Data.Children_Age;
                        } else {
                            targetValueRequest.EC_ID = 0;
                            targetValueRequest.Children_Age = 0;
                        }
                        targetValueRequest.Due_Date = 0;
                        targetValueRequest.Product_ID = product.Product_ID;
                        targetValueRequest.Risk_Profile_ID = transaction_Data.Risk_Profile_ID;

                        //===========================================================================================
                        log.debug("rekomendasiProduct_carousel() targetValueRequest |=======|||debug|||=======| Body targetValueRequest : {}", new Gson().toJson(targetValueRequest, CalculatorRequestBody.class));
                        //===========================================================================================

                        CalculatorResponseBody targetValueResponse = wppService.POST_Calculator(
                                appProperties.OCBC_WPP_PATH_TARGET_VALUE,
                                targetValueRequest);

                        if (targetValueResponse.RC == 0) {
                            //===========================================================================================
                            log.debug("rekomendasiProduct_carousel() targetValueResponse |=======|||debug|||=======| Body targetValueResponse : {}", new Gson().toJson(targetValueResponse, CalculatorResponseBody.class));
                            //===========================================================================================

                            pesan = pesan.replace("#result#", new DecimalFormat("IDR ###,###",
                                    DecimalFormatSymbols.getInstance(Locale.ENGLISH)).format(targetValueResponse.Result))
                                    .replace("#rate#", targetValueResponse.Rate);

                            //===========================================================================================
                            log.debug("rekomendasiProduct_carousel() pesan |=======|||debug|||=======| pesan : {}", pesan);
                            //===========================================================================================

                            String pathImg = pictureUtil.generatePic(pesan, contactId, product.Product_Type, targetValueResponse.Investment_Type);
                            pathImg = pathImg.replace("/var/www/html", "").trim();
                            button = new ButtonTemplate();
                            button.setPictureLink(appProperties.OCBC_WEBVIEW_BASE_URL + pathImg);
                            button.setPicturePath(appProperties.OCBC_WEBVIEW_BASE_URL + pathImg);
                            String title = "";

                            //===========================================================================================
                            log.debug("rekomendasiProduct_carousel() product.Product_Type |=======|||debug|||=======| product.Product_Type : {}", product.Product_Type);
                            //===========================================================================================

                            String subtitle = "";
                            switch (product.Product_Type) {
                                case "TK":
                                    title = "Taka";
                                    subtitle = "TAKA Bunga Pasti";
                                    break;
                                case "DP":
                                    title = "Deposito";
                                    subtitle = "";
                                    break;
                                case "MFA":
                                case "MFB":
                                    title = "Reksa Dana";
                                    subtitle = product.Product_Name;
                                    break;
                            }
                            button.setTitle(title);
                            button.setSubTitle(subtitle);
                            List<EasyMap> actions = new ArrayList<>();
                            EasyMap bookAction = new EasyMap();
                            bookAction.setName("Pilih");
                            bookAction.setValue(product.Product_ID
                                    + " & " + product.Product_Name
                                    + " & " + product.Average_Rate
                                    + " & " + product.Bad_Rate
                                    + " & " + product.Good_Rate
                                    //                                    + " & " + transaction_Data.Investment_Type
                                    + " & " + targetValueResponse.Investment_Type
                                    + " & " + targetValueResponse.Result
                                    + " & " + product.Product_Type
                                    + " & " + targetValueResponse.Rate);
                            actions.add(bookAction);
                            button.setButtonValues(actions);
                            buttonBuilder = new ButtonBuilder(button);
                            buttonList.add(buttonBuilder.build());
                        } else {

                        }
                    }
                }
            }
        }
        //==============================================================================
        //==============================================================================
        ButtonTemplate button2 = new ButtonTemplate();
        button2.setTitle("Reksa Dana Lainnya");
        button2.setSubTitle("Daftar seluruh produk investasi reksa dana.");
        button2.setPictureLink(appProperties.OCBC_PICTURES_ETC);
        button2.setPicturePath(appProperties.OCBC_PICTURES_ETC);
        List<EasyMap> actions2 = new ArrayList<>();
        EasyMap bookAction2 = new EasyMap();
        bookAction2.setName("Lihat dan Pilih");
        String reffId = contactId;
        int lifeGoalId = dataLeads1.getTransaction_Data()[0].getLife_Goal_ID();
        Long nominalAmount = nominal_amount;
        int tenor = dataLeads1.getTransaction_Data()[0].getTenor();
        int riskProfileId = dataLeads1.getTransaction_Data()[0].getRisk_Profile_ID();
        int investmentType = dataLeads1.getTransaction_Data()[0].getInvestment_Type();
        int EC = dataLeads1.getTransaction_Data()[0].getEC_ID();
        int Child_age = dataLeads1.getTransaction_Data()[0].getChildren_Age();
        int Tipe = 0;
        if (lifeGoalId == 1) {
            Tipe = 2;
        } else {
            Tipe = 1;
        }
        if (lifeGoalId == 2) {
            bookAction2.setValue(appProperties.OCBC_WEBVIEW_BASE_URL
                    + "/product/"
                    + reffId + "/"
                    + lifeGoalId + "/"
                    + nominalAmount + "/"
                    + Child_age + "/"
                    + riskProfileId + "/"
                    + Tipe + "/"
                    + investmentType + "/"
                    + transaction_Data.Initial_Amount + "/"
                    + EC + "/"
                    + dataLeads1.getTransaction_Data()[0].getName() + "?view=widget");
        } else {
            if (lifeGoalId == 1) {
                bookAction2.setValue(appProperties.OCBC_WEBVIEW_BASE_URL
                        + "/product/"
                        + reffId + "/"
                        + lifeGoalId + "/"
                        + nominalAmount + "/"
                        + tenor + "/"
                        + riskProfileId + "/"
                        + Tipe + "/"
                        + investmentType + "/"
                        + 0 + "/"
                        + 0 + "/"
                        + dataLeads1.getTransaction_Data()[0].getName() + "?view=widget");
            } else {
                bookAction2.setValue(appProperties.OCBC_WEBVIEW_BASE_URL
                        + "/product/"
                        + reffId + "/"
                        + lifeGoalId + "/"
                        + nominalAmount + "/"
                        + tenor + "/"
                        + riskProfileId + "/"
                        + Tipe + "/"
                        + investmentType + "/"
                        + transaction_Data.Initial_Amount + "/"
                        + 0 + "/"
                        + dataLeads1.getTransaction_Data()[0].getName() + "?view=widget");
            }
        }
        actions2.add(bookAction2);
        button2.setButtonValues(actions2);
        ButtonBuilder buttonBuilder2 = new ButtonBuilder(button2);
        buttonList.add(buttonBuilder2.build());
        CarouselBuilder carouselBuilder = new CarouselBuilder(buttonList);
        return carouselBuilder;
    }

    /**
     * digunakan untuk menampilkan menampilkan carousel rekomendasi product
     *
     * @param extensionRequest digunakan untuk memanggil data-data dari response
     * user yang sudah diolah oleh bot
     * @return json dengan format class extensionResult
     */
    public ExtensionResult rekomendasiProduct_carousel(ExtensionRequest extensionRequest) {
        Map<String, String> output = new HashMap<>();
        Map<String, String> clearEntities = new HashMap<>();
        ExtensionResult extensionResult = new ExtensionResult();
        StringBuilder respBuilder = new StringBuilder();

        String belumtau_reksadana = dialogUtil.getEasyMapValueByName(extensionRequest, "belumtau_reksadana");
        String intention = dialogUtil.getEasyMapValueByName(extensionRequest, "intention");
//        clearEntities = validationMethod.valStatusReksadana(belumtau_reksadana);
        extensionResult.setAgent(false);
        extensionResult.setRepeat(false);
        extensionResult.setSuccess(true);
        extensionResult.setNext(true);
        if (belumtau_reksadana.equalsIgnoreCase("lanjut") || belumtau_reksadana.equalsIgnoreCase("ok") || intention.equalsIgnoreCase("coba ulang")) {
            CarouselBuilder carouselBuilder = rekomendasiProduct_outputCarousel(extensionRequest);
            bubble = dialogUtil.CreateBubble("rekomendasiProduct_belumtauReksadana", 2, null);

            respBuilder.append(bubble)
                    .append(CONSTANT_SPLIT_SYNTAX)
                    .append(carouselBuilder.build());
            output.put(OUTPUT, respBuilder.toString());
            extensionResult.setValue(output);
        } else {
            clearEntities.put("belumtau_reksadana", "");
            clearEntities.put("rekomendasi", "");

            bubble = dialogUtil.CreateBubble("rekomendasiProduct_belumtauReksadana", 1, null);

            respBuilder.append(bubble);
            output.put(OUTPUT, respBuilder.toString());
            extensionResult.setValue(output);
        }
        extensionResult.setEntities(clearEntities);

        return extensionResult;
    }

    /**
     * digunakan untuk melakukan konfirmasi terhadap rekomendasi product yang
     * dipilih
     *
     * @param extensionRequest digunakan untuk memanggil data-data dari response
     * user yang sudah diolah oleh bot
     * @return json dengan format class extensionResult
     */
    public ExtensionResult rekomendasiProduct_carouselConfirmation(ExtensionRequest extensionRequest) {
        log.debug("rekomendasiProduct_carouselConfirmation() extension request: {}", new Gson().toJson(extensionRequest, ExtensionRequest.class));
        ExtensionResult extensionResult = new ExtensionResult();
        Map<String, String> output = new HashMap<>();
        Map<String, String> clearEntities = new HashMap<>();
        String rekomendasi = dialogUtil.getEasyMapValueByName(extensionRequest, "rekomendasi");
        extensionResult.setAgent(false);
        extensionResult.setRepeat(false);
        extensionResult.setSuccess(true);
        extensionResult.setNext(true);
        log.debug("rekomendasiProduct_carouselConfirmation() rekomendasi ==============| debug |============== rekomendasi: {}", rekomendasi);
        String[] data = rekomendasi.split(" & ");
        int lengthdata = data.length;
        log.debug(lengthdata);
        if (lengthdata > 1) {
            clearEntities.put("validasi_rekomendasi", "validasiRekomendasi");
            extensionResult.setEntities(clearEntities);
            extensionResult.setEntities(clearEntities);
            String bubbleSummarywebview = rekomendasiProduct_final(extensionRequest);
            output.put(OUTPUT, bubbleSummarywebview);
            extensionResult.setValue(output);
        } else {
            clearEntities.put("rekomendasi", "");
            clearEntities.put("validasi_rekomendasi", "");
            CarouselBuilder carouselBuilder = rekomendasiProduct_outputCarousel(extensionRequest);
            output.put(OUTPUT, carouselBuilder.build());
            extensionResult.setValue(output);
            extensionResult.setEntities(clearEntities);
        }
        return extensionResult;
    }

    /**
     * digunakan untuk melakukan validasi terhadap pilihan product yang sudah
     * diberikan terhadap user dan melakukan penyimpanan data kedalam variabel
     * global
     *
     * @param extensionRequest digunakan untuk memanggil data-data dari response
     * user yang sudah diolah oleh bot
     * @return json dengan format class extensionResult
     */
    public String rekomendasiProduct_final(ExtensionRequest extensionRequest) {
        log.debug("rekomendasiProduct_final() extension request: {}", new Gson().toJson(extensionRequest, ExtensionRequest.class));
        ExtensionResult extensionResult = new ExtensionResult();
        Map<String, String> output = new HashMap<>();
        String rekomendasi = dialogUtil.getEasyMapValueByName(extensionRequest, "rekomendasi");
        extensionResult.setAgent(false);
        extensionResult.setRepeat(false);
        extensionResult.setSuccess(true);
        extensionResult.setNext(true);
        StringBuilder respBuilder = new StringBuilder();
        log.debug("rekomendasiProduct_final() rekomendasi ==============| debug |============== rekomendasi: {}", rekomendasi);
        String[] data = rekomendasi.split(" & ");
        String product_ID = "";
        String investment_Type = "";
        Long rekom_terget_amount = null;
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
        // life goal id 
        // value 1 = Pertumbuhan Aset
        // value 2 = Pendidikan Kuliah Anak
        // value 3 = Lainnya
        // value 3 = Tidak Tahu
        String[] depositosplit = data[1].split("_");
        String splitdeposito = depositosplit[0];

        String[] takasplit = data[1].split(" ");
        String splittaka = takasplit[0];

        if (splitdeposito.equalsIgnoreCase("Deposito")) {
            data[1] = splitdeposito;
        } else if (splittaka.equalsIgnoreCase("Taka")) {
            data[1] = splittaka;

        }

        int life_goal_id = dataLeads1.getTransaction_Data()[0].getLife_Goal_ID();

        if (data[0].toLowerCase().contains("lainnya")) {

            if (dataLeads1.getTransaction_Data()[0].getLife_Goal_ID() == 1) {
                respBuilder.append("Produk : " + data[1].toUpperCase() + " \n"
                        + "Target/ estimasi pertumbuhan aset hingga : IDR " + validationMethod.replaceSparatorRp(data[2]) + " \n"
                        + "Estimasi return per tahun : " + data[3] + " \n");
                rekom_terget_amount = Long.parseLong(data[2]);
                investment_Type = dataLeads1.getTransaction_Data()[0].getInvestment_Type() + "";
                product_ID = data[4];
            } else {
                String mode;
                if ("0".equalsIgnoreCase(data[2])) {
                    mode = "Perbulan";
                } else {
                    mode = "Lumpsum";
                }
                data[2] = data[2].replace("Pertama", "Awal").replace("pertama", "Awal");
                respBuilder.append("Produk : " + data[1].toUpperCase() + " \n"
                        + "Mode : " + data[2] + " \n"
                        + "Jumlah : IDR " + validationMethod.replaceSparatorRp(data[3]) + " \n"
                        + "Estimasi return per tahun : " + data[4] + " \n");
                rekom_terget_amount = Long.parseLong(data[3]);
                if (data[2] == "Lumpsum") {
                    investment_Type = "1";
                } else {
                    investment_Type = "0";
                }
                product_ID = data[5];

            }
        } else {
            product_ID = data[0];
            investment_Type = "";
            rekom_terget_amount = Long.parseLong(data[6]);
            String estimasi_laba = "";
            if ("TK".equalsIgnoreCase(data[7]) || "DP".equalsIgnoreCase(data[7])) {
                estimasi_laba = data[8];
            } else {
                estimasi_laba = data[2] + "%";
            }
            if (dataLeads1.getTransaction_Data()[0].getLife_Goal_ID() == 1) {
                respBuilder.append("Produk : " + data[1].toUpperCase() + " \n"
                        + "Target/ estimasi pertumbuhan aset hingga : IDR " + validationMethod.replaceSparatorRp(data[6]) + " \n"
                        + "Estimasi return per tahun :  " + estimasi_laba + "\n");
//                if ("false".equalsIgnoreCase(data[5])) {
                if ("0".equalsIgnoreCase(data[5]) || "false".equalsIgnoreCase(data[5])) {
                    investment_Type = "0";
                } else {
                    investment_Type = "1";
                }
            } else {
                String mode = "";
                if ("0".equalsIgnoreCase(data[5]) || "false".equalsIgnoreCase(data[5])) {
//                if ("false".equalsIgnoreCase(data[5])) {
                    investment_Type = "0";
                    mode = "Perbulan";
                } else {
                    investment_Type = "1";
                    mode = "Lumpsum";
                }
                respBuilder.append("Produk : " + data[1].toUpperCase() + " \n"
                        + "Mode : " + mode + " \n"
                        + "Jumlah : IDR " + validationMethod.replaceSparatorRp(data[6]) + " \n"
                        + "Estimasi return per tahun :  " + estimasi_laba + "\n");
            }

        }

        long investment_amount = 0;
        long target_amount = 0;
        long initial_amount = 0;

        switch (life_goal_id) {
            case 1:
                investment_amount = dataLeads1.getTransaction_Data()[0].getInvestment_Amount();
                target_amount = rekom_terget_amount;
                initial_amount = 0;
                break;
            case 2:
                investment_amount = rekom_terget_amount;
                target_amount = dataLeads1.getTransaction_Data()[0].getTarget_Amount();
                initial_amount = dataLeads1.getTransaction_Data()[0].getInitial_Amount();
                break;
            default:
                investment_amount = rekom_terget_amount;
                target_amount = dataLeads1.getTransaction_Data()[0].getTarget_Amount();
                initial_amount = dataLeads1.getTransaction_Data()[0].getInitial_Amount();
                break;
        }
        //======================================================================
        //======================================================================
        log.debug("lifeGoalLainnya_validasiWebView() investment_amount =======| Debug |======= investment_amount: {}", investment_amount);
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
        transaction_Data.setLife_Goal_ID(dataLeads1.getTransaction_Data()[0].getLife_Goal_ID());
        transaction_Data.setFinancial_Goal(dataLeads1.getTransaction_Data()[0].getFinancial_Goal());
        transaction_Data.setInvestment_Type(Integer.parseInt(investment_Type));
        transaction_Data.setTenor(dataLeads1.getTransaction_Data()[0].getTenor());
        transaction_Data.setInvestment_Amount(investment_amount);
        transaction_Data.setTarget_Amount(target_amount);
        transaction_Data.setInitial_Amount(initial_amount);
        transaction_Data.setEC_ID(dataLeads1.getTransaction_Data()[0].getEC_ID());
        transaction_Data.setChildren_Age(dataLeads1.getTransaction_Data()[0].getChildren_Age());
        transaction_Data.setProduct_ID(Integer.parseInt(product_ID));
        transaction_Data.setDatetime_Start_Process(dataLeads1.getTransaction_Data()[0].getDatetime_Start_Process());
        transaction_Data.setDatetime_End_Process(dataLeads1.getTransaction_Data()[0].getDatetime_Start_Process());
        transaction_Data.setTime_Horizon(dataLeads1.getTransaction_Data()[0].getTime_Horizon());
        transaction_Data.setSource_of_Income(dataLeads1.getTransaction_Data()[0].getSource_of_Income());
        transaction_Data.setInvestment_Knowledge(dataLeads1.getTransaction_Data()[0].getInvestment_Knowledge());
        transaction_Data.setIncome_Usage(dataLeads1.getTransaction_Data()[0].getIncome_Usage());
        transaction_Data.setPotential_Loss(dataLeads1.getTransaction_Data()[0].getPotential_Loss());
        transaction_Data.setPlatform_Name("OCBC NISP Website");
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
        return respBuilder.toString();
    }

    /**
     * Untuk validasi inputan user setelah memilih Rekomendasi Product
     *
     * @param extensionRequest digunakan untuk memanggil data-data dari response
     * user yang sudah diolah oleh bot
     * @return json dengan format class extensionResult
     */
    public ExtensionResult validasi_konfirmasiRekomendasiProduk(ExtensionRequest extensionRequest) {
        log.debug("validasi_konfirmasiRekomendasiProduk() extension request: {}", new Gson().toJson(extensionRequest, ExtensionRequest.class));
        ExtensionResult extensionResult = new ExtensionResult();
        extensionResult.setAgent(false);
        extensionResult.setRepeat(false);
        extensionResult.setSuccess(true);
        extensionResult.setNext(true);
        Map<String, String> clearEntities = new HashMap<>();

        String konfirmasi_rekomendasiproduk = dialogUtil.getEasyMapValueByName(extensionRequest, "konfirmasi_rekomendasiproduk").toLowerCase();
        if (konfirmasi_rekomendasiproduk.equalsIgnoreCase("yakin")) {
        } else if (konfirmasi_rekomendasiproduk.equalsIgnoreCase("coba ulang")) {
            clearEntities.put("rekomendasi", "");
            clearEntities.put("validasi_rekomendasi", "");
            clearEntities.put("konfirmasi_rekomendasiproduk", "");
            clearEntities.put("validasi_konfirmasi", "");
            clearEntities.put("before_final", "");
            Map<String, String> output = new HashMap<>();
            output.put(OUTPUT, "");
            extensionResult.setValue(output);
        } else {
            clearEntities.put("konfirmasi_rekomendasiproduk", "");
            clearEntities.put("validasi_konfirmasi", "");
            Map<String, String> output = new HashMap<>();
            output.put(OUTPUT, "");
            extensionResult.setValue(output);
        }
        extensionResult.setEntities(clearEntities);
        return extensionResult;
    }

    /**
     * Digunakan untuk melakukan validasi terhadap action user
     *
     * @param extensionRequest digunakan untuk memanggil data-data dari response
     * user yang sudah diolah oleh bot
     * @return json dengan format class extensionResult
     */
    public ExtensionResult validasi_beforefinal(ExtensionRequest extensionRequest) {
        log.debug("validasi_beforefinal() extension request: {}", new Gson().toJson(extensionRequest, ExtensionRequest.class));
        ExtensionResult extensionResult = new ExtensionResult();
        extensionResult.setAgent(false);
        extensionResult.setRepeat(false);
        extensionResult.setSuccess(true);
        extensionResult.setNext(true);
        Map<String, String> clearEntities = new HashMap<>();

        String validasi_konfirmasi = dialogUtil.getEasyMapValueByName(extensionRequest, "validasi_konfirmasi").toLowerCase();
        if (validasi_konfirmasi.equalsIgnoreCase("get contact user")) {
            clearEntities.put("before_final", "Done");
        } else if (validasi_konfirmasi.equalsIgnoreCase("ulang rekomendasi")) {
            clearEntities.put("rekomendasi", "");
            clearEntities.put("validasi_rekomendasi", "");
            clearEntities.put("konfirmasi_rekomendasiproduk", "");
            clearEntities.put("validasi_konfirmasi", "");
            clearEntities.put("before_final", "");
            Map<String, String> output = new HashMap<>();
            output.put(OUTPUT, "");
            extensionResult.setValue(output);
        } else {
            clearEntities.put("validasi_konfirmasi", "");
            clearEntities.put("before_final", "");
            Map<String, String> output = new HashMap<>();
            output.put(OUTPUT, "");
            extensionResult.setValue(output);
        }
        extensionResult.setEntities(clearEntities);
        return extensionResult;
    }

    /**
     * Untuk menampilkan pertanyaan terakhir yang akan langsung direct ke dialog
     * Get Contact User
     *
     * @param extensionRequest digunakan untuk memanggil data-data dari response
     * user yang sudah diolah oleh bot
     * @return json dengan format class extensionResult
     */
    public ExtensionResult beforefinal_konfirmasi_rekomendasiproduk(ExtensionRequest extensionRequest) {
        ExtensionResult extensionResult = new ExtensionResult();
        extensionResult.setAgent(false);
        extensionResult.setRepeat(false);
        extensionResult.setSuccess(true);
        extensionResult.setNext(true);
        Map<String, String> clearEntities = new HashMap<>();

        String konfirmasi_rekomendasiproduk = dialogUtil.getEasyMapValueByName(extensionRequest, "konfirmasi_rekomendasiproduk").toLowerCase();
        if (konfirmasi_rekomendasiproduk.equalsIgnoreCase("get contact user")) {
            clearEntities.put("before_final", "Done");
        } else {
            clearEntities.put("konfirmasi_rekomendasiproduk", "");
            clearEntities.put("before_final", "");
            Map<String, String> output = new HashMap<>();
            output.put(OUTPUT, "");
            extensionResult.setValue(output);
        }
        extensionResult.setEntities(clearEntities);
        return extensionResult;
    }

}
//ratting_commen = String 200 chat
//ratting = int 

