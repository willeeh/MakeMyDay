package com.appreferendum.helloworld.resources;

import com.appreferendum.helloworld.model.Statement;

import com.mongodb.DB;
import com.yammer.metrics.annotation.Timed;
import net.vz.mongodb.jackson.DBCursor;
import net.vz.mongodb.jackson.JacksonDBCollection;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/hello-world/id/{id}")
@Produces(MediaType.APPLICATION_JSON)
public class HelloWorldResource
{
    private static final Logger logger = LoggerFactory.getLogger(HelloWorldResource.class);

    private final DB db;

    public HelloWorldResource(DB db)
    {
        this.db = db;
    }

    @GET
    @Timed
    public Statement getHello(@PathParam("id") String id)
    {
        logger.info("--> Entra en el método al menos!!!");

        JacksonDBCollection<Statement, String> statements =
                        JacksonDBCollection.wrap(db.getCollection("statements"), Statement.class, String.class);
        DBCursor<Statement> cursor = statements.find().is("id", id);

        if (!cursor.hasNext())
        {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }

        return cursor.next();
    }
}
