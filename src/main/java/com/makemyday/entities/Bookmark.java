package com.makemyday.entities;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Reference;
import com.makemyday.entities.base.Identity;
import org.hibernate.validator.constraints.NotEmpty;

@Entity(value = "bookmarks", noClassnameStored = true)
public class Bookmark extends Identity
{
	@NotEmpty
	@Reference
	private User user;

	@NotEmpty
	@Reference
	private Post post;


	public Bookmark()
	{
	}

	public Bookmark(User user, Post post)
	{
		this.user = user;
		this.post = post;
	}

	public User getUser()
	{
		return user;
	}

	public void setUser(User user)
	{
		this.user = user;
	}

	public Post getPost()
	{
		return post;
	}

	public void setPost(Post post)
	{
		this.post = post;
	}
}
