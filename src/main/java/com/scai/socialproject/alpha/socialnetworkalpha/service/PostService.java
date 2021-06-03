package com.scai.socialproject.alpha.socialnetworkalpha.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.scai.socialproject.alpha.socialnetworkalpha.dto.PostDTO;
import com.scai.socialproject.alpha.socialnetworkalpha.entity.Post;

public interface PostService {
	public List<PostDTO> findAllPosts();
	
	public PostDTO findPostById(String idPost, String idProfileLogged);
	
	public List<PostDTO> findPostsProfilePage(String idProfile);
	
	public List<PostDTO> getHomepage(String idProfile);
	
	public void savePost(Post post);
	
	public void savePost(PostDTO postDTO);
	
	public boolean updatePost(PostDTO postDTO);
	
	public boolean deletePostById(String idPost, String idProfile);
}
