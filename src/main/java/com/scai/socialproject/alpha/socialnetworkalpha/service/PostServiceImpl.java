package com.scai.socialproject.alpha.socialnetworkalpha.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.scai.socialproject.alpha.socialnetworkalpha.dto.PostDTO;
import com.scai.socialproject.alpha.socialnetworkalpha.entity.Post;
import com.scai.socialproject.alpha.socialnetworkalpha.repository.CrudPost;

@Service
public class PostServiceImpl implements PostService {
	private CrudPost postRepo;
	
	@Autowired
	public PostServiceImpl(CrudPost postRepo) {
		this.postRepo = postRepo;
	}
	
	
	@Override
	@Transactional
	public List<PostDTO> findAllPosts() {
		return postRepo.findAllPosts();
	}

	@Override
	@Transactional
	public PostDTO findPostById(String idPost) {
		return postRepo.findPostById(idPost);
	}

	@Override
	@Transactional
	public void savePost(Post post) {
		postRepo.savePost(post);
	}

	@Override
	@Transactional
	public boolean updatePost(PostDTO postDTO) {
		return postRepo.updatePost(postDTO);		
	}

	@Override
	@Transactional
	public ResponseEntity<HttpStatus> deletePostById(String idPost) {
		if(postRepo.deletePostById(idPost)) {
			return new ResponseEntity<HttpStatus>(HttpStatus.OK);
		}
		
		return new ResponseEntity<HttpStatus>(HttpStatus.NOT_FOUND);
	}


	@Override
	@Transactional
	public List<PostDTO> findPostsProfilePage(String idProfile) {
		return postRepo.findPostsProfilePage(idProfile);
	}

	@Override
	@Transactional
	public void savePost(PostDTO postDTO) {
		postRepo.savePost(postDTO);
	}

	@Override
	@Transactional
	public List<PostDTO> getHomepage(String idProfile) {
		
		
		return postRepo.getHomepage(idProfile);
	}
}
