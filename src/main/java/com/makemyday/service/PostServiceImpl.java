package com.makemyday.service;

import com.google.code.morphia.query.Query;
import com.google.code.morphia.query.UpdateOperations;
import com.google.inject.Inject;
import com.makemyday.dao.Dao;
import com.makemyday.entities.Post;
import com.makemyday.entities.User;
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

	@Override
	public void incrementVote(String postId, User user, Post.VoteType type)
	{
		ObjectId postObjectId = new ObjectId(postId);
		Query<Post> query = postDAO.createQuery();
		query.and(
				query.criteria("_id").equal(postObjectId),
				query.criteria("voters").notEqual(user)
		);

		// This query succeeds only if the voters array doesn't contain the user
		UpdateOperations<Post> updateOperations = postDAO.createUpdateOperations();
		updateOperations.inc(type.fieldName).add("voters", user);

		postDAO.update(query, updateOperations);
	}

}
