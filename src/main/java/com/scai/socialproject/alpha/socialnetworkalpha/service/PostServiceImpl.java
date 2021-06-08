package com.scai.socialproject.alpha.socialnetworkalpha.service;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.scai.socialproject.alpha.socialnetworkalpha.dto.FollowDTO;
import com.scai.socialproject.alpha.socialnetworkalpha.dto.PostDTO;
import com.scai.socialproject.alpha.socialnetworkalpha.entity.Post;
import com.scai.socialproject.alpha.socialnetworkalpha.repository.CrudFollow;
import com.scai.socialproject.alpha.socialnetworkalpha.repository.CrudPost;

@Service
public class PostServiceImpl implements PostService {
	private CrudPost postRepo;
	private CrudFollow followRepo;
	
	@Autowired
	public PostServiceImpl(CrudPost postRepo, CrudFollow followRepo) {
		this.postRepo = postRepo;
		this.followRepo = followRepo;
	}
	
	
	@Override
	@Transactional
	public List<PostDTO> findAllPosts() {
		return postRepo.findAllPosts();
	}

	@Override
	@Transactional
	public PostDTO findPostById(String idPost, String idProfileLogged) {
		PostDTO ris = postRepo.findPostById(idPost);
		
		ris.getLikes().stream()
		.forEach( l -> {
			if(l.getIdProfile().equals(idProfileLogged)) {
				ris.setLiked(true);
			}
		});
		
		return ris;
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
	public boolean deletePostById(String idPost, String idProfile) {
		PostDTO post = postRepo.findPostById(idPost);
		
		if(post.getIdProfile().equals(idProfile)) {
			postRepo.deletePostById(idPost);
			return true;
		}
		return false;
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
		List<PostDTO> posts = new LinkedList<>();
		List<FollowDTO> following = followRepo.findFollowingForProfile(idProfile);
		for(FollowDTO follow : following) {
			String id = follow.getIdFollowed();
			List<PostDTO> postsProfile = postRepo.getPosts(id);
			posts.addAll(postsProfile);
		}
		
		List<PostDTO> postsSorted =
		posts.stream()
		.sorted(Comparator.comparing(
				PostDTO::getLocalDate,
				Comparator.reverseOrder()
				))
		.collect(Collectors.toList());

		
		postsSorted.stream().forEach( p -> {
			p.getLikes().forEach(l -> {
				if(l.getIdProfile().equals(idProfile)) {
					p.setLiked(true);
				}
			});
		});

		return postsSorted;
	}
}
