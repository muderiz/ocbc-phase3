/**
 * Copyright (c) 2014 InMotion Innovation Technology. All Rights Reserved. <BR>
 * <BR>
 * This software contains confidential and proprietary information of InMotion
 * Innovation Technology. ("Confidential Information").<BR>
 * <BR>
 * Such Confidential Information shall not be disclosed and it shall only be
 * used in accordance with the terms of the license agreement entered into with
 * IMI; other than in accordance with the written permission of IMI. <BR>
 *
 *
 */
package com.imi.dolphin.sdkwebservice.service;

import com.google.common.collect.HashBiMap;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imi.dolphin.sdkwebservice.builder.ButtonBuilder;
import com.imi.dolphin.sdkwebservice.builder.CarouselBuilder;
import com.imi.dolphin.sdkwebservice.builder.FormBuilder;
import com.imi.dolphin.sdkwebservice.builder.ImageBuilder;
import com.imi.dolphin.sdkwebservice.builder.QuickReplyBuilder;
import com.imi.dolphin.sdkwebservice.builder.VideoBuilder;
import static com.imi.dolphin.sdkwebservice.facade.LainnyaService.OUTPUT;
import com.imi.dolphin.sdkwebservice.model.AdditionalFieldOCBC;
import com.imi.dolphin.sdkwebservice.model.ButtonTemplate;
import com.imi.dolphin.sdkwebservice.model.Contact;
import com.imi.dolphin.sdkwebservice.model.DataLeads;
import com.imi.dolphin.sdkwebservice.model.EasyMap;
import com.imi.dolphin.sdkwebservice.model.ExtensionRequest;
import com.imi.dolphin.sdkwebservice.model.ExtensionResult;
import com.imi.dolphin.sdkwebservice.model.MailModel;
import com.imi.dolphin.sdkwebservice.model.RiskProfileResponse;
import com.imi.dolphin.sdkwebservice.model.Transaction_Data;
import com.imi.dolphin.sdkwebservice.model.UserToken;
import com.imi.dolphin.sdkwebservice.param.ParamSdk;
import com.imi.dolphin.sdkwebservice.property.AppProperties;
import com.imi.dolphin.sdkwebservice.util.DialogUtil;
import com.imi.dolphin.sdkwebservice.util.OkHttpUtil;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.Date;
import javax.imageio.ImageIO;

import okhttp3.Request;
import okhttp3.Response;
import org.apache.commons.io.FileUtils;
import org.springframework.scheduling.annotation.Scheduled;

/**
 *
 * @author reja
 *
 */
@Service
public class ServiceImp implements IService {

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
     * Get parameter value from request body parameter
     *
     * @param extensionRequest
     * @param name
     * @return
     */
    private String getEasyMapValueByName(ExtensionRequest extensionRequest, String name) {
        log.debug("getEasyMapValueByName() extension request: {} name: {}", extensionRequest, name);
        EasyMap easyMap = extensionRequest.getParameters().stream().filter(x -> x.getName().equals(name)).findAny()
                .orElse(null);
        if (easyMap != null) {
            return easyMap.getValue();
        }
        return "";
    }

//    @Scheduled(fixedRate = 43200000)
//    public void scheduleTaskWithFixedRate() {
//        log.debug("Fixed Rate Task :: Execution Time - {}", dateTimeFormatter.format(LocalDateTime.now()));
//        String dir = "./percobaan/";
//        File f = new File(dir);
//
//        String[] daftar = f.list();
//        java.util.Arrays.sort(daftar);
//
//        System.out.println("File dan direktori dalam ./percobaan");
//        System.out.println();
//
//        for (int i = 0; i < daftar.length; i++) {
//            File fTemp = new File(dir + "/" + daftar[i]);
//            if (fTemp.isDirectory()) {
//                System.out.println(daftar[i] + "\t\t<DIR>");
//            } else {
//                System.out.println(daftar[i]);
//                String dir2 = dir + daftar[i];
//                File hapus = new File(dir2);
//                hapus.delete();
//            }
//        }
//    }

    /*
	 * Sample Srn status with static result
	 * 
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.imi.dolphin.sdkwebservice.service.IService#getSrnResult(com.imi.dolphin.
	 * sdkwebservice.model.ExtensionRequest)
     */
    @Override
    public ExtensionResult getSrnResult(ExtensionRequest extensionRequest) {
        log.debug("getSrnResult() extension request: {}", extensionRequest);
        ExtensionResult extensionResult = new ExtensionResult();
        extensionResult.setAgent(false);
        extensionResult.setRepeat(false);
        extensionResult.setSuccess(true);
        extensionResult.setNext(true);
        Map<String, String> output = new HashMap<>();
        StringBuilder respBuilder = new StringBuilder();
        respBuilder.append(
                "20-July-2018 16:10:32 Ahmad Mahatir Ridwan - PIC sudah onsite cek problem(printer nyala-mati)\n");
        respBuilder.append("PIC troubleshoot. restart printer(NOK), ganti kabel power(NOK)\n");
        respBuilder.append("PIC akan eskalasi ke vendor terkait.");
        output.put(OUTPUT, respBuilder.toString());
        extensionResult.setValue(output);
        return extensionResult;
    }

    /*
	 * Sample Customer Info with static result
	 * 
	 * (non-Javadoc)
	 * 
	 * @see com.imi.dolphin.sdkwebservice.service.IService#getCustomerInfo(com.imi.
	 * dolphin.sdkwebservice.model.ExtensionRequest)
     */
    @Override
    public ExtensionResult getCustomerInfo(ExtensionRequest extensionRequest) {
        log.debug("getCustomerInfo() extension request: {}", extensionRequest);
        String account = getEasyMapValueByName(extensionRequest, "akun");
        String name = getEasyMapValueByName(extensionRequest, "name");
        Map<String, String> output = new HashMap<>();
        StringBuilder respBuilder = new StringBuilder();
        if (account.substring(0, 1).equals("1")) {
            respBuilder.append("Ticket Number : " + extensionRequest.getIntent().getTicket().getTicketNumber() + "\n");
            respBuilder.append(" Data Customer Account " + account + "\n");
            respBuilder.append("Nama: " + name + "\n");
            respBuilder.append("Setoran tiap bulan : Rp. 500,000\n");
            respBuilder.append("Jatuh tempo berikutnya : 15 Agustus 2018");
        } else {
            respBuilder.append("Ticket Number : " + extensionRequest.getIntent().getTicket().getTicketNumber() + "\n");
            respBuilder.append(appProperties.getFormId() + " Data Customer Account " + account + "\n");
            respBuilder.append("Nama: " + name + "\n");
            respBuilder.append("Setoran tiap bulan : Rp. 1,000,000\n");
            respBuilder.append("Jatuh tempo berikutnya : 27 Agustus 2018");
        }
        ExtensionResult extensionResult = new ExtensionResult();
        extensionResult.setAgent(false);
        extensionResult.setRepeat(false);
        extensionResult.setSuccess(true);
        extensionResult.setNext(true);

        output.put(OUTPUT, respBuilder.toString());
        extensionResult.setValue(output);
        return extensionResult;
    }

    /*
	 * Modify Customer Name Entity
	 * 
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.imi.dolphin.sdkwebservice.service.IService#clearCustomerName(com.imi.
	 * dolphin.sdkwebservice.model.ExtensionRequest)
     */
    @Override
    public ExtensionResult modifyCustomerName(ExtensionRequest extensionRequest) {
        log.debug("modifyCustomerName() extension request: {}", extensionRequest);
        ExtensionResult extensionResult = new ExtensionResult();
        extensionResult.setAgent(false);
        extensionResult.setRepeat(false);
        extensionResult.setSuccess(true);
        extensionResult.setNext(true);

        Map<String, String> clearEntities = new HashMap<>();
        String name = getEasyMapValueByName(extensionRequest, "name");
        if (name.equalsIgnoreCase("reja")) {
            clearEntities.put("name", "budi");
            extensionResult.setEntities(clearEntities);
        }
        return extensionResult;
    }

    /*
	 * Sample Product info with static value
	 * 
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.imi.dolphin.sdkwebservice.service.IService#getProductInfo(com.imi.dolphin
	 * .sdkwebservice.model.ExtensionRequest)
     */
    @Override
    public ExtensionResult getProductInfo(ExtensionRequest extensionRequest) {
        log.debug("getProductInfo() extension request: {}", extensionRequest);
        String model = getEasyMapValueByName(extensionRequest, "model");
        String type = getEasyMapValueByName(extensionRequest, "type");

        Map<String, String> output = new HashMap<>();
        StringBuilder respBuilder = new StringBuilder();

        respBuilder.append("Untuk harga mobil " + model + " tipe " + type + " adalah 800,000,000\n");
        respBuilder.append("Jika kak {customer_name} tertarik, bisa klik tombol dibawah ini. \n");
        respBuilder.append("Maka nanti live agent kami akan menghubungi kakak ;)");

        ExtensionResult extensionResult = new ExtensionResult();
        extensionResult.setAgent(false);
        extensionResult.setRepeat(false);
        extensionResult.setSuccess(true);
        extensionResult.setNext(true);

        output.put(OUTPUT, respBuilder.toString());
        extensionResult.setValue(output);
        return extensionResult;
    }

