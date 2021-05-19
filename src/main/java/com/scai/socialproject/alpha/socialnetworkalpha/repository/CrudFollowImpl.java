package com.scai.socialproject.alpha.socialnetworkalpha.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.scai.socialproject.alpha.socialnetworkalpha.dto.FollowDTO;
import com.scai.socialproject.alpha.socialnetworkalpha.entity.Follow;
import com.scai.socialproject.alpha.socialnetworkalpha.entity.Profile;
import com.scai.socialproject.alpha.socialnetworkalpha.utils.DTOutils;

import org.hibernate.query.Query;

@Repository
public class CrudFollowImpl implements CrudFollow {
	private EntityManager entityManager;
	
	@Autowired
	public CrudFollowImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public List<FollowDTO> findAllFollowers() {
		Session session = entityManager.unwrap(Session.class);
		Query<Follow> query = session.createQuery("from Follow", Follow.class);
		List<Follow> follows = query.getResultList();
		List<FollowDTO> followsDTO = DTOutils.followToDTO(follows);
		
		
		return followsDTO;
	}

	@Override
	public List<FollowDTO> findFollowersForProfile(String idProfile) {
		Session session = entityManager.unwrap(Session.class);
		Query<Follow> query = session.createQuery("from Follow where id_followed=:idProfile");
		query.setParameter("idProfile", idProfile);
		List<Follow> followers = query.getResultList();
		List<FollowDTO> followersDTO = DTOutils.followToDTO(followers);
		
		return followersDTO;
	}

	@Override
	public List<FollowDTO> findFollowingForProfile(String idProfile) {
		Session session = entityManager.unwrap(Session.class);
		Query<Follow> query = session.createQuery("from Follow where id_follower=:idProfile");
		query.setParameter("idProfile", idProfile);
		List<Follow> followings = query.getResultList();
		List<FollowDTO> followingsDTO = DTOutils.followToDTO(followings);

		return followingsDTO;
	}

	@Override
	public FollowDTO findFollowById(String id) {
		Session session = entityManager.unwrap(Session.class);
		Follow follow = session.get(Follow.class, id);
		FollowDTO followDTO = DTOutils.followToDTO(follow);
		
		return followDTO;
	}

	@Override
	public void saveFollow(Follow follow) {
		Session session = entityManager.unwrap(Session.class);
		session.save(follow);
	}

	@Override
	public void deleteFollowById(String idFollow) {
		Session session = entityManager.unwrap(Session.class);
		
		Query query = session.createQuery("delete from Follow where id_follow=:idFollow");
		query.setParameter("idFollow", idFollow);
		query.executeUpdate();
	}

	@Override
	public void deleteFollow(String idFollower, String idFollowed) {
		Session session = entityManager.unwrap(Session.class);
		
		Query query = session.createQuery("delete from Follow where id_follower=:idFollower AND id_followed=:idFollowed");
		query.setParameter("idFollower", idFollower).setParameter("idFollowed", idFollowed);
		query.executeUpdate();
		}

	@Override
	public FollowDTO addFollow(String idFollower, String idFollowed) {
		Session session = entityManager.unwrap(Session.class);
		Profile follower = session.get(Profile.class, idFollower);
		Profile followed = session.get(Profile.class, idFollowed);
		
		Follow follow = new Follow();
		follow.setFollower(follower); follow.setFollowed(followed);
		session.save(follow);
		
		return DTOutils.followToDTO(follow);
	}

	@Override
	public FollowDTO getFollow(String idFollower, String idFollowed) {
		Session session = entityManager.unwrap(Session.class);
		try {
			Query<Follow> query = session.createQuery("from Follow where id_follower = :idFollower AND id_followed = :idFollowed");
			Follow follow = query.getSingleResult();
			return DTOutils.followToDTO(follow);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return null;
	}

}
