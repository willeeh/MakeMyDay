package com.makemyday.service;

import com.makemyday.entities.Post;
import com.makemyday.entities.User;
import org.bson.types.ObjectId;

import java.util.Collection;

public interface UserService
{
    ObjectId createUser(User user);

    void updateUser(User user);

    User getUserById(String id);

    User getUserByFacebookId(String facebookId);

	void addBookmark(String user, String post);

	void removeBookmark(String userId, String postId);

	Collection<Post> getBookmarks(User user);
}
