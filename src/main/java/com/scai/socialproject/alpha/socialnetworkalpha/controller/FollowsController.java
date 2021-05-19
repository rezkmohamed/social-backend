package com.scai.socialproject.alpha.socialnetworkalpha.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scai.socialproject.alpha.socialnetworkalpha.dto.FollowDTO;
import com.scai.socialproject.alpha.socialnetworkalpha.entity.Follow;
import com.scai.socialproject.alpha.socialnetworkalpha.entity.Profile;
import com.scai.socialproject.alpha.socialnetworkalpha.service.FollowService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("followers")
public class FollowsController {
	private FollowService followService;
	
	@Autowired
	public FollowsController(FollowService followService) {
		this.followService = followService;
	}
	
	//OK!
	@GetMapping("/allfollow")
	public List<FollowDTO> findAllFollowers(){
		return followService.findAllFollowers();
	}
	
	@GetMapping("/get/{idFollower}/{idFollowed}")
	public FollowDTO getFollow(@PathVariable String idFollower, @PathVariable String idFollowed) {
		return followService.getFollow(idFollower, idFollowed);
	}
}
