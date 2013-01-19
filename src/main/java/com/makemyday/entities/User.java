package com.makemyday.entities;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
import org.bson.types.ObjectId;
import org.hibernate.validator.constraints.NotEmpty;

@Entity(value="users", noClassnameStored=true)
public class User
{
    private @Id
    ObjectId id;

    private @NotEmpty
    String facebookId;
    private @NotEmpty String name;
    private @NotEmpty String token;
    private String email;

    public User()
    {
    }

    public User(String facebookId, String name, String email, String token)
    {
        this.facebookId = facebookId;
        this.name = name;
        this.email = email;
        this.token = token;
    }

    public ObjectId getId()
    {
        return id;
    }

    public void setId(ObjectId id)
    {
        this.id = id;
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

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getToken()
    {
        return token;
    }

    public void setToken(String token)
    {
        this.token = token;
    }

}