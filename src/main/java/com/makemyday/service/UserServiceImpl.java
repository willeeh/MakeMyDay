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
    public ObjectId createUser(User user)
    {
        User existingUser = getUserByFacebookId(user.getFacebookId());

		if (existingUser != null)
			return existingUser.getId();

		updateUser(user);
		return user.getId();
    }

    @Override
    public void updateUser(User user)
    {
        userDAO.save(user);
    }

	@Override
	public User getUserById(String id)
	{
		return userDAO.get(new ObjectId(id));
	}

	@Override
    public User getUserByFacebookId(String facebookId)
    {
        return userDAO.findOne("facebookId", facebookId);
    }

	@Override
	public void addBookmark(String userId, String postId)
	{
		User user = getUserByFacebookId(userId);
		Post post = postService.getPostById(postId);

		user.getBookmarks().add(post);
		updateUser(user);
	}

	@Override
	public void removeBookmark(String userId, String postId)
	{
		User user = getUserByFacebookId(userId);
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
