package com.makemyday.resources;

import com.google.inject.Inject;
import com.makemyday.entities.Post;
import com.makemyday.service.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.util.Collection;

@Path("/post")
@Produces(MediaType.APPLICATION_JSON)
public class PostResource
{
	private static final Logger logger = LoggerFactory.getLogger(PostResource.class);

	private static final int MAX_POST_LIMIT = 100;

	private PostService postService;

	@Inject
	public PostResource(PostService postService)
	{
		this.postService = postService;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createPost(@Valid Post post)
	{
		postService.createPost(post);

		return Response.created(UriBuilder.fromResource(PostResource.class).build(post.getId())).build();
	}

	@GET
	@Path("/{id}")
	public Post getPost(@PathParam("id") String postId)
	{
		Post post = postService.getPostById(postId);

		if (post == null)
		{
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}

		return post;
	}

	@GET
	public Collection<Post> getLatestPosts(
			@QueryParam("offset") @DefaultValue("0") int offset,
			@QueryParam("limit") @DefaultValue("10") int limit)
	{
		if (offset < 0 || limit < 0)
			throw new WebApplicationException(Response.Status.PRECONDITION_FAILED); // TODO: Add message

		if (limit > MAX_POST_LIMIT)
			throw new WebApplicationException(
					Response.status(Response.Status.PRECONDITION_FAILED).entity(
							"Limit can't be greater than " + MAX_POST_LIMIT).build());

		return postService.getLatestPosts(offset,  limit);
	}
}