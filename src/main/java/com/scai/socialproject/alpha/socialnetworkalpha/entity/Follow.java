package com.scai.socialproject.alpha.socialnetworkalpha.entity;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="follow")
public class Follow {
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(name="id_follow")
	private String idFollow;
	
	@Column(name="date")
	private String date;
	
	@ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE, 
                        CascadeType.REFRESH, CascadeType.DETACH})
	@JoinColumn(name="id_follower")
	private Profile follower;
	
	@ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE, 
                        CascadeType.REFRESH, CascadeType.DETACH})
	@JoinColumn(name="id_followed")
	private Profile followed;
	
	public Follow() {}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	public String getIdFollow() {
		return idFollow;
	}

	public void setIdFollow(String idFollow) {
		this.idFollow = idFollow;
	}
	
	public Profile getFollower() {
		return follower;
	}

	public void setFollower(Profile follower) {
		this.follower = follower;
	}
	
	public Profile getFollowed() {
		return followed;
	}

	public void setFollowed(Profile followed) {
		this.followed = followed;
	}

	@Override
	public String toString() {
		return "Follow [idFollow=" + idFollow + ", date=" + date + ", follower=" + follower + ", followed=" + followed
				+ "]";
	}
}
