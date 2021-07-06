package com.scai.socialproject.alpha.socialnetworkalpha.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(name="comments", schema = "social_clone")
public class Comment {
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(name="id_comment")
	private String idComment;
	
	@Column(name="comment")
	private String comment;
	
	@Column(name="date")
	private Date date;
	
	@Column(name="isseen")
	private boolean isSeen;
	
	@ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE, 
                        CascadeType.REFRESH, CascadeType.DETACH})
	@JoinColumn(name="id_post")
	private Post post;
	
	@ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE, 
                        CascadeType.REFRESH, CascadeType.DETACH, CascadeType.REMOVE})
	@JoinColumn(name="id_writer")
	private Profile writer;
	
	@OneToMany(mappedBy="comment",
			   cascade=CascadeType.ALL,
			   fetch=FetchType.LAZY)
	private List<CommentLike> commentLikes;
	
	public Comment() {}

	public Comment(String comment, Long date) {
		super();
		this.comment = comment;
		this.date = new Date(date);
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
	
	public Long getDateMillis() {
		return date.getTime();
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}
	
	public Profile getWriter() {
		return writer;
	}

	public void setWriter(Profile writer) {
		this.writer = writer;
	}
	
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
	
	public boolean isSeen() {
		return isSeen;
	}

	public void setSeen(boolean isSeen) {
		this.isSeen = isSeen;
	}
	
	@Override
	public String toString() {
		return "Comment [idComment=" + idComment + ", comment=" + comment + ", date=" + date + "]";
	}
}
