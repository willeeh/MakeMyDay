package com.makemyday.resources;

import com.google.inject.Inject;
import com.makemyday.entities.User;
import com.makemyday.service.UserService;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
		ObjectId objectId = userService.createUser(user);
		return Response.status(Response.Status.CREATED).entity(objectId.toStringMongod()).build();
	}

	@GET
	@Path("/{id}")
	public User getUserById(@PathParam("id") String id)
	{
		User user = userService.getUserById(id);

		checkUserExists(user);

		return user;
	}

	@GET
	public User getUserByFacebookId(@QueryParam("facebookId") String facebookId)
	{
		User user = userService.getUserByFacebookId(facebookId);

		checkUserExists(user);

		return user;
	}

	@GET
	@Path("/{id}/bookmark/{postId}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addBookmark(@PathParam("id") String facebookId, @PathParam("postId") String postId)
	{
		userService.addBookmark(facebookId, postId);
		return Response.ok().build();
	}

	private void checkUserExists(User user)
	{
		if (user == null)
		{
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
	}
}