package com.scai.socialproject.alpha.socialnetworkalpha.repository;

import java.util.List;

import com.scai.socialproject.alpha.socialnetworkalpha.dto.LikeDTO;
import com.scai.socialproject.alpha.socialnetworkalpha.entity.Like;

public interface CrudLike {
	public List<LikeDTO> findAllLikes();
	
	public List<LikeDTO> findLikesForPost(String idPost);
	
	public void addLike(Like like);
	
	public LikeDTO addLike(String idPost, String idProfile);
	
	public void deleteLike(String idLike);
	
	public void deleteLike(String idPost, String idProfile);
}
