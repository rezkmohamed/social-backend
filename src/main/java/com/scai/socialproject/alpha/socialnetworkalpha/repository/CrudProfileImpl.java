package com.scai.socialproject.alpha.socialnetworkalpha.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import com.scai.socialproject.alpha.socialnetworkalpha.dto.ProfileDTO;
import com.scai.socialproject.alpha.socialnetworkalpha.dto.User;
import com.scai.socialproject.alpha.socialnetworkalpha.entity.Follow;
import com.scai.socialproject.alpha.socialnetworkalpha.entity.Like;
import com.scai.socialproject.alpha.socialnetworkalpha.entity.Post;
import com.scai.socialproject.alpha.socialnetworkalpha.entity.Profile;
import com.scai.socialproject.alpha.socialnetworkalpha.utils.DTOFollowUtils;
import com.scai.socialproject.alpha.socialnetworkalpha.utils.DTOPostUtils;
import com.scai.socialproject.alpha.socialnetworkalpha.utils.DTOProfileUtils;
import com.scai.socialproject.alpha.socialnetworkalpha.utils.ImgUtils;

import org.hibernate.query.Query;

@Repository
public class CrudProfileImpl implements CrudProfile {
	private EntityManager entityManager;
	private ImgUtils imgUtils;
	
	@Autowired
	public CrudProfileImpl(EntityManager entityManager, ImgUtils imgUtils) {
		this.entityManager = entityManager;
		this.imgUtils = imgUtils;
	}
	
	@Override
	public List<ProfileDTO> findAllProfiles() {
		Session session = entityManager.unwrap(Session.class);
		Query<Profile> query = session.createQuery("from Profile", Profile.class);
		List<Profile> profiles = query.getResultList();
		List<ProfileDTO> profilesDTO = DTOProfileUtils.profileToDTO(profiles, imgUtils);
		
		
		return profilesDTO;
	}
	
	

	@Override
	public ProfileDTO findProfileById(String idProfile) {
		Session session = entityManager.unwrap(Session.class);
		Profile profile = session.get(Profile.class, idProfile);
		if(profile != null) {
			ProfileDTO profileDTO = DTOProfileUtils.profileToDTO(profile);
			profileDTO.setPosts(DTOPostUtils.postToDTO(profile.getPosts()));
			//profileDTO.setFollowers(DTOFollowUtils.followToDTO(profile.getFollowers()));
			profileDTO.setFollowersCounter(profile.getFollowers().size());
			//profileDTO.setFollowing(DTOFollowUtils.followToDTO(profile.getFollowing()));
			profileDTO.setFollowingCounter(profile.getFollowing().size());
			return profileDTO;
		}
		
		return null;
	}

	@Override
	public void saveProfile(Profile profile){
		Session session = entityManager.unwrap(Session.class);
		List<ProfileDTO> profiles = this.findAllProfiles();
		for(ProfileDTO profileDTO : profiles) {
			if(profileDTO.getEmail().equalsIgnoreCase(profile.getEmail())) {

			}
		}
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
				.createQuery("from Profile where email= :email AND password = :password");
		query.setParameter("email", email); query.setParameter("password", pass);
		try {
			Profile profile = query.getSingleResult();
			User user = DTOProfileUtils.profileToUser(profile);
			return user;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	//OK!
	@Override
	public List<ProfileDTO> findProfilesLikesPost(String idPost) {
		Session session = entityManager.unwrap(Session.class);

		List<Profile> profiles = new ArrayList<>();
		Post p = session.get(Post.class, idPost);
		p.getLikes();
		for(Like l : p.getLikes()) {
			profiles.add(l.getProfileLiker());
		}
		List<ProfileDTO> profilesDTO = DTOProfileUtils.profileToDTO(profiles, imgUtils);
		return profilesDTO;
	}

	//OK!
	@Override
	public List<ProfileDTO> findFollowersProfile(String idProfile) {
		Session session = entityManager.unwrap(Session.class);
		List<Profile> profiles = new ArrayList<>();
		Profile profile = session.get(Profile.class, idProfile);
		profile.getFollowers();
		System.out.println("FOLLOWERS DI " + profile.getName());
		for(Follow f : profile.getFollowers()) {
			Profile p = f.getFollower();
			System.out.println(p.getIdProfile());
			profiles.add(p);
		}
		List<ProfileDTO> profilesDTO = DTOProfileUtils.profileToDTO(profiles, imgUtils);
		
		return profilesDTO;
	}

	//OK!
	@Override
	public List<ProfileDTO> findFollowingProfile(String idProfile) {
		Session session = entityManager.unwrap(Session.class);
		List<Profile> profiles = new ArrayList<>();
		Profile profile = session.get(Profile.class, idProfile);
		System.out.println("FOLLOWING DI " + profile.getName());
		for(Follow f : profile.getFollowing()) {
			Profile p = f.getFollowed();
			System.out.println(p.getIdProfile());
			profiles.add(p);
		}

		List<ProfileDTO> profilesDTO = DTOProfileUtils.profileToDTO(profiles, imgUtils);

		return profilesDTO;
	}

	@Override
	public List<ProfileDTO> searchProfilesByName(String profileName, int startingIndex) {
		Session session = entityManager.unwrap(Session.class);
		Query<Profile> query = session.createQuery("from Profile where nickname like :string");
		query.setParameter("string", '%'+profileName+'%');
		query.setFirstResult(startingIndex);
		query.setMaxResults(10);
		
		List<Profile> profiles = query.getResultList();
		List<ProfileDTO> profilesDTO = DTOProfileUtils.profileToDTO(profiles, imgUtils);		
		
		return profilesDTO;
	}

	@Override
	public void updateProfileEntity(Profile profile) {
		Session session = entityManager.unwrap(Session.class);
		session.update(profile);
	}

	@Override
	public Profile findProfileByEmail(String email) {
		Session session = entityManager.unwrap(Session.class);
		Query<Profile> query = session.createQuery("from Profile where email = :mail");
		query.setParameter("mail", email);
		try {
			Profile profile = query.getSingleResult();
			return profile;
		} catch (Exception e) {
			System.out.println("profile with email " + email + " not found.");
		}
		
		return null;
	}
}
