package com.hhj.seckill.mapper;

import com.hhj.seckill.entry.User;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface UserMapper {

    public User selectByNick(String nick);

    public boolean addUser(User user);
}