    /*
	 * Get messages from third party service
	 * 
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.imi.dolphin.sdkwebservice.service.IService#getMessageBody(com.imi.dolphin
	 * .sdkwebservice.model.ExtensionRequest)
     */
    @Override
    public ExtensionResult getMessageBody(ExtensionRequest extensionRequest) {
        log.debug("getMessageBody() extension request: {}", extensionRequest);
        Map<String, String> output = new HashMap<>();
        StringBuilder respBuilder = new StringBuilder();

        try {
            okHttpUtil.init(true);
            Request request = new Request.Builder().url("https://jsonplaceholder.typicode.com/comments").get().build();
            Response response = okHttpUtil.getClient().newCall(request).execute();

            JSONArray jsonArray = new JSONArray(response.body().string());

            JSONObject jsonObject = jsonArray.getJSONObject(0);
            String message = jsonObject.getString("body");
            respBuilder.append(message);
        } catch (Exception e) {
            log.debug("getMessageBody() {}", e.getMessage());
        }

        ExtensionResult extensionResult = new ExtensionResult();
        extensionResult.setAgent(false);
        extensionResult.setRepeat(false);
        extensionResult.setSuccess(true);
        extensionResult.setNext(true);

        output.put(OUTPUT, respBuilder.toString());
        extensionResult.setValue(output);
        return extensionResult;
    }

    /*
	 * Generate quick replies output
	 * 
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.imi.dolphin.sdkwebservice.service.IFormService#getQuickReplies(com.imi.
	 * dolphin.sdkwebservice.model.ExtensionRequest)
     */
    @Override
    public ExtensionResult getQuickReplies(ExtensionRequest extensionRequest) {
        log.debug("getQuickReplies() extension request: {}", extensionRequest);
        Map<String, String> output = new HashMap<>();
        Map<String, String> map = new HashMap<>();
        map.put("Hello", "World");
        map.put("Java", "Coffee");

        QuickReplyBuilder quickReplyBuilder = new QuickReplyBuilder.Builder("Hello").addAll(map).build();
        output.put(OUTPUT, quickReplyBuilder.string());
        ExtensionResult extensionResult = new ExtensionResult();
        extensionResult.setAgent(false);
        extensionResult.setRepeat(false);
        extensionResult.setSuccess(true);
        extensionResult.setNext(true);
        extensionResult.setValue(output);
        return extensionResult;
    }

    /*
	 * Generate Forms
	 *
	 * (non-Javadoc)
	 * 
	 * @see com.imi.dolphin.sdkwebservice.service.IService#getForms(com.imi.dolphin.
	 * sdkwebservice.model.ExtensionRequest)
     */
    @Override
    public ExtensionResult getForms(ExtensionRequest extensionRequest) {
        log.debug("getForms() extension request: {}", extensionRequest);
        Map<String, String> output = new HashMap<>();
        FormBuilder formBuilder = new FormBuilder(appProperties.getFormId());

        ButtonTemplate button = new ButtonTemplate();
        button.setTitle("Title is here");
        button.setSubTitle("Subtitle is here");
        button.setPictureLink(ParamSdk.SAMPLE_IMAGE_PATH);
        button.setPicturePath(ParamSdk.SAMPLE_IMAGE_PATH);
        List<EasyMap> actions = new ArrayList<>();
        EasyMap bookAction = new EasyMap();
        bookAction.setName("Label here");
        bookAction.setValue(formBuilder.build());
        actions.add(bookAction);
        button.setButtonValues(actions);
        ButtonBuilder buttonBuilder = new ButtonBuilder(button);

        output.put(OUTPUT, buttonBuilder.build());
        ExtensionResult extensionResult = new ExtensionResult();
        extensionResult.setAgent(false);
        extensionResult.setRepeat(false);
        extensionResult.setSuccess(true);
        extensionResult.setNext(true);
        extensionResult.setValue(output);
        return extensionResult;
    }

    /*
	 * Generate buttons output
	 * 
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.imi.dolphin.sdkwebservice.service.IService#getButtons(com.imi.dolphin.
	 * sdkwebservice.model.ExtensionRequest)
     */
    @Override
    public ExtensionResult getButtons(ExtensionRequest extensionRequest) {
        log.debug("getButtons() extension request: {}", extensionRequest);
        Map<String, String> output = new HashMap<>();

        ButtonTemplate button = new ButtonTemplate();
        button.setTitle(ParamSdk.SAMPLE_TITLE);
        button.setSubTitle(ParamSdk.SAMPLE_SUBTITLE);
        button.setPictureLink(ParamSdk.SAMPLE_IMAGE_PATH);
        button.setPicturePath(ParamSdk.SAMPLE_IMAGE_PATH);
        List<EasyMap> actions = new ArrayList<>();
        EasyMap bookAction = new EasyMap();
        bookAction.setName(ParamSdk.SAMPLE_LABEL);
        bookAction.setValue(ParamSdk.SAMPLE_PAYLOAD);
        actions.add(bookAction);
        button.setButtonValues(actions);

        ButtonBuilder buttonBuilder = new ButtonBuilder(button);
        output.put(OUTPUT, buttonBuilder.build());

        ExtensionResult extensionResult = new ExtensionResult();
        extensionResult.setAgent(false);
        extensionResult.setRepeat(false);
        extensionResult.setSuccess(true);
        extensionResult.setNext(true);
        extensionResult.setValue(output);
        return extensionResult;
    }

    /*
	 * Generate Carousel
	 * 
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.imi.dolphin.sdkwebservice.service.IService#getCarousel(com.imi.dolphin.
	 * sdkwebservice.model.ExtensionRequest)
     */
    @Override
    public ExtensionResult getCarousel(ExtensionRequest extensionRequest) {
        log.debug("getCarousel() extension request: {}", extensionRequest);
        Map<String, String> output = new HashMap<>();

        List<String> buttonList = new ArrayList<>();
        ButtonTemplate button;
        ButtonBuilder buttonBuilder;
        for (int i = 0; i < 6; i++) {
            button = new ButtonTemplate();
            button.setPictureLink(ParamSdk.SAMPLE_IMAGE_PATH);
            button.setPicturePath(ParamSdk.SAMPLE_IMAGE_PATH);
            button.setTitle(ParamSdk.SAMPLE_TITLE.concat(String.valueOf(i)));
            button.setSubTitle(ParamSdk.SAMPLE_SUBTITLE.concat(String.valueOf(i)));
            List<EasyMap> actions = new ArrayList<>();
            EasyMap bookAction = new EasyMap();
            bookAction.setName(ParamSdk.SAMPLE_LABEL);
            bookAction.setValue(ParamSdk.SAMPLE_PAYLOAD);
            actions.add(bookAction);
            button.setButtonValues(actions);
            buttonBuilder = new ButtonBuilder(button);
            buttonList.add(buttonBuilder.build());
        }
        CarouselBuilder carouselBuilder = new CarouselBuilder(buttonList);
        output.put(OUTPUT, carouselBuilder.build());

        ExtensionResult extensionResult = new ExtensionResult();
        extensionResult.setAgent(false);
        extensionResult.setRepeat(false);
        extensionResult.setSuccess(true);
        extensionResult.setNext(true);
        extensionResult.setValue(output);
        return extensionResult;
    }

    /*
	 * Transfer ticket to agent
	 * 
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.imi.dolphin.sdkwebservice.service.IService#doTransferToAgent(com.imi.
	 * dolphin.sdkwebservice.model.ExtensionRequest)
     */
    @Override
    public ExtensionResult doTransferToAgent(ExtensionRequest extensionRequest) {
        log.debug("doTransferToAgent() extension request: {}", extensionRequest);
        ExtensionResult extensionResult = new ExtensionResult();
        extensionResult.setAgent(true);
        extensionResult.setRepeat(false);
        extensionResult.setSuccess(true);
        extensionResult.setNext(false);
        return extensionResult;
    }

    /*
	 * Send Location
	 * 
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.imi.dolphin.sdkwebservice.service.IService#doSendLocation(com.imi.dolphin
	 * .sdkwebservice.model.ExtensionRequest)
     */
    @Override
    public ExtensionResult doSendLocation(ExtensionRequest extensionRequest) {
        log.debug("doSendLocation() extension request: {}", extensionRequest);
        Map<String, String> output = new HashMap<>();
        QuickReplyBuilder quickReplyBuilder = new QuickReplyBuilder.Builder("Kirim lokasi kakak ya")
                .add("Location", "location").build();
        output.put(OUTPUT, quickReplyBuilder.string());
        ExtensionResult extensionResult = new ExtensionResult();
        extensionResult.setAgent(false);
        extensionResult.setRepeat(false);
        extensionResult.setSuccess(true);
        extensionResult.setNext(true);
        extensionResult.setValue(output);
        return extensionResult;
    }

