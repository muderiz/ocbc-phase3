/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imi.dolphin.sdkwebservice.facade;

import com.google.gson.Gson;
import static com.imi.dolphin.sdkwebservice.facade.RekomendasiProductService.OUTPUT;
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
import java.util.HashMap;
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
public class ValidasiService {
    
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
    
    public ExtensionResult validasi_konfirmasi(ExtensionRequest extensionRequest) {
        log.debug("validasi_konfirmasi() extension request: {}", new Gson().toJson(extensionRequest, ExtensionRequest.class));
        ExtensionResult extensionResult = new ExtensionResult();
        extensionResult.setAgent(false);
        extensionResult.setRepeat(false);
        extensionResult.setSuccess(true);
        extensionResult.setNext(true);
        Map<String, String> clearEntities = new HashMap<>();

        String validasiwebview = dialogUtil.getEasyMapValueByName(extensionRequest, "konfirmasi").toLowerCase();
        if (validasiwebview.equalsIgnoreCase("mulai berinvestasi sekarang")) {
            clearEntities.put("before_final", "Done");
        }
        else {
            clearEntities.put("konfirmasi", "");
            clearEntities.put("validasi_konfirmasi", "");
            Map<String, String> output = new HashMap<>();
            output.put(OUTPUT, "");
            extensionResult.setValue(output);
        }
        extensionResult.setEntities(clearEntities);
        return extensionResult;
    }
}
