package com.scai.socialproject.alpha.socialnetworkalpha.dto;

import java.util.Date;

public class LikeDTO {
	private String idLike;
	private String idPost;
	private String idProfile;
	private Date date;
	
	public LikeDTO() {}

	public LikeDTO(String idLike, String idPost, String idProfile) {
		super();
		this.idLike = idLike;
		this.idPost = idPost;
		this.idProfile = idProfile;
	}

	public String getIdLike() {
		return idLike;
	}

	public void setIdLike(String idLike) {
		this.idLike = idLike;
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
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	@Override
	public String toString() {
		return "LikeDTO [idLike=" + idLike + ", idPost=" + idPost + ", idProfile=" + idProfile + "]";
	}
}