    /*
	 * Generate Image
	 * 
	 * (non-Javadoc)
	 * 
	 * @see com.imi.dolphin.sdkwebservice.service.IService#getImage(com.imi.dolphin.
	 * sdkwebservice.model.ExtensionRequest)
     */
    @Override
    public ExtensionResult getImage(ExtensionRequest extensionRequest) {
        log.debug("getImage() extension request: {}", extensionRequest);
        Map<String, String> output = new HashMap<>();

        ButtonTemplate image = new ButtonTemplate();
        image.setPictureLink(ParamSdk.SAMPLE_IMAGE_PATH);
        image.setPicturePath(ParamSdk.SAMPLE_IMAGE_PATH);

        ImageBuilder imageBuilder = new ImageBuilder(image);
        output.put(OUTPUT, imageBuilder.build());

        ExtensionResult extensionResult = new ExtensionResult();
        extensionResult.setAgent(false);
        extensionResult.setRepeat(false);
        extensionResult.setSuccess(true);
        extensionResult.setNext(true);
        extensionResult.setValue(output);
        return extensionResult;
    }

    /*
	 * Split bubble chat conversation
	 * 
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.imi.dolphin.sdkwebservice.service.IService#getSplitConversation(com.imi.
	 * dolphin.sdkwebservice.model.ExtensionRequest)
     */
    @Override
    public ExtensionResult getSplitConversation(ExtensionRequest extensionRequest) {
        log.debug("getSplitConversation() extension request: {}", extensionRequest);
        String firstLine = "Terima kasih {customer_name}";
        String secondLine = "Data telah kami terima dan agent kami akan proses terlebih dahulu ya kak";
        Map<String, String> output = new HashMap<>();
        output.put(OUTPUT, firstLine + ParamSdk.SPLIT_CHAT + secondLine);

        ExtensionResult extensionResult = new ExtensionResult();
        extensionResult.setAgent(false);
        extensionResult.setRepeat(false);
        extensionResult.setSuccess(true);
        extensionResult.setNext(true);
        extensionResult.setValue(output);
        return extensionResult;
    }

    /*
	 * Send mail configuration on application.properties file
	 * 
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.imi.dolphin.sdkwebservice.service.IService#doSendMail(com.imi.dolphin.
	 * sdkwebservice.model.ExtensionRequest)
     */
    @Override
    public ExtensionResult doSendMail(ExtensionRequest extensionRequest) {
        log.debug("doSendMail() extension request: {}", extensionRequest);
        String recipient = getEasyMapValueByName(extensionRequest, "recipient");
        MailModel mailModel = new MailModel(recipient, "3Dolphins SDK Mail Subject", "3Dolphins SDK mail content");
        String sendMailResult = svcMailService.sendMail(mailModel);

        Map<String, String> output = new HashMap<>();
        output.put(OUTPUT, sendMailResult);
        ExtensionResult extensionResult = new ExtensionResult();
        extensionResult.setAgent(false);
        extensionResult.setRepeat(false);
        extensionResult.setSuccess(true);
        extensionResult.setNext(true);
        extensionResult.setValue(output);
        return extensionResult;
    }

    /*(non-Javadoc)
	 * @see com.imi.dolphin.sdkwebservice.service.IService#getDolphinResponse(com.imi.dolphin.sdkwebservice.model.ExtensionRequest)
     */
    @Override
    public ExtensionResult getDolphinResponse(ExtensionRequest extensionRequest) {
        userToken = svcDolphinService.getUserToken(userToken);
        log.debug("getDolphinResponse() extension request: {} user token: {}", extensionRequest, userToken);
        String contactId = extensionRequest.getIntent().getTicket().getContactId();
        Contact contact = svcDolphinService.getCustomer(userToken, contactId);
        contact.setContactFirstName("YOUR NAME");
        String status_pernikahan = null;
        String penghasilan = null;
        String pengalaman_berinvestasi = null;
        List<String> listData = new ArrayList<>();

        listData.add("{penghasilan = " + penghasilan + "}");
        listData.add("{status pernikahan = " + status_pernikahan + "}");
        listData.add("{pengalaman berinvestasi = " + pengalaman_berinvestasi + "}");

        contact.setAdditionalField(listData);
        contact = svcDolphinService.updateCustomer(userToken, contact);
        Map<String, String> output = new HashMap<>();
        output.put(OUTPUT, "PING " + contact.getContactFirstName());
        ExtensionResult extensionResult = new ExtensionResult();
        extensionResult.setAgent(false);
        extensionResult.setRepeat(false);
        extensionResult.setSuccess(true);
        extensionResult.setNext(true);
        extensionResult.setValue(output);
        return extensionResult;
    }

    public String getContactID(ExtensionRequest extensionRequest) {
        userToken = svcDolphinService.getUserToken(userToken);
        log.debug("getContactID() extension request: {}", new Gson().toJson(extensionRequest, ExtensionRequest.class));
        String contactId = extensionRequest.getIntent().getTicket().getContactId();
        return contactId;
    }

    // setName Phase 1
//    @Override
//    public ExtensionResult setName(ExtensionRequest extensionRequest) {
//        userToken = svcDolphinService.getUserToken(userToken);
//        log.debug("setName() extension request: {}", new Gson().toJson(extensionRequest, ExtensionRequest.class));
//        String contactId = extensionRequest.getIntent().getTicket().getContactId();
//        String nama = getEasyMapValueByName(extensionRequest, "nama");
//        Contact contact = svcDolphinService.getCustomer(userToken, contactId);
//        contact.setContactFirstName(nama);
//
//        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date date = new Date();
//        //System.out.println(dateFormat.format(date)); //2016/11/16 12:08:43 //2019-01-18 08:12:12
//
//        ArrayList<Transaction_Data> list_transaction_Data = new ArrayList<>();
//        Transaction_Data transaction_Data = new Transaction_Data();
//        transaction_Data.setName(nama);
//        transaction_Data.setAge(0);
//        transaction_Data.setEmail("null");
//        transaction_Data.setPhone_Number("null");
//        transaction_Data.setLocation("null");
//        transaction_Data.setIs_Married(0);
//        transaction_Data.setIs_Already_Invest(0);
//        transaction_Data.setFinancial_Info_Income_Range_ID(0);
//        transaction_Data.setFinancial_Info_Saved_Income_ID(0);
//        transaction_Data.setRisk_Profile_ID(0);
//        transaction_Data.setLife_Goal_ID(0);
//        transaction_Data.setFinancial_Goal("null");
//        transaction_Data.setInvestment_Type(3);
//        transaction_Data.setTenor(0);
//        transaction_Data.setInvestment_Amount(0);
//        transaction_Data.setTarget_Amount(0);
//        transaction_Data.setInitial_Amount(0);
//        transaction_Data.setEC_ID(0);
//        transaction_Data.setChildren_Age(0);
//        transaction_Data.setProduct_ID(0);
//        transaction_Data.setDatetime_Start_Process(dateFormat.format(date));
//        transaction_Data.setDatetime_End_Process("");
//        transaction_Data.setTime_Horizon("");
//        transaction_Data.setSource_of_Income("2.a");
//        transaction_Data.setInvestment_Knowledge("3.a");
//        transaction_Data.setIncome_Usage("4.a");
//        transaction_Data.setPotential_Loss("5.a");
//        transaction_Data.setPlatform_Name("OCBC NISP Website");
//        transaction_Data.setRating(0);
//        transaction_Data.setRating_Comment("Default");
//        list_transaction_Data.add(transaction_Data);
//
//        Transaction_Data[] arrTransaction_data = new Transaction_Data[1];
//        arrTransaction_data = list_transaction_Data.toArray(arrTransaction_data);
//        DataLeads dataLeads = new DataLeads();
//        dataLeads.setChannel_ID("1");
//        dataLeads.setExt_Reff_ID(contactId);
//        dataLeads.setTransaction_Data(arrTransaction_data);
//
//        List<String> listData = new ArrayList<>();
//        listData.add("" + new Gson().toJson(dataLeads, DataLeads.class) + "");
//        contact.setAdditionalField(listData);
//
//        contact = svcDolphinService.updateCustomer(userToken, contact);
//        Map<String, String> output = new HashMap<>();
//        output.put(OUTPUT, "berhasil bro di method SETNAME");
//        ExtensionResult extensionResult = new ExtensionResult();
//        extensionResult.setAgent(false);
//        extensionResult.setRepeat(false);
//        extensionResult.setSuccess(true);
//        extensionResult.setNext(true);
//        extensionResult.setValue(output);
//        return extensionResult;
//    }
    /**
     * setName Phase 2
     *
     * @param extensionRequest
     * @return
     */
    @Override
    public ExtensionResult setName(ExtensionRequest extensionRequest) {
        userToken = svcDolphinService.getUserToken(userToken);
        log.debug("setName() extension request: {}", new Gson().toJson(extensionRequest, ExtensionRequest.class));
        String contactId = extensionRequest.getIntent().getTicket().getContactId();
        String nama = "kamu";
        Contact contact = svcDolphinService.getCustomer(userToken, contactId);

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        //System.out.println(dateFormat.format(date)); //2016/11/16 12:08:43 //2019-01-18 08:12:12

        ArrayList<Transaction_Data> list_transaction_Data = new ArrayList<>();
        Transaction_Data transaction_Data = new Transaction_Data();
        transaction_Data.setName(nama);
        transaction_Data.setAge(0);
        transaction_Data.setEmail("null");
        transaction_Data.setPhone_Number("null");
        transaction_Data.setLocation("null");
        transaction_Data.setIs_Married(0);
        transaction_Data.setIs_Already_Invest(0);
        transaction_Data.setFinancial_Info_Income_Range_ID(1);
        transaction_Data.setFinancial_Info_Saved_Income_ID(1);
        transaction_Data.setRisk_Profile_ID(0);
        transaction_Data.setLife_Goal_ID(0);
        transaction_Data.setFinancial_Goal("null");
        transaction_Data.setInvestment_Type(3);
        transaction_Data.setTenor(0);
        transaction_Data.setInvestment_Amount(0);
        transaction_Data.setTarget_Amount(0);
        transaction_Data.setInitial_Amount(0);
        transaction_Data.setEC_ID(0);
        transaction_Data.setChildren_Age(0);
        transaction_Data.setProduct_ID(0);
        transaction_Data.setDatetime_Start_Process(dateFormat.format(date));
        transaction_Data.setDatetime_End_Process("");
        transaction_Data.setTime_Horizon("1.a");
        transaction_Data.setSource_of_Income("2.a");
        transaction_Data.setInvestment_Knowledge("3.a");
        transaction_Data.setIncome_Usage("4.a");
        transaction_Data.setPotential_Loss("5.a");
        transaction_Data.setPlatform_Name("OCBC NISP Website");
        transaction_Data.setRating(0);
        transaction_Data.setRating_Comment("Default");
        transaction_Data.setStatus_Nasabah("Default");
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
        Map<String, String> output = new HashMap<>();
        output.put(OUTPUT, "berhasil bro di method SETNAME");
        ExtensionResult extensionResult = new ExtensionResult();
        extensionResult.setAgent(false);
        extensionResult.setRepeat(false);
        extensionResult.setSuccess(true);
        extensionResult.setNext(true);
        extensionResult.setValue(output);
        return extensionResult;
    }

