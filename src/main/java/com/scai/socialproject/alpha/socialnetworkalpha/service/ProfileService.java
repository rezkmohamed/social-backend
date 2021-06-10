package com.scai.socialproject.alpha.socialnetworkalpha.service;

import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.scai.socialproject.alpha.socialnetworkalpha.dto.NewPasswordDTO;
import com.scai.socialproject.alpha.socialnetworkalpha.dto.ProfileDTO;
import com.scai.socialproject.alpha.socialnetworkalpha.dto.User;
import com.scai.socialproject.alpha.socialnetworkalpha.entity.Profile;

public interface ProfileService {
	public List<ProfileDTO> findAllProfiles();
	
	public List<ProfileDTO> findProfilesLikesPost(String idPost);
	
	public List<ProfileDTO> findFollowersProfile(String idProfile);
	
	public List<ProfileDTO> findFollowingProfile(String idProfile);
	
	public List<ProfileDTO> searchProfilesByName(String profileName);
	
	public ProfileDTO findProfileById(String idProfile);
	
	public Profile findProfile(String idProfile);
	
	public boolean saveProfile(Profile profile);
	
	public boolean updateProfile(ProfileDTO profileDTO);
	
	public void deleteProfileById(String idProfile);
	
	public ResponseEntity<User> login(String email, String pass);
	
	public boolean checkEmail(User user);
	
	public boolean updatePassword(NewPasswordDTO newPasswordDTO);
	
	public boolean resetPassword(String email);
	
	public boolean uploadProfilePicture(MultipartFile file, String idProfile) throws IllegalStateException, IOException;
}
