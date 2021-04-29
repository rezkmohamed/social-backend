package com.scai.socialproject.alpha.socialnetworkalpha.entity;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="like")
public class Like {
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(name="id_like")
	private String id;
	
	@Column(name="date")
	private String date;
	
	@ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE, 
                        CascadeType.REFRESH, CascadeType.DETACH})
	@JoinColumn(name="id_post")
	private Post post;
	
	@ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE, 
                        CascadeType.REFRESH, CascadeType.DETACH})
	@JoinColumn(name="id_profile_liker")
	private Profile profileLiker;
	
	public Like() {}

	public Like(String date) {
		super();
		this.date = date;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@JsonIgnore
	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	@JsonIgnore
	public Profile getProfileLiker() {
		return profileLiker;
	}

	public void setProfileLiker(Profile profileLiker) {
		this.profileLiker = profileLiker;
	}
}
