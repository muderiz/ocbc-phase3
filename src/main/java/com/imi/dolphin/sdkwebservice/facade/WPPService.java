/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imi.dolphin.sdkwebservice.facade;

import com.google.gson.Gson;
import com.imi.dolphin.sdkwebservice.model.CalculatorRequestBody;
import com.imi.dolphin.sdkwebservice.model.CalculatorResponseBody;
import com.imi.dolphin.sdkwebservice.model.DataLeads;
//import com.imi.dolphin.sdkwebservice.model.FutureValueRequest;
//import com.imi.dolphin.sdkwebservice.model.FutureValueResponse;
//import com.imi.dolphin.sdkwebservice.model.ListProductRequest;
//import com.imi.dolphin.sdkwebservice.model.ListProductResponse;
//import com.imi.dolphin.sdkwebservice.model.RiskProfileResponse;
//import com.imi.dolphin.sdkwebservice.model.RiskProfileRequest;
//import com.imi.dolphin.sdkwebservice.model.TargetValueRequest;
//import com.imi.dolphin.sdkwebservice.model.TargetValueResponse;
import com.imi.dolphin.sdkwebservice.param.ParamSdk;
import com.imi.dolphin.sdkwebservice.property.AppProperties;
import com.imi.dolphin.sdkwebservice.service.ServiceImp;
import com.imi.dolphin.sdkwebservice.util.OkHttpUtil;
import java.io.FileNotFoundException;
import java.io.IOException;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
//import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author cokkyturnip
 */
@Service
public class WPPService {

    private static final Logger log = LogManager.getLogger(WPPService.class);

    @Autowired
    Gson gson;

    @Autowired
    private AppProperties appProp;

    public CalculatorResponseBody POST_Calculator(String path, CalculatorRequestBody requestBody) {
        CalculatorResponseBody objCalcResBody = new CalculatorResponseBody();

        try {
            OkHttpUtil okHttpUtil = new OkHttpUtil();
            okHttpUtil.init(true);
            Request.Builder builder = okHttpUtil.getBuilder();

            String jsonBody = gson.toJson(requestBody);

            RequestBody body = RequestBody.create(ParamSdk.JSON, jsonBody);
            log.debug("POST_Calculator() body yang di tangkap: {}", body);

            Request request = builder
                    .url(appProp.OCBC_WPP_BASE_URL + path)
                    .post(body)
                    .build();

            Response response = okHttpUtil.getClient().newCall(request).execute();
            objCalcResBody = gson.fromJson(response.body().string(), CalculatorResponseBody.class);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return objCalcResBody;
    }

    JSONObject POST_Data_Leads(String path, DataLeads requestBody) {
        JSONObject obj = new JSONObject();

        try {
            OkHttpUtil okHttpUtil = new OkHttpUtil();
            okHttpUtil.init(true);
            Request.Builder builder = okHttpUtil.getBuilder();

            String jsonBody = gson.toJson(requestBody);

            RequestBody body = RequestBody.create(ParamSdk.JSON, jsonBody);

            Request request = builder
                    .url(appProp.OCBC_WPP_BASE_URL + path)
                    .post(body)
                    .build();

            Response response = okHttpUtil.getClient().newCall(request).execute();
            obj = gson.fromJson(response.body().string(), JSONObject.class);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return obj;
    }
}
