/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imi.dolphin.sdkwebservice.service;

import com.imi.dolphin.sdkwebservice.model.RiskProfileResponse;
import com.imi.dolphin.sdkwebservice.util.OkHttpUtil;
import java.io.FileNotFoundException;
import java.io.IOException;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.RequestBody;

/**
 *
 * @author chochong
 */
public class GetBodyFromAPI {

    public String getBodyformApi() {
        String a = "";
        try {
            OkHttpUtil okHttpUtil = new OkHttpUtil();
            okHttpUtil.init(true);
            Request request = new Request.Builder().url("https://api.myjson.com/bins/w0j2f").get().build();
            Response response = okHttpUtil.getClient().newCall(request).execute();
            JSONObject jsonobj = new JSONObject(response.body().string());
            a = jsonobj.getString("nama");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return a;
    }

    public int getBodyRiskProfile() {
        int a = 0;
        RiskProfileResponse riskProfile = new RiskProfileResponse();
        RiskProfileResponse responseRiskProfile = new RiskProfileResponse();
        try {
            OkHttpUtil okHttpUtil = new OkHttpUtil();
            okHttpUtil.init(true);
            Request request = new Request.Builder().url("https://api.myjson.com/bins/rcljj").get().build();
            Response response = okHttpUtil.getClient().newCall(request).execute();
            JSONObject jsonobj = new JSONObject(response.body().string());
            a = jsonobj.getInt("Channel_ID");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return a;
    }

    public RiskProfileResponse getBodyObjectRiskProfile() {
        int a = 0;
        RiskProfileResponse responseRiskProfile = new RiskProfileResponse();
        try {
            OkHttpUtil okHttpUtil = new OkHttpUtil();
            okHttpUtil.init(true);
            Request request = new Request.Builder().url("https://api.myjson.com/bins/rcljj").get().build();
            Response response = okHttpUtil.getClient().newCall(request).execute();
            JSONObject jsonobj = new JSONObject(response.body().string());
            a = jsonobj.getInt("Risk_Profile_Type");
            responseRiskProfile.setChannel_ID(a+"");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseRiskProfile;
    }
    
    public String getBodyFormApi_RiskProfile(okhttp3.RequestBody body) {
        String Risk_Profile_Type = "";
        try {
            OkHttpUtil okHttpUtil = new OkHttpUtil();
            okHttpUtil.init(true);
            Request request = new Request.Builder().url("https://api.myjson.com/bins/rcljj").post(body).build();
            Response response = okHttpUtil.getClient().newCall(request).execute();
            JSONObject jsonobj = new JSONObject(response.body().string());
            Risk_Profile_Type = jsonobj.getString("Risk_Profile_Type");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Risk_Profile_Type;
    }
}
