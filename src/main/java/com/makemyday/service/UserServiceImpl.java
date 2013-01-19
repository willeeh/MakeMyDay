package com.makemyday.service;

import com.google.inject.Inject;
import com.makemyday.dao.Dao;
import com.makemyday.entities.User;
import org.bson.types.ObjectId;

public class UserServiceImpl implements UserService
{
    private final Dao<User, ObjectId> userDAO;

    @Inject
    public UserServiceImpl(Dao<User, ObjectId> userDAO)
    {
        this.userDAO = userDAO;
    }

    @Override
    public boolean createUser(User user)
    {
        User existingUser = userDAO.findOne("facebookId", user.getFacebookId());

        if (existingUser == null)
        {
            /*UserStats stats = new UserStats();
            userStatsDAO.save(stats);
            user.setStats(stats);*/
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
