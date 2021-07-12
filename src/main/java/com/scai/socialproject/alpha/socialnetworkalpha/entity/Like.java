package com.scai.socialproject.alpha.socialnetworkalpha.entity;

import java.util.Date;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="likes")
public class Like {
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(name="id_like")
	private String id;
	
	@Column(name="date")
	private Date date;
	
	@ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE, 
                        CascadeType.REFRESH, CascadeType.DETACH})
	@JoinColumn(name="id_post")
	private Post post;
	
	@ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE, 
                        CascadeType.REFRESH, CascadeType.DETACH})
	@JoinColumn(name="id_profile_liker")
	private Profile profileLiker;
	
	public Like() {}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public Profile getProfileLiker() {
		return profileLiker;
	}

	public void setProfileLiker(Profile profileLiker) {
		this.profileLiker = profileLiker;
	}
	
	public Long getDateMillis() {
		return date.getTime();
	}
	
	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Like [id=" + id + ", date=" + date + ", post=" + post + ", profileLiker="
				+ profileLiker + "]";
	}
}
