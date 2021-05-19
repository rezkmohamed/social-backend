package com.scai.socialproject.alpha.socialnetworkalpha.repository;

import java.util.List;

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
import com.scai.socialproject.alpha.socialnetworkalpha.utils.DTOutils;

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
		List<PostDTO> postsDTO = DTOutils.postToDTO(posts);
		
		return postsDTO;
	}

	@Override
	public PostDTO findPostById(String idPost) {
		Session session = entityManager.unwrap(Session.class);
		Post post = session.get(Post.class, idPost);
		PostDTO postDTO = DTOutils.postToDTO(post);
		
		Profile profile = session.get(Profile.class, postDTO.getIdProfile());
		ProfileDTO profileDTO = DTOutils.profileToDTO(profile);
		postDTO.setProfile(profileDTO);
		
		Query<Like> query = session
				.createQuery("from Like where id_post = :idPost");
		query.setParameter("idPost", idPost);
		
		List<Like> likes = query.getResultList();
		List<LikeDTO> likesDTO = DTOutils.likeToDTO(likes);
		postDTO.setLikes(likesDTO);
		postDTO.setLikesCounter(likesDTO.size());
		
		return postDTO;
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
		post.setProfile(profile);
		session.save(post);
	}

	@Override
	public void updatePost(Post post) {
		Session session = entityManager.unwrap(Session.class);
		session.update(post);
	}

	@Override
	public void deletePostById(String idPost) {
		Session session = entityManager.unwrap(Session.class);
		
		Query query = session.createQuery("delete from Post where id_post=:idPost");
		query.setParameter("idPost", idPost);
		query.executeUpdate();
	}
	

	@Override
	public List<PostDTO> findPostsProfilePage(String idProfile) {
		Session session = entityManager.unwrap(Session.class);
		Query<Post> query = session.createQuery("from Post where id_profile=:idProfile");
		query.setParameter("idProfile", idProfile);
		List<Post> posts = query.getResultList();
		List<PostDTO> postsDTO = DTOutils.postToDTO(posts);
		for(PostDTO postDTO : postsDTO) {
			Query<Comment> query2 = session.createQuery("from Comment where id_post=:idPost");
			query2.setParameter("idPost", postDTO.getIdPost());
			List<Comment> comments = query2.getResultList();
			if(comments != null) {
				postDTO.setCommentsCounter(comments.size());
			}
			else {
				postDTO.setCommentsCounter(0);
			}
			
			Query<Like> query3 = session.createQuery("from Like where id_post=:idPost");
			query3.setParameter("idPost", postDTO.getIdPost());
			List<Like> likes = query3.getResultList();
			if(likes != null) {
				postDTO.setLikesCounter(likes.size());
			}
			else {
				postDTO.setLikesCounter(0);
			}
		}
		
		return postsDTO;
	}


	@Override
	public List<PostDTO> getHomepage(String idProfile) {
		Session session = entityManager.unwrap(Session.class);
		Query<Post> query = session.createQuery("from Post where id_profile <> :idProfile");
		query.setParameter("idProfile", idProfile);
		
		List<Post> posts = query.getResultList();
		List<PostDTO> postsDTO = DTOutils.postToDTO(posts);
		
		for(PostDTO postDTO : postsDTO) {
			Query<Profile> query2 = session.createQuery("from Profile where id_profile = :idProfile");
			query2.setParameter("idProfile", postDTO.getIdProfile());
			Profile profile = query2.getSingleResult();
			postDTO.setProfile(DTOutils.profileToDTO(profile));
			
			Query<Like> query3 = session
					.createQuery("from Like where id_post = :idPost");
			query3.setParameter("idPost", postDTO.getIdPost());
			List<Like> likes = query3.getResultList();
			List<LikeDTO> likesDTO = DTOutils.likeToDTO(likes);
			postDTO.setLikes(likesDTO);
			postDTO.setLikesCounter(likesDTO.size());
			
		}
				
		return postsDTO;
	}

}
