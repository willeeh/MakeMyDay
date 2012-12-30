package com.referappdum.service;

import com.referappdum.entities.User;

public interface UserService
{
    boolean createUser(User user);

    void updateUser(User user);

    User getUser(String facebookId);
}
