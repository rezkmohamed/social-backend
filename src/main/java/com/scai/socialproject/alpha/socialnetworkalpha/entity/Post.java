package com.scai.socialproject.alpha.socialnetworkalpha.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="post")
public class Post {
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(name="id_post")
	private String idPost;
	
	@Column(name="img_post")
	private String img_post;
	
	@Column(name="description")
	private String description;
	
	@Column(name="date")
	private Date date;
	
	@ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE, 
			            CascadeType.REFRESH, CascadeType.DETACH},
			   fetch=FetchType.LAZY)
	@JoinColumn(name="id_profile")
	@JsonIgnore
	private Profile profile;
	
	@OneToMany(mappedBy="post",
			   cascade=CascadeType.ALL,
			   fetch=FetchType.LAZY)
	private List<Comment> comments;
	
	@OneToMany(mappedBy="post",
			   cascade=CascadeType.ALL,
			   fetch=FetchType.LAZY)
	private List<Like> likes;
	
	@OneToMany(mappedBy="post",
			   cascade=CascadeType.ALL,
			   fetch=FetchType.LAZY)
	private List<Notification> notifications;
	
	public Post() {}

	public Post(String img_post, String description, Long date) {
		super();
		this.img_post = img_post;
		this.description = description;
		this.date = new Date(date);
	}

	public String getIdPost() {
		return idPost;
	}

	public void setIdPost(String idPost) {
		this.idPost = idPost;
	}

	public String getImg_post() {
		return img_post;
	}

	public void setImg_post(String img_post) {
		this.img_post = img_post;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getDateMillis() {
		return date.getTime();
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public Profile getProfile() {
		return profile;
	}
	
	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	public void addComment(Comment comment) {
		if(comments == null) {
			comments = new ArrayList<>();
		}
		
		comments.add(comment);
	}
	
	public List<Like> getLikes() {
		return likes;
	}

	public void setLikes(List<Like> likes) {
		this.likes = likes;
	}
	
	public void addLike(Like like) {
		if(likes == null) {
			likes = new ArrayList<>();
		}
		
		likes.add(like);
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public List<Notification> getNotifications() {
		return notifications;
	}

	public void setNotifications(List<Notification> notifications) {
		this.notifications = notifications;
	}
}
