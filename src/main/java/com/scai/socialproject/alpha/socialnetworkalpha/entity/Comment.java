package com.scai.socialproject.alpha.socialnetworkalpha.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="comment")
public class Comment {
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(name="id_comment")
	private String idComment;
	
	@Column(name="comment")
	private String comment;
	
	@Column(name="date")
	private String date;
	
	@ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE, 
                        CascadeType.REFRESH, CascadeType.DETACH})
	@JoinColumn(name="id_post")
	private Post post;
	
	@ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE, 
                        CascadeType.REFRESH, CascadeType.DETACH})
	@JoinColumn(name="id_profile")
	private Profile writer;
	
	@OneToMany(mappedBy="comment",
			   cascade=CascadeType.ALL,
			   fetch=FetchType.LAZY)
	private List<CommentLike> commentLikes;
	
	public Comment() {}

	public Comment(String comment, String date) {
		super();
		this.comment = comment;
		this.date = date;
	}

	public String getIdComment() {
		return idComment;
	}

	public void setIdComment(String idComment) {
		this.idComment = idComment;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
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
	public Profile getWriter() {
		return writer;
	}

	public void setWriter(Profile writer) {
		this.writer = writer;
	}
	
	@JsonIgnore
	public List<CommentLike> getCommentLikes() {
		return commentLikes;
	}

	public void setCommentLikes(List<CommentLike> commentLikes) {
		this.commentLikes = commentLikes;
	}
	
	public void addCommentlike(CommentLike commentLike) {
		if(this.commentLikes == null) {
			this.commentLikes = new ArrayList<>();
		}
		
		this.commentLikes.add(commentLike);
	}
	
	@Override
	public String toString() {
		return "Comment [idComment=" + idComment + ", comment=" + comment + ", date=" + date + "]";
	}
}
