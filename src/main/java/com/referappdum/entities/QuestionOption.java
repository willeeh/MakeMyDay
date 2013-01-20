package com.referappdum.entities;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
import com.google.code.morphia.annotations.Reference;

@Entity(value="question_options", noClassnameStored=true)
public class QuestionOption
{
    @Id
    private String id;
    private String text;
    @Reference
    private Question question;

    public QuestionOption()
    {
    }

    public QuestionOption(String text, Question question)
    {
        this.text = text;
        this.question = question;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getText()
    {
        return text;
    }

    public void setText(String text)
    {
        this.text = text;
    }

    public Question getQuestion()
    {
        return question;
    }

    public void setQuestion(Question question)
    {
        this.question = question;
    }
}
