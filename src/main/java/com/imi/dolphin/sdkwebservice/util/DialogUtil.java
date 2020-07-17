/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imi.dolphin.sdkwebservice.util;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.imi.dolphin.sdkwebservice.builder.QuickReplyBuilder;
import com.imi.dolphin.sdkwebservice.model.Dialog;
import com.imi.dolphin.sdkwebservice.model.EasyMap;
import com.imi.dolphin.sdkwebservice.model.ExtensionRequest;
import com.imi.dolphin.sdkwebservice.model.Location;
import com.imi.dolphin.sdkwebservice.service.ServiceImp;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import okhttp3.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

/**
 *
 * @author cokkyturnip
 */
@Component
public class DialogUtil {

    private final String tagParam = "#";
    private final String path = System.getProperty("user.dir");
    private String fileJSON = "dialog.json";
    private String fileJSON_location = "location.json";
    private static final Logger log = LogManager.getLogger(ServiceImp.class);

    private List<Dialog> getListDialog(String path) {
        StringBuilder contentBuilder = new StringBuilder();
        File file = null;
        try {
            file = ResourceUtils.getFile(path + "/" + fileJSON);
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String sCurrentLine;
            while ((sCurrentLine = bufferedReader.readLine()) != null) {
                contentBuilder.append(sCurrentLine);
            }
            Gson gson = new Gson();
            List<Dialog> list = gson.fromJson(contentBuilder.toString(), new TypeToken<List<Dialog>>() {
            }.getType());
            return list;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private List<Location> getListLocation(String path) {
        StringBuilder contentBuilder = new StringBuilder();
        File file = null;
        try {
            file = ResourceUtils.getFile(path + "/" + fileJSON_location);
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String sCurrentLine;
            while ((sCurrentLine = bufferedReader.readLine()) != null) {
                contentBuilder.append(sCurrentLine);
            }
            Gson gson = new Gson();
            List<Location> list = gson.fromJson(contentBuilder.toString(), new TypeToken<List<Location>>() {
            }.getType());
            return list;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String CreateBubble(String stepname, int index, Map<String, String> params) {
        List<Dialog> listDialog = getListDialog(path);

        StringBuilder respBuilder = new StringBuilder();

//        Cari Dialog yang diinginkan
        Dialog objDialog = listDialog.stream()
                .filter(dialog -> dialog.getStep().equals(stepname))
                .filter(dialog -> new Integer(dialog.getIndex()).equals(index))
                .findAny()
                .orElse(null);

        if (objDialog != null) {//Jika dialog yang diinginkan ditemukan

            String dialog = objDialog.getDialog().replace("\\n", System.lineSeparator());

            //Jika dialog perlu direplace dengan params
            if (params != null) {
                for (String key : params.keySet()) {
                    dialog = dialog.replaceAll(tagParam + key + tagParam, params.get(key));
                }
            }
            respBuilder.append(dialog);

            switch (objDialog.getAction()) {
                case "QR"://Jika action berupa Quick Reply
                    Map<String, String> map = new LinkedHashMap<>();
                    String[] arrMap = objDialog.getMap().split("#");
                    for (String objMap : arrMap) {
                        map.put(objMap.split(",")[0].trim(), objMap.split(",")[1].trim());
                    }

                    QuickReplyBuilder quickReplyBuilder = new QuickReplyBuilder.Builder(respBuilder + "").addAll(map).build();
                    return quickReplyBuilder.string();

            }

            return respBuilder.toString();
        } else {
            return "Dialog Was Not Found";
        }
    }

    public String getEasyMapValueByName(ExtensionRequest extensionRequest, String name) {
        log.debug("getEasyMapValueByName() extension request: {} name: {}", extensionRequest, name);
        EasyMap easyMap = extensionRequest.getParameters().stream().filter(x -> x.getName().equals(name)).findAny()
                .orElse(null);
        if (easyMap != null) {
            return easyMap.getValue();
        }
        return "";
    }

    public List<Location> location(String namaKota) {
        List<Location> objKota = null;
        log.debug("location() objectKota {}", namaKota);
        try {
            List<Location> listKota = getListLocation(path);
            String kotaBesar = namaKota.toUpperCase();

            objKota = listKota.stream()
                    .filter(kota -> kota.getKota().contains(kotaBesar))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            log.debug("location() objectKota masukk kedalam exception {}", objKota);
        }
        log.debug("location() return objectKota {}", objKota);
        return objKota;
    }
}
