package com.scai.socialproject.alpha.socialnetworkalpha.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.scai.socialproject.alpha.socialnetworkalpha.dto.PostDTO;
import com.scai.socialproject.alpha.socialnetworkalpha.service.PostService;
import com.scai.socialproject.alpha.socialnetworkalpha.utils.RequestUtils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/posts")
public class PostsController {
	private PostService postService;
	
	@Autowired
	public PostsController(PostService postService) {
		this.postService = postService;
	}
	
	//OK!
	@GetMapping("")
	public List<PostDTO> findAllPosts(){
		return postService.findAllPosts();
	}
	
	//OK!
	@GetMapping("/homepage")
	public ResponseEntity<List<PostDTO>> getHomepage(HttpServletRequest request, HttpServletResponse response ) throws IOException{
		String idProfile = RequestUtils.idProfileFromToken(request);
		
		return new ResponseEntity<>(postService.getHomepage(idProfile), HttpStatus.OK);
	}
	
	//OK!
	@GetMapping("/{idPost}")
	public PostDTO findPostById(@PathVariable String idPost, HttpServletRequest request) {
		String idProfile = RequestUtils.idProfileFromToken(request);

		PostDTO post = postService.findPostById(idPost, idProfile);
		if(post == null) {
			throw new RuntimeException("ERROR - POST WITH ID: " + idPost + " NOT FOUND");
		}
		return post;
	}
	
	@PostMapping("/newpost")
	public ResponseEntity<PostDTO> addPostTest(
			@RequestParam("myFile") MultipartFile file, 
			@RequestParam("description") String description,
			@RequestParam("date") String date,
			MultipartHttpServletRequest request) throws IllegalStateException, IOException {
		String idProfile = RequestUtils.idProfileFromToken(request);
		
		PostDTO newPost = postService.savePostTest(file, description, date, idProfile);
		if(newPost == null) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<>(newPost, HttpStatus.OK);
	}
	
	//OK!
	@DeleteMapping("/{idPost}")
	public ResponseEntity<HttpStatus> deletePostById(@PathVariable String idPost, HttpServletRequest request) {
		String idProfile = RequestUtils.idProfileFromToken(request);
		
		if(postService.deletePostById(idPost, idProfile)) {
			return new ResponseEntity<HttpStatus>(HttpStatus.OK);
		}
		
		return new ResponseEntity<HttpStatus>(HttpStatus.UNAUTHORIZED);
	}
	
	@PutMapping("/{idPost}")
	public ResponseEntity<HttpStatus> updatePost(@RequestBody(required = false) PostDTO postDTO, HttpServletRequest request, HttpServletResponse response ) throws HttpMessageNotReadableException{
		String idProfile = RequestUtils.idProfileFromToken(request);
		postDTO.setIdProfile(idProfile);
		
		if(postService.updatePost(postDTO)) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
}
