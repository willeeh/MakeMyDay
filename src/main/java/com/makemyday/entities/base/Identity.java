package com.makemyday.entities.base;

import com.google.code.morphia.annotations.Id;
import org.bson.types.ObjectId;

import java.util.Date;

public class Identity
{
    @Id
    private ObjectId id;

    private Date createdOn;

    public ObjectId getId()
    {
        return id;
    }

    public void setId(ObjectId id)
    {
        this.id = id;
    }

    public Date getCreatedOn()
    {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn)
    {
        this.createdOn = createdOn;
    }
}
