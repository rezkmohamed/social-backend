package com.scai.socialproject.alpha.socialnetworkalpha.dto;

import java.util.List;

import com.scai.socialproject.alpha.socialnetworkalpha.entity.CommentLike;
import com.scai.socialproject.alpha.socialnetworkalpha.utils.DTOCommentsLikeUtils;

public class CommentDTO {
	private String idComment;
	private String comment;
	private String date;
	private String idPost;
	private String idProfile;
	private String nicknameProfile;
	private List<CommentLikeDTO> commentLikes;
	private int commentLikesCounter;
	private boolean isLiked;
	
	public CommentDTO() {}

	public CommentDTO(String idComment, String comment, String date, String idPost, String idProfile) {
		super();
		this.idComment = idComment;
		this.comment = comment;
		this.date = date;
		this.idPost = idPost;
		this.idProfile = idProfile;
	}
	
	public CommentDTO(String idComment, String comment, String date, String idPost, String idProfile,
			String nicknameProfile) {
		super();
		this.idComment = idComment;
		this.comment = comment;
		this.date = date;
		this.idPost = idPost;
		this.idProfile = idProfile;
		this.nicknameProfile = nicknameProfile;
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

	public String getIdPost() {
		return idPost;
	}

	public void setIdPost(String idPost) {
		this.idPost = idPost;
	}

	public String getIdProfile() {
		return idProfile;
	}

	public void setIdProfile(String idProfile) {
		this.idProfile = idProfile;
	}

	public List<CommentLikeDTO> getCommentLikes() {
		return commentLikes;
	}

	public void setCommentlikes(List<CommentLike> commentLikes) {
		this.commentLikes = DTOCommentsLikeUtils.commentLikeToDTO(commentLikes);
	}

	public int getCommentLikesCounter() {
		return commentLikesCounter;
	}

	public void setCommentLikesCounter(int commentLikesCounter) {
		this.commentLikesCounter = commentLikesCounter;
	}
	
	public String getNicknameProfile() {
		return nicknameProfile;
	}

	public void setNicknameProfile(String nicknameProfile) {
		this.nicknameProfile = nicknameProfile;
	}

	public void setCommentLikes(List<CommentLikeDTO> commentLikes) {
		this.commentLikes = commentLikes;
	}

	public boolean isLiked() {
		return isLiked;
	}

	public void setLiked(boolean isLiked) {
		this.isLiked = isLiked;
	}
	
	@Override
	public String toString() {
		return "CommentDTO [idComment=" + idComment + ", comment=" + comment + ", date=" + date + ", idPost=" + idPost
				+ ", idProfile=" + idProfile + "]";
	}
}
