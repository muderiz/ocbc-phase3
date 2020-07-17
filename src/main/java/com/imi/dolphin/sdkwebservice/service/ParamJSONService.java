/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imi.dolphin.sdkwebservice.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.imi.dolphin.sdkwebservice.model.Country;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;

/**
 *
 * @author cokkyturnip
 */
@Component
public class ParamJSONService {

    public String getJSONStringfromFile(String fileName) {

        File file = FileUtils.getFile(fileName);

        StringBuilder contentBuilder = new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String sCurrentLine;
            while ((sCurrentLine = bufferedReader.readLine()) != null) {
                contentBuilder.append(sCurrentLine);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contentBuilder.toString();
    }

    public List<Country> getListCountryfromFileJson(String fileJson) {
        String strJson = getJSONStringfromFile(fileJson);
        Gson gson = new Gson();

        List<Country> list = gson.fromJson(strJson.toString(), new TypeToken<List<Country>>() {
        }.getType());
        return list;
    }

}
