package com.scai.socialproject.alpha.socialnetworkalpha.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	
	//OKAY
	@PostMapping("")
	public CommentDTO addComment(@RequestBody CommentDTO commentDTO) {
		return commentService.addComment(commentDTO);
	}
	
	//PUT MAPPING (UPDATE TEXT COMMENT)
	@PutMapping("")
	public CommentDTO updateComment(@RequestBody CommentDTO commentDTO) {
		return commentService.updateComment(commentDTO);
	}
	
	//DELETE COMMENT
	@DeleteMapping("/{idComment}")
	public void deleteComment(@PathVariable String idComment) {
		commentService.deleteComment(idComment);
	}
}
