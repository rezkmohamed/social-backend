package com.scai.socialproject.alpha.socialnetworkalpha.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.scai.socialproject.alpha.socialnetworkalpha.dto.NotificationDTO;
import com.scai.socialproject.alpha.socialnetworkalpha.dto.NotificationTypeDTO;
import com.scai.socialproject.alpha.socialnetworkalpha.dto.ProfileDTO;
import com.scai.socialproject.alpha.socialnetworkalpha.entity.Comment;
import com.scai.socialproject.alpha.socialnetworkalpha.entity.CommentLike;
import com.scai.socialproject.alpha.socialnetworkalpha.entity.Follow;
import com.scai.socialproject.alpha.socialnetworkalpha.entity.Like;
import com.scai.socialproject.alpha.socialnetworkalpha.entity.Post;

public class DTONotificationUtils {
	public static NotificationDTO DTONoficationFromFollow(Follow follow) {
		NotificationDTO ris = new NotificationDTO(DTOProfileUtils.profileToDTO(follow.getFollower()),
				DTOProfileUtils.profileToDTO(follow.getFollowed()), NotificationTypeDTO.FOLLOW, follow.getDateMillis());
		ris.setSeen(follow.isSeen());
		return ris;
	}
	
	public static List<NotificationDTO> DTONotificationFromFollow(List<Follow> follows, ImgUtils imgUtils){
		List<NotificationDTO> ris = new ArrayList<>();
		for(Follow follow : follows) {
			NotificationDTO tmp = DTONoficationFromFollow(follow);
			if(tmp.getProfileNotificator().getProPic() != null) {
				try {
					tmp.getProfileNotificator().setProPic(imgUtils.fileImgToBase64Encoding(tmp.getProfileNotificator().getProPic()));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			ris.add(tmp);
		}
		
		return ris;
	}
	
	public static NotificationDTO DTONotificationFromLike(Like like, ProfileDTO profileToNotify) {
		NotificationDTO ris = new NotificationDTO(DTOProfileUtils.profileToDTO(like.getProfileLiker()),
				profileToNotify, NotificationTypeDTO.LIKE, like.getDateMillis());
		ris.setSeen(like.isSeen());
		
		return ris;
	}
	
	public static List<NotificationDTO> DTONotificationFromLike(List<Like> likes, ProfileDTO profileToNotify, ImgUtils imgUtils){
		List<NotificationDTO> ris = new ArrayList<>();
		for(Like like : likes) {
			NotificationDTO tmp = DTONotificationFromLike(like, profileToNotify);
			Post p = like.getPost();
			tmp.setPost(DTOPostUtils.postToDTO(p));
			if(tmp.getProfileNotificator().getProPic() != null) {
				try {
					tmp.getProfileToNotify().setProPic(imgUtils.fileImgToBase64Encoding(tmp.getProfileNotificator().getProPic()));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			ris.add(tmp);
		}
		return ris;
	}
	
	public static NotificationDTO DTONotificationFromComment(Comment comment, ProfileDTO profileToNotify) {
		NotificationDTO ris = new NotificationDTO(DTOProfileUtils.profileToDTO(comment.getWriter()),
				profileToNotify, NotificationTypeDTO.COMMENT, comment.getDateMillis());
		ris.setSeen(comment.isSeen());
		
		return ris;
	}
	
	public static List<NotificationDTO> DTONotificationFromComment(List<Comment> comments, ProfileDTO profileToNotify, ImgUtils imgUtils){
		List<NotificationDTO> ris = new ArrayList<>();
		for(Comment comment : comments) {
			NotificationDTO tmp = DTONotificationFromComment(comment, profileToNotify);
			Post p = comment.getPost();
			tmp.setPost(DTOPostUtils.postToDTO(p));
			if(tmp.getProfileNotificator().getProPic() != null) {
				try {
					tmp.getProfileToNotify().setProPic(imgUtils.fileImgToBase64Encoding(tmp.getProfileNotificator().getProPic()));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			ris.add(tmp);
		}
		
		return ris;
	}
	
	public static NotificationDTO DTONotificationFromCommentLike(CommentLike commentLike, ProfileDTO profileToNotify){
		NotificationDTO ris = new NotificationDTO(DTOProfileUtils.profileToDTO(commentLike.getProfile()), profileToNotify, NotificationTypeDTO.COMMENT_LIKE,
													commentLike.getDateMillis());
		ris.setSeen(commentLike.isSeen());
		
		return ris;
	}
	
	public static List<NotificationDTO> DTONotificationFromCommentLike(List<CommentLike> commentLikes, ProfileDTO profileToNotify, ImgUtils imgUtils){
		List<NotificationDTO> ris = new ArrayList<>();
		for(CommentLike commentLike : commentLikes) {
			NotificationDTO tmp = DTONotificationFromCommentLike(commentLike, profileToNotify);
			Post p = commentLike.getComment().getPost();
			tmp.setPost(DTOPostUtils.postToDTO(p));
			if(tmp.getProfileNotificator().getProPic() != null) {
				try {
					tmp.getProfileToNotify().setProPic(imgUtils.fileImgToBase64Encoding(tmp.getProfileNotificator().getProPic()));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			ris.add(tmp);
		}
		
		return ris;
	}
}
