package com.scai.socialproject.alpha.socialnetworkalpha.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.scai.socialproject.alpha.socialnetworkalpha.dto.ProfileDTO;
import com.scai.socialproject.alpha.socialnetworkalpha.dto.User;
import com.scai.socialproject.alpha.socialnetworkalpha.entity.Profile;

public class DTOProfileUtils {
	private static ProfileDTO DTOFromProfile(Profile profile) {
		ProfileDTO tmp = new ProfileDTO(profile.getIdProfile(),
										profile.getName(),
										profile.getNickname(),
										profile.getBio(),
										profile.getProPic(),
										profile.getEmail());
		return tmp;
	}
	
	public static List<ProfileDTO> profileToDTO(List<Profile> profiles){
		List<ProfileDTO> ris = new ArrayList<>();
		for(Profile profile : profiles) {
			ProfileDTO tmp = DTOFromProfile(profile);
			try {
				tmp.setProPic(ImgUtils.fileImgToBase64Encoding(tmp.getProPic()));
			} catch (IOException e) {
				e.printStackTrace();
			}
			ris.add(tmp);
		}
		
		return ris;
	}
	
	public static ProfileDTO profileToDTO(Profile profile) {
		ProfileDTO ris = DTOFromProfile(profile);
		return ris;
	}
	
	public static User profileToUser(Profile profile) {
		return new User(profile.getIdProfile(), profile.getNickname(),profile.getEmail(), profile.getPassword());
	}

}
