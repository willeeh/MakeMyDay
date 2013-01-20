package com.makemyday.service;

import com.makemyday.entities.Post;
import com.makemyday.entities.User;
import com.makemyday.entities.Vote;

public interface VoteService
{
	/**
	 * Create a new vote
	 * @param post
	 * @param type
	 * @return
	 */
	Vote createVote(Post post, Vote.TYPE type);

	/**
	 * Update a vote
	 * @param vote
	 */
	void updateVote(Vote vote);

	/**
	 * Increment the vote counter and adds user to list of voters
	 * @param post
	 * @param user
	 */
	void incrementVote(Post post, User user);

	/**
	 * Gets the vote of a post
	 * @param post
	 * @return
	 */
	Vote getVote(Post post);
}
