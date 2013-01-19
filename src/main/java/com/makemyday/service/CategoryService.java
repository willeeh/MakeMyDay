package com.makemyday.service;

import com.makemyday.entities.Category;

import java.util.Collection;

public interface CategoryService
{
	/**
	 * Just saves the category.
	 */
    void createCategory(Category category);

	/**
	 * Gets all categories
	 */
	Collection<Category> getCategories();
}
