package com.referappdum.service;

import com.google.inject.Inject;
import com.referappdum.dao.Dao;
import com.referappdum.entities.User;
import com.referappdum.entities.stats.UserStats;
import org.bson.types.ObjectId;

public class UserServiceImpl implements UserService
{
    private final Dao<User, ObjectId> userDAO;
    private final Dao<UserStats, ObjectId> userStatsDAO;

    @Inject
    public UserServiceImpl(Dao<User, ObjectId> userDAO, Dao<UserStats, ObjectId> userStatsDAO)
    {
        this.userDAO = userDAO;
        this.userStatsDAO = userStatsDAO;
    }

    @Override
    public void createUser(User user)
    {
        User existingUser = userDAO.findOne("facebookId", user.getFacebookId());

        if (existingUser == null)
        {
            userDAO.save(user);
            UserStats stats = new UserStats(user);
            userStatsDAO.save(stats);
        }
    }

    @Override
    public void updateUser(User user)
    {
        //TODO @gceballos Do it!
    }
}
