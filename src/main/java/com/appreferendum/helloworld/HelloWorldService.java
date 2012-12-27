package com.appreferendum.helloworld;

import com.appreferendum.helloworld.health.TemplateHealthCheck;
import com.appreferendum.helloworld.resources.HelloWorldCreationResource;
import com.appreferendum.helloworld.resources.HelloWorldResource;
import com.appreferendum.helloworld.service.MongoManaged;
import com.mongodb.DB;
import com.mongodb.MongoURI;
import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;

import java.net.URI;

public class HelloWorldService extends Service<HelloWorldConfiguration>
{
    public static void main(String[] args) throws Exception
    {
        new HelloWorldService().run(args);
    }

    @Override
    public void initialize(Bootstrap<HelloWorldConfiguration> bootstrap)
    {
        bootstrap.setName("hello-world");
    }

    @Override
    public void run(HelloWorldConfiguration configuration, Environment environment) throws Exception
    {
        MongoURI mongoURI = new MongoURI(System.getenv("MONGOHQ_URL"));
        DB db = mongoURI.connectDB();
        db.authenticate(mongoURI.getUsername(), mongoURI.getPassword());

        MongoManaged mongoManaged = new MongoManaged(db);
        environment.manage(mongoManaged);

        final String template = configuration.getTemplate();
        final String defaultName = configuration.getDefaultName();

        environment.addResource( new HelloWorldResource(db) );
        environment.addResource( new HelloWorldCreationResource(db, template, defaultName) );

        environment.addHealthCheck(new TemplateHealthCheck(template));
    }
}
