package com.scai.socialproject.alpha.socialnetworkalpha.dto;

import java.util.Date;

public class NotificationDTO {
	private ProfileDTO profileNotificator;
	private ProfileDTO profileToNotify;
	private NotificationTypeDTO notificationType;
	private Date date;
	private boolean isSeen;
	private PostDTO post;
	private String commentMessage;
	private String idProfileNotificator;
	private String idProfileToNotify;
	private String imgProfileNotificator;
	private String nicknameProfileNotificator;
	private String idPost;
	
	public NotificationDTO() {}
	
	public NotificationDTO(ProfileDTO profileNotificator, ProfileDTO profileToNotify,
			NotificationTypeDTO notificationType, Long date) {
		super();
		this.profileNotificator = profileNotificator;
		this.profileToNotify = profileToNotify;
		this.notificationType = notificationType;
		this.date = new Date(date);
	}

	public ProfileDTO getProfileNotificator() {
		return profileNotificator;
	}

	public void setProfileNotificator(ProfileDTO profileNotificator) {
		this.profileNotificator = profileNotificator;
	}

	public ProfileDTO getProfileToNotify() {
		return profileToNotify;
	}

	public void setProfileToNotify(ProfileDTO profileToNotify) {
		this.profileToNotify = profileToNotify;
	}

	public NotificationTypeDTO getNotificationType() {
		return notificationType;
	}

	public void setNotificationType(NotificationTypeDTO notificationType) {
		this.notificationType = notificationType;
	}

	public Long getDateMillis() {
		return date.getTime();
	}
	
	public void setDate(Date date) {
		this.date = date;
	}

	public boolean isSeen() {
		return isSeen;
	}

	public void setSeen(boolean isSeen) {
		this.isSeen = isSeen;
	}

	public void setIsSeen(boolean isSeen) {
		this.isSeen = isSeen;
	}
	
	public PostDTO getPost() {
		return post;
	}

	public void setPost(PostDTO post) {
		this.post = post;
	}

	public String getIdProfileNotificator() {
		return idProfileNotificator;
	}

	public void setIdProfileNotificator(String idProfileNotificator) {
		this.idProfileNotificator = idProfileNotificator;
	}

	public String getIdProfileToNotify() {
		return idProfileToNotify;
	}

	public void setIdProfileToNotify(String idProfileToNotify) {
		this.idProfileToNotify = idProfileToNotify;
	}

	public String getImgProfileNotificator() {
		return imgProfileNotificator;
	}

	public void setImgProfileNotificator(String imgProfileNotificator) {
		this.imgProfileNotificator = imgProfileNotificator;
	}

	public String getNicknameProfileNotificator() {
		return nicknameProfileNotificator;
	}

	public void setNicknameProfileNotificator(String nicknameProfileNotificator) {
		this.nicknameProfileNotificator = nicknameProfileNotificator;
	}

	public String getIdPost() {
		return idPost;
	}

	public void setIdPost(String idPost) {
		this.idPost = idPost;
	}

	public String getCommentMessage() {
		return commentMessage;
	}

	public void setCommentMessage(String commentMessage) {
		this.commentMessage = commentMessage;
	}
}
