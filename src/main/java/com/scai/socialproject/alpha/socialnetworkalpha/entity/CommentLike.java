package com.scai.socialproject.alpha.socialnetworkalpha.entity;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="comment_like")
public class CommentLike {
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(name="id_comment_like")
	private String idCommentLike;
	
	@Column(name="date")
	private String date;
	
	@ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE, 
                        CascadeType.REFRESH, CascadeType.DETACH})
	@JoinColumn(name="id_comment")
	private Comment comment;
	
	@ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE, 
                        CascadeType.REFRESH, CascadeType.DETACH})
	@JoinColumn(name="id_liker")
	private Profile profile;
	
	public CommentLike() {}

	public CommentLike(String date) {
		super();
		this.date = date;
	}

	public String getIdCommentLike() {
		return idCommentLike;
	}

	public void setIdCommentLike(String idCommentLike) {
		this.idCommentLike = idCommentLike;
	}
	
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@JsonIgnore
	public Comment getComment() {
		return comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}

	@JsonIgnore
	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}
	
	
}
