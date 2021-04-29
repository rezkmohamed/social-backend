package com.scai.socialproject.alpha.socialnetworkalpha.repository;

import java.util.List;

import com.scai.socialproject.alpha.socialnetworkalpha.dto.FollowDTO;
import com.scai.socialproject.alpha.socialnetworkalpha.entity.Follow;

public interface CrudFollow {
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
	
	public FollowDTO findFollowById(String id);
	
	public void saveFollow(Follow follow);
	
	public void deleteFollowById(String idFollow);
	
	public void deleteFollow(String idFollower, String idFollowed);
}
