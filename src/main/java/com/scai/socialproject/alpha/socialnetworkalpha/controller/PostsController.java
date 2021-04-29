package com.scai.socialproject.alpha.socialnetworkalpha.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scai.socialproject.alpha.socialnetworkalpha.dto.PostDTO;
import com.scai.socialproject.alpha.socialnetworkalpha.entity.Post;
import com.scai.socialproject.alpha.socialnetworkalpha.service.LikeService;
import com.scai.socialproject.alpha.socialnetworkalpha.service.PostService;

@RestController
@RequestMapping("/posts")
public class PostsController {
	private PostService postService;
	private LikeService likeService;
	
	public PostsController(PostService postService, LikeService likeService) {
		this.postService = postService;
		this.likeService = likeService;
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
	
	@PostMapping("")
	public Post addPost(@RequestBody Post post) {
		postService.savePost(post);
		return post;
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
