package com.couponize.Auth.service.impl;

import com.couponize.Auth.dao.UserDao;
import com.couponize.Auth.model.User;
import com.couponize.Auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    public User createUser(User user) {
        return userDao.create(user);
    }
}
