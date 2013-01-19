package com.makemyday.resources;

import com.google.inject.Inject;
import com.makemyday.entities.Category;
import com.makemyday.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.util.Collection;

@Path("/category")
@Produces(MediaType.APPLICATION_JSON)
public class CategoryResource
{

	private static final Logger logger = LoggerFactory.getLogger(UserResource.class);

	private CategoryService categoryService;

	@Inject
	public CategoryResource(CategoryService categoryService)
	{
		this.categoryService = categoryService;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response signUp(@Valid Category category)
	{
		categoryService.createCategory(category);
		return Response.created(UriBuilder.fromResource(CategoryResource.class).build()).build();
	}

	@GET
	public Collection<Category> getCategories()
	{
		return categoryService.getCategories();
	}
}
