package com.scai.socialproject.alpha.socialnetworkalpha.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.scai.socialproject.alpha.socialnetworkalpha.dto.PostDTO;
import com.scai.socialproject.alpha.socialnetworkalpha.entity.Post;
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
		
		return postDTO;
	}

	@Override
	public void savePost(Post post) {
		Session session = entityManager.unwrap(Session.class);
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
			Query query2 = session.createQuery("SELECT COUNT(*) FROM Comment WHERE id_post=:idPost");
			query2.setParameter("idPost", postDTO.getIdPost());
			postDTO.setCommentsCounter((int) query2.uniqueResult());
			Query query3 = session.createQuery("SELECT COUNT(*) FROM Like WHERE id_post=:idPost");
			query3.setParameter("idPost", postDTO.getIdPost());
			postDTO.setLikesCounter((int)query3.uniqueResult());
		}
		
		return postsDTO;
	}

}
