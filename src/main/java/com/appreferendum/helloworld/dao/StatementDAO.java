package com.appreferendum.helloworld.dao;

import com.appreferendum.helloworld.model.Statement;
import com.google.code.morphia.Morphia;
import com.google.code.morphia.dao.BasicDAO;
import com.mongodb.Mongo;
import org.bson.types.ObjectId;

public class StatementDAO extends BasicDAO<Statement, ObjectId>
{
    public StatementDAO(Mongo mongo, Morphia morphia, String dbName)
    {
        super(Statement.class, mongo, morphia, dbName);
    }
}
