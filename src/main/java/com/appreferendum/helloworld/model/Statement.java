package com.appreferendum.helloworld.model;

import net.vz.mongodb.jackson.Id;
import net.vz.mongodb.jackson.ObjectId;

public class Statement
{
    private Long id;

    private String content;

    public Statement(Long id, String content)
    {
        this.id = id;
        this.content = content;
    }

    @Id
    @ObjectId
    public Long getId()
    {
        return id;
    }

    @Id
    @ObjectId
    public void setId(Long id)
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
