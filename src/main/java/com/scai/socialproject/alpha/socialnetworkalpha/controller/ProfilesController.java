package com.scai.socialproject.alpha.socialnetworkalpha.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scai.socialproject.alpha.socialnetworkalpha.dto.FollowDTO;
import com.scai.socialproject.alpha.socialnetworkalpha.dto.ProfileDTO;
import com.scai.socialproject.alpha.socialnetworkalpha.entity.Follow;
import com.scai.socialproject.alpha.socialnetworkalpha.entity.Post;
import com.scai.socialproject.alpha.socialnetworkalpha.entity.Profile;
import com.scai.socialproject.alpha.socialnetworkalpha.service.FollowService;
import com.scai.socialproject.alpha.socialnetworkalpha.service.PostService;
import com.scai.socialproject.alpha.socialnetworkalpha.service.ProfileService;
import com.scai.socialproject.alpha.socialnetworkalpha.utils.DTOutils;

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
	
	
	//FIXME
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
	
	//OK
	@GetMapping("/{idProfile}/followers")
	public List<FollowDTO> findFollowersForProfile(@PathVariable("idProfile") String idProfile){
		return followService.findFollowersForProfile(idProfile);
	}	
	//OK
	@GetMapping("/{idProfile}/following")
	public List<FollowDTO> findFollowingForProfile(@PathVariable("idProfile") String idProfile){
		return followService.findFollowingForProfile(idProfile);
	}
	
	//OK
	@DeleteMapping("/{idFollower}/unfollow/{idFollowed}")
	public void deleteFollowByIdFollower(@PathVariable("idFollower") String idFollower,@PathVariable("idFollowed") String idFollowed) {
		this.followService.deleteFollow(idFollower, idFollowed);
	}
	
	//OK
	@GetMapping("/test/follow")
	public List<FollowDTO> testFollowHibernate(){
		Profile profile1 = profileService.findProfile("2391f55f-2444-4055-80b4-4b3fd412dd29");
		Profile profile2 = profileService.findProfile("8f016dd7-92e7-41bc-91e2-bde9cedbbd17");
		Follow follow = new Follow();
		follow.setDate("31/03/2022");
		follow.setFollower(profile1);
		follow.setFollowed(profile2);
		followService.saveFollow(follow);
		
		return followService.findAllFollowers();
	}
	
	//OK
	@GetMapping("/test/profilesandposts")
	public Profile testProfileAndPostHibernate(){
		Profile testProfile = new Profile("mohamed", "mohamed99",
				"bio scema", "profilepath1", "mohamed@rezk.com", "password");
		Post post1 = new Post("img1", "description1", "11/04/2021");
		Post post2 = new Post("img2", "description2", "11/04/2021");

		testProfile.addPost(post1);
		testProfile.addPost(post2);
		post1.setProfile(testProfile);
		post2.setProfile(testProfile);
		
		profileService.saveProfile(testProfile);
		
		return testProfile;
	}

}
