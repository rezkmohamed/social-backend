package com.scai.socialproject.alpha.socialnetworkalpha.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

/**
 * 
CREATE TABLE `profile` (
  `id_profile` varchar(45) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `nickname` varchar(45) DEFAULT NULL,
  `bio` varchar(45) DEFAULT NULL, 
  `profile_pic` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_profile`)
);
 *
 */
@Entity
@Table(name="profile")
public class Profile {
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(name="id_profile")
	private String idProfile;
	
	@Column(name="name")
	private String name;
	
	@Column(name="nickname")
	private String nickname;
	
	@Column(name="bio")
	private String bio;
	
	@Column(name="profile_pic")
	private String proPic;
	
	@Column(name="email")
	private String email;
	
	@Column(name="password")
	private String password;
	
	@OneToMany(fetch=FetchType.LAZY,
			   mappedBy="profile",
			   cascade= CascadeType.ALL)
	private List<Post> posts;
	
	@OneToMany(mappedBy="followed",
			   cascade=CascadeType.ALL,
			   fetch=FetchType.LAZY)
	private List<Follow> followers;
	
	@OneToMany(mappedBy="follower",
			   cascade=CascadeType.ALL,
			   fetch=FetchType.LAZY)
	private List<Follow> following;
	
	@OneToMany(mappedBy="writer",
			   cascade=CascadeType.ALL,
			   fetch=FetchType.LAZY)
	private List<Comment> comments;
	
	@OneToMany(mappedBy="profile",
			   cascade=CascadeType.ALL,
			   fetch=FetchType.LAZY)
	private List<CommentLike> commentLikes;
	
	@OneToMany(mappedBy="profileSender", 
			   cascade=CascadeType.ALL,
			   fetch=FetchType.LAZY)
	private List<Message> messagesSented;
	
	@OneToMany(mappedBy="profileReciver",
			   cascade=CascadeType.ALL,
			   fetch=FetchType.LAZY)
	private List<Message> messagesRecived;
	
	@OneToMany(mappedBy="firstProfile",
			   cascade=CascadeType.ALL,
			   fetch=FetchType.LAZY)
	private List<Conversation> conversations;
	
	public Profile() {}

	public Profile(String name, String nickname, String bio, String proPic, String email, String password) {
		super();
		this.name = name;
		this.nickname = nickname;
		this.bio = bio;
		this.proPic = proPic;
		this.email = email;
		this.password = password;
	}

	public String getIdProfile() {
		return idProfile;
	}

	public void setIdProfile(String idProfile) {
		this.idProfile = idProfile;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}
	
	public void addPost(Post post) {
		if(this.posts == null) {
			this.posts = new ArrayList<>();
		}
		
		this.posts.add(post);
	}
	
	public List<Follow> getFollowers() {
		return followers;
	}

	public void setFollowers(List<Follow> followers) {
		this.followers = followers;
	}
	
	public void addFollower(Follow follow) {
		if(followers == null) {
			followers = new ArrayList<>();
		}
		
		followers.add(follow);
	}
	
	public List<Follow> getFollowing() {
		return following;
	}

	public void setFollowing(List<Follow> following) {
		this.following = following;
	}
	
	public void addFollowing(Follow follow) {
		if(following == null) {
			following = new ArrayList<>();
		}
		
		following.add(follow);
	}
	
	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	
	public void addComment(Comment comment) {
		if(comments == null) {
			comments = new ArrayList<>();
		}
		
		comments.add(comment);
	}
	
	public List<CommentLike> getCommentLikes() {
		return commentLikes;
	}

	public void setCommentLikes(List<CommentLike> commentLike) {
		this.commentLikes = commentLike;
	}
	
	public void addCommentLike(CommentLike commentLike) {
		if(this.commentLikes == null) {
			this.commentLikes = new ArrayList<>();
		}
		
		this.commentLikes.add(commentLike);
	}
	
	public List<Message> getMessagesSented() {
		return messagesSented;
	}

	public void setMessagesSented(List<Message> messagesSented) {
		this.messagesSented = messagesSented;
	}

	public List<Message> getMessagesRecived() {
		return messagesRecived;
	}

	public void setMessagesRecived(List<Message> messagesRecived) {
		this.messagesRecived = messagesRecived;
	}
	
	public List<Conversation> getConversations() {
		return conversations;
	}

	public void setConversations(List<Conversation> conversations) {
		this.conversations = conversations;
	}
	
	@Override
	public String toString() {
		return "Profile [idProfile=" + idProfile + ", name=" + name + ", nickname=" + nickname + ", bio=" + bio
				+ ", proPic=" + proPic + ", email=" + email + ", password=" + password + "]";
	}
}
