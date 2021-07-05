package com.scai.socialproject.alpha.socialnetworkalpha.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.scai.socialproject.alpha.socialnetworkalpha.dto.PostDTO;
import com.scai.socialproject.alpha.socialnetworkalpha.entity.Comment;
import com.scai.socialproject.alpha.socialnetworkalpha.entity.Like;
import com.scai.socialproject.alpha.socialnetworkalpha.entity.Post;
import com.scai.socialproject.alpha.socialnetworkalpha.entity.Profile;

public class DTOPostUtils {
	
	private static PostDTO DTOFromPost(Post post) {
		PostDTO tmp = new PostDTO(post.getIdPost(),
								  post.getImg_post(), 
								  post.getDescription(), 
								  post.getProfile().getIdProfile());
		tmp.setDate(post.getDateMillis());
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
	
	public static PostDTO fillPostCompleteDTO(Post post, ImgUtils imgUtils) {
		PostDTO postDTO = DTOPostUtils.postToDTO(post);
		
		Profile p = post.getProfile();
		postDTO.setProfile(DTOProfileUtils.profileToDTO(p));
		try {
			if(postDTO.getProfile().getProPic() != null) {
				postDTO.getProfile().setProPic(imgUtils.fileImgToBase64Encoding(postDTO.getProfile().getProPic()));
			}
			if(postDTO.getUrlImg() != null) {
				postDTO.setUrlImg(imgUtils.fileImgToBase64Encoding(postDTO.getUrlImg()));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		List<Like> likes = post.getLikes();
		postDTO.setLikes(DTOLikeUtils.likeToDTO(likes));
		postDTO.setLikesCounter(likes.size());
		
		List<Comment> comments = post.getComments();
		postDTO.setComments(DTOCommentUtils.commentToDTO(comments));
		postDTO.setCommentsCounter(comments.size());
		
		return postDTO;
	}

}
