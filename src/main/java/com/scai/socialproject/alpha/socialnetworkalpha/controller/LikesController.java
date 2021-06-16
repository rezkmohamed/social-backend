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

import com.scai.socialproject.alpha.socialnetworkalpha.dto.LikeDTO;
import com.scai.socialproject.alpha.socialnetworkalpha.dto.ProfileDTO;
import com.scai.socialproject.alpha.socialnetworkalpha.entity.Like;
import com.scai.socialproject.alpha.socialnetworkalpha.service.LikeService;
import com.scai.socialproject.alpha.socialnetworkalpha.service.ProfileService;
import com.scai.socialproject.alpha.socialnetworkalpha.utils.RequestUtils;

//@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("likes")
public class LikesController {
	private LikeService likeService;
	private ProfileService profileService;
	private RequestUtils requestUtils;

	
	@Autowired
	public LikesController(LikeService likeService, ProfileService profileService, RequestUtils requestUtils) {
		this.likeService = likeService;
		this.profileService = profileService;
		this.requestUtils = requestUtils;
	}
	
	//OKS
	@GetMapping("")
	public List<LikeDTO> findAllLikes(){
		return likeService.findAllLikes();
	}
	
	//OK!
	@GetMapping("/{idPost}")
	public List<ProfileDTO> findProfilesLikesForPost(@PathVariable String idPost){
		return profileService.findProfilesLikesPost(idPost);
	}
	
	@GetMapping("/{idProfile}/{idPost}")
	public ResponseEntity<LikeDTO> getLike(@PathVariable String idProfile, @PathVariable String idPost) {
		LikeDTO res = likeService.getLikeByLikerAndPost(idProfile, idPost);
		
		if(res == null) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
		
	//OKAY
	@PostMapping("add/{idPost}")
	public ResponseEntity<LikeDTO> addLike(@PathVariable String idPost, HttpServletRequest request) {
		String idP = requestUtils.idProfileFromToken(request);
		
		LikeDTO like = likeService.addLike(idPost, idP);
		if(like == null) {
			return new ResponseEntity<>(like, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(like, HttpStatus.OK);
	}
	
	//OKAY
	@DeleteMapping("/delete/{idPost}")
	public ResponseEntity<HttpStatus> deleteLikeByPostAndProfile(@PathVariable String idPost, HttpServletRequest request) {
		String idP = requestUtils.idProfileFromToken(request);
				
		if(likeService.deleteLike(idPost, idP)) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
 }
