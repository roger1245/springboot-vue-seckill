package com.hhj.seckill.entry;

import com.alibaba.druid.sql.visitor.functions.Right;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    private Integer id;
    private String password;
    private String nick;
    private String salt;
    private Date registerDate;
    private Date lastLoginDate;
    private Integer loginCount;

}
