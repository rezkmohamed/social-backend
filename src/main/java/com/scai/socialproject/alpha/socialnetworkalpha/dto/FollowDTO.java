package com.scai.socialproject.alpha.socialnetworkalpha.dto;

public class FollowDTO {
	private String idFollow;
	private String idFollower;
	private String idFollowed;
	
	public FollowDTO() {}

	public FollowDTO(String idFollow, String idFollower, String idFollowed) {
		super();
		this.idFollow = idFollow;
		this.idFollower = idFollower;
		this.idFollowed = idFollowed;
	}

	public String getIdFollow() {
		return idFollow;
	}

	public void setIdFollow(String idFollow) {
		this.idFollow = idFollow;
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
		return "FollowDTO [idFollow=" + idFollow + ", idFollower=" + idFollower + ", idFollowed="
				+ idFollowed + "]";
	}
}
