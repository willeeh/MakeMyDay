package com.makemyday.service;

import com.makemyday.entities.Post;
import com.makemyday.entities.User;
import com.makemyday.entities.Vote;

public interface VoteService
{
	Vote createVote(Post post, Vote.TYPE type);

	void updateVote(Vote vote);

	void incrementVote(Post post, User user);

	void addVoter(Vote vote, User user);

	Vote getVote(Post post);
}
