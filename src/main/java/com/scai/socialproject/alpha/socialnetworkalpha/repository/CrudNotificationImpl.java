package com.scai.socialproject.alpha.socialnetworkalpha.repository;

import java.util.List;

import javax.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.query.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.scai.socialproject.alpha.socialnetworkalpha.dto.NotificationDTO;
import com.scai.socialproject.alpha.socialnetworkalpha.entity.Follow;
import com.scai.socialproject.alpha.socialnetworkalpha.utils.DTONotificationUtils;
import com.scai.socialproject.alpha.socialnetworkalpha.utils.ImgUtils;

@Repository
public class CrudNotificationImpl implements CrudNotification {
	@Autowired
	private CrudFollow followRepo;
	@Autowired
	private CrudComment commentRepo;
	@Autowired
	private CrudLike likeRepo;
	@Autowired
	private CrudCommentLike commentLikeRepo;
	@Autowired
	private CrudProfile profileRepo;
	@Autowired
	private EntityManager entityManager;
	@Autowired
	private ImgUtils imgUtils;

	@Override
	public List<NotificationDTO> getNotificationsForProfile(String idProfile) {
		Session session = entityManager.unwrap(Session.class);
		Query<Follow> queryNewFollowers = session.createQuery("from Follow where id_followed = :idProfile ORDER BY date DESC");
		queryNewFollowers.setParameter("idProfile", idProfile);
		List<Follow> newFollowers = queryNewFollowers.getResultList();
		List<NotificationDTO> newFollowersNotification = DTONotificationUtils.DTONotificationFromFollow(newFollowers, imgUtils);
		
		return newFollowersNotification;
	}
	

	@Override
	public boolean setNotificationsAsSeenForProfile(String idProfile) {
		Session session = entityManager.unwrap(Session.class);
		Query querySetNewFollowersAsSeen = session.createQuery("update Follow set isseen = 1 where id_followed = :idProfile AND isseen = 0");
		querySetNewFollowersAsSeen.setParameter("idProfile", idProfile);
		int ris = querySetNewFollowersAsSeen.executeUpdate();
		if(ris > 0) {
			return true;
		}
		
		return false;
	}

}
