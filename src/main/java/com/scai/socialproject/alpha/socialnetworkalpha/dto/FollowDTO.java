package com.scai.socialproject.alpha.socialnetworkalpha.dto;

public class FollowDTO {
	private String idFollow;
	private String date;
	private String idFollower;
	private String idFollowed;
	
	public FollowDTO() {}

	public FollowDTO(String idFollow, String date, String idFollower, String idFollowed) {
		super();
		this.idFollow = idFollow;
		this.date = date;
		this.idFollower = idFollower;
		this.idFollowed = idFollowed;
	}

	public String getIdFollow() {
		return idFollow;
	}

	public void setIdFollow(String idFollow) {
		this.idFollow = idFollow;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getIdFollower() {
		return idFollower;
	}

	public void setIdFollower(String idFollower) {
		this.idFollower = idFollower;
	}

	public String getIdFollowed() {
		return idFollowed;
	}

	public void setIdFollowed(String idFollowed) {
		this.idFollowed = idFollowed;
	}

	@Override
	public String toString() {
		return "FollowDTO [idFollow=" + idFollow + ", date=" + date + ", idFollower=" + idFollower + ", idFollowed="
				+ idFollowed + "]";
	}
}
