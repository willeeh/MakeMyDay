package com.makemyday.service;

import com.google.inject.Inject;
import com.makemyday.dao.Dao;
import com.makemyday.entities.Category;
import org.bson.types.ObjectId;

import java.util.Collection;

public class CategoryServiceImpl implements CategoryService
{
	private final Dao<Category, ObjectId> categoryDAO;

	@Inject
	public CategoryServiceImpl(Dao<Category, ObjectId> categoryDAO)
	{
		this.categoryDAO = categoryDAO;
	}

	@Override
	public Collection<Category> getCategories()
	{
		// Here we use asList because there won't be too many categories
		return categoryDAO.find().asList();
	}
}
