package com.scai.socialproject.alpha.socialnetworkalpha.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.scai.socialproject.alpha.socialnetworkalpha.dto.FollowDTO;
import com.scai.socialproject.alpha.socialnetworkalpha.entity.Follow;
import com.scai.socialproject.alpha.socialnetworkalpha.repository.CrudFollow;

@Service
public class FollowServiceImpl implements FollowService {
	@Autowired
	private CrudFollow followRepo;

	
	@Override
	@Transactional
	public List<FollowDTO> findAllFollowers() {
		return followRepo.findAllFollowers();
	}

	@Override
	@Transactional
	public List<FollowDTO> findFollowersForProfile(String idProfile) {
		return followRepo.findFollowersForProfile(idProfile);
	}

	@Override
	@Transactional
	public List<FollowDTO> findFollowingForProfile(String idProfile) {
		return followRepo.findFollowingForProfile(idProfile);
	}

	@Override
	@Transactional
	public FollowDTO findFollowById(String id) {
		return followRepo.findFollowById(id);
	}

	@Override
	@Transactional
	public void saveFollow(Follow follow) {
		followRepo.saveFollow(follow);
	}

	@Override
	@Transactional
	public void deleteFollowById(String idFollow) {
		followRepo.deleteFollowById(idFollow);
	}

	@Override
	@Transactional
	public boolean deleteFollow(String idFollower, String idFollowed) {
		return followRepo.deleteFollow(idFollower, idFollowed);
	}

	@Override
	@Transactional
	public FollowDTO addFollow(String idFollower, String idFollowed) {
		return followRepo.addFollow(idFollower, idFollowed);
	}

	@Override
	@Transactional
	public FollowDTO getFollow(String idFollower, String idFollowed) {
		return followRepo.getFollow(idFollower, idFollowed);
	}
}
