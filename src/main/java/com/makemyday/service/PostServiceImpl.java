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
	public static final String POST_IMPORTANCE = "postImportance";

	private final Dao<Post, ObjectId> postDAO;

	private final SequenceService sequenceService;

	@Inject
	public PostServiceImpl(Dao<Post, ObjectId> postDAO, SequenceService sequenceService)
	{
		this.postDAO = postDAO;
		this.sequenceService = sequenceService;
	}

	@Override
	public void createPost(Post post)
	{
		long postImportance = sequenceService.getCounter(POST_IMPORTANCE);
		post.setImportance(postImportance);
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
		atomicIncrement(user, type, postObjectId);
	}

	private void atomicIncrement(User user, Post.VoteType type, ObjectId objectId)
	{
		Query<Post> query = postDAO.createQuery();
		query.and(
				query.criteria("_id").equal(objectId),
				query.criteria("voters").notEqual(user)
		);

		// This query succeeds only if the voters array doesn't contain the user
		UpdateOperations<Post> updateOperations = postDAO.createUpdateOperations();
		updateOperations
				.inc(type.fieldName)
				.inc("importance", type.importanceIncrement)
				.add("voters", user);

		postDAO.update(query, updateOperations);
	}

}
