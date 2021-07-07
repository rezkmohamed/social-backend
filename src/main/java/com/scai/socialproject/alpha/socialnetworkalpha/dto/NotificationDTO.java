package com.scai.socialproject.alpha.socialnetworkalpha.dto;

import java.util.Date;

public class NotificationDTO {
	private ProfileDTO profileNotificator;
	private ProfileDTO profileToNotify;
	private NotificationTypeDTO notificationType;
	private Date date;
	private boolean isSeen;
	
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
}