    @Override
    public ExtensionResult validasiNama(ExtensionRequest extensionRequest) {
        log.debug("validasiNama() extension request: {}", new Gson().toJson(extensionRequest, ExtensionRequest.class));
        ExtensionResult extensionResult = new ExtensionResult();
        Map<String, String> output = new HashMap<>();
        Map<String, String> clearEntities = new HashMap<>();
        String nama = getEasyMapValueByName(extensionRequest, "nama");

        //        ValidationMethod validationMethod = new ValidationMethod();
        boolean status = validationMethod.valName(nama);
        extensionResult.setAgent(false);
        extensionResult.setRepeat(false);
        extensionResult.setSuccess(true);
        extensionResult.setNext(true);
        StringBuilder respBuilder = new StringBuilder();
        if (status == false) {
            clearEntities.put("nama", "");
            clearEntities.put("validasi_nama", "");
            extensionResult.setEntities(clearEntities);
            respBuilder.append("Hmm, sepertinya nama yang Kamu masukkan salah. Tolong ketik nama yang benar ya 😄 \n");
            respBuilder.append("Siapakah nama kamu?");
            output.put(OUTPUT, respBuilder.toString());
            extensionResult.setValue(output);
        } else {
            clearEntities.put("validasi_nama", "validasiNama");
            extensionResult.setEntities(clearEntities);
        }
//        extensionResult.setValue(output);
        return extensionResult;
    }

    @Override
    public ExtensionResult entitasNama(ExtensionRequest extensionRequest) {
        log.debug("entitasNama() extension request: {}", new Gson().toJson(extensionRequest, ExtensionRequest.class));
        ExtensionResult extensionResult = new ExtensionResult();
        extensionResult.setAgent(false);
        extensionResult.setRepeat(false);
        extensionResult.setSuccess(true);
        extensionResult.setNext(true);
        Map<String, String> output = new HashMap<>();
//        StringBuilder respBuilder = new StringBuilder();
//        respBuilder.append("Halo! Aku {bot_name}, penasehat keuangan pribadi Kamu 😄. \n");
//        respBuilder.append("Sebelum kita mulai, siapa nama kamu?");
//        output.put(OUTPUT, respBuilder.toString());
//        DialogUtil dialogUtil = new DialogUtil();
        bubble = dialogUtil.CreateBubble("entitasNama", 1, null);
        output.put(OUTPUT, bubble);
        extensionResult.setValue(output);
        return extensionResult;
    }

    @Override
    public String getBodyFromApia() {
        String a = "";
        GetBodyFromAPI getBody = new GetBodyFromAPI();
        a = getBody.getBodyformApi();
        return a;
    }

    @Override
    public int getBodyFormApib() {
        int b = 0;
        GetBodyFromAPI getBody = new GetBodyFromAPI();
        b = getBody.getBodyRiskProfile();
        return b;
    }

    public GetBodyFromAPI getBodyObject() {
        GetBodyFromAPI getBody = new GetBodyFromAPI();
        getBody.getBodyObjectRiskProfile();
        return getBody;
    }

    public Boolean generatePDF() {
        boolean status = true;
        StringBuilder contentBuilder = new StringBuilder();
        try {
//            BufferedReader bufferedReader = new BufferedReader(new FileReader("E:/METRO DATA/PROYEK CHAT BOT - 3dolphins/OCBC NISP/base64.txt"));
//            String sCurrentLine;
//            while ((sCurrentLine = bufferedReader.readLine()) != null) {
//                contentBuilder.append(sCurrentLine);
//            }  
            OkHttpUtil okHttpUtil = new OkHttpUtil();
            okHttpUtil.init(true);
            Request request = new Request.Builder().url("https://api.myjson.com/bins/d536v").get().build();
            Response response = okHttpUtil.getClient().newCall(request).execute();
            JSONObject jsonobj = new JSONObject(response.body().string());
            byte[] decodedBytes = Base64.getDecoder().decode(jsonobj.getString("PDF_Report").replace("\n", ""));
//            byte[] decodedBytes = Base64.getDecoder().decode(contentBuilder.toString());
            FileUtils.writeByteArrayToFile(new File("coba.pdf"), decodedBytes);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            status = false;
        } catch (IOException e) {
            e.printStackTrace();
            status = false;
        }
        return status;
    }

    @Override
    public ExtensionResult mulaiBerinvestasi_entitasSyaratDanKetentuan(ExtensionRequest extensionRequest) {
//========================================================================================================
        log.debug("getCarousel() extension request: {}", extensionRequest);
        Map<String, String> output = new HashMap<>();
        String syarat_ketentuan_pic = appProperties.getOcbc_carousel_gambar_syaratdanketentuan();
        String syarat_ketentuan_title = appProperties.getOcbc_carousel_title_syaratdanketentuan();
        String syarat_ketentuan_payload = appProperties.OCBC_WEBVIEW_BASE_URL + appProperties.OCBC_WEBVIEW_BASE_URL_TNC + "?view=widget";
        String syarat_ketentuan_subtitle = appProperties.getOcbc_carousel_subtitle_syaratdanketentuan();
        String syarat_ketentuan_button = appProperties.getOcbc_carousel_nameButton_syaratdanketentuan();
        List<String> buttonList = new ArrayList<>();
        ButtonTemplate button;
        ButtonBuilder buttonBuilder;
        button = new ButtonTemplate();
        button.setPictureLink(syarat_ketentuan_pic);
        button.setPicturePath(syarat_ketentuan_pic);
        button.setTitle(syarat_ketentuan_title + "");
        button.setSubTitle(syarat_ketentuan_subtitle + "");
        List<EasyMap> actions = new ArrayList<>();
        EasyMap bookAction = new EasyMap();
        bookAction.setName(syarat_ketentuan_button + "");
        bookAction.setValue(syarat_ketentuan_payload + "");
        actions.add(bookAction);
        button.setButtonValues(actions);
        buttonBuilder = new ButtonBuilder(button);
        buttonList.add(buttonBuilder.build());
        StringBuilder respBuilder = new StringBuilder();
//        respBuilder.append("Dengan menggunakan platform MONA, Anda telah menyetujui  syarat dan ketentuan yang berlaku.");

        bubble = dialogUtil.CreateBubble("mulaiBerinvestasi_validasiSyaratKetentuan", 3, null);
        respBuilder.append(bubble)
                .append(CONSTANT_SPLIT_SYNTAX)
                .append(buttonBuilder.build());
        output.put(OUTPUT, respBuilder.toString());

        ExtensionResult extensionResult = new ExtensionResult();
        extensionResult.setAgent(false);
        extensionResult.setRepeat(false);
        extensionResult.setSuccess(true);
        extensionResult.setNext(true);
        extensionResult.setValue(output);
        return extensionResult;

//========================================================================================================
    }

