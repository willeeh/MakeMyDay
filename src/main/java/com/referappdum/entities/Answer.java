package com.referappdum.entities;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
import com.google.code.morphia.annotations.Reference;

@Entity(value="answers", noClassnameStored=true)
public class Answer
{
    @Id
    private String id;
    @Reference
    private QuestionOption questionOption;
    @Reference
    private User user;

    public Answer()
    {
    }

    public Answer(QuestionOption questionOption, User user)
    {
        this.questionOption = questionOption;
        this.user = user;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public QuestionOption getQuestionOption()
    {
        return questionOption;
    }

    public void setQuestionOption(QuestionOption questionOption)
    {
        this.questionOption = questionOption;
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }
}
