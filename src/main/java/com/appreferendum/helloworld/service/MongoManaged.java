package com.appreferendum.helloworld.service;

import com.mongodb.DB;
import com.yammer.dropwizard.lifecycle.Managed;

public class MongoManaged implements Managed
{
    private DB m;

    public MongoManaged(DB m)
    {
        this.m = m;
    }

    @Override
    public void start() throws Exception
    {

    }

    @Override
    public void stop() throws Exception
    {
        m.getMongo().close();
    }
}
