package com.appreferendum.helloworld.core;

public class Saying
{
    private final String id;
    private final String content;

    public Saying(String id, String content)
    {
        this.id = id;
        this.content = content;
    }

    public String getId()
    {
        return id;
    }

    public String getContent()
    {
        return content;
    }
}
