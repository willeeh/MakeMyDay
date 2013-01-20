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
    public boolean createUser(User user)
    {
        User existingUser = userDAO.findOne("facebookId", user.getFacebookId());

        if (existingUser == null)
        {
            UserStats stats = new UserStats();
            userStatsDAO.save(stats);
            user.setStats(stats);
            userDAO.save(user);
            return true;
        }

        return false;
    }

    @Override
    public void updateUser(User user)
    {
        //TODO @gceballos Do it!
    }

    @Override
    public User getUser(String facebookId)
    {
        return userDAO.findOne("facebookId", facebookId);
    }
}
