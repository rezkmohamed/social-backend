package com.scai.socialproject.alpha.socialnetworkalpha.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scai.socialproject.alpha.socialnetworkalpha.service.CommentLikeService;
import com.scai.socialproject.alpha.socialnetworkalpha.utils.RequestUtils;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("commentlike")
public class CommentLikeController {
	private CommentLikeService commentLikeService;

	@Autowired
	public CommentLikeController(CommentLikeService commentLikeService) {
		super();
		this.commentLikeService = commentLikeService;
	}
	
	@GetMapping("/test")
	public String testingCommentLike() {
		return "comment like works!";
	}
	
	@PostMapping("add/{idComment}")
	public ResponseEntity<HttpStatus> addCommentLike(@PathVariable String idComment ,HttpServletRequest request){
		String idProfile = RequestUtils.idProfileFromToken(request);
		if(commentLikeService.addCommentLike(idComment, idProfile)) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
}
