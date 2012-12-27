package com.appreferendum.helloworld.resources;

import com.appreferendum.helloworld.dao.StatementDAO;
import com.appreferendum.helloworld.model.Statement;

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

    private final StatementDAO dao;
    private final String template;
    private final String defaultName;

    public HelloWorldResource(StatementDAO dao, String template, String defaultName)
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

        ObjectId i = new ObjectId(id);
        Statement statement = dao.get(i);

        if (statement == null)
        {
            logger.info("Statement not found");
            throw new WebApplicationException(Response.Status.NOT_FOUND);
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
}
