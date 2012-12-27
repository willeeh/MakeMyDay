package com.appreferendum.helloworld.resources;

import com.appreferendum.helloworld.model.Statement;
import com.google.common.base.Optional;
import com.mongodb.DB;
import com.yammer.metrics.annotation.Timed;
import net.vz.mongodb.jackson.DBCursor;
import net.vz.mongodb.jackson.JacksonDBCollection;
import net.vz.mongodb.jackson.WriteResult;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/hello-world/id/{id}")
@Produces(MediaType.APPLICATION_JSON)
public class HelloWorldResource
{
    private final DB db;

    public HelloWorldResource(DB db)
    {
        this.db = db;
    }

    @GET
    @Timed
    public Statement getHello(@PathParam("id") String id)
    {
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
