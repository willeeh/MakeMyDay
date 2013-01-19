package com.makemyday.service;

import com.makemyday.entities.Post;
import com.makemyday.entities.User;

import java.util.Collection;

public interface UserService
{
    void createUser(User user);

    void updateUser(User user);

    User getUser(String facebookId);

	void addBookmark(String user, String post);

	void removeBookmark(String userId, String postId);

	Collection<Post> getBookmarks(User user);
}
