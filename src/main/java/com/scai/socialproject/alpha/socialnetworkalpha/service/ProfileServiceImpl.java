package com.scai.socialproject.alpha.socialnetworkalpha.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.scai.socialproject.alpha.socialnetworkalpha.dto.ProfileDTO;
import com.scai.socialproject.alpha.socialnetworkalpha.entity.Profile;
import com.scai.socialproject.alpha.socialnetworkalpha.repository.CrudProfile;

@Service
public class ProfileServiceImpl implements ProfileService {
	private CrudProfile profileRepo;
	
	@Autowired
	public ProfileServiceImpl(CrudProfile profileRepo) {
		this.profileRepo = profileRepo;
	}
	
	@Override
	@Transactional
	public List<ProfileDTO> findAllProfiles() {
		return profileRepo.findAllProfiles();
	}

	@Override
	@Transactional
	public ProfileDTO findProfileById(String idProfile) {
		return profileRepo.findProfileById(idProfile);
	}

	@Override
	@Transactional
	public void saveProfile(Profile profile) {
		profileRepo.saveProfile(profile);
	}

	@Override
	@Transactional
	public void updateProfile(Profile profile) {
		profileRepo.updateProfile(profile);
	}

	@Override
	@Transactional
	public void deleteProfileById(String idProfile) {
		profileRepo.deleteProfileById(idProfile);
	}

	@Override
	@Transactional
	public Profile findProfile(String idProfile) {
		return profileRepo.findProfile(idProfile);
	}

}
