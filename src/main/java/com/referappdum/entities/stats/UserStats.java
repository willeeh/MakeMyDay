package com.referappdum.entities.stats;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
import org.bson.types.ObjectId;

@Entity(value="user_stats", noClassnameStored=true)
public class UserStats
{
    @Id
    private ObjectId id;
    private Long votes = 0l;

    public UserStats()
    {
    }

    public ObjectId getId()
    {
        return id;
    }

    public void setId(ObjectId id)
    {
        this.id = id;
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
