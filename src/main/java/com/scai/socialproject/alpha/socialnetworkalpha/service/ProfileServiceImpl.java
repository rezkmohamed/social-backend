package com.scai.socialproject.alpha.socialnetworkalpha.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.scai.socialproject.alpha.socialnetworkalpha.dto.ProfileDTO;
import com.scai.socialproject.alpha.socialnetworkalpha.dto.User;
import com.scai.socialproject.alpha.socialnetworkalpha.entity.Profile;
import com.scai.socialproject.alpha.socialnetworkalpha.repository.CrudProfile;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import javassist.tools.web.BadHttpRequest;


@Service
public class ProfileServiceImpl implements ProfileService {
	private CrudProfile profileRepo;
	
	@Autowired
	public ProfileServiceImpl(CrudProfile profileRepo) {
		this.profileRepo = profileRepo;
	}
	
	@Override
	@Transactional
	public List<ProfileDTO> findAllProfiles() {
		return profileRepo.findAllProfiles();
	}

	@Override
	@Transactional
	public ProfileDTO findProfileById(String idProfile) {
		return profileRepo.findProfileById(idProfile);
	}

	@Override
	@Transactional
	public ResponseEntity<ProfileDTO> saveProfile(Profile profile) {
		return profileRepo.saveProfile(profile);
	}

	@Override
	@Transactional
	public ResponseEntity<ProfileDTO> updateProfile(ProfileDTO profileDTO) {
		return profileRepo.updateProfile(profileDTO);
	}

	@Override
	@Transactional
	public void deleteProfileById(String idProfile) {
		profileRepo.deleteProfileById(idProfile);
	}

	@Override
	@Transactional
	public Profile findProfile(String idProfile) {
		return profileRepo.findProfile(idProfile);
	}

	@Override
	@Transactional
	public ResponseEntity<User> login(String email, String pass) {
		User user = profileRepo.getUserAuth(email, pass);
		
		if(user != null) {
			HttpHeaders headers = new HttpHeaders();
        	HashMap<String, Object> addedValues = new HashMap<String, Object>();
        	addedValues.put("idUser", user.getIdUser());
        	addedValues.put("nickname", user.getNickname());
			String token = Jwts.builder()
					.addClaims(addedValues)
					.setIssuedAt(new Date(System.currentTimeMillis()))
					.setExpiration(new Date(System.currentTimeMillis() + 60 * 60 * 1000))
					.signWith(SignatureAlgorithm.HS512, "ciao").compact();
			headers.add("Authentication", "Bearer " + token);
			return ResponseEntity.ok().headers(headers).build();
		}
		
		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	}

	@Override
	@Transactional
	public List<ProfileDTO> findProfilesLikesPost(String idPost) {	
		return profileRepo.findProfilesLikesPost(idPost);
	}

	@Override
	@Transactional
	public List<ProfileDTO> findFollowersProfile(String idProfile) {
		return profileRepo.findFollowersProfile(idProfile);
	}

	@Override
	@Transactional
	public List<ProfileDTO> findFollowingProfile(String idProfile) {
		return profileRepo.findFollowingProfile(idProfile);
	}

	@Override
	@Transactional
	public List<ProfileDTO> searchProfilesByName(String profileName) {
		return profileRepo.searchProfilesByName(profileName);
	}

	@Override
	@Transactional
	public ResponseEntity<User> checkEmail(User user) {
		Profile profile = profileRepo.findProfile(user.getIdUser());
		if(profile != null) {
			if(profile.getPassword().equals(user.getPass())) {
				return new ResponseEntity<User>(HttpStatus.OK);
			}
		}
		
		return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
	}

}
