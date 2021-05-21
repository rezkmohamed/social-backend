package com.scai.socialproject.alpha.socialnetworkalpha.repository;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.SQLQuery;
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
import com.scai.socialproject.alpha.socialnetworkalpha.utils.DTOutils;

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
		List<ProfileDTO> profilesDTO = DTOutils.profileToDTO(profiles);
		
		
		return profilesDTO;
	}

	@Override
	public ProfileDTO findProfileById(String idProfile) {
		Session session = entityManager.unwrap(Session.class);
		Profile profile = session.get(Profile.class, idProfile);
		ProfileDTO profileDTO = DTOutils.profileToDTO(profile);
		/*
		Query<BigInteger> query = session.createSQLQuery("select count(*) from social_clone.follow "
				+ "where social_clone.follow.id_followed = :idProfile");
		query.setParameter("idProfile", idProfile);
		int followers = query.uniqueResult().intValue();
		profileDTO.setFollowersCounter(followers);
		
		Query<BigInteger> query2 = session.createSQLQuery("select count(*) from social_clone.follow "
				+ "where social_clone.follow.id_follower = :idProfile");
		query2.setParameter("idProfile", idProfile);
		int following = query.uniqueResult().intValue();
		profileDTO.setFollowingCounter(following);
		*/
		
		Query<Follow> queryFollowers = session.createQuery("from Follow where id_followed = :idProfile");
		queryFollowers.setParameter("idProfile", idProfile);
		List<Follow> followers = queryFollowers.getResultList();
		if(followers != null) {
			List<FollowDTO> followersDTO = DTOutils.followToDTO(followers);
			profileDTO.setFollowers(followersDTO);
			profileDTO.setFollowersCounter(followersDTO.size());
		} else {
			profileDTO.setFollowersCounter(0);
		}
		
		Query<Follow> queryFollowing = session.createQuery("from Follow where id_follower = :idProfile");
		queryFollowing.setParameter("idProfile", idProfile);
		List<Follow> following = queryFollowing.getResultList();
		if(following != null) {
			List<FollowDTO> followingDTO = DTOutils.followToDTO(following);
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
		return new ResponseEntity(DTOutils.profileToDTO(profile), HttpStatus.OK);
	}
	
	@Override
	public void updateProfile(ProfileDTO profileDTO) {
		Session session = entityManager.unwrap(Session.class);
		Profile profile = session.get(Profile.class, profileDTO.getId());
		profile.setName(profileDTO.getName()); profile.setNickname(profileDTO.getNickname());
		profile.setBio(profileDTO.getBio()); profile.setProPic(profileDTO.getProPic());
		profile.setEmail(profileDTO.getEmail());
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
		Profile profile = query.getSingleResult();
		System.out.println(profile);
		if(profile == null) {
			return null;
		}
		User user = DTOutils.profileToUser(profile);
		
		return user;
	}
	
	private List<ProfileDTO> responseToListProfiles(List<Object[]> rows) {
		List<ProfileDTO> profiles = new ArrayList<>();
		for(Object[] row : rows) {
			ProfileDTO profileDTO = new ProfileDTO();
			profileDTO.setId(row[0].toString());
			if(row[1] != null) {
				profileDTO.setName(row[1].toString());
			}
			if(row[2] != null) {
				profileDTO.setNickname(row[2].toString());
			}
			if(row[3] != null) {
				profileDTO.setBio(row[3].toString());
			}
			if(row[4] != null) {
				profileDTO.setProPic(row[4].toString());
			}
			profileDTO.setEmail(row[5].toString());
			profiles.add(profileDTO);
		}
		
		
		return profiles;
	}

	@Override
	public List<ProfileDTO> findProfilesLikesPost(String idPost) {
		Session session = entityManager.unwrap(Session.class);
//		Query query = session.createSQLQuery("select social_clone.profile.id_profile, social_clone.profile.name, social_clone.profile.nickname, social_clone.profile.bio, "
//				+ "social_clone.profile.profile_pic, social_clone.profile.email "
//				+ "from social_clone.profile where "
//				+ "social_clone.profile.id_profile IN "
//				+ "( select id_profile_liker from social_clone.likes where "
//				+ "social_clone.likes.id_post = :idPost)");
//		query.setParameter("idPost", idPost);
//		List<Object[]> rows = query.list();
//		return responseToListProfiles(rows);
		Post p = session.get(Post.class, idPost);
		p.getLikes();
		for(Like l : p.getLikes()) {
			System.out.println(l.getProfileLiker().getName());
		}
		return null;
	}

	@Override
	public List<ProfileDTO> findFollowersProfile(String idProfile) {
		Session session = entityManager.unwrap(Session.class);
		Query query = session.createSQLQuery("select social_clone.profile.id_profile, "
				+ "social_clone.profile.name, social_clone.profile.nickname, "
				+ "social_clone.profile.bio, social_clone.profile.profile_pic, social_clone.profile.email "
				+ "from social_clone.profile where social_clone.profile.id_profile in "
				+ "(select id_follower from social_clone.follow "
				+ "where social_clone.follow.id_followed = :idProfile)");
		query.setParameter("idProfile", idProfile);
		List<Object[]> rows = query.list();
		
		return responseToListProfiles(rows);
	}


	@Override
	public List<ProfileDTO> findFollowingProfile(String idProfile) {
		Session session = entityManager.unwrap(Session.class);
		Query query = session.createSQLQuery("select social_clone.profile.id_profile, "
				+ "social_clone.profile.name, social_clone.profile.nickname, "
				+ "social_clone.profile.bio, social_clone.profile.profile_pic, social_clone.profile.email "
				+ "from social_clone.profile where social_clone.profile.id_profile in "
				+ "(select id_followed from social_clone.follow "
				+ "where social_clone.follow.id_follower = :idProfile)");
		query.setParameter("idProfile", idProfile);
		List<Object[]> rows = query.list();
		
		return responseToListProfiles(rows);
	}

	@Override
	public List<ProfileDTO> searchProfilesByName(String profileName) {
		Session session = entityManager.unwrap(Session.class);
		Query<Profile> query = 
				session
				.createQuery("from Profile where nickname like :string");
		query.setParameter("string", '%'+profileName+'%');
		List<Profile> profiles = query.getResultList();
		List<ProfileDTO> profilesDTO = DTOutils.profileToDTO(profiles);		
		
		return profilesDTO;
	}
	

}
