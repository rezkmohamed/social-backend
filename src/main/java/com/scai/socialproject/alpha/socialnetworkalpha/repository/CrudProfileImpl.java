package com.scai.socialproject.alpha.socialnetworkalpha.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.scai.socialproject.alpha.socialnetworkalpha.dto.ProfileDTO;
import com.scai.socialproject.alpha.socialnetworkalpha.dto.User;
import com.scai.socialproject.alpha.socialnetworkalpha.entity.Profile;
import com.scai.socialproject.alpha.socialnetworkalpha.utils.DTOutils;

import org.hibernate.query.Query;

@Repository
public class CrudProfileImpl implements CrudProfile {
	private EntityManager entityManager;
	
	@Autowired
	public CrudProfileImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Override
	public List<ProfileDTO> findAllProfiles() {
		Session session = entityManager.unwrap(Session.class);
		Query<Profile> query = session.createQuery("from Profile", Profile.class);
		List<Profile> profiles = query.getResultList();
		List<ProfileDTO> profilesDTO = DTOutils.profileToDTO(profiles);
		
		
		return profilesDTO;
	}

	@Override
	public ProfileDTO findProfileById(String idProfile) {
		Session session = entityManager.unwrap(Session.class);
		Profile profile = session.get(Profile.class, idProfile);
		ProfileDTO profileDTO = DTOutils.profileToDTO(profile);
		
		return profileDTO;
	}

	@Override
	public void saveProfile(Profile profile) {
		Session session = entityManager.unwrap(Session.class);
		session.save(profile);
	}
	
	@Override
	public void updateProfile(Profile profile) {
		Session session = entityManager.unwrap(Session.class);
		session.update(profile);
	}
	
	@Override
	public void deleteProfileById(String idProfile) {
		Session session = entityManager.unwrap(Session.class);
		
		Query query = session.createQuery("delete from Profile where id_profile=:idProfile");
		query.setParameter("idProfile", idProfile);
		query.executeUpdate();
	}

	@Override
	public Profile findProfile(String idProfile) {
		Session session = entityManager.unwrap(Session.class);
		Profile profile = session.get(Profile.class, idProfile);

		return profile;
	}

	@Override
	public User getUserAuth(String email, String pass) {
		Session session = entityManager.unwrap(Session.class);
		Query<Profile> query = session
				.createQuery("from Profile where email=:email AND password=:password");
		query.setParameter("email", email); query.setParameter("password", pass);
		Profile profile = query.getSingleResult();
		if(profile == null) {
			return null;
		}
		User user = DTOutils.profileToUser(profile);
		
		return user;
	}

	
	
	
	
	

}
