package com.makemyday.entities;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Reference;

@Entity(value="votes", noClassnameStored=true)
public class Vote
{
    public enum TYPE { LIKE, DISLIKE }

    private TYPE type;

    private Long value;

    @Reference
    private Post post;

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
}
