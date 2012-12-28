package com.referappdum.entities.stats;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
import com.google.code.morphia.annotations.Reference;
import com.referappdum.entities.User;

@Entity(value="user_stats", noClassnameStored=true)
public class UserStats
{
    @Id
    private String id;
    @Reference
    private User user;
    private Long votes = 0l;

    public UserStats()
    {
    }

    public UserStats(User user)
    {
        this.user = user;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    public Long getVotes()
    {
        return votes;
    }

    public void addVotes()
    {
        votes += 1;
    }
}
