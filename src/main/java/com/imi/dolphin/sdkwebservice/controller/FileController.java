/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imi.dolphin.sdkwebservice.controller;

import com.imi.dolphin.sdkwebservice.facade.FileService;
import com.imi.dolphin.sdkwebservice.property.AppProperties;
import com.imi.dolphin.sdkwebservice.util.AES256Util;
import com.imi.dolphin.sdkwebservice.util.FileStorageUtil;
import java.io.IOException;
import java.security.spec.InvalidKeySpecException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author cokkyturnip
 */
@RestController
@RequestMapping("/pdf")
public class FileController {

	@Autowired
	FileService fileService;

	@Autowired
	AppProperties appProp;

	@Autowired
	AES256Util aesUtil;

	@GetMapping("/{encrypted}")
	public ResponseEntity<Resource> downloadFile(
			HttpServletRequest request,
			@PathVariable String encrypted) throws InvalidKeySpecException, Exception {
//			@PathVariable String token) throws InvalidKeySpecException, Exception {

		String[] arrStrings = aesUtil.decrypt(encrypted.replace("&", "/"))
				.split("&split&");
		
		Boolean exp = fileService.validateToken(arrStrings[0]);
		
		if (!exp) {
			throw new Exception("File has been expired");
		}

		String fileName = arrStrings[1];

		Resource resource = fileService.download(fileName);

		// Try to determine file's content type
		String contentType = null;
		try {
			contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		// Fallback to the default content type if type could not be determined
		if (contentType == null) {
			contentType = "application/octet-stream";
		}

		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType(contentType))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
				.body(resource);
	}
}
