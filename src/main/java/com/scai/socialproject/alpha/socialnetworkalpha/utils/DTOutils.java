package com.scai.socialproject.alpha.socialnetworkalpha.utils;

import java.util.ArrayList;
import java.util.List;

import com.scai.socialproject.alpha.socialnetworkalpha.dto.CommentDTO;
import com.scai.socialproject.alpha.socialnetworkalpha.dto.FollowDTO;
import com.scai.socialproject.alpha.socialnetworkalpha.dto.LikeDTO;
import com.scai.socialproject.alpha.socialnetworkalpha.dto.PostDTO;
import com.scai.socialproject.alpha.socialnetworkalpha.dto.ProfileDTO;
import com.scai.socialproject.alpha.socialnetworkalpha.entity.Comment;
import com.scai.socialproject.alpha.socialnetworkalpha.entity.Follow;
import com.scai.socialproject.alpha.socialnetworkalpha.entity.Like;
import com.scai.socialproject.alpha.socialnetworkalpha.entity.Post;
import com.scai.socialproject.alpha.socialnetworkalpha.entity.Profile;

public class DTOutils {
	//PROFILE UTILS
	
	private static ProfileDTO DTOFromProfile(Profile profile) {
		ProfileDTO tmp = new ProfileDTO(profile.getIdProfile(),
										profile.getName(),
										profile.getNickname(),
										profile.getBio(),
										profile.getProPic(),
										profile.getEmail());
		return tmp;
	}
	
	public static List<ProfileDTO> profileToDTO(List<Profile> profiles){
		List<ProfileDTO> ris = new ArrayList<>();
		for(Profile profile : profiles) {
			ProfileDTO tmp = DTOFromProfile(profile);
			ris.add(tmp);
		}
		
		return ris;
	}
	
	public static ProfileDTO profileToDTO(Profile profile) {
		ProfileDTO ris = DTOFromProfile(profile);
		return ris;
	}
	
	//POST UTILS
	
	private static PostDTO DTOFromPost(Post post) {
		PostDTO tmp = new PostDTO(post.getIdPost(),
								  post.getImg_post(), 
								  post.getDescription(), 
								  post.getDate(), 
								  post.getProfile().getIdProfile());
		return tmp;
	}
	
	public static List<PostDTO> postToDTO(List<Post> posts){
		List<PostDTO> ris = new ArrayList<>();
		for(Post post : posts) {
			PostDTO tmp = DTOFromPost(post);
			ris.add(tmp);
		}
		
		return ris;
	}
	
	public static PostDTO postToDTO(Post post) {
		PostDTO ris = DTOFromPost(post);
		return ris;
	}
	
	// FOLLOW UTILS
	private static FollowDTO DTOFromFollow(Follow follow) {
		FollowDTO tmp = new FollowDTO(follow.getIdFollow(),
									  follow.getDate(), 
									  follow.getFollower().getIdProfile(), 
									  follow.getFollowed().getIdProfile());
		return tmp;
	}
	
	public static List<FollowDTO> followToDTO(List<Follow> follows){
		List<FollowDTO> ris = new ArrayList<>();
		for(Follow follow : follows) {
			FollowDTO tmp = DTOFromFollow(follow);
			ris.add(tmp);
		}
		
		return ris;
	}
	
	public static FollowDTO followToDTO(Follow follow) {
		FollowDTO ris = DTOFromFollow(follow);
		return ris;
	}
	
	// LIKE UTILS
	private static LikeDTO DTOFromLike(Like like) {
		return new LikeDTO(like.getId(),
								  like.getDate(),
								  like.getPost().getIdPost(),
								  like.getProfileLiker().getIdProfile());
	}
	
	public static List<LikeDTO> likeToDTO(List<Like> likes){
		List<LikeDTO> ris = new ArrayList<>();
		for(Like like : likes) {
			LikeDTO tmp = DTOFromLike(like);
			ris.add(tmp);
		}
		
		return ris;
	}
	
	public static LikeDTO likeToDTO(Like like) {
		return DTOFromLike(like);
	}
	
	// COMMENTS UTILS
	private static CommentDTO DTOFromComment(Comment comment) {
		return new CommentDTO(comment.getIdComment(), 
									    comment.getComment(), 
									    comment.getDate(), 
									    comment.getPost().getIdPost(), 
									    comment.getWriter().getIdProfile());
	}
	
	public static List<CommentDTO> commentToDTO(List<Comment> comments){
		List<CommentDTO> ris = new ArrayList<>();
		for(Comment comment : comments) {
			CommentDTO tmp = DTOFromComment(comment);
		}
		
		return ris;
	}
	
	public static CommentDTO commentToDTO(Comment comment) {
		return DTOFromComment(comment);
	}	
	
}
