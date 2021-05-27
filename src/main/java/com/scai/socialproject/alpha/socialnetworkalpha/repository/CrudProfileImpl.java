package com.scai.socialproject.alpha.socialnetworkalpha.repository;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import com.scai.socialproject.alpha.socialnetworkalpha.dto.FollowDTO;
import com.scai.socialproject.alpha.socialnetworkalpha.dto.ProfileDTO;
import com.scai.socialproject.alpha.socialnetworkalpha.dto.User;
import com.scai.socialproject.alpha.socialnetworkalpha.entity.Follow;
import com.scai.socialproject.alpha.socialnetworkalpha.entity.Like;
import com.scai.socialproject.alpha.socialnetworkalpha.entity.Post;
import com.scai.socialproject.alpha.socialnetworkalpha.entity.Profile;
import com.scai.socialproject.alpha.socialnetworkalpha.utils.DTOFollowUtils;
import com.scai.socialproject.alpha.socialnetworkalpha.utils.DTOProfileUtils;

import javassist.tools.web.BadHttpRequest;

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
		List<ProfileDTO> profilesDTO = DTOProfileUtils.profileToDTO(profiles);
		
		
		return profilesDTO;
	}

	@Override
	public ProfileDTO findProfileById(String idProfile) {
		Session session = entityManager.unwrap(Session.class);
		Profile profile = session.get(Profile.class, idProfile);
		ProfileDTO profileDTO = DTOProfileUtils.profileToDTO(profile);
		
		Query<Follow> queryFollowers = session.createQuery("from Follow where id_followed = :idProfile");
		queryFollowers.setParameter("idProfile", idProfile);
		List<Follow> followers = queryFollowers.getResultList();
		if(followers != null) {
			List<FollowDTO> followersDTO = DTOFollowUtils.followToDTO(followers);
			profileDTO.setFollowers(followersDTO);
			profileDTO.setFollowersCounter(followersDTO.size());
		} else {
			profileDTO.setFollowersCounter(0);
		}
		
		Query<Follow> queryFollowing = session.createQuery("from Follow where id_follower = :idProfile");
		queryFollowing.setParameter("idProfile", idProfile);
		List<Follow> following = queryFollowing.getResultList();
		if(following != null) {
			List<FollowDTO> followingDTO = DTOFollowUtils.followToDTO(following);
			profileDTO.setFollowing(followingDTO);
			profileDTO.setFollowingCounter(followingDTO.size());
		} else {
			profileDTO.setFollowingCounter(0);
		}
		
		return profileDTO;
	}

	@Override
	public ResponseEntity<ProfileDTO> saveProfile(Profile profile){
		Session session = entityManager.unwrap(Session.class);
		List<ProfileDTO> allProfiles = this.findAllProfiles();
		for(ProfileDTO profileDTO : allProfiles) {
			if(profileDTO.getEmail().equalsIgnoreCase(profile.getEmail())) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
		}
		
		session.save(profile);
		return new ResponseEntity(DTOProfileUtils.profileToDTO(profile), HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<ProfileDTO> updateProfile(ProfileDTO profileDTO) {
		Session session = entityManager.unwrap(Session.class);
		Profile profile = session.get(Profile.class, profileDTO.getId());
		if(profile == null) {
			return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
		}
		profile.setName(profileDTO.getName()); profile.setNickname(profileDTO.getNickname());
		profile.setBio(profileDTO.getBio()); profile.setProPic(profileDTO.getProPic());
		profile.setEmail(profileDTO.getEmail());
		session.update(profile);
		return new ResponseEntity(profile, HttpStatus.OK);
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
		Profile profile = query.getSingleResult();
		System.out.println(profile);
		if(profile == null) {
			return null;
		}
		User user = DTOProfileUtils.profileToUser(profile);
		
		return user;
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
		List<ProfileDTO> profilesDTO = DTOProfileUtils.profileToDTO(profiles);
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
		List<ProfileDTO> profilesDTO = DTOProfileUtils.profileToDTO(profiles);
		
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

		List<ProfileDTO> profilesDTO = DTOProfileUtils.profileToDTO(profiles);

		return profilesDTO;
	}

	@Override
	public List<ProfileDTO> searchProfilesByName(String profileName) {
		Session session = entityManager.unwrap(Session.class);
		Query<Profile> query = 
				session
				.createQuery("from Profile where nickname like :string");
		query.setParameter("string", '%'+profileName+'%');
		List<Profile> profiles = query.getResultList();
		List<ProfileDTO> profilesDTO = DTOProfileUtils.profileToDTO(profiles);		
		
		return profilesDTO;
	}

	@Override
	public void updateProfileEntity(Profile profile) {
		Session session = entityManager.unwrap(Session.class);
		session.update(profile);
	}
	

}
