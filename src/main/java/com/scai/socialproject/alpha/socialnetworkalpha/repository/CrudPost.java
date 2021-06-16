package com.scai.socialproject.alpha.socialnetworkalpha.repository;

import java.util.List;

import com.scai.socialproject.alpha.socialnetworkalpha.dto.PostDTO;
import com.scai.socialproject.alpha.socialnetworkalpha.entity.Post;

public interface CrudPost {
	public List<PostDTO> findAllPosts();
	
	public List<PostDTO> getPosts(String idProfile);
	
	public List<PostDTO> findPostsProfilePage(String idProfile);
	
	//public List<PostDTO> getHomepage(String idProfile);
	
	public List<PostDTO> loadNextPostsForProfile(String idProfile, int startingIndex);
	
	public PostDTO findPostById(String idPost);
	
	public void savePost(Post post);
	
	public void savePost(PostDTO postDTO);
	
	public boolean updatePost(PostDTO postDTO);
	
	public boolean deletePostById(String idPost);
}
