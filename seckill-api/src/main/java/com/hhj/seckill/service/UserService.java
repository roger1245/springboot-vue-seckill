package com.hhj.seckill.service;

import com.hhj.seckill.entry.User;


public interface UserService {

    User  selectByNick(String nick);

    boolean addUser(User user);
}