    @Override
    public ExtensionResult mulaiBerinvestasi_validasiSyaratKetentuan(ExtensionRequest extensionRequest) {
        log.debug("validasiSyaratKetentuan() extension request: {}", new Gson().toJson(extensionRequest, ExtensionRequest.class));
        ExtensionResult extensionResult = new ExtensionResult();
        Map<String, String> output = new HashMap<>();
        Map<String, String> clearEntities = new HashMap<>();
        String syaratKetentuan = getEasyMapValueByName(extensionRequest, "syarat_dan_ketentuan");
        //        ValidationMethod validationMethod = new ValidationMethod();
        boolean status = validationMethod.valSyaratKetentuan(syaratKetentuan);
        extensionResult.setAgent(false);
        extensionResult.setRepeat(false);
        extensionResult.setSuccess(true);
        extensionResult.setNext(true);
        Map<String, String> map = new HashMap<>();
//        StringBuilder respBuilder = new StringBuilder();

        log.debug("validasiSyaratKetentuan() variabel lokal syaratKetentuan: {}", syaratKetentuan);

        log.debug("validasiSyaratKetentuan() variabel lokal status: {}", status);

        if (status == false) {
            clearEntities.put("syarat_dan_ketentuan", "");
            clearEntities.put("validasi_syarat_ketentuan", "");
            extensionResult.setEntities(clearEntities);

            log.debug("getCarousel() extension request: {}", extensionRequest);

            String syarat_ketentuan_pic = appProperties.getOcbc_carousel_gambar_syaratdanketentuan();
            String syarat_ketentuan_title = appProperties.getOcbc_carousel_title_syaratdanketentuan();
            String syarat_ketentuan_payload = appProperties.OCBC_WEBVIEW_BASE_URL + appProperties.OCBC_WEBVIEW_BASE_URL_TNC + "?view=widget";
            String syarat_ketentuan_subtitle = appProperties.getOcbc_carousel_subtitle_syaratdanketentuan();
            String syarat_ketentuan_button = appProperties.getOcbc_carousel_nameButton_syaratdanketentuan();
            List<String> buttonList = new ArrayList<>();
            ButtonTemplate button;
            ButtonBuilder buttonBuilder;
            button = new ButtonTemplate();
            button.setPictureLink(syarat_ketentuan_pic);
            button.setPicturePath(syarat_ketentuan_pic);
            button.setTitle(syarat_ketentuan_title + "");
            button.setSubTitle(syarat_ketentuan_subtitle + "");
            List<EasyMap> actions = new ArrayList<>();
            EasyMap bookAction = new EasyMap();
            bookAction.setName(syarat_ketentuan_button + "");
            bookAction.setValue(syarat_ketentuan_payload + "");
            actions.add(bookAction);
            button.setButtonValues(actions);
            buttonBuilder = new ButtonBuilder(button);
            buttonList.add(buttonBuilder.build());
            StringBuilder respBuilder = new StringBuilder();
//        respBuilder.append("Dengan menggunakan platform MONA, Anda telah menyetujui  syarat dan ketentuan yang berlaku.");

            bubble = dialogUtil.CreateBubble("mulaiBerinvestasi_validasiSyaratKetentuan", 3, null);
            respBuilder.append(bubble)
                    .append(CONSTANT_SPLIT_SYNTAX)
                    .append(buttonBuilder.build());
            output.put(OUTPUT, respBuilder.toString());

        } else {
            clearEntities.put("validasi_syarat_ketentuan", "validasiSyaratKetentuan");
            extensionResult.setEntities(clearEntities);
//            bubble = dialogUtil.CreateBubble("mulaiBerinvestasi_validasiSyaratKetentuan", 2, null);
//            output.put(OUTPUT, bubble);
        }
        extensionResult.setValue(output);
        return extensionResult;
    }

    @Override
    public ExtensionResult mulaiBerinvestasi_entitasUmur(ExtensionRequest extensionRequest) {
        log.debug("entitasUmur() extension request: {}", extensionRequest);
        ExtensionResult extensionResult = new ExtensionResult();
        extensionResult.setAgent(false);
        extensionResult.setRepeat(false);
        extensionResult.setSuccess(true);
        extensionResult.setNext(true);
        Map<String, String> output = new HashMap<>();
//        StringBuilder respBuilder = new StringBuilder();
//        respBuilder.append("Yay! Yuk kita mulai dengan memahami profil {first_name} terlebih dahulu sebelum kita mencoba simulasi Life Goal {first_name} sesaat lagi. \n");
//        respBuilder.append("Berapakah umur {first_name} sekarang?");
//        output.put(OUTPUT, respBuilder.toString());

        bubble = dialogUtil.CreateBubble("mulaiBerinvestasi_entitasUmur", 1, null);
        output.put(OUTPUT, bubble);
        extensionResult.setValue(output);
        return extensionResult;
    }

    @Override
    public ExtensionResult mulaiBerinvestasi_validasiUmur(ExtensionRequest extensionRequest) {
        log.debug("validasiUmur() extension request: {}", new Gson().toJson(extensionRequest, ExtensionRequest.class));
        ExtensionResult extensionResult = new ExtensionResult();
        Map<String, String> output = new HashMap<>();
        Map<String, String> clearEntities = new HashMap<>();
        String umur = getEasyMapValueByName(extensionRequest, "umur");

        umur = umur.toLowerCase()
                .replace("tahun", "")
                .replace("thun", "")
                .replace("thn", "")
                .replace(",", "")
                .replace(".", "")
                .replace("-", "")
                .trim();

        String kalimat = umur;
        String[] kata = kalimat.split("");
        if (kata.length > 2) {
            umur = kata[0] + kata[1];
        } else {
            umur = umur;
        }

        String status = validationMethod.ValAge(umur);
        extensionResult.setAgent(false);
        extensionResult.setRepeat(false);
        extensionResult.setSuccess(true);
        extensionResult.setNext(true);
        StringBuilder respBuilder = new StringBuilder();
        Map<String, String> map = new HashMap<>();
        if ("Terlalu Muda".equals(status)) {
            clearEntities.put("umur", "");
            clearEntities.put("validasi_umur", "");
            extensionResult.setEntities(clearEntities);
//            respBuilder.append("{bot_name} suka banget sama semangat {first_name} yang ingin memulai investasi di usia semuda sekarang! salut!");
//            respBuilder.append("Tetapi &#44; mengingat umur {first_name} yang masih berjiwa muda banget nih &#44; ada baiknya kita memulai dengan belajar investasi terlebih dahulu ya 😄");
//            map.put("Belajar Investasi", "belajar berinvestasi sekarang");
//            QuickReplyBuilder quickReplyBuilder = new QuickReplyBuilder.Builder(respBuilder + "").addAll(map).build();
//            output.put(OUTPUT, quickReplyBuilder.string());

            bubble = dialogUtil.CreateBubble("mulaiBerinvestasi_validasiUmur", 1, null);
            output.put(OUTPUT, bubble);
        } else if ("Mengandung Angka".equals(status)) {
            clearEntities.put("umur", "");
            clearEntities.put("validasi_umur", "");
            extensionResult.setEntities(clearEntities);
//            respBuilder.append("Maaf {first_name}, {bot_name} belum bisa mengerti umur yang Kamu maksud nih. Coba tuk masukkan angka ya \n");
//            respBuilder.append("'Berapakah umur {first_name} sekarang?");
//            output.put(OUTPUT, respBuilder.toString());
            bubble = dialogUtil.CreateBubble("mulaiBerinvestasi_validasiUmur", 2, null);
            output.put(OUTPUT, bubble);
        } else {
            clearEntities.put("umur", umur);
            clearEntities.put("validasi_umur", "validasiUmur");
            extensionResult.setEntities(clearEntities);
//            respBuilder.append("Apa {first_name} sudah menikah?");
//            map.put("Belum", "Belum");
//            map.put("Sudah", "Sudah");
//            QuickReplyBuilder quickReplyBuilder = new QuickReplyBuilder.Builder(respBuilder + "").addAll(map).build();
//            output.put(OUTPUT, quickReplyBuilder.string());
            bubble = dialogUtil.CreateBubble("mulaiBerinvestasi_validasiUmur", 3, null);
            output.put(OUTPUT, bubble);
        }
        extensionResult.setValue(output);
        return extensionResult;
    }

