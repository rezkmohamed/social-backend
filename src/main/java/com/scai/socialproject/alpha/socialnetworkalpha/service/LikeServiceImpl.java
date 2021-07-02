package com.scai.socialproject.alpha.socialnetworkalpha.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.scai.socialproject.alpha.socialnetworkalpha.dto.LikeDTO;
import com.scai.socialproject.alpha.socialnetworkalpha.repository.CrudLike;

@Service
public class LikeServiceImpl implements LikeService {
	@Autowired
	private CrudLike likeRepo;


	@Override
	@Transactional
	public List<LikeDTO> findAllLikes() {
		return likeRepo.findAllLikes();
	}

	@Override
	@Transactional
	public List<LikeDTO> findLikesForPost(String idPost) {
		return likeRepo.findLikesForPost(idPost);
	}

	@Override
	@Transactional
	public LikeDTO addLike(String idPost, String idProfile) {
		return likeRepo.addLike(idPost, idProfile);
	}

	@Override
	@Transactional
	public boolean deleteLike(String idPost, String idProfile) {
		return likeRepo.deleteLike(idPost, idProfile);
	}

	@Override
	@Transactional
	public LikeDTO getLikeByLikerAndPost(String idProfile, String idPost) {
		return likeRepo.getLikeByLikerAndPost(idProfile, idPost);
	}

}
