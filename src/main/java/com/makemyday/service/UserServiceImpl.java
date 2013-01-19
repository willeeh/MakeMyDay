package com.makemyday.service;

import com.google.inject.Inject;
import com.makemyday.dao.Dao;
import com.makemyday.entities.Post;
import com.makemyday.entities.User;
import org.bson.types.ObjectId;

import java.util.Collection;

public class UserServiceImpl implements UserService
{
    private final Dao<User, ObjectId> userDAO;

	private final PostService postService;

    @Inject
    public UserServiceImpl(Dao<User, ObjectId> userDAO, PostService postService)
    {
        this.userDAO = userDAO;
		this.postService = postService;
	}

    @Override
    public void createUser(User user)
    {
        User existingUser = userDAO.findOne("facebookId", user.getFacebookId());

        if (existingUser == null)
        {
            /*UserStats stats = new UserStats();
            userStatsDAO.save(stats);
            user.setStats(stats);*/
            userDAO.save(user);
        }
    }

    @Override
    public void updateUser(User user)
    {
        //TODO @gceballos Do it!
    }

    @Override
    public User getUser(String facebookId)
    {
        return userDAO.findOne("facebookId", facebookId);
    }

	@Override
	public void addBookmark(String userId, String postId)
	{
		User user = getUser(userId);
		Post post = postService.getPostById(postId);

		user.getBookmarks().add(post);
		updateUser(user);
	}

	@Override
	public void removeBookmark(String userId, String postId)
	{
		User user = getUser(userId);
		Post post = postService.getPostById(postId);

		user.getBookmarks().add(post);
		updateUser(user);
	}

	@Override
	public Collection<Post> getBookmarks(User user)
	{
		return user.getBookmarks();
	}
}
