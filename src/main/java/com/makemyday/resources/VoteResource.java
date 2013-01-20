package com.makemyday.resources;

import com.google.inject.Inject;
import com.makemyday.entities.Post;
import com.makemyday.entities.User;
import com.makemyday.entities.Vote;
import com.makemyday.service.UserService;
import com.makemyday.service.VoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

@Path("/vote")
@Produces(MediaType.APPLICATION_JSON)
public class VoteResource
{
	private static final Logger logger = LoggerFactory.getLogger(VoteResource.class);

	private final VoteService voteService;

	private final UserService userService;

	@Inject
	public VoteResource(VoteService voteService, UserService userService)
	{
		this.voteService = voteService;
		this.userService = userService;
	}

	// TODO must validate post
	@POST
	@Path("/like/{userId}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response like(@PathParam("userId") String userId, Post post)
	{
		User user = userService.getUserById(userId);
		voteService.incrementVote(post, user, Vote.TYPE.LIKE);
		return Response.created(UriBuilder.fromResource(PostResource.class).build(post.getId())).build();
	}

	// TODO must validate post
	@POST
	@Path("/dislike/{userId}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response dislike(@PathParam("userId") String userId, Post post)
	{
		User user = userService.getUserById(userId);
		voteService.incrementVote(post, user, Vote.TYPE.DISLIKE);
		return Response.created(UriBuilder.fromResource(PostResource.class).build(post.getId())).build();
	}
}
