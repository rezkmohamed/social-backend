package com.scai.socialproject.alpha.socialnetworkalpha.controller;

import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.scai.socialproject.alpha.socialnetworkalpha.dto.User;
import com.scai.socialproject.alpha.socialnetworkalpha.entity.Profile;
import com.scai.socialproject.alpha.socialnetworkalpha.service.ProfileService;
import com.scai.socialproject.alpha.socialnetworkalpha.utils.RequestUtils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController("")
public class AuthController {
	@Autowired
	private ProfileService profileService;
	@Autowired
	private RequestUtils requestUtils;
	@Value("${signingKey}")
	private String signingKey;

	@PostMapping("/login")
	public ResponseEntity<User> login(@RequestBody User user){	
		User get = profileService.login(user.getEmail(), user.getPass());
		
		if(user != null) {
			HttpHeaders headers = new HttpHeaders();
        	HashMap<String, Object> addedValues = new HashMap<String, Object>();
        	addedValues.put("idUser", get.getIdUser());
        	addedValues.put("nickname", get.getNickname());
			String token = Jwts.builder()
					.addClaims(addedValues)
					.setIssuedAt(new Date(System.currentTimeMillis()))
					.setExpiration(new Date(System.currentTimeMillis() + 4 * 120 * 60 * 1000))
					.signWith(SignatureAlgorithm.HS512, this.signingKey).compact();
			headers.add("Authentication", "Bearer " + token);
			return ResponseEntity.ok().headers(headers).build();
		}
		
		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	}
	
	@PostMapping("checkpassword")
	public ResponseEntity<HttpStatus> checkPassword(@RequestBody User user, HttpServletRequest request){
		String idProfile = requestUtils.idProfileFromToken(request);
		user.setIdUser(idProfile);
		
		if(profileService.checkEmail(user)) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@PostMapping("/register")
	public ResponseEntity<HttpStatus> register(@RequestBody Profile profile) {
		if(profileService.saveProfile(profile)) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@PutMapping("resetpassword")
	public ResponseEntity<HttpStatus> resetPassword(@RequestBody String email){
		if(profileService.resetPassword(email)) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
}
