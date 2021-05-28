package com.scai.socialproject.alpha.socialnetworkalpha.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scai.socialproject.alpha.socialnetworkalpha.dto.NewPasswordDTO;
import com.scai.socialproject.alpha.socialnetworkalpha.dto.ProfileDTO;
import com.scai.socialproject.alpha.socialnetworkalpha.entity.Profile;
import com.scai.socialproject.alpha.socialnetworkalpha.service.ProfileService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/profiles")
public class ProfilesController {
	private ProfileService profileService;
	
	@Autowired
	public ProfilesController(ProfileService profileService) {
		this.profileService = profileService;
	}
	
	//OK!
	@GetMapping("")
	public List<ProfileDTO> findAllProfiles(){
		return profileService.findAllProfiles();
	}
	
	//OK
	@GetMapping("/{idProfile}")
	public ResponseEntity<ProfileDTO> findProfileById(@PathVariable String idProfile) {
		return profileService.findProfileById(idProfile);
	}
	
	@PostMapping("")
    public Profile addProfile(@RequestBody Profile profile) {
		profileService.saveProfile(profile);
		return profile;
	}
	
	//OK!
	/*@DeleteMapping("/{idProfile}")
	public String deleteProfile(@PathVariable String idProfile) {
		ProfileDTO profile = profileService.findProfileById(idProfile);
		if(profile == null) {
			throw new RuntimeException("ERROR - PROFILE WITH ID: " + idProfile + " NOT FOUND");
		}
		
		profileService.deleteProfileById(idProfile);
		return "SUCCESS - PROFILE DELETED WITH ID: " + idProfile;
	}*/
	
	//OK!
	@GetMapping("/search/{profileName}")
	public List<ProfileDTO> searchProfilesByName(@PathVariable String profileName){
		return profileService.searchProfilesByName(profileName);
	}
	
	@PutMapping("")
	public ResponseEntity<HttpStatus> updateAccount(@RequestBody ProfileDTO profileDTO) {
		return profileService.updateProfile(profileDTO);
	}
	
	@PutMapping("newpassword/{idProfile}")
	public ResponseEntity<HttpStatus> updatePassword(@RequestBody NewPasswordDTO newPasswordDTO){
		return profileService.updatePassword(newPasswordDTO);
	}
}
