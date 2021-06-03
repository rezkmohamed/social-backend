package com.scai.socialproject.alpha.socialnetworkalpha.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import com.scai.socialproject.alpha.socialnetworkalpha.utils.RequestUtils;

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
		FollowDTO follow = followService.getFollow(idFollower, idFollowed);
		if(follow == null) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(follow, HttpStatus.OK);
	}
	
	//OK
	@PostMapping("/{idFollower}/follow/{idFollowed}")
	public ResponseEntity<FollowDTO> addFollow(@PathVariable("idFollower") String idFollower,@PathVariable("idFollowed") String idFollowed, HttpServletRequest request) {
		String idProfileFollower = RequestUtils.idProfileFromToken(request);
		FollowDTO ris = followService.addFollow(idProfileFollower, idFollowed);
		
		if(ris == null) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<>(ris, HttpStatus.OK);
	}
	
	//OK
	@DeleteMapping("/{idFollower}/unfollow/{idFollowed}")
	public ResponseEntity<String> deleteFollowByIdFollower(@PathVariable("idFollower") String idFollower,@PathVariable("idFollowed") String idFollowed, HttpServletRequest request) {
		String idProfileFollower = RequestUtils.idProfileFromToken(request);
		if(followService.deleteFollow(idProfileFollower, idFollowed)) {
			return new ResponseEntity<>("OK!", HttpStatus.OK);
		}
		
		
		return new ResponseEntity<>("SOME ERROR JUST HAPPENED", HttpStatus.BAD_REQUEST);
	}
}
