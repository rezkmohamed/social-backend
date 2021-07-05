package com.scai.socialproject.alpha.socialnetworkalpha.entity;

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
}
