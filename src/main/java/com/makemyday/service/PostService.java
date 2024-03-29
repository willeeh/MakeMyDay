package com.makemyday.service;

import com.makemyday.entities.Post;
import com.makemyday.entities.User;

import java.util.Collection;

public interface PostService
{
	/**
	 * Just creates the post.
	 */
    void createPost(Post post);

	/**
	 * Gets a post by its objetId
	 */
    Post getPostById(String postId);

	/**
	 * Gets latest posts ordered by date (first the newest)
	 */
	Collection<Post> getLatestPosts(int offset, int limit);

	void incrementVote(String postId, User user, Post.VoteType type);

}