    @Override
    public ExtensionResult mulaiBerinvestasi_entitasStatusPerkawinan(ExtensionRequest extensionRequest) {
        log.debug("entitasStatusPerkawinan() extension request: {}", extensionRequest);
        Map<String, String> output = new HashMap<>();
        Map<String, String> map = new HashMap<>();
//        StringBuilder respBuilder = new StringBuilder();
//        respBuilder.append("Apa {first_name} sudah menikah?");
//        map.put("Belum", "Belum");
//        map.put("Sudah", "Sudah");
//        QuickReplyBuilder quickReplyBuilder = new QuickReplyBuilder.Builder(respBuilder + "").addAll(map).build();
//        output.put(OUTPUT, quickReplyBuilder.string());
        bubble = dialogUtil.CreateBubble("mulaiBerinvestasi_entitasStatusPerkawinan", 1, null);
        output.put(OUTPUT, bubble);
        ExtensionResult extensionResult = new ExtensionResult();
        extensionResult.setAgent(false);
        extensionResult.setRepeat(false);
        extensionResult.setSuccess(true);
        extensionResult.setNext(true);
        extensionResult.setValue(output);
        return extensionResult;
    }

    @Override
    public ExtensionResult mulaiBerinvestasi_validasiStatusPerkawinan(ExtensionRequest extensionRequest) {
        log.debug("validasiStatusPerkawinan() extension request: {}", new Gson().toJson(extensionRequest, ExtensionRequest.class));
        ExtensionResult extensionResult = new ExtensionResult();
        Map<String, String> output = new HashMap<>();
        Map<String, String> clearEntities = new HashMap<>();
        String statusPerkawinan = getEasyMapValueByName(extensionRequest, "status_perkawinan");

        //Free typing akan disinonimkan antara 'Ya' atau 'Tidak'
        clearEntities = validationMethod.valStatusPerkawinan(statusPerkawinan);

        Map<String, String> map = new HashMap<>();

        if (clearEntities.get("status_perkawinan").equals("")) {
//            respBuilder.append("Apa {first_name} sudah menikah?");
//            map.put("Belum", "0");
//            map.put("Sudah", "1");
//            QuickReplyBuilder quickReplyBuilder = new QuickReplyBuilder.Builder(respBuilder + "").addAll(map).build();
//            output.put(OUTPUT, quickReplyBuilder.string());
            bubble = dialogUtil.CreateBubble("mulaiBerinvestasi_validasiStatusPerkawinan", 1, null);
        } else {
//            respBuilder.append("Sekarang {bot_name} ingin tau kebiasaan menabung {first_name}. \n");
//            respBuilder.append("Berapa kisaran pendapatan bulanan {first_name} saat ini? ");
//            map.put("Kurang dari Rp. 15 juta", "1");
//            map.put("Rp. 15 juta - 35 juta", "2");
//            map.put("Lebih dari Rp. 35 juta", "3");
//            QuickReplyBuilder quickReplyBuilder = new QuickReplyBuilder.Builder(respBuilder + "").addAll(map).build();
//            output.put(OUTPUT, quickReplyBuilder.string());
            bubble = dialogUtil.CreateBubble("mulaiBerinvestasi_validasiStatusPerkawinan", 2, null);
        }
        output.put(OUTPUT, bubble);

        extensionResult.setEntities(clearEntities);
        extensionResult.setAgent(false);
        extensionResult.setRepeat(false);
        extensionResult.setSuccess(true);
        extensionResult.setNext(true);
        extensionResult.setValue(output);
        return extensionResult;
    }

    @Override
    public ExtensionResult mulaiBerinvestasi_entitasPendapatan(ExtensionRequest extensionRequest) {
        log.debug("entitasPendapatan() extension request: {}", extensionRequest);
        Map<String, String> output = new HashMap<>();
        Map<String, String> map = new HashMap<>();
//        StringBuilder respBuilder = new StringBuilder();
//        respBuilder.append("Sekarang {bot_name} ingin tau kebiasaan menabung {first_name}. \n");
//        respBuilder.append("Berapa kisaran pendapatan bulanan {first_name} saat ini? ");
//        map.put("Kurang dari Rp. 15 juta", "Kurang dari 15 juta");
//        map.put("Rp. 15 juta - 35 juta", "15 sampai 35 juta");
//        map.put("Lebih dari Rp. 35 juta", "Lebih dari 35 juta");
//        QuickReplyBuilder quickReplyBuilder = new QuickReplyBuilder.Builder(respBuilder + "").addAll(map).build();
//        output.put(OUTPUT, quickReplyBuilder.string());
        bubble = dialogUtil.CreateBubble("mulaiBerinvestasi_entitasPendapatan", 1, null);
        output.put(OUTPUT, bubble);
        ExtensionResult extensionResult = new ExtensionResult();
        extensionResult.setAgent(false);
        extensionResult.setRepeat(false);
        extensionResult.setSuccess(true);
        extensionResult.setNext(true);
        extensionResult.setValue(output);
        return extensionResult;
    }

    @Override
    public ExtensionResult mulaiBerinvestasi_validasiPendapatan(ExtensionRequest extensionRequest) {
        log.debug("validasiPendapatan() extension request: {}", new Gson().toJson(extensionRequest, ExtensionRequest.class));
        ExtensionResult extensionResult = new ExtensionResult();
        Map<String, String> output = new HashMap<>();
        Map<String, String> clearEntities = new HashMap<>();
        String statusPendapatan = getEasyMapValueByName(extensionRequest, "pendapatan");
        //        ValidationMethod validationMethod = new ValidationMethod();
        clearEntities = validationMethod.valPendapatan(statusPendapatan);
        extensionResult.setEntities(clearEntities);

        extensionResult.setAgent(false);
        extensionResult.setRepeat(false);
        extensionResult.setSuccess(true);
        extensionResult.setNext(true);
//        Map<String, String> map = new HashMap<>();
//        StringBuilder respBuilder = new StringBuilder();

        if (clearEntities.get("pendapatan").equals("")) {
//            clearEntities.put("pendapatan", "");
//            clearEntities.put("validasi_pendapatan", "");
//            extensionResult.setEntities(clearEntities);
//            respBuilder.append("Sekarang {bot_name} ingin tau kebiasaan menabung {first_name}. \n");
//            respBuilder.append("Berapa kisaran pendapatan bulanan {first_name} saat ini? ");
//            map.put("Kurang dari Rp. 15 juta", "1");
//            map.put("Rp. 15 juta - 35 juta", "2");
//            map.put("Lebih dari Rp. 35 juta", "3");
//            QuickReplyBuilder quickReplyBuilder = new QuickReplyBuilder.Builder(respBuilder + "").addAll(map).build();
//            output.put(OUTPUT, quickReplyBuilder.string());
            bubble = dialogUtil.CreateBubble("mulaiBerinvestasi_validasiPendapatan", 1, null);
        } else {
//            clearEntities.put("validasi_pendapatan", "validasiPendapatan");
//            extensionResult.setEntities(clearEntities);
//            respBuilder.append("Berapa persen dari pendapatan {first_name} yang biasanya ditabung?");
//            map.put("Kurang dari 10%", "1");
//            map.put("sekitar 10%-30%", "2");
//            map.put("lebih dari 30%", "3");
//            QuickReplyBuilder quickReplyBuilder = new QuickReplyBuilder.Builder(respBuilder + "").addAll(map).build();
//            output.put(OUTPUT, quickReplyBuilder.string());
            bubble = dialogUtil.CreateBubble("mulaiBerinvestasi_validasiPendapatan", 2, null);
        }
        output.put(OUTPUT, bubble);

        extensionResult.setValue(output);
        return extensionResult;
    }

    @Override
    public ExtensionResult mulaiBerinvestasi_entitasTabungan(ExtensionRequest extensionRequest) {
        log.debug("entitasTabungan() extension request: {}", extensionRequest);
        Map<String, String> output = new HashMap<>();
        bubble = dialogUtil.CreateBubble("mulaiBerinvestasi_entitasTabungan", 1, null);
        output.put(OUTPUT, bubble);
        ExtensionResult extensionResult = new ExtensionResult();
        extensionResult.setAgent(false);
        extensionResult.setRepeat(false);
        extensionResult.setSuccess(true);
        extensionResult.setNext(true);
        extensionResult.setValue(output);
        return extensionResult;
    }

