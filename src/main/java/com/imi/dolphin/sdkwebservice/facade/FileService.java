/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imi.dolphin.sdkwebservice.facade;

import com.imi.dolphin.sdkwebservice.model.UserToken;
import com.imi.dolphin.sdkwebservice.property.AppProperties;
import com.imi.dolphin.sdkwebservice.util.FileStorageUtil;
import java.security.spec.InvalidKeySpecException;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

/**
 *
 * @author cokkyturnip
 */
@Service
public class FileService {

	@Autowired
	AppProperties appProp;

	UserToken userToken;

        /**
         * to get file resource
         * @param fileName is parameter local
         * @return resource
         * @throws InvalidKeySpecException 
         */
	public Resource download(String fileName) throws InvalidKeySpecException {

		// Load file as Resource
//		fileName = fileName + ".pdf";
		String fullPath = appProp.OCBC_FOLDER_PDF;

		FileStorageUtil fileStorageUtil = new FileStorageUtil(fullPath);

		Resource resource = fileStorageUtil.loadFileAsResource(fileName);

		return resource;
	}

        /**
         * to get status token valid or invalid
         * @param token is variable local
         * @return boolean true or false
         * @throws InvalidKeySpecException s
         */
	public Boolean validateToken(String token) throws InvalidKeySpecException {
//		String strDateTime = new AES256Util(appProp.OCBC_ENCRYPT_KEY,
//				appProp.OCBC_ENCRYPT_SALT)
//				.decrypt(token);

		String[] strDateTime = token.split(" ");
		String[] strDate = strDateTime[0].split("-");
		String[] strTime = strDateTime[1].split(":");
		DateTime dtNow = DateTime.now();
		DateTime dtToken = new DateTime(
				Integer.parseInt(strDate[0]), Integer.parseInt(strDate[1]), Integer.parseInt(strDate[2]),
				Integer.parseInt(strTime[0]), Integer.parseInt(strTime[1]), Integer.parseInt(strTime[2])
		);

		return (dtNow.minusSeconds(Integer.parseInt(appProp.OCBC_ENCRYPT_TOKEN_EXP)).compareTo(dtToken) <= 0);
	}
}
