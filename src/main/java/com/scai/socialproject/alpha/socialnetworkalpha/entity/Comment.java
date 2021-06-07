package com.scai.socialproject.alpha.socialnetworkalpha.entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
	private LocalDateTime date;
	
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

	public Comment(String comment, String date) {
		super();
		this.comment = comment;
		
		int endOfString = 19;
		date = date.substring(0, endOfString);
		DateTimeFormatter formatter = DateTimeFormatter
				.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
		LocalDateTime dateInput = LocalDateTime.parse(date, formatter);
		this.date = dateInput;
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
		return date.toString();
	}

	public LocalDateTime getLocalDateTime() {
		return date;
	}

	public void setDate(String date) {
		int endOfString = 19;
		date = date.substring(0, endOfString);
		DateTimeFormatter formatter = DateTimeFormatter
				.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
		LocalDateTime dateInput = LocalDateTime.parse(date, formatter);
		this.date = dateInput;
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
	
	@Override
	public String toString() {
		return "Comment [idComment=" + idComment + ", comment=" + comment + ", date=" + date + "]";
	}
}
