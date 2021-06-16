package com.scai.socialproject.alpha.socialnetworkalpha.dto;

import java.util.List;

public class ProfileDTO {
	private String id;
	private String name;
	private String nickname;
	private String bio;
	private String proPic;
	private String email;
	private List<PostDTO> posts;
	private int postsCounter;
	private List<FollowDTO> followers;
	private int followersCounter;
	private List<FollowDTO> following;
	private int followingCounter;
	
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
	
	public List<PostDTO> getPosts() {
		return posts;
	}

	public void setPosts(List<PostDTO> posts) {
		this.posts = posts;
	}

	public List<FollowDTO> getFollowers() {
		return followers;
	}

	public void setFollowers(List<FollowDTO> followers) {
		this.followers = followers;
	}

	public List<FollowDTO> getFollowing() {
		return following;
	}

	public void setFollowing(List<FollowDTO> following) {
		this.following = following;
	}
	
	public int getFollowersCounter() {
		return followersCounter;
	}

	public void setFollowersCounter(int followersCounter) {
		this.followersCounter = followersCounter;
	}

	public int getFollowingCounter() {
		return followingCounter;
	}

	public void setFollowingCounter(int followingCounter) {
		this.followingCounter = followingCounter;
	}
	
	public int getPostsCounter() {
		return postsCounter;
	}

	public void setPostsCounter(int postsCounter) {
		this.postsCounter = postsCounter;
	}
	
	@Override
	public String toString() {
		return "ProfileDTO [id=" + id + ", name=" + name + ", nickname=" + nickname + ", bio=" + bio + ", proPic="
				+ proPic + ", email=" + email + "]";
	}
}	
