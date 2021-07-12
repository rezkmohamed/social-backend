package com.scai.socialproject.alpha.socialnetworkalpha.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.scai.socialproject.alpha.socialnetworkalpha.dto.FollowDTO;
import com.scai.socialproject.alpha.socialnetworkalpha.entity.Follow;
import com.scai.socialproject.alpha.socialnetworkalpha.entity.Notification;
import com.scai.socialproject.alpha.socialnetworkalpha.entity.Profile;
import com.scai.socialproject.alpha.socialnetworkalpha.utils.DTOFollowUtils;
import com.scai.socialproject.alpha.socialnetworkalpha.utils.DTONotificationUtils;

import org.hibernate.query.Query;

@Repository
public class CrudFollowImpl implements CrudFollow {
	@Autowired
	private EntityManager entityManager;

	@Override
	public List<FollowDTO> findAllFollowers() {
		Session session = entityManager.unwrap(Session.class);
		Query<Follow> query = session.createQuery("from Follow", Follow.class);
		List<Follow> follows = query.getResultList();
		List<FollowDTO> followsDTO = DTOFollowUtils.followToDTO(follows);
		
		
		return followsDTO;
	}

	@Override
	public List<FollowDTO> findFollowersForProfile(String idProfile) {
		Session session = entityManager.unwrap(Session.class);
		Query<Follow> query = session.createQuery("from Follow where id_followed=:idProfile");
		query.setParameter("idProfile", idProfile);
		List<Follow> followers = query.getResultList();
		List<FollowDTO> followersDTO = DTOFollowUtils.followToDTO(followers);
		
		return followersDTO;
	}

	@Override
	public List<FollowDTO> findFollowingForProfile(String idProfile) {
		Session session = entityManager.unwrap(Session.class);
		Query<Follow> query = session.createQuery("from Follow where id_follower=:idProfile");
		query.setParameter("idProfile", idProfile);
		List<Follow> followings = query.getResultList();
		List<FollowDTO> followingsDTO = DTOFollowUtils.followToDTO(followings);

		return followingsDTO;
	}

	@Override
	public FollowDTO findFollowById(String id) {
		Session session = entityManager.unwrap(Session.class);
		Follow follow = session.get(Follow.class, id);
		FollowDTO followDTO = DTOFollowUtils.followToDTO(follow);
		
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
	public boolean deleteFollow(String idFollower, String idFollowed) {		
		Session session = entityManager.unwrap(Session.class);
		Query query = session.createQuery("delete from Follow where id_follower=:idFollower AND id_followed=:idFollowed");
		query.setParameter("idFollower", idFollower).setParameter("idFollowed", idFollowed);
		if(query.executeUpdate() == 0) {
			return false;
		}
		return true;

	}

	@Override
	public FollowDTO addFollow(String idFollower, String idFollowed) {
		Session session = entityManager.unwrap(Session.class);
		Profile follower = session.get(Profile.class, idFollower);
		if(follower == null) {
			return null;
		}
		Profile followed = session.get(Profile.class, idFollowed);
		if(followed == null) {
			return null;
		}
		Follow follow = new Follow();
		follow.setFollower(follower); follow.setFollowed(followed);
		session.save(follow);
		
//		Notification newNotification = DTONotificationUtils.createNewNotificationFromFollow(follow);
//		session.save(newNotification);
		
		return DTOFollowUtils.followToDTO(follow);
	}

	@Override
	public FollowDTO getFollow(String idFollower, String idFollowed) {
		Session session = entityManager.unwrap(Session.class);
		
		Query<Follow> query = session.createQuery("from Follow where id_follower = :idFollower AND id_followed = :idFollowed");
		query.setParameter("idFollower", idFollower); 
		query.setParameter("idFollowed", idFollowed);
		try {
			Follow follow = query.getSingleResult();
			return DTOFollowUtils.followToDTO(follow);
		} catch (Exception e) {
		}
		return null;
	}

}
