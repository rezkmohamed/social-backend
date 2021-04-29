package com.scai.socialproject.alpha.socialnetworkalpha.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.scai.socialproject.alpha.socialnetworkalpha.dto.CommentDTO;
import com.scai.socialproject.alpha.socialnetworkalpha.entity.Comment;
import com.scai.socialproject.alpha.socialnetworkalpha.repository.CrudComment;

@Service
public class CommentServiceImpl implements CommentService {
	private CrudComment commentRepo;
	
	@Autowired
	public CommentServiceImpl(CrudComment commentRepo) {
		this.commentRepo = commentRepo;
	}

	@Override
	@Transactional
	public List<CommentDTO> findAllComments() {
		return commentRepo.findAllComments();
	}

	@Override
	@Transactional
	public List<CommentDTO> findAllComments(String idPost) {
		return commentRepo.findAllComments(idPost);
	}

	@Override
	@Transactional
	public void addComment(Comment comment) {
		commentRepo.addComment(comment);
	}

	@Override
	@Transactional
	public void deleteComment(String idPost, String idProfile) {
		commentRepo.deleteComment(idPost, idProfile);
	}

}
