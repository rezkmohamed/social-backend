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
	public List<NotificationDTO> getNewFollowersNotificationForProfile(String idProfile) {
		/*Session session = entityManager.unwrap(Session.class);
		Query<Follow> queryNewFollowers = session.createQuery("from Follow where id_followed = :idProfile ORDER BY date DESC");
		queryNewFollowers.setParameter("idProfile", idProfile);
		List<Follow> newFollowers = queryNewFollowers.getResultList();
		List<NotificationDTO> newFollowersNotification = DTONotificationUtils.DTONotificationFromFollow(newFollowers, imgUtils);
		
		return newFollowersNotification;*/
		return null;
	}

	@Override
	public List<NotificationDTO> getNewLikesNotificationForProfile(String idProfile) {
		/*Session session = entityManager.unwrap(Session.class);
		Profile profile = session.get(Profile.class, idProfile);
		ProfileDTO profileDTO = DTOProfileUtils.profileToDTO(profile);
		List<PostDTO> posts = postRepo.findPostsProfilePage(idProfile);
		List<String> idPosts = new ArrayList<>();
		for(PostDTO p : posts) {
			idPosts.add(p.getIdPost());
		}
		Query<Like> queryNewLikes = session.createQuery("from Like where id_post in (:idPost) AND id_profile_liker <> :idProfile ORDER BY date DESC");
		queryNewLikes.setParameter("idPost", idPosts);
		queryNewLikes.setParameter("idProfile", idProfile);
		List<Like> newLikes = queryNewLikes.getResultList();
		List<NotificationDTO> newLikesNotification = DTONotificationUtils.DTONotificationFromLike(newLikes, profileDTO, imgUtils);
		
		return newLikesNotification;*/
		return null;

	}
	
	@Override
	public List<NotificationDTO> getNewCommentsForProfile(String idProfile) {
		/*Session session = entityManager.unwrap(Session.class);
		Profile profile = session.get(Profile.class, idProfile);
		ProfileDTO profileDTO = DTOProfileUtils.profileToDTO(profile);
		List<PostDTO> posts = postRepo.findPostsProfilePage(idProfile);
		List<String> idPosts = new ArrayList<>();
		for(PostDTO p : posts) {
			idPosts.add(p.getIdPost());
		}
		Query<Comment> queryNewComments = session.createQuery("from Comment where id_post in (:idPost) AND id_writer <> :idProfile ORDER BY date DESC");
		queryNewComments.setParameter("idPost", idPosts);
		queryNewComments.setParameter("idProfile", idProfile);
		List<Comment> newComments = queryNewComments.getResultList();
		List<NotificationDTO> newCommentsNotification = DTONotificationUtils.DTONotificationFromComment(newComments, profileDTO, imgUtils);
		
		return newCommentsNotification;*/
		return null;

	}

	@Override
	public List<NotificationDTO> getNewCommentLikesForProfile(String idProfile) {
//		Session session = entityManager.unwrap(Session.class);
//		Profile profile = session.get(Profile.class, idProfile);
//		ProfileDTO profileDTO = DTOProfileUtils.profileToDTO(profile);
//		List<CommentDTO> comments = commentRepo.findAllCommentsForProfile(idProfile);
//		List<String> idComments = new ArrayList<>();
//		for(CommentDTO c : comments) {
//			idComments.add(c.getIdComment());
//		}
//		Query<CommentLike> queryNewCommentLikes = session.createQuery("from CommentLike where id_comment in (:idComment) AND id_liker <> :idProfile ORDER BY date DESC");
//		queryNewCommentLikes.setParameter("idComment", idComments);
//		queryNewCommentLikes.setParameter("idProfile", idProfile);
//		List<CommentLike> newCommentLikes = queryNewCommentLikes.getResultList();
//		List<NotificationDTO> newCommentLikesNotification = DTONotificationUtils.DTONotificationFromCommentLike(newCommentLikes, profileDTO, imgUtils);
//		
//		return newCommentLikesNotification;
		return null;

	}

	@Override
	public boolean setNotificationsAsSeenForProfile(String idProfile) {
//		Session session = entityManager.unwrap(Session.class);
//		Query querySetNewFollowersAsSeen = session.createQuery("update Follow set isseen = 1 where id_followed = :idProfile AND isseen = 0");
//		querySetNewFollowersAsSeen.setParameter("idProfile", idProfile);
//		int ris = querySetNewFollowersAsSeen.executeUpdate();
//		
//		List<PostDTO> posts = postRepo.findPostsProfilePage(idProfile);
//		List<String> idPosts = new ArrayList<>();
//		for(PostDTO p : posts) {
//			idPosts.add(p.getIdPost());
//		}
//		
//		Query querySetNewLikesAsSeen = session.createQuery("update Like set isseen = 1 where id_post in (:idPost) AND isseen = 0");
//		querySetNewLikesAsSeen.setParameter("idPost", idPosts);
//		querySetNewLikesAsSeen.executeUpdate();
//		
//		
//		Query querySetNewCommentsAsSeen = session.createQuery("update Comment set isseen = 1 where id_post in (:idPost) AND isseen = 0");
//		querySetNewCommentsAsSeen.setParameter("idPost", idPosts);
//		querySetNewCommentsAsSeen.executeUpdate();
//		
//		
//		
//		
//		List<CommentDTO> comments = commentRepo.findAllCommentsForProfile(idProfile);
//		List<String> idComments = new ArrayList<>();
//		for(CommentDTO c : comments) {
//			idComments.add(c.getIdComment());
//		}
//		Query querySetNewCommentLikesAsSeen = session.createQuery("update CommentLike set isseen = 1 where id_comment in (:idComment) AND isseen = 0");
//		querySetNewCommentLikesAsSeen.setParameter("idComment", idComments);
//		querySetNewCommentLikesAsSeen.executeUpdate();
//		
//		if(ris > 0) {
//			return true;
//		}
//		
//		return false;
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
}
