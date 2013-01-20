package com.referappdum.entities;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;

@Entity(value="categories", noClassnameStored=true)
public class Category
{
    @Id
    private String id;
    private String keyLabel;

    public Category()
    {
    }

    public Category(String keyLabel)
    {
        this.keyLabel = keyLabel;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getKeyLabel()
    {
        return keyLabel;
    }

    public void setKeyLabel(String keyLabel)
    {
        this.keyLabel = keyLabel;
    }
}
