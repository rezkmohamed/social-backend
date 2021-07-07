package com.scai.socialproject.alpha.socialnetworkalpha.dto;

import java.util.Date;

public class CommentLikeDTO {
	private String idCommentLike;
	private String idComment;
	private String idProfile;
	private Date date;
	
	public CommentLikeDTO() {}

	public CommentLikeDTO(String idCommentLike, String idComment, String idProfile) {
		super();
		this.idCommentLike = idCommentLike;
		this.idComment = idComment;
		this.idProfile = idProfile;
	}

	public String getIdCommentLike() {
		return idCommentLike;
	}

	public void setIdCommentLike(String idCommentLike) {
		this.idCommentLike = idCommentLike;
	}

	public String getIdComment() {
		return idComment;
	}

	public void setIdComment(String idComment) {
		this.idComment = idComment;
	}

	public String getIdProfile() {
		return idProfile;
	}

	public void setIdProfile(String idProfile) {
		this.idProfile = idProfile;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "CommentLikeDTO [idCommentLike=" + idCommentLike + ", idComment=" + idComment + ", idProfile="
				+ idProfile + "]";
	}
}
