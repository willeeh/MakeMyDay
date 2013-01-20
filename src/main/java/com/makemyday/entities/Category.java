package com.makemyday.entities;

import com.google.code.morphia.annotations.Entity;
import com.makemyday.entities.base.Identity;
import org.hibernate.validator.constraints.NotEmpty;

@Entity(value = "categories", noClassnameStored = true)
public class Category extends Identity
{
	@NotEmpty
	private String name;

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}
}
