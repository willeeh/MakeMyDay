package com.makemyday.resources;

import com.google.inject.Inject;
import com.makemyday.entities.Category;
import com.makemyday.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Collection;

@Path("/category")
@Produces(MediaType.APPLICATION_JSON)
public class CategoryResource
{

	private static final Logger logger = LoggerFactory.getLogger(CategoryResource.class);

	private CategoryService categoryService;

	@Inject
	public CategoryResource(CategoryService categoryService)
	{
		this.categoryService = categoryService;
	}

	@GET
	public Collection<Category> getCategories()
	{
		return categoryService.getCategories();
	}
}
