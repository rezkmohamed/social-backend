package com.scai.socialproject.alpha.socialnetworkalpha.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.scai.socialproject.alpha.socialnetworkalpha.dto.FollowDTO;
import com.scai.socialproject.alpha.socialnetworkalpha.dto.PostDTO;
import com.scai.socialproject.alpha.socialnetworkalpha.entity.Post;
import com.scai.socialproject.alpha.socialnetworkalpha.entity.Profile;
import com.scai.socialproject.alpha.socialnetworkalpha.repository.CrudFollow;
import com.scai.socialproject.alpha.socialnetworkalpha.repository.CrudPost;
import com.scai.socialproject.alpha.socialnetworkalpha.repository.CrudProfile;
import com.scai.socialproject.alpha.socialnetworkalpha.utils.DTOPostUtils;
import com.scai.socialproject.alpha.socialnetworkalpha.utils.ImgUtils;

@Service
public class PostServiceImpl implements PostService {
	private CrudPost postRepo;
	private CrudFollow followRepo;
	private CrudProfile profileRepo;
	@Value("${basePathFileSystem}")
	private String basePathFileSystem;
	private ImgUtils imgUtils;

	
	@Autowired
	public PostServiceImpl(CrudPost postRepo, CrudFollow followRepo, CrudProfile profileRepo, ImgUtils imgUtils) {
		this.postRepo = postRepo;
		this.followRepo = followRepo;
		this.profileRepo = profileRepo;
		this.imgUtils = imgUtils;
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
		
		ris.getComments().stream()
		.forEach(c -> {
			c.getCommentLikes().stream()
			.forEach(cl -> {
				if(cl.getIdProfile().equals(idProfileLogged)) {
					c.setLiked(true);
				}
			});
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
	public List<PostDTO> getNextPostsForProfilePage(String idProfile, int startingIndex) {
		return postRepo.loadNextPostsForProfile(idProfile, startingIndex);
	}

	@Override
	@Transactional
	public List<PostDTO> getHomepage(String idProfile, int startingIndex) {
		List<PostDTO> posts = new LinkedList<>();
		
		posts = postRepo.getNextPostsHomepage(idProfile, startingIndex);
		
		posts.stream()
		.forEach( p -> {
			p.getComments().forEach( c -> {
				c.getCommentLikes().forEach(cl -> {
					if(cl.getIdProfile().equals(idProfile)) {
						c.setLiked(true);
					}
				});
			});
		});
		
		posts.stream().forEach( p -> {
			p.getLikes().forEach(l -> {
				if(l.getIdProfile().equals(idProfile)) {
					p.setLiked(true);
				}
			});
		}); 

		return posts;
	}


	@Override
	@Transactional
	public PostDTO savePostTest(MultipartFile img, String description, long date, String idProfile) throws IllegalStateException, IOException {
		String filename = img.getOriginalFilename();
		String extension  = filename.substring(filename.lastIndexOf(".") + 1);

		if(extension.equalsIgnoreCase("jpg") || extension.equalsIgnoreCase("jpeg") || extension.equalsIgnoreCase("png")) {
				String newImg = UUID.randomUUID().toString()+ "." + extension;
				img.transferTo(new File(basePathFileSystem + newImg));
				Post post = new Post(newImg, description, date);
				Profile profile = profileRepo.findProfile(idProfile);
				post.setProfile(profile);
				postRepo.savePost(post);
				PostDTO postDTO = DTOPostUtils.postToDTO(post);
				return postDTO;
		}
		
		return null;
	}
}
