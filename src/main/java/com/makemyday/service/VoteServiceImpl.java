package com.makemyday.service;

import com.google.code.morphia.query.UpdateOperations;
import com.makemyday.dao.Dao;
import com.makemyday.entities.Post;
import com.makemyday.entities.User;
import com.makemyday.entities.Vote;
import org.bson.types.ObjectId;

public class VoteServiceImpl implements VoteService
{

	private final Dao<Vote, ObjectId> userDAO;

	public VoteServiceImpl(Dao<Vote, ObjectId> userDAO)
	{
		this.userDAO = userDAO;
	}

	@Override
	public Vote createVote(Post post, Vote.TYPE type)
	{
		Vote vote = new Vote(type, post);
		userDAO.save(vote);
		return vote;
	}

	@Override
	public void updateVote(Vote vote)
	{
		userDAO.save(vote);
	}

	/**
	 * http://cookbook.mongodb.org/patterns/votes/ It uses an approach based on to store information of vote in the object itself.
	 * Instead of that, we are using an approach where the vote information is stored in other collection. Think about it an I+D!
	 * @param post
	 * @param user
	 */
	@Override
	public void incrementVote(Post post, User user)
	{
		Vote vote = getVote(post);
		if (!userHasVoted(vote, user))
		{
			addVoter(vote, user);
			UpdateOperations<Vote> updateOperations = userDAO.createUpdateOperations();
			updateOperations.inc("field"); //TODO field name?

			//TODO update ?
		}

	}

	@Override
	public void addVoter(Vote vote, User user)
	{
		vote.getVoters().add(user);
		updateVote(vote);
	}

	@Override
	public Vote getVote(Post post)
	{
		return null;  //To change body of implemented methods use File | Settings | File Templates.
	}

	private boolean userHasVoted(Vote vote, User user)
	{
		return vote.getVoters().contains(user);
	}


}
