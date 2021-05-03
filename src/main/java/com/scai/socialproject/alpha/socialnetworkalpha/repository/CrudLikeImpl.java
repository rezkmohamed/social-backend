package com.scai.socialproject.alpha.socialnetworkalpha.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.hibernate.query.Query;

import com.scai.socialproject.alpha.socialnetworkalpha.dto.LikeDTO;
import com.scai.socialproject.alpha.socialnetworkalpha.entity.Like;
import com.scai.socialproject.alpha.socialnetworkalpha.entity.Post;
import com.scai.socialproject.alpha.socialnetworkalpha.entity.Profile;
import com.scai.socialproject.alpha.socialnetworkalpha.utils.DTOutils;

@Repository
public class CrudLikeImpl implements CrudLike {
	private EntityManager entityManager;
	
	@Autowired
	public CrudLikeImpl(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}

	@Override
	public List<LikeDTO> findAllLikes() {
		Session session = entityManager.unwrap(Session.class);
		Query<Like> query = session.createQuery("from Like", Like.class);
		List<Like> likes = query.getResultList();
		List<LikeDTO> likesDTO = DTOutils.likeToDTO(likes);
		
		return likesDTO;
	}

	@Override
	public List<LikeDTO> findLikesForPost(String idPost) {
		Session session = entityManager.unwrap(Session.class);
		Query<Like> query = session.createQuery("from Like where id_post=:idPost");
		query.setParameter("idPost", idPost);
		List<Like> likes = query.getResultList();
		List<LikeDTO> likesDTO = DTOutils.likeToDTO(likes);
		
		return likesDTO;
	}

	@Override
	public void addLike(Like like) {
		Session session = entityManager.unwrap(Session.class);
		session.save(like);
	}
	
	@Override
	public LikeDTO addLike(String idPost, String idProfile) {
		Session session = entityManager.unwrap(Session.class);
		Post post = session.get(Post.class, idPost);
		Profile profile = session.get(Profile.class, idProfile);
		Like newLike = new Like();
		newLike.setPost(post); newLike.setProfileLiker(profile);
		newLike.setDate("31/03/1999");
		session.save(newLike);
		return DTOutils.likeToDTO(newLike);
	}

	@Override
	public void deleteLike(String idLike) {
		Session session = entityManager.unwrap(Session.class);
		Query query = session.createQuery("delete from Like where id_like=:idLike");
		query.setParameter("idLike", idLike);
		query.executeUpdate();		
	}
	
	@Override
	public void deleteLike(String idPost, String idProfile) {
		Session session = entityManager.unwrap(Session.class);
		
		Query query = session
			.createQuery("delete from Like where id_post=:idPost and id_profile_liker=:idProfile");
		query.setParameter("idPost", idPost); query.setParameter("idProfile", idProfile);
		query.executeUpdate();
		
		/*Query<Like> query = session
				.createQuery("from Like where id_post=:idPost and id_profile_liker=:idProfile");
		query.setParameter("idPost", idPost);
		query.setParameter("idProfile", idProfile);
		Like like = query.uniqueResult();
		if(like == null) {
			System.out.println("like not found");
			return;
		}
		System.out.println("like found");
		String idLike = like.getId();
		this.deleteLike(idLike);
		*/
	}

}
