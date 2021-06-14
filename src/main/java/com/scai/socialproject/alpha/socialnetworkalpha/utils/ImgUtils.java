package com.scai.socialproject.alpha.socialnetworkalpha.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ImgUtils {
	@Value("${basePathFileSystem}")
	private String basePathFileSystem;
	
	public String fileImgToBase64Encoding(String proPic) throws IOException {
		String extension = proPic.substring(proPic.lastIndexOf(".") + 1);
		
		Path path = Paths.get(basePathFileSystem + proPic);
		byte[] content = null;
	    content = Files.readAllBytes(path);

		String img = "data:image/" + extension + ";base64, " + Base64.getEncoder().encodeToString(content);
		
		return img;
	}
}
