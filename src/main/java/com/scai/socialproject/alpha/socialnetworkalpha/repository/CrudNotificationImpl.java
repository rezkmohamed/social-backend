package com.scai.socialproject.alpha.socialnetworkalpha.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.query.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.scai.socialproject.alpha.socialnetworkalpha.dto.CommentDTO;
import com.scai.socialproject.alpha.socialnetworkalpha.dto.CommentLikeDTO;
import com.scai.socialproject.alpha.socialnetworkalpha.dto.NotificationDTO;
import com.scai.socialproject.alpha.socialnetworkalpha.dto.NotificationTypeDTO;
import com.scai.socialproject.alpha.socialnetworkalpha.dto.PostDTO;
import com.scai.socialproject.alpha.socialnetworkalpha.dto.ProfileDTO;
import com.scai.socialproject.alpha.socialnetworkalpha.entity.Comment;
import com.scai.socialproject.alpha.socialnetworkalpha.entity.CommentLike;
import com.scai.socialproject.alpha.socialnetworkalpha.entity.Follow;
import com.scai.socialproject.alpha.socialnetworkalpha.entity.Like;
import com.scai.socialproject.alpha.socialnetworkalpha.entity.Notification;
import com.scai.socialproject.alpha.socialnetworkalpha.entity.Post;
import com.scai.socialproject.alpha.socialnetworkalpha.entity.Profile;
import com.scai.socialproject.alpha.socialnetworkalpha.utils.DTONotificationUtils;
import com.scai.socialproject.alpha.socialnetworkalpha.utils.DTOProfileUtils;
import com.scai.socialproject.alpha.socialnetworkalpha.utils.ImgUtils;

@Repository
public class CrudNotificationImpl implements CrudNotification {
	@Autowired
	private CrudPost postRepo;
	@Autowired
	private CrudComment commentRepo;
	@Autowired
	private EntityManager entityManager;
	@Autowired
	private ImgUtils imgUtils;

	@Override
	public boolean setNotificationsAsSeenForProfile(String idProfile) {
		Session session = entityManager.unwrap(Session.class);
		Query query = session.createQuery("update Notification set isseen = 1 where id_profile_to_notify = :idProfile AND isseen = 0");
		query.setParameter("idProfile", idProfile);
		query.executeUpdate();
		
		return true;
	}

	@Override
	public List<NotificationDTO> getNotificationsForProfile(String idProfile) {
		Session session = entityManager.unwrap(Session.class);
		Query<Notification> query = session.createQuery("from Notification where id_profile_to_notify = :idProfile ORDER BY date DESC");
		query.setParameter("idProfile", idProfile);
		List<Notification> notifications = query.getResultList();
		List<NotificationDTO> notificationsDTO = DTONotificationUtils.DTONotificationFromNotification(notifications, imgUtils);
		
		return notificationsDTO;
	}

	@Override
	public String addNewNotification(Notification notification) {
		Session session = entityManager.unwrap(Session.class);		
		
		return (String) session.save(notification);
	}

	@Override
	public void deleteCommentLikeNotification(String idProfileNotificator, String idPost, String comment) {
		Session session = entityManager.unwrap(Session.class);		
		Query query = session.createQuery("delete from Notification where id_profile_notificator = :idProfile AND id_post = :idPost AND comment = :comment AND notification_type = :notifType");
		query.setParameter("idProfile", idProfileNotificator);
		query.setParameter("idPost", idPost);
		query.setParameter("comment", comment);
		query.setParameter("notifType", 2);
		
		query.executeUpdate();
	}

	@Override
	public void deleteFollowNotification(String idProfileNotificator, String idProfileToNotify) {
		Session session = entityManager.unwrap(Session.class);		
		Query query = session.createQuery("delete from Notification where id_profile_notificator = :idProfile1 AND id_profile_to_notify = :idProfile2 AND notification_type = :notifType");
		query.setParameter("idProfile1", idProfileNotificator);
		query.setParameter("idProfile2", idProfileToNotify);
		query.setParameter("notifType", 3);
		
		query.executeUpdate();
	}
}
