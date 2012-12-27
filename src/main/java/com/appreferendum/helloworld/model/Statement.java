package com.appreferendum.helloworld.model;

import net.vz.mongodb.jackson.ObjectId;
import javax.persistence.Id;

public class Statement
{
    private String id;

    private String content;

    public Statement()
    {
    }

    public Statement(String content)
    {
        this.content = content;
    }

    @Id
    @ObjectId
    public String getId()
    {
        return id;
    }

    @Id
    @ObjectId
    public void setId(String id)
    {
        this.id = id;
    }

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }
}
