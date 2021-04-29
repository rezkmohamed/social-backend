package com.scai.socialproject.alpha.socialnetworkalpha.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.scai.socialproject.alpha.socialnetworkalpha.dto.LikeDTO;
import com.scai.socialproject.alpha.socialnetworkalpha.entity.Like;
import com.scai.socialproject.alpha.socialnetworkalpha.repository.CrudLike;

@Service
public class LikeServiceImpl implements LikeService {
	private CrudLike likeRepo;
	
	@Autowired
	public LikeServiceImpl(CrudLike likeRepo) {
		super();
		this.likeRepo = likeRepo;
	}

	@Override
	@Transactional
	public List<LikeDTO> findAllLikes() {
		return likeRepo.findAllLikes();
	}

	@Override
	public List<LikeDTO> findLikesForPost(String idPost) {
		return likeRepo.findLikesForPost(idPost);
	}

	@Override
	public void addLike(Like like) {
		likeRepo.addLike(like);
	}

	@Override
	public void addLike(String idPost, String idProfile) {
		likeRepo.addLike(idPost, idProfile);
	}

	@Override
	public void deleteLike(String idLike) {
		likeRepo.deleteLike(idLike);
	}

	@Override
	public void deleteLike(String idPost, String idProfile) {
		likeRepo.deleteLike(idPost, idProfile);
	}

}
