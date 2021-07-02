package com.scai.socialproject.alpha.socialnetworkalpha.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.scai.socialproject.alpha.socialnetworkalpha.dto.NewPasswordDTO;
import com.scai.socialproject.alpha.socialnetworkalpha.dto.ProfileDTO;
import com.scai.socialproject.alpha.socialnetworkalpha.entity.Profile;
import com.scai.socialproject.alpha.socialnetworkalpha.service.ProfileService;
import com.scai.socialproject.alpha.socialnetworkalpha.utils.DTOProfileUtils;
import com.scai.socialproject.alpha.socialnetworkalpha.utils.RequestUtils;

@RestController
@RequestMapping("/profiles")
public class ProfilesController {
	@Autowired
	private ProfileService profileService;
	@Autowired
	private RequestUtils requestUtils;

	@GetMapping("/{idProfile}")
	public ResponseEntity<ProfileDTO> findProfileById(@PathVariable String idProfile) throws IOException {
		ProfileDTO profile = profileService.findProfileById(idProfile);
		if(profile != null) {
			return new ResponseEntity<>(profile, HttpStatus.OK);
		}
		
		return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
	}
	
	@PostMapping("")
    public ResponseEntity<ProfileDTO> addProfile(@RequestBody Profile profile) {
		if(profileService.saveProfile(profile)) {
			return new ResponseEntity<>(DTOProfileUtils.profileToDTO(profile), HttpStatus.OK);
		}
		return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("/search/{profileName}/{startingIndex}")
	public ResponseEntity<List<ProfileDTO>> searchProfilesByName(@PathVariable String profileName, @PathVariable int startingIndex){
		return new ResponseEntity<>(profileService.searchProfilesByName(profileName, startingIndex), HttpStatus.OK);
	}

	@PostMapping("/updategeneraldata")
	public ResponseEntity<HttpStatus> updateAccount(@RequestBody ProfileDTO profileDTO,
			HttpServletRequest request) throws IOException {
		String idProfile = requestUtils.idProfileFromToken(request);
		profileDTO.setId(idProfile);
		
		if(profileService.updateProfile(profileDTO)) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	
	@PostMapping("/propic")
	public ResponseEntity<HttpStatus> uploadProfilePic(@RequestParam("myFile") MultipartFile file, MultipartHttpServletRequest request) throws IllegalStateException, IOException{
		System.out.println(file.getOriginalFilename());
		String idProfile = requestUtils.idProfileFromToken(request);
		if(profileService.uploadProfilePicture(file, idProfile)) {
			return new ResponseEntity<HttpStatus>(HttpStatus.OK);

		}
		
		return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
	}
	
	@PutMapping("newpassword")
	public ResponseEntity<HttpStatus> updatePassword(@RequestBody NewPasswordDTO newPasswordDTO,HttpServletRequest request){
		String idProfile = requestUtils.idProfileFromToken(request);
		newPasswordDTO.setIdProfile(idProfile);
		
		if(profileService.updatePassword(newPasswordDTO)) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
}
