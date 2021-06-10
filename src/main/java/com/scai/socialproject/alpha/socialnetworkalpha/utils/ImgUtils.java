package com.scai.socialproject.alpha.socialnetworkalpha.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

public class ImgUtils {
	
	public static String fileImgToBase64Encoding(String proPic) throws IOException {
		String basePathFileSystem = "C:\\immagini\\";

		Path path = Paths.get(basePathFileSystem + proPic);
		byte[] content = null;
	    content = Files.readAllBytes(path);

		String img = Base64.getEncoder().encodeToString(content);
		
		return img;
	}
}