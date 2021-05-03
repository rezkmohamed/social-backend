package com.scai.socialproject.alpha.socialnetworkalpha.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scai.socialproject.alpha.socialnetworkalpha.dto.LikeDTO;
import com.scai.socialproject.alpha.socialnetworkalpha.entity.Like;
import com.scai.socialproject.alpha.socialnetworkalpha.service.LikeService;

@RestController
@RequestMapping("likes")
public class LikesController {
	private LikeService likeService;
	
	@Autowired
	public LikesController(LikeService likeService) {
		this.likeService = likeService;
	}
	
	//OKS
	@GetMapping("")
	public List<LikeDTO> findAllLikes(){
		return likeService.findAllLikes();
	}
	
	//OK
	@GetMapping("/{idPost}")
	public List<LikeDTO> findLikesForPost(@PathVariable String idPost){
		return likeService.findLikesForPost(idPost);
	}
	
	@PostMapping("")
	public Like addLike(Like like) {
		likeService.addLike(like);
		return like;
	}
	
	//OKAY
	@PostMapping("add/{idPost}/{idProfile}")
	public LikeDTO addLike(@PathVariable String idPost, @PathVariable String idProfile) {
		return likeService.addLike(idPost, idProfile);
	}
	
	//OKAY
	@DeleteMapping("/{idLike}")
	public void deleteLikeById(@PathVariable String idLike) {
		likeService.deleteLike(idLike);
	}
	
	//OKAY
	@DeleteMapping("/delete/{idPost}/{idProfile}")
	public void deleteLikeByPostAndProfile(@PathVariable String idPost,@PathVariable String idProfile) {
		likeService.deleteLike(idPost, idProfile);
	}
 }
