package com.makemyday.service;

import com.makemyday.entities.User;

public interface UserService
{
    boolean createUser(User user);

    void updateUser(User user);

    User getUser(String facebookId);
}
