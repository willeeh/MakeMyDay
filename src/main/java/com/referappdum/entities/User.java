package com.referappdum.entities;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
import com.google.code.morphia.annotations.Reference;
import com.referappdum.entities.stats.UserStats;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.ArrayList;
import java.util.List;

@Entity(value="users", noClassnameStored=true)
public class User
{
    private @Id String id;

    private @NotEmpty String facebookId;
    private @NotEmpty String name;
    private @NotEmpty String token;
    private String email;

    @Reference
    private UserStats stats;

    @Reference(lazy = true)
    private List<Answer> answers = new ArrayList<Answer>();

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

    public String getId()
    {
        return id;
    }

    public void setId(String id)
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

    public UserStats getStats()
    {
        return stats;
    }

    public void setStats(UserStats stats)
    {
        this.stats = stats;
    }

    public List<Answer> getAnswers()
    {
        return answers;
    }

    public void addAnswer(Answer answer)
    {
        answers.add(answer);
    }

}
