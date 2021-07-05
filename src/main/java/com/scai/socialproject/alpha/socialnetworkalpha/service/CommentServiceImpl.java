package com.scai.socialproject.alpha.socialnetworkalpha.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.scai.socialproject.alpha.socialnetworkalpha.dto.CommentDTO;
import com.scai.socialproject.alpha.socialnetworkalpha.repository.CrudComment;

@Service
public class CommentServiceImpl implements CommentService {
	@Autowired
	private CrudComment commentRepo;
	

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
	public CommentDTO addComment(CommentDTO commentDTO) {	
		Date date = new Date();
		commentDTO.setDate(date.getTime());
		return commentRepo.addComment(commentDTO);
	}

	@Override
	@Transactional
	public void deleteComment(String idComment) {
		commentRepo.deleteComment(idComment);
	}

	@Override
	@Transactional
	public CommentDTO updateComment(CommentDTO commentDTO) {
		return commentRepo.updateComment(commentDTO);
	}

}
