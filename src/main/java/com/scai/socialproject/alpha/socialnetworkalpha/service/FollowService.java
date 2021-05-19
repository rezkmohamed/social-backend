package com.scai.socialproject.alpha.socialnetworkalpha.service;

import java.util.List;

import com.scai.socialproject.alpha.socialnetworkalpha.dto.FollowDTO;
import com.scai.socialproject.alpha.socialnetworkalpha.entity.Follow;

public interface FollowService {
	public List<FollowDTO> findAllFollowers();
	/**
	 * METODO CHE RESTITUISCE LA LISTA DEI FOLLOWERS DI UN DATO PROFILO
	 * @param idProfile ID PROFILO DI CUI SI CERCANO I FOLLOWERS
	 * @return
	 */
	public List<FollowDTO> findFollowersForProfile(String idProfile);
	/**
	 * METODO CHE RESTITUISCE LA LISTA DELLE PERSONE CHE SEGUE UN DATO PROFILO
	 * @param idProfile ID PROFILO DI CUI SI CERCANO I FOLLOWING
	 * @return
	 */
	public List<FollowDTO> findFollowingForProfile(String idProfile);
	
	public FollowDTO getFollow(String idFollower, String idFollowed);
	
	public FollowDTO findFollowById(String id);
	
	public void saveFollow(Follow follow);
	
	public FollowDTO addFollow(String idFollower, String idFollowed);
	
	public void deleteFollowById(String idFollow);
	
	public void deleteFollow(String idFollower, String idFollowed);
}