    @Override
    public ExtensionResult mulaiBerinvestasi_validasiTabungan(ExtensionRequest extensionRequest) {
        log.debug("validasiTabungan() extension request: {}", new Gson().toJson(extensionRequest, ExtensionRequest.class));
        ExtensionResult extensionResult = new ExtensionResult();
        Map<String, String> output = new HashMap<>();
        Map<String, String> clearEntities = new HashMap<>();
        String statusTabungan = getEasyMapValueByName(extensionRequest, "tabungan");
        //        ValidationMethod validationMethod = new ValidationMethod();
        clearEntities = validationMethod.valTabungan(statusTabungan);
        extensionResult.setAgent(false);
        extensionResult.setRepeat(false);
        extensionResult.setSuccess(true);
        extensionResult.setNext(true);
//        Map<String, String> map = new HashMap<>();
//        StringBuilder respBuilder = new StringBuilder();
        if (clearEntities.get("tabungan").equals("")) {
            bubble = dialogUtil.CreateBubble("mulaiBerinvestasi_validasiTabungan", 1, null);
        } else {
            bubble = dialogUtil.CreateBubble("mulaiBerinvestasi_validasiTabungan", 2, null);
        }
        extensionResult.setEntities(clearEntities);

        output.put(OUTPUT, bubble);
        extensionResult.setValue(output);
        return extensionResult;
    }

    @Override
    public ExtensionResult mulaiBerinvestasi_entitasStatusInvestasi(ExtensionRequest extensionRequest) {
        log.debug("entitasStatusInvestasi() extension request: {}", extensionRequest);
        Map<String, String> output = new HashMap<>();
        bubble = dialogUtil.CreateBubble("mulaiBerinvestasi_entitasStatusInvestasi", 1, null);
        output.put(OUTPUT, bubble);
        ExtensionResult extensionResult = new ExtensionResult();
        extensionResult.setAgent(false);
        extensionResult.setRepeat(false);
        extensionResult.setSuccess(true);
        extensionResult.setNext(true);
        extensionResult.setValue(output);
        return extensionResult;
    }

    @Override
    public ExtensionResult mulaiBerinvestasi_validasiStatusInvestasi(ExtensionRequest extensionRequest) {
        log.debug("validasiStatusInvestasi() extension request: {}", new Gson().toJson(extensionRequest, ExtensionRequest.class));
        ExtensionResult extensionResult = new ExtensionResult();
        Map<String, String> output = new HashMap<>();
        Map<String, String> clearEntities = new HashMap<>();
        String statusInvestasi = getEasyMapValueByName(extensionRequest, "status_investasi");
        clearEntities = validationMethod.valStatusInvestasi(statusInvestasi);
        extensionResult.setEntities(clearEntities);
        extensionResult.setAgent(false);
        extensionResult.setRepeat(false);
        extensionResult.setSuccess(true);
        extensionResult.setNext(true);
        Map<String, String> map = new HashMap<>();
        if (clearEntities.get("status_investasi").equals("")) {
            bubble = dialogUtil.CreateBubble("mulaiBerinvestasi_validasiStatusInvestasi", 1, null);
            output.put(OUTPUT, bubble);
            extensionResult.setValue(output);
        } else if (clearEntities.get("status_investasi").equals("0")) {
            setData(extensionRequest);
            bubble = dialogUtil.CreateBubble("mulaiBerinvestasi_validasiStatusInvestasi", 2, null);
            output.put(OUTPUT, bubble);
            extensionResult.setValue(output);
        } else {
            output.put(OUTPUT, "");
            extensionResult.setValue(output);
        }
        return extensionResult;
    }

