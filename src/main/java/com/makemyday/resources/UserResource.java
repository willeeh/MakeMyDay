package com.makemyday.resources;

import com.google.inject.Inject;
import com.makemyday.entities.User;
import com.makemyday.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource
{
    private static final Logger logger = LoggerFactory.getLogger(UserResource.class);

    private UserService userService;

    @Inject
    public UserResource(UserService userService)
    {
        this.userService = userService;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response signUp(@Valid User user)
    {
        if ( userService.createUser(user) )
        {
            return Response.created( UriBuilder.fromResource(UserResource.class).build(user.getFacebookId()) ).build();
        }
        else
        {
            return Response.status(Response.Status.NOT_MODIFIED).build();
        }
    }

    @GET
    @Path("/{id}")
    public User getUser(@PathParam("id") String facebookId)
    {
        User user = userService.getUser(facebookId);

        if (user == null)
        {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }

        return user;

    }
}