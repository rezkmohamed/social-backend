package com.scai.socialproject.alpha.socialnetworkalpha.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.query.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.scai.socialproject.alpha.socialnetworkalpha.dto.NotificationDTO;
import com.scai.socialproject.alpha.socialnetworkalpha.dto.PostDTO;
import com.scai.socialproject.alpha.socialnetworkalpha.dto.ProfileDTO;
import com.scai.socialproject.alpha.socialnetworkalpha.entity.Follow;
import com.scai.socialproject.alpha.socialnetworkalpha.entity.Like;
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
		List<NotificationDTO> ris = new ArrayList<>();
		Session session = entityManager.unwrap(Session.class);
		Query<Follow> queryNewFollowers = session.createQuery("from Follow where id_followed = :idProfile ORDER BY date DESC");
		queryNewFollowers.setParameter("idProfile", idProfile);
		List<Follow> newFollowers = queryNewFollowers.getResultList();
		List<NotificationDTO> newFollowersNotification = DTONotificationUtils.DTONotificationFromFollow(newFollowers, imgUtils);
		
		
		Profile profile = session.get(Profile.class, idProfile);
		ProfileDTO profileDTO = DTOProfileUtils.profileToDTO(profile);
		List<PostDTO> posts = postRepo.findPostsProfilePage(idProfile);
		List<String> idPosts = new ArrayList<>();
		for(PostDTO p : posts) {
			idPosts.add(p.getIdPost());
		}
		Query<Like> queryNewLikes = session.createQuery("from Like where id_post in (:idPost) ORDER BY date DESC");
		queryNewLikes.setParameter("idPost", idPosts);
		List<Like> newLikes = queryNewLikes.getResultList();
		List<NotificationDTO> newLikesNotification = DTONotificationUtils.DTONotificationFromLike(newLikes, profileDTO, imgUtils);
		ris.addAll(newFollowersNotification);
		ris.addAll(newLikesNotification);
		
		
		return ris;
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
