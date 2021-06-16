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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scai.socialproject.alpha.socialnetworkalpha.dto.CommentDTO;
import com.scai.socialproject.alpha.socialnetworkalpha.service.CommentLikeService;
import com.scai.socialproject.alpha.socialnetworkalpha.service.CommentService;
import com.scai.socialproject.alpha.socialnetworkalpha.utils.RequestUtils;

//@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/comments")
public class CommentController {
	private CommentService commentService;
	private RequestUtils requestUtils;

	@Autowired
	public CommentController(CommentService commentService, RequestUtils requestUtils) {
		this.commentService = commentService;
		this.requestUtils = requestUtils;
		
	}
	
	//OK
	@GetMapping("/{idPost}")
	public List<CommentDTO> findCommentsForPost(@PathVariable String idPost){
		return commentService.findAllComments(idPost);
	}
	
	
	//OKAY
	@PostMapping("")
	public ResponseEntity<CommentDTO> addComment(@RequestBody CommentDTO commentDTO, HttpServletRequest request) {
		String idProfile = requestUtils.idProfileFromToken(request);
		commentDTO.setIdProfile(idProfile);
		CommentDTO ris = commentService.addComment(commentDTO);
		
		return new ResponseEntity<>(ris, HttpStatus.OK);
	}
	
	@PutMapping("")
	public ResponseEntity<CommentDTO> updateComment(@RequestBody CommentDTO commentDTO, HttpServletRequest request) {
		String idProfile = requestUtils.idProfileFromToken(request);
		commentDTO.setIdProfile(idProfile);
		CommentDTO ris = commentService.updateComment(commentDTO);
		
		return new ResponseEntity<>(ris, HttpStatus.OK);
	}
	
	//DELETE COMMENT
	@DeleteMapping("/{idComment}")
	public void deleteComment(@PathVariable String idComment) {
		commentService.deleteComment(idComment);
	}
}
