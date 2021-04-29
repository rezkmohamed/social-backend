package com.scai.socialproject.alpha.socialnetworkalpha.dto;

public class LikeDTO {
	public String idLike;
	public String date;
	public String idPost;
	public String idProfile;
	
	public LikeDTO() {}

	public LikeDTO(String idLike, String date, String idPost, String idProfile) {
		super();
		this.idLike = idLike;
		this.date = date;
		this.idPost = idPost;
		this.idProfile = idProfile;
	}

	public String getIdLike() {
		return idLike;
	}

	public void setIdLike(String idLike) {
		this.idLike = idLike;
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
		return "LikeDTO [idLike=" + idLike + ", date=" + date + ", idPost=" + idPost + ", idProfile=" + idProfile + "]";
	}
}
