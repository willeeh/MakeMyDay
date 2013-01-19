package com.makemyday.entities;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Reference;
import com.makemyday.entities.base.Identity;
import org.hibernate.validator.constraints.NotEmpty;

@Entity(value="messages", noClassnameStored=true)
public class Post extends Identity
{
    @NotEmpty
    private String message;

    @NotEmpty
    @Reference
    private User creator;

    @NotEmpty
    @Reference
    private Category category;

    public Post(String message, User creator, Category category)
    {
        this.message = message;
        this.creator = creator;
        this.category = category;
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
}
