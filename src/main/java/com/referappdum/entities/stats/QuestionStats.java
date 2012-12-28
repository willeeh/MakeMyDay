package com.referappdum.entities.stats;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;

@Entity(value="question_stats", noClassnameStored=true)
public class QuestionStats
{
    @Id
    private String id;
    private Long votes = 0l;

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public Long getVotes()
    {
        return votes;
    }

    public void addVotes()
    {
        votes += 1;
    }
}
