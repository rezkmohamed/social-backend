package com.scai.socialproject.alpha.socialnetworkalpha.entity;

import java.util.Date;

import javax.persistence.*;


import org.hibernate.annotations.GenericGenerator;

import com.scai.socialproject.alpha.socialnetworkalpha.dto.NotificationTypeDTO;

@Entity
@Table(name="notifications")
public class Notification {
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(name="id_notification")
	private String idNotification;
	
	@ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE, 
            CascadeType.REFRESH, CascadeType.DETACH})
	@JoinColumn(name="id_profile_notificator")
	private Profile profileNotificator;
	
	@ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE, 
            CascadeType.REFRESH, CascadeType.DETACH})
	@JoinColumn(name="id_profile_to_notify")
	private Profile profileToNotify;
	
	@ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE, 
            CascadeType.REFRESH, CascadeType.DETACH})
	@JoinColumn(name="id_post")
	private Post post;
	
	@Column(name="comment")
	private String comment;
	
	@Column(name="date")
	private Date date;

	@Column(name="isseen")
	private boolean isSeen;
	
	@Column(name="notification_type")
	private NotificationTypeDTO notificationType;
	
	public Notification() {}

	public Notification(boolean isSeen, NotificationTypeDTO notificationType) {
		this.isSeen = isSeen;
		this.notificationType = notificationType;
	}

	public Notification(Profile profileNotificator, Profile profileToNotify, boolean isSeen,
			NotificationTypeDTO notificationType) {
		super();
		this.profileNotificator = profileNotificator;
		this.profileToNotify = profileToNotify;
		this.isSeen = isSeen;
		this.notificationType = notificationType;
	}

	public String getIdNotification() {
		return idNotification;
	}

	public void setIdNotification(String idNotification) {
		this.idNotification = idNotification;
	}

	public Profile getProfileNotificator() {
		return profileNotificator;
	}

	public void setProfileNotificator(Profile profileNotificator) {
		this.profileNotificator = profileNotificator;
	}

	public Profile getProfileToNotify() {
		return profileToNotify;
	}

	public void setProfileToNotify(Profile profileToNotify) {
		this.profileToNotify = profileToNotify;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public boolean isSeen() {
		return isSeen;
	}

	public void setSeen(boolean isSeen) {
		this.isSeen = isSeen;
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
}
