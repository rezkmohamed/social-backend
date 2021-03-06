package com.scai.socialproject.alpha.socialnetworkalpha.controller;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scai.socialproject.alpha.socialnetworkalpha.service.CommentLikeService;
import com.scai.socialproject.alpha.socialnetworkalpha.utils.RequestUtils;

@RestController
@RequestMapping("commentlike")
public class CommentLikeController {
	private CommentLikeService commentLikeService;
	private RequestUtils requestUtils;

	@Autowired
	public CommentLikeController(CommentLikeService commentLikeService, RequestUtils requestUtils) {
		super();
		this.commentLikeService = commentLikeService;
		this.requestUtils = requestUtils;
	}
	
	@PostMapping("/add/{idComment}")
	public ResponseEntity<HttpStatus> addCommentLike(@PathVariable String idComment ,HttpServletRequest request){
		String idProfile = requestUtils.idProfileFromToken(request);
		if(commentLikeService.addCommentLike(idComment, idProfile)) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@DeleteMapping("/delete/{idComment}")
	public ResponseEntity<HttpStatus> deleteCommentLike(@PathVariable String idComment ,HttpServletRequest request){
		String idProfile = requestUtils.idProfileFromToken(request);
		if(commentLikeService.deleteCommentLike(idComment, idProfile)) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
}
