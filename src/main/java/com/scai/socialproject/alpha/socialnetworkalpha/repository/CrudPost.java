package com.scai.socialproject.alpha.socialnetworkalpha.repository;

import java.util.List;

import com.scai.socialproject.alpha.socialnetworkalpha.dto.PostDTO;
import com.scai.socialproject.alpha.socialnetworkalpha.entity.Post;

public interface CrudPost {
	public List<PostDTO> findAllPosts();
	
	public PostDTO findPostById(String idPost);
	
	public void savePost(Post post);
	
	public void updatePost(Post post);
	
	public void deletePostById(String idPost);
}
