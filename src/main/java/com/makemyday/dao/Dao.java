package com.makemyday.dao;

import com.google.code.morphia.Morphia;
import com.google.code.morphia.dao.BasicDAO;
import com.google.code.morphia.query.Query;
import com.google.code.morphia.query.UpdateOperations;
import com.google.code.morphia.query.UpdateResults;
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

	public UpdateResults<T> update(Query<T> q, UpdateOperations<T> ops, boolean createIfMissing)
	{
		return getDatastore().update(q, ops, createIfMissing);
	}

	public T findAndModify(Query<T> query, UpdateOperations<T> update,  boolean oldVersion)
	{
		return (T) getDatastore().findAndModify(query, update, oldVersion);
	}

	public T findAndModify(Query<T> query, UpdateOperations<T> update,  boolean oldVersion, boolean createIfMissing)
	{
		return (T) getDatastore().findAndModify(query, update, oldVersion, createIfMissing);
	}


}

