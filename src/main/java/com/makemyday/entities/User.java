package com.makemyday.entities;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
import com.google.code.morphia.annotations.Reference;
import com.makemyday.entities.base.Identity;
import org.bson.types.ObjectId;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.List;

@Entity(value="users", noClassnameStored=true)
public class User extends Identity
{
    @NotEmpty
    private String facebookId;

    private String name;

    private String gender;

    private String email;

    private String picture;

    @Reference(lazy = true)
    private List<Device> devices;

    public User()
    {
    }

    public User(String facebookId, String name, String gender, String email, String picture)
    {
        this.facebookId = facebookId;
        this.name = name;
        this.gender = gender;
        this.email = email;
        this.picture = picture;
    }

    public String getFacebookId()
    {
        return facebookId;
    }

    public void setFacebookId(String facebookId)
    {
        this.facebookId = facebookId;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getGender()
    {
        return gender;
    }

    public void setGender(String gender)
    {
        this.gender = gender;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getPicture()
    {
        return picture;
    }

    public void setPicture(String picture)
    {
        this.picture = picture;
    }
}