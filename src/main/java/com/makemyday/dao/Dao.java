package com.makemyday.dao;

import com.google.code.morphia.Morphia;
import com.google.code.morphia.dao.BasicDAO;
import com.google.inject.Inject;
import com.google.inject.TypeLiteral;
import com.google.inject.name.Named;
import com.mongodb.Mongo;


@SuppressWarnings("unchecked")
public class Dao<T, K> extends BasicDAO<T, K>
{
    @Inject
    public Dao(TypeLiteral<T> dtoType, Mongo mongo, Morphia morphia, @Named("databaseName") String dbName)
    {
        super((Class<T>)dtoType.getRawType(), mongo, morphia, dbName);
    }
}

