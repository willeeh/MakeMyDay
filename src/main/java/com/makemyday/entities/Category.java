package com.makemyday.entities;

import com.google.code.morphia.annotations.Entity;
import com.makemyday.entities.base.Identity;
import org.hibernate.validator.constraints.NotEmpty;

@Entity(value="categories", noClassnameStored=true)
public class Category extends Identity
{
    @NotEmpty
    private String category;

    public Category(String category)
    {
        this.category = category;
    }

    public String getCategory()
    {
        return category;
    }

    public void setCategory(String category)
    {
        this.category = category;
    }
}
