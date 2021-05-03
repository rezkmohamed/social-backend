package com.scai.socialproject.alpha.socialnetworkalpha.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scai.socialproject.alpha.socialnetworkalpha.dto.CommentDTO;
import com.scai.socialproject.alpha.socialnetworkalpha.service.CommentLikeService;
import com.scai.socialproject.alpha.socialnetworkalpha.service.CommentService;

@RestController
@RequestMapping("/comments")
public class CommentController {
	private CommentService commentService;
	private CommentLikeService commentLikeService;
	
	@Autowired
	public CommentController(CommentService commentService, CommentLikeService commentLikeService) {
		this.commentService = commentService;
	}
	
	//OK
	@GetMapping("/{idPost}")
	public List<CommentDTO> findCommentsForPost(@PathVariable String idPost){
		return commentService.findAllComments(idPost);
	}
	
}
