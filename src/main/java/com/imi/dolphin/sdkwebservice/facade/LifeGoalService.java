/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imi.dolphin.sdkwebservice.facade;

import com.imi.dolphin.sdkwebservice.model.ExtensionRequest;
import com.imi.dolphin.sdkwebservice.model.ExtensionResult;
import static com.imi.dolphin.sdkwebservice.service.ServiceImp.OUTPUT;
import com.imi.dolphin.sdkwebservice.service.ValidationMethod;
import com.imi.dolphin.sdkwebservice.util.DialogUtil;
import java.util.HashMap;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Deka
 */
@Service
public class LifeGoalService {

    private static final Logger log = LogManager.getLogger(LifeGoalService.class);

    @Autowired
    DialogUtil dialogUtil;

    @Autowired
    ValidationMethod validationMethod;

    /**
     * key output digunkan untuk menampilkan response dari bot sesuai dengan
     * jawaban yang telah di set didalam file dialog
     *
     * @param extensionRequest digunakan untuk memanggil data-data dari response
     * user yang sudah diolah oleh bot
     * @return extensionResult mengirimkan json dengan format class
     * extensionResult
     */
    public ExtensionResult LifeGoal_validasiLifeGoal(ExtensionRequest extensionRequest) {
        log.debug("log validasi life goal - Extention Request : ", extensionRequest);
        String lifeGoal = dialogUtil.getEasyMapValueByName(extensionRequest, "lifegoal").toLowerCase();
        Map<String, String> entities = new HashMap<>();
        Map<String, String> output = new HashMap<>();

        entities = validationMethod.valLifeGoal(lifeGoal);
        String bubble = dialogUtil.CreateBubble("maaf", 1, null);

        output.put(OUTPUT, bubble);
        ExtensionResult extensionResult = new ExtensionResult();
        extensionResult.setEntities(entities);
        extensionResult.setAgent(false);
        extensionResult.setRepeat(false);
        extensionResult.setSuccess(true);
        extensionResult.setNext(true);
        extensionResult.setValue(output);

        return extensionResult;
    }

}
