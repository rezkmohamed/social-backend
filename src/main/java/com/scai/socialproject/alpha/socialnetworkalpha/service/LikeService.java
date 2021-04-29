package com.scai.socialproject.alpha.socialnetworkalpha.service;

import java.util.List;

import com.scai.socialproject.alpha.socialnetworkalpha.dto.LikeDTO;
import com.scai.socialproject.alpha.socialnetworkalpha.entity.Like;

public interface LikeService {
	public List<LikeDTO> findAllLikes();
	
	public List<LikeDTO> findLikesForPost(String idPost);
	
	public void addLike(Like like);
	
	public void addLike(String idPost, String idProfile);
	
	public void deleteLike(String idLike);
	
	public void deleteLike(String idPost, String idProfile);
}
