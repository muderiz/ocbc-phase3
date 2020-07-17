/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imi.dolphin.sdkwebservice.controller;

import com.imi.dolphin.sdkwebservice.facade.LifeGoalService;
import com.imi.dolphin.sdkwebservice.model.ExtensionRequest;
import com.imi.dolphin.sdkwebservice.model.ExtensionResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Deka
 */
@RestController
public class LifeGoalController {

    private static final Logger log = LogManager.getLogger(LifeGoalController.class);

    @Autowired
    LifeGoalService lifeGoalService;

    /**
     * memanggil service dengan endPoint /lifegoal/validasiLifegoal
     * @param extentionRequest digunakan untuk memanggil data-data dari response user yang sudah diolah oleh bot
     * @return Json dengan format class ExtentionResult
     */
    @RequestMapping
    @PostMapping("/lifegoal/validasiLifegoal")
    public ExtensionResult LifeGoal_validasiLifeGoal(@RequestBody ExtensionRequest extentionRequest) {
        return lifeGoalService.LifeGoal_validasiLifeGoal(extentionRequest);
    }
}
