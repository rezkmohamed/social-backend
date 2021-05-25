package com.scai.socialproject.alpha.socialnetworkalpha.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scai.socialproject.alpha.socialnetworkalpha.dto.FollowDTO;
import com.scai.socialproject.alpha.socialnetworkalpha.dto.ProfileDTO;
import com.scai.socialproject.alpha.socialnetworkalpha.entity.Follow;
import com.scai.socialproject.alpha.socialnetworkalpha.entity.Profile;
import com.scai.socialproject.alpha.socialnetworkalpha.service.FollowService;
import com.scai.socialproject.alpha.socialnetworkalpha.service.ProfileService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("followers")
public class FollowsController {
	private FollowService followService;
	private ProfileService profileService;
	
	@Autowired
	public FollowsController(FollowService followService, ProfileService profileService) {
		this.followService = followService;
		this.profileService = profileService;
	}
	
	//OK!
	@GetMapping("/allfollow")
	public List<FollowDTO> findAllFollowers(){
		return followService.findAllFollowers();
	}
	
	//OK
	@GetMapping("/{idProfile}/followers")
	public List<ProfileDTO> findFollowersForProfile(@PathVariable("idProfile") String idProfile){
		return profileService.findFollowersProfile(idProfile);
	}
	
	//OK
	@GetMapping("/{idProfile}/following")
	public List<ProfileDTO> findFollowingForProfile(@PathVariable("idProfile") String idProfile){
		return profileService.findFollowingProfile(idProfile);
	}
	
	//OK
	@GetMapping("/get/{idFollower}/{idFollowed}")
	public ResponseEntity<FollowDTO> getFollow(@PathVariable String idFollower, @PathVariable String idFollowed) {
		return followService.getFollow(idFollower, idFollowed);
	}
	
	//OK
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