    @Override
    public ExtensionResult mulaiBerinvestasi_validasiRiskProfile(ExtensionRequest extensionRequest) {
        log.debug("mulaiBerinvestasi_validasiRiskProfile: {}", new Gson().toJson(extensionRequest, ExtensionRequest.class));
        ExtensionResult extensionResult = new ExtensionResult();
        Map<String, String> output = new HashMap<>();
        Map<String, String> clearEntities = new HashMap<>();
        String profile_resiko = getEasyMapValueByName(extensionRequest, "profile_resiko");
        //        ValidationMethod validationMethod = new ValidationMethod();
        clearEntities = validationMethod.valRiskProfile(profile_resiko);

        if (!clearEntities.get("profile_resiko").equals("")) {
            setData(extensionRequest);
            //        entities.put("set_data", "setData");
        }
        bubble = dialogUtil.CreateBubble("mulaiBerinvestasi_validasiRiskProfile", 1, null);
        extensionResult.setEntities(clearEntities);
        output.put(OUTPUT, bubble);

        extensionResult.setAgent(false);
        extensionResult.setRepeat(false);
        extensionResult.setSuccess(true);
        extensionResult.setNext(true);

        extensionResult.setValue(output);
        return extensionResult;
    }

//    @Override
//    public ExtensionResult setDataMulaiBerinvestasi(ExtensionRequest extensionRequest) {
//        return null;
//    }
    private void setData(ExtensionRequest extensionRequest) {

        log.debug("setDataMulaiBerinvestasi() extension request: {}", new Gson().toJson(extensionRequest, ExtensionRequest.class));

        String umur = getEasyMapValueByName(extensionRequest, "umur");
        String statusPernikahan = getEasyMapValueByName(extensionRequest, "status_perkawinan");
        String pendapatanBulanan = getEasyMapValueByName(extensionRequest, "pendapatan");
        String tabungan = getEasyMapValueByName(extensionRequest, "tabungan");
        String status_investasi = getEasyMapValueByName(extensionRequest, "status_investasi");
        String profile_resiko = getEasyMapValueByName(extensionRequest, "profile_resiko");

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
        transaction_Data.setAge(Integer.parseInt(umur));
        transaction_Data.setEmail(dataLeads1.getTransaction_Data()[0].getEmail());
        transaction_Data.setPhone_Number(dataLeads1.getTransaction_Data()[0].getPhone_Number());
        transaction_Data.setLocation(dataLeads1.getTransaction_Data()[0].getLocation());
        transaction_Data.setIs_Married(Integer.parseInt(statusPernikahan));
        transaction_Data.setIs_Already_Invest(Integer.parseInt(status_investasi));
        transaction_Data.setFinancial_Info_Income_Range_ID(Integer.parseInt(pendapatanBulanan));
        transaction_Data.setFinancial_Info_Saved_Income_ID(Integer.parseInt(tabungan));
        transaction_Data.setRisk_Profile_ID(convertProfileResiko);
        transaction_Data.setLife_Goal_ID(dataLeads1.getTransaction_Data()[0].getLife_Goal_ID());
        transaction_Data.setFinancial_Goal(dataLeads1.getTransaction_Data()[0].getFinancial_Goal());
        transaction_Data.setInvestment_Type(dataLeads1.getTransaction_Data()[0].getInvestment_Type());
        transaction_Data.setTenor(dataLeads1.getTransaction_Data()[0].getTenor());
        transaction_Data.setInvestment_Amount(dataLeads1.getTransaction_Data()[0].getInitial_Amount());
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
        transaction_Data.setStatus_Nasabah(dataLeads1.getTransaction_Data()[0].getStatus_Nasabah());
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

    @Override
    public ExtensionResult setAdditionalField(ExtensionRequest extensionRequest) {
        userToken = svcDolphinService.getUserToken(userToken);
        log.debug("setName() extension request: {}", new Gson().toJson(extensionRequest, ExtensionRequest.class));
        String contactId = extensionRequest.getIntent().getTicket().getContactId();
        String pendapatan = getEasyMapValueByName(extensionRequest, "pendapatan");
        String tabungan = getEasyMapValueByName(extensionRequest, "tabungan");
        String statusInvestasi = getEasyMapValueByName(extensionRequest, "status_investasi");
        String statusPerkawinan = getEasyMapValueByName(extensionRequest, "status_perkawinan");
        String umur = getEasyMapValueByName(extensionRequest, "umur");
        String profileResiko = getEasyMapValueByName(extensionRequest, "profile_resiko");
        Contact contact = svcDolphinService.getCustomer(userToken, contactId);
        contact.setAgeMax(Integer.parseInt(umur));

        AdditionalFieldOCBC additionalFieldOCBC = new AdditionalFieldOCBC();
        additionalFieldOCBC.setPendapatan(pendapatan);
        additionalFieldOCBC.setStatusInvestasi(statusInvestasi);
        additionalFieldOCBC.setTabungan(tabungan);
        additionalFieldOCBC.setStatusPerkawinan(statusPerkawinan);
        additionalFieldOCBC.setProfileResiko(profileResiko);

        List<String> listData = new ArrayList<>();
        listData.add("" + new Gson().toJson(additionalFieldOCBC, AdditionalFieldOCBC.class) + "");
        contact.setAdditionalField(listData);

        contact = svcDolphinService.updateCustomer(userToken, contact);
        ExtensionResult extensionResult = new ExtensionResult();
        extensionResult.setAgent(false);
        extensionResult.setRepeat(false);
        extensionResult.setSuccess(true);
        extensionResult.setNext(true);
        return extensionResult;
    }

    @Override
    public ExtensionResult getAdditionalField(ExtensionRequest extensionRequest) {
        userToken = svcDolphinService.getUserToken(userToken);
        Map<String, String> output = new HashMap<>();
        log.debug("getAdditionalField() extension request: {}", new Gson().toJson(extensionRequest, ExtensionRequest.class));
        String contactId = extensionRequest.getIntent().getTicket().getContactId();
        Contact contact = svcDolphinService.getCustomer(userToken, contactId);

        String b = contact.getAdditionalField().get(0);
        DataLeads dataLeads = new Gson().fromJson(b, DataLeads.class);

        dataLeads.getTransaction_Data()[0].getEC_ID();
        StringBuilder respBuilder = new StringBuilder();
        respBuilder.append("Name = " + dataLeads.getTransaction_Data()[0].getName() + "\n");
        respBuilder.append("Age = " + dataLeads.getTransaction_Data()[0].getAge() + " \n");
        respBuilder.append("Email = " + dataLeads.getTransaction_Data()[0].getEmail() + " \n");
        respBuilder.append("Phone_Number = " + dataLeads.getTransaction_Data()[0].getPhone_Number() + " \n");
        respBuilder.append("Location = " + dataLeads.getTransaction_Data()[0].getLocation() + " \n");
        respBuilder.append("Is_Married = " + dataLeads.getTransaction_Data()[0].getIs_Married() + " \n");
        respBuilder.append("Is_Already_Invest = " + dataLeads.getTransaction_Data()[0].getIs_Already_Invest() + " \n");
        respBuilder.append("Financial_Info_Income_Range_ID = " + dataLeads.getTransaction_Data()[0].getFinancial_Info_Income_Range_ID() + " \n");
        respBuilder.append("Financial_Info_Saved_Income_ID = " + dataLeads.getTransaction_Data()[0].getFinancial_Info_Saved_Income_ID() + " \n");
        respBuilder.append("Risk_Profile_ID = " + dataLeads.getTransaction_Data()[0].getRisk_Profile_ID() + " \n");
        respBuilder.append("Life_Goal_ID = " + dataLeads.getTransaction_Data()[0].getLife_Goal_ID() + " \n");
        respBuilder.append("Financial_Goal = " + dataLeads.getTransaction_Data()[0].getFinancial_Goal() + " \n");
        respBuilder.append("Investment_Type = " + dataLeads.getTransaction_Data()[0].getInvestment_Type() + " \n");
        respBuilder.append("Tenor = " + dataLeads.getTransaction_Data()[0].getTenor() + " \n");
        respBuilder.append("Investment_Amount = " + dataLeads.getTransaction_Data()[0].getInvestment_Amount() + " \n");
        respBuilder.append("Target_Amount = " + dataLeads.getTransaction_Data()[0].getTarget_Amount() + " \n");
        respBuilder.append("Initial_Amount = " + dataLeads.getTransaction_Data()[0].getInitial_Amount() + " \n");
        respBuilder.append("EC_ID = " + dataLeads.getTransaction_Data()[0].getEC_ID() + " \n");
        respBuilder.append("Children_Age = " + dataLeads.getTransaction_Data()[0].getChildren_Age() + " \n");
        respBuilder.append("Product_ID = " + dataLeads.getTransaction_Data()[0].getProduct_ID() + " \n");
        respBuilder.append("Datetime_Start_Process = " + dataLeads.getTransaction_Data()[0].getDatetime_Start_Process() + " \n");
        respBuilder.append("Datetime_End_Process = " + dataLeads.getTransaction_Data()[0].getDatetime_End_Process() + " \n");
        respBuilder.append("Time_Horizon = " + dataLeads.getTransaction_Data()[0].getTime_Horizon() + " \n");
        respBuilder.append("Source_of_Income = " + dataLeads.getTransaction_Data()[0].getSource_of_Income() + " \n");
        respBuilder.append("Investment_Knowledge = " + dataLeads.getTransaction_Data()[0].getInvestment_Knowledge() + " \n");
        respBuilder.append("Income_Usage = " + dataLeads.getTransaction_Data()[0].getIncome_Usage() + " \n");
        respBuilder.append("Potential_Loss = " + dataLeads.getTransaction_Data()[0].getPotential_Loss() + " \n");
        respBuilder.append("Platform_Name = " + dataLeads.getTransaction_Data()[0].getPlatform_Name() + " \n");
        respBuilder.append("Rating = " + dataLeads.getTransaction_Data()[0].getRating() + " \n");
        respBuilder.append("Rating Comment = " + dataLeads.getTransaction_Data()[0].getRating_Comment() + " \n");

        output.put(OUTPUT, respBuilder.toString());
        ExtensionResult extensionResult = new ExtensionResult();
        extensionResult.setAgent(false);
        extensionResult.setRepeat(false);
        extensionResult.setSuccess(true);
        extensionResult.setNext(true);
        extensionResult.setValue(output);
        return extensionResult;
    }

    public Boolean generatePic(String pesan, int i, ExtensionRequest extensionRequest) {
        boolean status = true;
        String text = "Hallo....";
        BufferedImage img = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = img.createGraphics();
        Font font = new Font("Arial", Font.PLAIN, 20);
        g2d.setFont(font);
        FontMetrics fm = g2d.getFontMetrics();
        int width = fm.stringWidth(text);
        int height = fm.getHeight();
        g2d.dispose();
        System.out.println(width + " === " + height);

        img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        g2d = img.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
        g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
        g2d.setFont(font);
        fm = g2d.getFontMetrics();
        g2d.setColor(Color.BLACK);
        g2d.drawString(text, 0, fm.getAscent());
        System.out.println(fm.getAscent());
        g2d.dispose();
        String id = this.getContactID(extensionRequest);
        try {
            ImageIO.write(img, "png", new File(appProperties.OCBC_FOLDER_PICTURES + id + "Text" + i + ".png"));
        } catch (IOException ex) {
            ex.printStackTrace();
            status = false;
        }
        return status;
    }

    @Override
    public ExtensionResult getCarouselGambarDinamis(ExtensionRequest extensionRequest) {
        log.debug("getCarouselGambar() extension request: {}", extensionRequest);
        Map<String, String> output = new HashMap<>();

        List<String> buttonList = new ArrayList<>();
        ButtonTemplate button;
        ButtonBuilder buttonBuilder;
        for (int i = 0; i < 3; i++) {
            this.generatePic("gambar" + i, i, extensionRequest);
            button = new ButtonTemplate();
            button.setPictureLink("");
            button.setPicturePath("");
            button.setTitle(ParamSdk.SAMPLE_TITLE.concat(String.valueOf(i)));
            button.setSubTitle(ParamSdk.SAMPLE_SUBTITLE.concat(String.valueOf(i)));
            List<EasyMap> actions = new ArrayList<>();
            EasyMap bookAction = new EasyMap();
            bookAction.setName(ParamSdk.SAMPLE_LABEL);
            bookAction.setValue(ParamSdk.SAMPLE_PAYLOAD);
            actions.add(bookAction);
            button.setButtonValues(actions);
            buttonBuilder = new ButtonBuilder(button);
            buttonList.add(buttonBuilder.build());
        }
        CarouselBuilder carouselBuilder = new CarouselBuilder(buttonList);
        output.put(OUTPUT, carouselBuilder.build());

        ExtensionResult extensionResult = new ExtensionResult();
        extensionResult.setAgent(false);
        extensionResult.setRepeat(false);
        extensionResult.setSuccess(true);
        extensionResult.setNext(true);
        extensionResult.setValue(output);
        return extensionResult;
    }

    public ExtensionResult coba_video(ExtensionRequest extensionRequest) {
        Map<String, String> output = new HashMap<>();
        ButtonTemplate button = new ButtonTemplate();
        button.setTitle("Video Youtube");
        button.setSubTitle("Coba Video Youtube");
        button.setPictureLink("https://youtu.be/XYuDrXB1Qcw?view=widget");
        button.setPicturePath("https://youtu.be/XYuDrXB1Qcw?view=widget");
        VideoBuilder buttonBuilder = new VideoBuilder(button);

        output.put(OUTPUT, buttonBuilder.build());
        ExtensionResult extensionResult = new ExtensionResult();
        extensionResult.setAgent(false);
        extensionResult.setRepeat(false);
        extensionResult.setSuccess(true);
        extensionResult.setNext(true);
        extensionResult.setValue(output);
        return extensionResult;
    }

}
