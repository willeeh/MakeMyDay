package com.appreferendum.helloworld.model;

import net.vz.mongodb.jackson.Id;
import net.vz.mongodb.jackson.ObjectId;

public class Statement
{
    private String id;

    private String content;

    public Statement(String id, String content)
    {
        this.id = id;
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
