package com.makemyday.service;

import com.google.inject.Inject;
import com.makemyday.dao.Dao;
import com.makemyday.entities.Post;
import org.bson.types.ObjectId;

import java.util.Collection;

public class PostServiceImpl implements PostService
{
	private final Dao<Post, ObjectId> postDAO;

	@Inject
	public PostServiceImpl(Dao<Post, ObjectId> postDAO)
	{
		this.postDAO = postDAO;
	}

	@Override
	public void createPost(Post post)
	{
		postDAO.save(post);
	}

	@Override
	public Post getPostById(String postId)
	{
		return postDAO.findOne("_id", new ObjectId(postId));
	}

	@Override
	public Collection<Post> getLatestPosts(int offset, int limit)
	{
		return postDAO.createQuery().order("-createdOn").offset(offset).limit(limit).asList();
	}
}
