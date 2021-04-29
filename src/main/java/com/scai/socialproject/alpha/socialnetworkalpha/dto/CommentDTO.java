package com.scai.socialproject.alpha.socialnetworkalpha.dto;

public class CommentDTO {
	public String idComment;
	public String comment;
	public String date;
	public String idPost;
	public String idProfile;
	
	public CommentDTO() {}

	public CommentDTO(String idComment, String comment, String date, String idPost, String idProfile) {
		super();
		this.idComment = idComment;
		this.comment = comment;
		this.date = date;
		this.idPost = idPost;
		this.idProfile = idProfile;
	}

	public String getIdComment() {
		return idComment;
	}

	public void setIdComment(String idComment) {
		this.idComment = idComment;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getIdPost() {
		return idPost;
	}

	public void setIdPost(String idPost) {
		this.idPost = idPost;
	}

	public String getIdProfile() {
		return idProfile;
	}

	public void setIdProfile(String idProfile) {
		this.idProfile = idProfile;
	}

	@Override
	public String toString() {
		return "CommentDTO [idComment=" + idComment + ", comment=" + comment + ", date=" + date + ", idPost=" + idPost
				+ ", idProfile=" + idProfile + "]";
	}
}
