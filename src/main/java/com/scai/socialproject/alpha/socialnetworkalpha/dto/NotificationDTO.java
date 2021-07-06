package com.scai.socialproject.alpha.socialnetworkalpha.dto;

public class NotificationDTO {
	private ProfileDTO profileNotificator;
	private ProfileDTO profileToNotify;
	private NotificationTypeDTO notificationType;
	
	public NotificationDTO() {}
	
	public NotificationDTO(ProfileDTO profileNotificator, ProfileDTO profileToNotify,
			NotificationTypeDTO notificationType) {
		super();
		this.profileNotificator = profileNotificator;
		this.profileToNotify = profileToNotify;
		this.notificationType = notificationType;
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
}
