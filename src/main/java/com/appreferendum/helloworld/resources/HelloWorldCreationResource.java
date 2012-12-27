package com.appreferendum.helloworld.resources;

import com.appreferendum.helloworld.model.Statement;
import com.google.common.base.Optional;
import com.mongodb.DB;
import com.yammer.metrics.annotation.Timed;
import net.vz.mongodb.jackson.JacksonDBCollection;
import net.vz.mongodb.jackson.WriteResult;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/hello-world")
@Produces(MediaType.APPLICATION_JSON)
public class HelloWorldCreationResource
{
    private final DB db;

    private final String template;
    private final String defaultName;

    public HelloWorldCreationResource(DB db, String template, String defaultName)
    {
        this.db = db;
        this.template = template;
        this.defaultName = defaultName;
    }

    @GET
    @Timed
    public Statement sayHello(@QueryParam("name") Optional<String> name)
    {

        JacksonDBCollection<Statement, String> statements =
                JacksonDBCollection.wrap(db.getCollection("statements"), Statement.class, String.class);
        Statement st =
                new Statement( String.format(template, name.or(defaultName)) );

        WriteResult<Statement, String> result = statements.save(st);

        return result.getSavedObject();
    }
}
