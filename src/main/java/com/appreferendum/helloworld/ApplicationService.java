package com.appreferendum.helloworld;

import com.appreferendum.helloworld.dao.StatementDAO;
import com.appreferendum.helloworld.health.TemplateHealthCheck;
import com.appreferendum.helloworld.resources.HelloWorldResource;
import com.appreferendum.helloworld.service.MongoManaged;
import com.google.code.morphia.Datastore;
import com.google.code.morphia.Morphia;
import com.mongodb.DB;
import com.mongodb.MongoURI;
import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;

public class ApplicationService extends Service<ApplicationConfiguration>
{
    public static void main(String[] args) throws Exception
    {
        new ApplicationService().run(args);
    }

    @Override
    public void initialize(Bootstrap<ApplicationConfiguration> bootstrap)
    {
        bootstrap.setName("hello-world");
    }

    @Override
    public void run(ApplicationConfiguration configuration, Environment environment) throws Exception
    {
        MongoURI mongoURI = new MongoURI(configuration.getDatabase().getUri());
        DB db = mongoURI.connectDB();
        if (mongoURI.getUsername() != null)
        {
            db.authenticate(mongoURI.getUsername(), mongoURI.getPassword());
        }

        Morphia morphia = new Morphia();
        Datastore ds = morphia.createDatastore(db.getMongo(), mongoURI.getDatabase());
        morphia.mapPackage("com.appreferendum.helloworld.model");

        MongoManaged mongoManaged = new MongoManaged(db);
        environment.manage(mongoManaged);

        final String template = configuration.getTemplate();
        final String defaultName = configuration.getDefaultName();

        StatementDAO statementDAO = new StatementDAO(db.getMongo(), morphia, mongoURI.getDatabase());

        environment.addResource( new HelloWorldResource(statementDAO, template, defaultName) );

        environment.addHealthCheck(new TemplateHealthCheck(template));
    }
}
