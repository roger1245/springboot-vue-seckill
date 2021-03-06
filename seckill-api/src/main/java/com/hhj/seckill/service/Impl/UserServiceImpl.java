package com.hhj.seckill.service.Impl;

import com.hhj.seckill.entry.User;
import com.hhj.seckill.mapper.UserMapper;
import com.hhj.seckill.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper mapper;
    @Override
    public User selectByNick(String nick) {
        User user = mapper.selectByNick(nick);
        return user;
    }

    @Override
    public boolean addUser(User user) {
        return mapper.addUser(user);
    }
}
