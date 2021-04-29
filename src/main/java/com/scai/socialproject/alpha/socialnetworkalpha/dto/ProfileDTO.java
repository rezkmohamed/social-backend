package com.scai.socialproject.alpha.socialnetworkalpha.dto;

public class ProfileDTO {
	private String id;
	private String name;
	private String nickname;
	private String bio;
	private String proPic;
	private String email;
	
	public ProfileDTO() {}

	public ProfileDTO(String id, String name, String nickname, String bio, String proPic, String email) {
		super();
		this.id = id;
		this.name = name;
		this.nickname = nickname;
		this.bio = bio;
		this.proPic = proPic;
		this.email = email;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public String getProPic() {
		return proPic;
	}

	public void setProPic(String proPic) {
		this.proPic = proPic;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public String toString() {
		return "ProfileDTO [id=" + id + ", name=" + name + ", nickname=" + nickname + ", bio=" + bio + ", proPic="
				+ proPic + ", email=" + email + "]";
	}
}	
