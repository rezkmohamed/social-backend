package com.scai.socialproject.alpha.socialnetworkalpha.repository;

import java.util.List;

import com.scai.socialproject.alpha.socialnetworkalpha.dto.PostDTO;
import com.scai.socialproject.alpha.socialnetworkalpha.entity.Post;

public interface CrudPost {
	public List<PostDTO> findAllPosts();
	
	public List<PostDTO> findPostsProfilePage(String idProfile);
	
	public List<PostDTO> getHomepage(String idProfile);
	
	public PostDTO findPostById(String idPost);
	
	public void savePost(Post post);
	
	public void savePost(PostDTO postDTO);
	
	public void updatePost(Post post);
	
	public boolean deletePostById(String idPost);
}
