package com.scai.socialproject.alpha.socialnetworkalpha.utils;

import java.io.IOException;
import java.util.Base64;

import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

public class ImgUtils {
	
	public static String fileImgToBase64Encoding(MultipartFile file) throws IOException {
		
		
		
		
		
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		if(fileName.contains("..")) {
			System.out.println("not a valid file.");
		}
	
		String img = Base64.getEncoder().encodeToString(file.getBytes());
		System.out.println(img);
		return img;
	}
}
