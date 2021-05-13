package com.scai.socialproject.alpha.socialnetworkalpha.repository;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.scai.socialproject.alpha.socialnetworkalpha.dto.ProfileDTO;
import com.scai.socialproject.alpha.socialnetworkalpha.dto.User;
import com.scai.socialproject.alpha.socialnetworkalpha.entity.Profile;

public interface CrudProfile {
	public List<ProfileDTO> findAllProfiles();
	
	public List<ProfileDTO> findProfilesLikesPost(String idPost);
	
	public List<ProfileDTO> findFollowersProfile(String idProfile);
	
	public List<ProfileDTO> findFollowingProfile(String idProfile);
	
	public List<ProfileDTO> searchProfilesByName(String profileName);
	
	public ProfileDTO findProfileById(String idProfile);
	
	public Profile findProfile(String idProfile);
	
	public void saveProfile(Profile profile);
	
	public void updateProfile(Profile profile);
	
	public void deleteProfileById(String idProfile);
	
	public User getUserAuth(String email, String pass);
}
