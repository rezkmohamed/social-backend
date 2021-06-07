package com.scai.socialproject.alpha.socialnetworkalpha.repository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;

import org.aspectj.weaver.NewParentTypeMunger;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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

@Repository
public class CrudPostImpl implements CrudPost {
	private EntityManager entityManager;
	
	@Autowired
	public CrudPostImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
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
		return DTOPostUtils.fillPostCompleteDTO(post);
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
		Post post = new Post(postDTO.getUrlImg(), postDTO.getDescription(), postDTO.getDate());
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
			postsDTO.add(DTOPostUtils.fillPostCompleteDTO(post));
		}
		
		return postsDTO;
	}


	@Override
	public List<PostDTO> getHomepage(String idProfile) {
		Session session = entityManager.unwrap(Session.class);
		Query<Post> query = session.createQuery("from Post where id_profile <> :idProfile");
		query.setParameter("idProfile", idProfile);
		
		System.out.println("HOMEPAGE FOR PROFILE - " + idProfile);
		List<Post> posts = query.getResultList();
		
		List<Post> postsSorted =
		posts.stream()
		.sorted(Comparator.comparing(
				Post::getLocalDateTime,
				Comparator.reverseOrder()
				))
		.collect(Collectors.toList());
		
		List<PostDTO> ris = new ArrayList<>();
		for(Post post : postsSorted) {
			PostDTO postDTO = DTOPostUtils.postToDTO(post);
			postDTO.setProfile(DTOProfileUtils.profileToDTO(post.getProfile()));
			postDTO.setComments(DTOCommentUtils.commentToDTO(post.getComments()));
			postDTO.setCommentsCounter(post.getComments().size());
			postDTO.setLikes(DTOLikeUtils.likeToDTO(post.getLikes()));
			postDTO.setLikesCounter(post.getLikes().size());
			ris.add(postDTO);
		}
		
		return ris;
	}

}
