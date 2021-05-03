package com.scai.socialproject.alpha.socialnetworkalpha.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scai.socialproject.alpha.socialnetworkalpha.dto.PostDTO;
import com.scai.socialproject.alpha.socialnetworkalpha.entity.Post;
import com.scai.socialproject.alpha.socialnetworkalpha.service.CommentService;
import com.scai.socialproject.alpha.socialnetworkalpha.service.LikeService;
import com.scai.socialproject.alpha.socialnetworkalpha.service.PostService;

@RestController
@RequestMapping("/posts")
public class PostsController {
	private PostService postService;
	private LikeService likeService;
	private CommentService commentService;
	
	@Autowired
	public PostsController(PostService postService, LikeService likeService, CommentService commentService) {
		this.postService = postService;
		this.likeService = likeService;
		this.commentService = commentService;
	}
	
	//OK!
	@GetMapping("")
	public List<PostDTO> findAllPosts(){
		return postService.findAllPosts();
	}
	
	//OK!
	@GetMapping("/{idPost}")
	public PostDTO findPostById(@PathVariable String idPost) {
		PostDTO post = postService.findPostById(idPost);
		if(post == null) {
			throw new RuntimeException("ERROR - POST WITH ID: " + idPost + " NOT FOUND");
		}
		return post;
	}
	
	
	
	//OK!
	@PostMapping("")
	public PostDTO addPost(@RequestBody PostDTO postDTO) {
		postService.savePost(postDTO);
		return postDTO;
	}
	
	//OK!
	@DeleteMapping("/{idPost}")
	public String deletePostById(@PathVariable String idPost) {
		PostDTO post = postService.findPostById(idPost);
		if(post != null) {
			postService.deletePostById(idPost);
			return "SUCCESS - POST DELETED ID: " + post.getIdPost();
		}
		throw new RuntimeException("ERROR - POST WITH ID: " + idPost + " NOT FOUND");
	}
	
}
