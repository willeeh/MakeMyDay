package com.makemyday.entities;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Reference;
import com.makemyday.entities.base.Identity;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.Collection;

@Entity(value="posts", noClassnameStored=true)
public class Post extends Identity
{
	public static enum VoteType
	{
		LIKE("likes", 1), DISLIKE("dislikes", -1);

		public final String fieldName;

		public final long importanceIncrement;

		VoteType(String fieldName, long importanceIncrement)
		{
			this.fieldName = fieldName;
			this.importanceIncrement = importanceIncrement;
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

	private long importance;

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

	public long getDislikes()
	{
		return dislikes;
	}

	public void setDislikes(long dislikes)
	{
		this.dislikes = dislikes;
	}

	public long getImportance()
	{
		return importance;
	}

	public void setImportance(long importance)
	{
		this.importance = importance;
	}
}
