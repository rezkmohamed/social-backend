package com.scai.socialproject.alpha.socialnetworkalpha.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scai.socialproject.alpha.socialnetworkalpha.dto.ProfileDTO;
import com.scai.socialproject.alpha.socialnetworkalpha.dto.User;
import com.scai.socialproject.alpha.socialnetworkalpha.entity.Profile;
import com.scai.socialproject.alpha.socialnetworkalpha.service.ProfileService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController("")
public class AuthController {
	private ProfileService profileService;

	@Autowired
	public AuthController(ProfileService profileService) {
		this.profileService = profileService;
	}
	
	@PostMapping("/login")
	public ResponseEntity<User> login(@RequestBody User user){		
		System.out.println(user);
		return profileService.login(user.getEmail(), user.getPass());
	}
	
	@PostMapping("checkpassword")
	public ResponseEntity<User> checkPassword(@RequestBody User user){
		return profileService.checkEmail(user);
	}
	
	@PostMapping("/register")
	public ResponseEntity<HttpStatus> register(@RequestBody Profile profile) {
		return profileService.saveProfile(profile);
	}
	
	@GetMapping("/testing-auth")
	public String testingAuth() {
		return "auth works";
	}
	
}
