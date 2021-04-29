package com.scai.socialproject.alpha.socialnetworkalpha.dto;

public class PostDTO {
	public String idPost;
	public String urlImg;
	public String description;
	public String date;
	public String idProfile;
	
	public PostDTO() {}

	public PostDTO(String idPost, String urlImg, String description, String date, String idProfile) {
		super();
		this.idPost = idPost;
		this.urlImg = urlImg;
		this.description = description;
		this.date = date;
		this.idProfile = idProfile;
	}

	public String getIdPost() {
		return idPost;
	}

	public void setIdPost(String idPost) {
		this.idPost = idPost;
	}

	public String getUrlImg() {
		return urlImg;
	}

	public void setUrlImg(String urlImg) {
		this.urlImg = urlImg;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getIdProfile() {
		return idProfile;
	}

	public void setIdProfile(String idProfile) {
		this.idProfile = idProfile;
	}

	@Override
	public String toString() {
		return "PostDTO [idPost=" + idPost + ", urlImg=" + urlImg + ", description=" + description + ", date=" + date
				+ ", idProfile=" + idProfile + "]";
	}
}
