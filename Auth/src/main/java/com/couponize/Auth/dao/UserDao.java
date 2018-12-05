package com.couponize.Auth.dao;

import com.couponize.Auth.model.User;

import java.util.HashMap;

public interface UserDao extends BaseDao<User>{
    User findByEmail(String email);

}
