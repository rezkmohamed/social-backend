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
	//private String basePathFileSystem = "C:\\immagini\\";
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
		
		try {
			ris.setUrlImg(imgUtils.fileImgToBase64Encoding(ris.getUrlImg()));
		} catch (IOException e) {
		}
		
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
		int lastPost = 3;

		Profile profile = profileRepo.findProfile(idProfile);
		
		return postRepo.loadNextPostsForProfile(idProfile, startingIndex);
		/*
		List<PostDTO> postsSorted = 
			profile.getPosts().stream()
			.map(p -> {
				PostDTO pdto = DTOPostUtils.postToDTO(p);
				try {
					pdto.setUrlImg(imgUtils.fileImgToBase64Encoding(pdto.getUrlImg()));
				} catch (Exception e) {
				}
				return pdto;
			})
			.sorted(Comparator.comparing(
					PostDTO::getLocalDate,
					Comparator.reverseOrder()
					))
			.collect(Collectors.toList());
		
		List<PostDTO> postsResponse = new ArrayList<>();
		for(int i = startingIndex; (i < startingIndex + lastPost) && i < postsSorted.size() ; i++) {
			postsResponse.add(postsSorted.get(i));
		}
		
		return postsResponse;*/
	}

	@Override
	@Transactional
	public List<PostDTO> getHomepage(String idProfile, int startingIndex) {
		//int lastPost = 6;
		List<PostDTO> posts = new LinkedList<>();
		
		posts = postRepo.getNextPostsHomepage(idProfile, startingIndex);
		
		/*
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
		
		List<PostDTO> postsRis = new LinkedList<>();
		for(int i = startingIndex; (i < startingIndex + lastPost) && i < postsSorted.size() ; i++) {
			postsRis.add(postsSorted.get(i));
		}
		
		/**
		 * COMMENT'S LIKE CHECK., ADJUSTING PROPIC
		 */
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
	public PostDTO savePostTest(MultipartFile img, String description, String date, String idProfile) throws IllegalStateException, IOException {
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
