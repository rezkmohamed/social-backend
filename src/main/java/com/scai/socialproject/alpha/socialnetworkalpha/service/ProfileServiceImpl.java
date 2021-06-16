package com.scai.socialproject.alpha.socialnetworkalpha.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.scai.socialproject.alpha.socialnetworkalpha.dto.NewPasswordDTO;
import com.scai.socialproject.alpha.socialnetworkalpha.dto.PostDTO;
import com.scai.socialproject.alpha.socialnetworkalpha.dto.ProfileDTO;
import com.scai.socialproject.alpha.socialnetworkalpha.dto.User;
import com.scai.socialproject.alpha.socialnetworkalpha.entity.Post;
import com.scai.socialproject.alpha.socialnetworkalpha.entity.Profile;
import com.scai.socialproject.alpha.socialnetworkalpha.repository.CrudProfile;
import com.scai.socialproject.alpha.socialnetworkalpha.utils.ImgUtils;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


@Service
public class ProfileServiceImpl implements ProfileService {
	private CrudProfile profileRepo;
	@Value("${basePathFileSystem}")
	private String basePathFileSystem;
	@Value("${signingKey}")
	private String signingKey;
	private ImgUtils imgUtils;
	
	@Autowired
	public ProfileServiceImpl(CrudProfile profileRepo, ImgUtils imgUtils) {
		this.profileRepo = profileRepo;
		this.imgUtils = imgUtils;
	}
	
	@Override
	@Transactional
	public List<ProfileDTO> findAllProfiles() {
		return profileRepo.findAllProfiles();
	}

	@Override
	@Transactional
	public ProfileDTO findProfileById(String idProfile) throws IOException {
		ProfileDTO profile = profileRepo.findProfileById(idProfile);
		if(profile.getProPic() != null) {
			profile.setProPic(imgUtils.fileImgToBase64Encoding(profile.getProPic()));
		}
		
		profile.setPostsCounter(profile.getPosts().size());
		
		List<PostDTO> postsSorted = 
		profile.getPosts().stream()
		.sorted(Comparator.comparing(
				PostDTO::getLocalDate,
				Comparator.reverseOrder()
				))
		.limit(6)
		.collect(Collectors.toList());
		
		postsSorted.stream()
		.forEach(p -> {
			try {
				p.setUrlImg(imgUtils.fileImgToBase64Encoding(p.getUrlImg()));
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		
		profile.setPosts(postsSorted);
		return profile;		
	}

	@Override
	@Transactional
	public boolean saveProfile(Profile profile) {		
		boolean ris = !profileRepo.findAllProfiles().stream()
		.filter(p -> p.getEmail().equals(profile.getEmail()))
		.findFirst().isPresent();
		
		if(ris) {
			profileRepo.saveProfile(profile);
		}
		
		return ris;
	}

	@Override
	@Transactional
	public boolean updateProfile(ProfileDTO profileDTO) {
		Profile profile = profileRepo.findProfile(profileDTO.getId());
		if(profile == null) {
			return false;
		}
		
		profile.setName(profileDTO.getName()); profile.setNickname(profileDTO.getNickname());
		profile.setBio(profileDTO.getBio()); profile.setProPic(profileDTO.getProPic());
		profile.setEmail(profileDTO.getEmail());
		
		profileRepo.updateProfile(profile);
		return true;
	}
	
	@Override
	@Transactional
	public boolean uploadProfilePicture(MultipartFile file, String idProfile) throws IllegalStateException, IOException {
		String filename = file.getOriginalFilename();
		System.out.println(file.getOriginalFilename());
		String extension  = filename.substring(filename.lastIndexOf(".") + 1);
		System.out.println(filename);
		System.out.println(extension);
		

		if(extension.equalsIgnoreCase("jpeg") || extension.equalsIgnoreCase("png")) {
			String newProfilePic = UUID.randomUUID().toString()+ "." + extension;
			file.transferTo(new File(basePathFileSystem + newProfilePic));
			Profile profile = profileRepo.findProfile(idProfile);
			profile.setProPic(newProfilePic);
			profileRepo.saveProfile(profile);
			return true;
		}

		return false;
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
	public User getUserAuth(String email, String pass) {
		return profileRepo.getUserAuth(email, pass);
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
					.setExpiration(new Date(System.currentTimeMillis() + 120 * 60 * 1000))
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
	public boolean checkEmail(User user) {
		Profile profile = profileRepo.findProfile(user.getIdUser());
		if(profile != null) {
			if(profile.getPassword().equals(user.getPass())) {
				return true;
			}
		}
		
		return false;
	}

	@Override
	@Transactional
	public boolean updatePassword(NewPasswordDTO newPasswordDTO) {
		Profile profile = profileRepo.findProfile(newPasswordDTO.getIdProfile());
		
		if(profile != null) {
			profile.setPassword(newPasswordDTO.getNewPassword());
			profileRepo.updateProfileEntity(profile);
			return true;
		}
		
		return false;
	}

	@Override
	@Transactional
	public boolean resetPassword(String email) {
		Profile profile = profileRepo.findProfileByEmail(email);
		if(profile != null ) {
			if(profile.getEmail().equals(email)) {
				profile.setPassword("password");
				profileRepo.updateProfile(profile);
				return true;
			}
		}
		
		return false;
	}
}
