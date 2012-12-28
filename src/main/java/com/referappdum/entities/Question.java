package com.referappdum.entities;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
import com.google.code.morphia.annotations.Reference;

import java.util.ArrayList;
import java.util.List;

@Entity(value="questions", noClassnameStored=true)
public class Question
{
    @Id
    private String id;
    private String question;
    private String locale;
    private User from;

    @Reference
    private List<Category> categories = new ArrayList<Category>();
    @Reference
    private List<QuestionOption> options = new ArrayList<QuestionOption>();

    public Question()
    {
    }

    public Question(String question, String locale, User from, Category... categories)
    {
        this.question = question;
        this.locale = locale;
        this.from = from;
        for (Category category : categories)
        {
            addCategory(category);
        }
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getQuestion()
    {
        return question;
    }

    public void setQuestion(String question)
    {
        this.question = question;
    }

    public String getLocale()
    {
        return locale;
    }

    public void setLocale(String locale)
    {
        this.locale = locale;
    }

    public User getFrom()
    {
        return from;
    }

    public void setFrom(User from)
    {
        this.from = from;
    }

    public List<Category> getCategories()
    {
        return categories;
    }

    public void addCategory(Category category)
    {
        categories.add(category);
    }

    public List<QuestionOption> getOptions()
    {
        return options;
    }

    public void setOptions(List<QuestionOption> options)
    {
        this.options = options;
    }
}
