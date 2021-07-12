package com.scai.socialproject.alpha.socialnetworkalpha.entity;

import java.util.Date;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="follow")
public class Follow {
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(name="id_follow")
	private String idFollow;
	
	@ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE, 
                        CascadeType.REFRESH, CascadeType.DETACH})
	@JoinColumn(name="id_follower")
	private Profile follower;
	
	@ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE, 
                        CascadeType.REFRESH, CascadeType.DETACH})
	@JoinColumn(name="id_followed")
	private Profile followed;
	
	@Column(name="date")
	private Date date;
	
	public Follow() {}

	
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
	
	public Long getDateMillis() {
		return date.getTime();
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	@Override
	public String toString() {
		return "Follow [idFollow=" + idFollow + ", follower=" + follower + ", followed=" + followed
				+ "]";
	}
}
