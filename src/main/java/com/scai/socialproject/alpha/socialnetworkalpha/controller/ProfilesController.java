package com.scai.socialproject.alpha.socialnetworkalpha.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.scai.socialproject.alpha.socialnetworkalpha.dto.FollowDTO;
import com.scai.socialproject.alpha.socialnetworkalpha.dto.ProfileDTO;
import com.scai.socialproject.alpha.socialnetworkalpha.entity.Profile;
import com.scai.socialproject.alpha.socialnetworkalpha.service.FollowService;
import com.scai.socialproject.alpha.socialnetworkalpha.service.PostService;
import com.scai.socialproject.alpha.socialnetworkalpha.service.ProfileService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/profiles")
public class ProfilesController {
	private ProfileService profileService;
	private PostService postService;
 	private FollowService followService;
	
	@Autowired
	public ProfilesController(ProfileService profileService, FollowService followService,
							  PostService postService) {
		this.profileService = profileService;
		this.followService = followService;
		this.postService = postService;
	}
	
	//OK!
	@GetMapping("")
	public List<ProfileDTO> findAllProfiles(){
		return profileService.findAllProfiles();
	}
	
	//OK!
	@GetMapping("/likes/{idPost}")
	public List<ProfileDTO> findProfilesLikesForPost(@PathVariable String idPost){
		return profileService.findProfilesLikesPost(idPost);
	}
	
	//OK
	@GetMapping("/{idProfile}")
	public ProfileDTO findProfileById(@PathVariable String idProfile) {
		ProfileDTO profile = profileService.findProfileById(idProfile);
		profile.setPosts(postService.findPostsProfilePage(idProfile));
		return profile;
	}
	
	@PostMapping("")
    public Profile addProfile(@RequestBody Profile profile) {
		profileService.saveProfile(profile);
		return profile;
	}
	
	//OK!
	@DeleteMapping("/{idProfile}")
	public String deleteProfile(@PathVariable String idProfile) {
		ProfileDTO profile = profileService.findProfileById(idProfile);
		if(profile == null) {
			throw new RuntimeException("ERROR - PROFILE WITH ID: " + idProfile + " NOT FOUND");
		}
		
		profileService.deleteProfileById(idProfile);
		return "SUCCESS - PROFILE DELETED WITH ID: " + idProfile;
	}
	
	@GetMapping("/{idProfile}/followers")
	public List<ProfileDTO> findFollowersForProfile(@PathVariable("idProfile") String idProfile){
		return profileService.findFollowersProfile(idProfile);
	}
	
	@GetMapping("/{idProfile}/following")
	public List<ProfileDTO> findFollowingForProfile(@PathVariable("idProfile") String idProfile){
		return profileService.findFollowingProfile(idProfile);
	}
	
	//OK!
	@GetMapping("/search/{profileName}")
	public List<ProfileDTO> searchProfilesByName(@PathVariable String profileName){
		return profileService.searchProfilesByName(profileName);
	}
	
	@PutMapping("")
	public void updateAccount(@RequestBody ProfileDTO profileDTO) {
		profileService.updateProfile(profileDTO);
	}
	
	@PostMapping("/{idFollower}/follow/{idFollowed}")
	public FollowDTO addFollow(@PathVariable("idFollower") String idFollower,@PathVariable("idFollowed") String idFollowed) {
		return followService.addFollow(idFollower, idFollowed);
	}
	
	//OK
	@DeleteMapping("/{idFollower}/unfollow/{idFollowed}")
	public ResponseEntity<String> deleteFollowByIdFollower(@PathVariable("idFollower") String idFollower,@PathVariable("idFollowed") String idFollowed) {
		return this.followService.deleteFollow(idFollower, idFollowed);
	}

}
