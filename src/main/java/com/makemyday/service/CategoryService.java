package com.makemyday.service;

import com.makemyday.entities.Category;

import java.util.Collection;

public interface CategoryService
{
	/**
	 * Gets all categories
	 */
	Collection<Category> getCategories();
}
