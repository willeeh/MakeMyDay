package com.makemyday.entities;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Reference;
import com.makemyday.entities.base.Identity;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.Collection;
import java.util.HashSet;

@Entity(value="posts", noClassnameStored=true)
public class Post extends Identity
{
	public static enum VoteType
	{
		LIKE("likes"), DISLIKE("dislikes");

		public final String fieldName;

		VoteType(String fieldName)
		{
			this.fieldName = fieldName;
		}
	}

    @NotEmpty
    private String message;

    @NotEmpty
    @Reference
    private User creator;

    @NotEmpty
    @Reference
    private Category category;

	/**
	 * Used this approach: http://cookbook.mongodb.org/patterns/votes/
	 */
	@Reference
	private Collection<User> voters;

	private long likes;

	private long dislikes;


	public Post()
	{
	}

	public Post(String message, User creator, Category category)
    {
        this.message = message;
        this.creator = creator;
        this.category = category;
		voters = new HashSet<User>();
		likes = 0;
		dislikes = 0;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public User getCreator()
    {
        return creator;
    }

    public void setCreator(User creator)
    {
        this.creator = creator;
    }

    public Category getCategory()
    {
        return category;
    }

    public void setCategory(Category category)
    {
        this.category = category;
    }

	public Collection<User> getVoters()
	{
		return voters;
	}

	public void setVoters(Collection<User> voters)
	{
		this.voters = voters;
	}

	public long getLikes()
	{
		return likes;
	}

	public void setLikes(long likes)
	{
		this.likes = likes;
	}

	public long getDislikes() {
		return dislikes;
	}

	public void setDislikes(long dislikes) {
		this.dislikes = dislikes;
	}
}
