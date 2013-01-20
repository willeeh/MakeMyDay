package com.referappdum.resources;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.referappdum.dao.Dao;
import com.referappdum.entities.Statement;

import com.google.common.base.Optional;
import com.yammer.metrics.annotation.Timed;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/hello-world")
@Produces(MediaType.APPLICATION_JSON)
public class HelloWorldResource
{
    private static final Logger logger = LoggerFactory.getLogger(HelloWorldResource.class);

    private final Dao<Statement, ObjectId> dao;
    private final String template;
    private final String defaultName;

    @Inject
    public HelloWorldResource(Dao<Statement, ObjectId> dao, @Named("template") String template, @Named("defaultName") String defaultName)
    {
        this.dao = dao;
        this.template = template;
        this.defaultName = defaultName;
    }

    @GET
    @Path("/id/{id}")
    @Timed
    public Statement getHello(@PathParam("id") String id)
    {
        logger.info("Getting statement by id " + id);

        ObjectId objectId = null;

        try
        {
            objectId = new ObjectId(id);
        }
        catch (IllegalArgumentException exception)
        {
            notFound();
        }

        Statement statement = dao.get(objectId);

        if (statement == null)
        {
            notFound();
        }

        logger.info("Statement found");
        return statement;
    }

    @GET
    @Timed
    public Statement sayHello(@QueryParam("name") Optional<String> name)
    {
        Statement statement = new Statement( String.format(template, name.or(defaultName)) );

        dao.save(statement);

        return statement;
    }

    private void notFound()
    {
        logger.info("Statement not found");
        throw new WebApplicationException(Response.Status.NOT_FOUND);
    }
}
