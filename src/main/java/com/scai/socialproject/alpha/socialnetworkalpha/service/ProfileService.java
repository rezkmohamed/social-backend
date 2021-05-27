package com.scai.socialproject.alpha.socialnetworkalpha.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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
	
	public ResponseEntity<ProfileDTO> findProfileById(String idProfile);
	
	public Profile findProfile(String idProfile);
	
	public ResponseEntity<HttpStatus> saveProfile(Profile profile);
	
	public ResponseEntity<ProfileDTO> updateProfile(ProfileDTO profileDTO);
	
	public void deleteProfileById(String idProfile);
	
	public ResponseEntity<User> login(String email, String pass);
	
	public ResponseEntity<User> checkEmail(User user);
	
	public ResponseEntity<HttpStatus> updatePassword(NewPasswordDTO newPasswordDTO);
}
