/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imi.dolphin.sdkwebservice.service;

import com.imi.dolphin.sdkwebservice.property.AppProperties;
import com.imi.dolphin.sdkwebservice.util.OkHttpUtil;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Base64;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.commons.io.FileUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author cokkyturnip
 */
@Service
public class GeneratePDF {

	@Autowired
	AppProperties appProp;

	public String generate(String report_code) {
		String full_url = appProp.OCBC_WPP_PATH_PDF_GENERATOR.replace("%report_code%", report_code);
		String path_file = appProp.OCBC_FOLDER_PDF + report_code + ".pdf";
		StringBuilder contentBuilder = new StringBuilder();
		try {
			OkHttpUtil okHttpUtil = new OkHttpUtil();
			okHttpUtil.init(true);
			Request request = new Request.Builder().url(appProp.OCBC_WPP_BASE_URL + full_url).get().build();
			Response response = okHttpUtil.getClient().newCall(request).execute();

			JSONObject jsonobj = new JSONObject(response.body().string());

			byte[] decodedBytes = Base64.getDecoder().decode(jsonobj.getString("PDF_Report").replace("\n", ""));
			FileUtils.writeByteArrayToFile(new File(path_file), decodedBytes);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			path_file = "";
		} catch (IOException e) {
			e.printStackTrace();
			path_file = "";
		}
		return path_file;
	}
}
