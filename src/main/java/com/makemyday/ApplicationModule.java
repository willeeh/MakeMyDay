package com.makemyday;

import com.google.code.morphia.Morphia;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.TypeLiteral;
import com.google.inject.name.Named;
import com.mongodb.DB;
import com.mongodb.Mongo;
import com.mongodb.MongoURI;
import com.yammer.dropwizard.config.Configuration;
import org.bson.types.ObjectId;

public class ApplicationModule extends AbstractModule
{
    @Override
    protected void configure()
    {
        /*bind(new TypeLiteral<Dao<User, ObjectId>>() {});
        bind(new TypeLiteral<Dao<UserStats, ObjectId>>() {});
        bind(new TypeLiteral<Dao<Statement, ObjectId>>() {});
        bind(UserService.class).to(UserServiceImpl.class);*/
    }

    @Provides
    public ApplicationConfiguration configuration(Configuration configuration)
    {
        return (ApplicationConfiguration) configuration;
    }

    @Provides
    @Named("template")
    public String provideTemplate(ApplicationConfiguration configuration)
    {
        return configuration.getTemplate();
    }

    @Provides
    @Named("defaultName")
    public String provideDefaultName(ApplicationConfiguration configuration)
    {
        return configuration.getDefaultName();
    }

    @Provides
    @Named("databaseName")
    public String provideDatabaseName(ApplicationConfiguration configuration)
    {
        return new MongoURI(configuration.getDatabase().getUri()).getDatabase();
    }

    @Provides
    public Mongo provideMongo(ApplicationConfiguration configuration) throws Exception
    {
        MongoURI mongoURI = new MongoURI(configuration.getDatabase().getUri());
        DB db = mongoURI.connectDB();
        if (mongoURI.getUsername() != null)
        {
            db.authenticate(mongoURI.getUsername(), mongoURI.getPassword());
        }
        return  db.getMongo();

    }

    @Provides
    public Morphia provideMorphia()
    {
        Morphia morphia = new Morphia();
        morphia.mapPackage("com.makemyday.entities");
        /*morphia.mapPackage("com.makemyday.entities.stats");*/
        return morphia;
    }

}
