package com.scai.socialproject.alpha.socialnetworkalpha.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scai.socialproject.alpha.socialnetworkalpha.dto.LikeDTO;
import com.scai.socialproject.alpha.socialnetworkalpha.service.LikeService;

@RestController
@RequestMapping("likes")
public class LikesController {
	private LikeService likeService;
	
	@Autowired
	public LikesController(LikeService likeService) {
		this.likeService = likeService;
	}
	
	@GetMapping("")
	public List<LikeDTO> findAllLikes(){
		return likeService.findAllLikes();
	}
	
	@GetMapping("test")
	public List<LikeDTO> testLikes(){
		return null;	
	}
 }
