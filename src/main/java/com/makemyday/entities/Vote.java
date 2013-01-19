package com.makemyday.entities;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Reference;
import com.makemyday.entities.base.Identity;

import java.util.Collection;
import java.util.HashSet;

@Entity(value="votes", noClassnameStored=true)
public class Vote extends Identity
{
    public enum TYPE { LIKE, DISLIKE }

    private TYPE type;

    private Long value;

    @Reference
    private Post post;

	/**
	 * Used this approach, must be tested, compared, etc.
	 * http://cookbook.mongodb.org/patterns/votes/
	 */
	@Reference
	private Collection<User> voters;


	public Vote()
	{
	}

	public Vote(TYPE type, Post post)
	{
		this.type = type;
		this.value = 0l;
		this.post = post;
		voters = new HashSet<User>();
	}

	public TYPE getType()
    {
        return type;
    }

    public void setType(TYPE type)
    {
        this.type = type;
    }

    public Long getValue()
    {
        return value;
    }

    public void setValue(Long value)
    {
        this.value = value;
    }

    public Post getPost()
    {
        return post;
    }

    public void setPost(Post post)
    {
        this.post = post;
    }

	public Collection<User> getVoters()
	{
		return voters;
	}

	public void setVoters(Collection<User> voters)
	{
		this.voters = voters;
	}
}
