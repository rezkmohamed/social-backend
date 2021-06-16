package com.scai.socialproject.alpha.socialnetworkalpha.repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;

import org.aspectj.weaver.NewParentTypeMunger;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.scai.socialproject.alpha.socialnetworkalpha.dto.CommentDTO;
import com.scai.socialproject.alpha.socialnetworkalpha.dto.CommentLikeDTO;
import com.scai.socialproject.alpha.socialnetworkalpha.dto.LikeDTO;
import com.scai.socialproject.alpha.socialnetworkalpha.dto.PostDTO;
import com.scai.socialproject.alpha.socialnetworkalpha.dto.ProfileDTO;
import com.scai.socialproject.alpha.socialnetworkalpha.entity.Comment;
import com.scai.socialproject.alpha.socialnetworkalpha.entity.Like;
import com.scai.socialproject.alpha.socialnetworkalpha.entity.Post;
import com.scai.socialproject.alpha.socialnetworkalpha.entity.Profile;
import com.scai.socialproject.alpha.socialnetworkalpha.utils.DTOCommentUtils;
import com.scai.socialproject.alpha.socialnetworkalpha.utils.DTOLikeUtils;
import com.scai.socialproject.alpha.socialnetworkalpha.utils.DTOPostUtils;
import com.scai.socialproject.alpha.socialnetworkalpha.utils.DTOProfileUtils;
import com.scai.socialproject.alpha.socialnetworkalpha.utils.ImgUtils;

@Repository
public class CrudPostImpl implements CrudPost {
	private EntityManager entityManager;
	private ImgUtils imgUtils;
	
	@Autowired
	public CrudPostImpl(EntityManager entityManager, ImgUtils imgUtils) {
		this.entityManager = entityManager;
		this.imgUtils = imgUtils;
	}
	
	
	@Override
	public List<PostDTO> findAllPosts() {
		Session session = entityManager.unwrap(Session.class);
		Query<Post> query = session.createQuery("from Post", Post.class);
		List<Post> posts = query.getResultList(); 
		List<PostDTO> postsDTO = DTOPostUtils.postToDTO(posts);
		
		return postsDTO;
	}

	@Override
	public PostDTO findPostById(String idPost) {
		Session session = entityManager.unwrap(Session.class);
		Post post = session.get(Post.class, idPost);
		return DTOPostUtils.fillPostCompleteDTO(post, imgUtils);
	}

	@Override
	public void savePost(Post post) {
		Session session = entityManager.unwrap(Session.class);
		session.save(post);
	}
	
	@Override
	public void savePost(PostDTO postDTO) {
		Session session = entityManager.unwrap(Session.class);
		Query<Profile> query = session.createQuery("from Profile where id_profile=:idProfile");
		query.setParameter("idProfile", postDTO.getIdProfile());
		Profile profile = query.getSingleResult();
		Post post = new Post(postDTO.getUrlImg(), postDTO.getDescription(), postDTO.getLocalDate().toString());
		System.out.println(post.getDate());
		
		post.setProfile(profile);
		session.save(post);
	}

	@Override
	public boolean updatePost(PostDTO postDTO) {
		Session session = entityManager.unwrap(Session.class);
		Query query = session.createQuery("update Post set description = :desc where id_post = :idPost");
		query.setParameter("desc", postDTO.getDescription()); query.setParameter("idPost", postDTO.getIdPost());
		if(query.executeUpdate() == 1){
			return true;
		}
		
		return false;
	}

	@Override
	public boolean deletePostById(String idPost) {
		Session session = entityManager.unwrap(Session.class);
		
		Query query = session.createQuery("delete from Post where id_post = :idPost");
		query.setParameter("idPost", idPost); 
		if(query.executeUpdate() == 1) {
			return true;
		}
		return false;
	}
	

	@Override
	public List<PostDTO> findPostsProfilePage(String idProfile) {
		Session session = entityManager.unwrap(Session.class);
		Query<Post> query = session.createQuery("from Post where id_profile=:idProfile");
		query.setParameter("idProfile", idProfile);
		List<Post> posts = query.getResultList();
		List<PostDTO> postsDTO = new ArrayList<>();

		for(Post post : posts) {
			postsDTO.add(DTOPostUtils.fillPostCompleteDTO(post, imgUtils));
		}
		
		return postsDTO;
	}
	
	
	@Override
	public List<PostDTO> getPosts(String idProfile){
		Session session = entityManager.unwrap(Session.class);
		Query<Post> query = session.createQuery("from Post where id_profile = :idProfile");
		query.setParameter("idProfile", idProfile);
		
		
		List<Post> posts = query.getResultList();
		List<PostDTO> ris = new ArrayList<>();
		for(Post post : posts) {
			PostDTO postDTO = DTOPostUtils.postToDTO(post);
			postDTO.setProfile(DTOProfileUtils.profileToDTO(post.getProfile()));
			try {
				if(postDTO.getProfile().getProPic() != null) {
					postDTO.getProfile().setProPic(imgUtils.fileImgToBase64Encoding(postDTO.getProfile().getProPic()));
				}
			} catch (IOException e) {
			}
			
			
			postDTO.setComments(DTOCommentUtils.commentToDTO(post.getComments()));			
			
			postDTO.setCommentsCounter(post.getComments().size());
			
			
			postDTO.setLikes(DTOLikeUtils.likeToDTO(post.getLikes()));
			postDTO.setLikesCounter(post.getLikes().size());
			ris.add(postDTO);
		}
		
		return ris;
	}

	@Override
	public List<PostDTO> loadNextPostsForProfile(String idProfile, int startingIndex) {
		Session session = entityManager.unwrap(Session.class);
		Query<Post> query = session.createQuery("from Post where id_profile = :idProfile ORDER BY date DESC");
		query.setParameter("idProfile", idProfile);
		query.setFirstResult(startingIndex);
		query.setMaxResults(6);
		List<Post> posts = query.getResultList();
		
		
		List<PostDTO> postsDTO = DTOPostUtils.postToDTO(posts);
		for(PostDTO p : postsDTO) {
			try {
				p.setUrlImg(imgUtils.fileImgToBase64Encoding(p.getUrlImg()));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		System.out.println(postsDTO);
		
		return postsDTO;
	}
	
}
