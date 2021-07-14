package com.scai.socialproject.alpha.socialnetworkalpha.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scai.socialproject.alpha.socialnetworkalpha.dto.FollowDTO;
import com.scai.socialproject.alpha.socialnetworkalpha.dto.ProfileDTO;
import com.scai.socialproject.alpha.socialnetworkalpha.service.FollowService;
import com.scai.socialproject.alpha.socialnetworkalpha.service.ProfileService;
import com.scai.socialproject.alpha.socialnetworkalpha.utils.RequestUtils;

@RestController
@RequestMapping("followers")
public class FollowsController {
	@Autowired
	private FollowService followService;
	@Autowired
	private ProfileService profileService;
	@Autowired
	private RequestUtils requestUtils;
	
	//OK
	@GetMapping("/{idProfile}/followers")
	public ResponseEntity<List<ProfileDTO>> findFollowersForProfile(@PathVariable("idProfile") String idProfile){
		try {
			List<ProfileDTO> response = profileService.findFollowersProfile(idProfile);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
		}
		
		return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
	}
	
	//OK
	@GetMapping("/{idProfile}/following")
	public ResponseEntity<List<ProfileDTO>> findFollowingForProfile(@PathVariable("idProfile") String idProfile){
		try {
			List<ProfileDTO> response = profileService.findFollowingProfile(idProfile);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
		}
		
		return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
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
	@PostMapping("/follow/{idFollowed}")
	public ResponseEntity<FollowDTO> addFollow(@PathVariable("idFollowed") String idFollowed, HttpServletRequest request) {
		String idProfileFollower = requestUtils.idProfileFromToken(request);
		FollowDTO ris = followService.addFollow(idProfileFollower, idFollowed);
		
		if(ris == null) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<>(ris, HttpStatus.OK);
	}
	
	//OK
	@DeleteMapping("/unfollow/{idFollowed}")
	public ResponseEntity<HttpStatus> deleteFollowByIdFollower(@PathVariable("idFollowed") String idFollowed, HttpServletRequest request) {
		String idProfileFollower = requestUtils.idProfileFromToken(request);
		if(followService.deleteFollow(idProfileFollower, idFollowed)) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
}
