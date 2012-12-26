package com.appreferendum.helloworld.resources;

import com.appreferendum.helloworld.core.Saying;
import com.appreferendum.helloworld.model.Statement;
import com.google.common.base.Optional;
import com.mongodb.DB;
import com.yammer.metrics.annotation.Timed;
import net.vz.mongodb.jackson.DBCursor;
import net.vz.mongodb.jackson.JacksonDBCollection;
import net.vz.mongodb.jackson.internal.stream.JacksonDBObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.concurrent.atomic.AtomicLong;

@Path("/hello-world")
@Produces(MediaType.APPLICATION_JSON)
public class HelloWorldResource
{
    private final DB db;

    private final String template;
    private final String defaultName;
    private final AtomicLong counter;

    public HelloWorldResource(DB db, String template, String defaultName)
    {
        this.db = db;
        this.template = template;
        this.defaultName = defaultName;
        this.counter = new AtomicLong();
    }

    @GET
    @Timed
    public Saying sayHello(@QueryParam("name") Optional<String> name)
    {

        JacksonDBCollection<Statement, String> statements =
                JacksonDBCollection.wrap(db.getCollection("statements"), Statement.class, String.class);
        Statement st =
                new Statement(
                        String.valueOf(counter.incrementAndGet()),
                        String.format(template, name.or(defaultName)) );

        DBCursor<Statement> cursor = statements.find().is("id", st.getId());
        if (cursor.hasNext())
        {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }

        statements.save(st);

        return new Saying(Long.valueOf(st.getId()), st.getContent());
    }

    /*@GET
    @Timed
    public Saying getHello(@QueryParam("id") Long id)
    {

    }*/
}
