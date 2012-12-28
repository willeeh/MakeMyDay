package com.referappdum.resources;

import com.google.inject.Inject;
import com.referappdum.entities.User;
import com.referappdum.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.Valid;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

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
    public void signUp(@Valid User user)
    {
        userService.createUser(user);
    }
}
