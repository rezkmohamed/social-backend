package com.scai.socialproject.alpha.socialnetworkalpha.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
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


@RestController
@RequestMapping("/posts")
public class PostsController {
	@Autowired
	private PostService postService;
	@Autowired
	private RequestUtils requestUtils;
	
	@GetMapping("/homepage/{startingIndex}")
	public ResponseEntity<List<PostDTO>> getHomepage(@PathVariable int startingIndex, HttpServletRequest request, HttpServletResponse response ) throws IOException{
		String idProfile = requestUtils.idProfileFromToken(request);
		
		return new ResponseEntity<>(postService.getHomepage(idProfile, startingIndex), HttpStatus.OK);
	}
	
	@GetMapping("/{idPost}")
	public ResponseEntity<PostDTO> findPostById(@PathVariable String idPost, HttpServletRequest request) {
		String idProfile = requestUtils.idProfileFromToken(request);

		PostDTO post = postService.findPostById(idPost, idProfile);
		if(post == null) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(post, HttpStatus.OK);
	}
	
	@GetMapping("/next/{idProfile}/{startingPost}")
	public ResponseEntity<List<PostDTO>> loadNextPostsForProfile(@PathVariable String idProfile ,@PathVariable int startingPost, HttpServletRequest request){
		return new ResponseEntity<>(postService.getNextPostsForProfilePage(idProfile, startingPost), HttpStatus.OK);
	}
	
	@PostMapping("/newpost")
	public ResponseEntity<PostDTO> addPost(
			@RequestParam("myFile") MultipartFile file, 
			@RequestParam("description") String description,
			@RequestParam("date") String date,
			MultipartHttpServletRequest request) throws IllegalStateException, IOException {
		String idProfile = requestUtils.idProfileFromToken(request);
		Date d = new Date();
		PostDTO newPost = postService.savePostTest(file, description, d.getTime(), idProfile);
		if(newPost == null) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<>(newPost, HttpStatus.OK);
	}
	
	@DeleteMapping("/{idPost}")
	public ResponseEntity<HttpStatus> deletePostById(@PathVariable String idPost, HttpServletRequest request) {
		String idProfile = requestUtils.idProfileFromToken(request);
		
		if(postService.deletePostById(idPost, idProfile)) {
			return new ResponseEntity<HttpStatus>(HttpStatus.OK);
		}
		
		return new ResponseEntity<HttpStatus>(HttpStatus.UNAUTHORIZED);
	}
	
	@PutMapping("/{idPost}")
	public ResponseEntity<HttpStatus> updatePost(@RequestBody(required = false) PostDTO postDTO, HttpServletRequest request, HttpServletResponse response ) throws HttpMessageNotReadableException{
		String idProfile = requestUtils.idProfileFromToken(request);
		postDTO.setIdProfile(idProfile);
		
		if(postService.updatePost(postDTO)) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
}
