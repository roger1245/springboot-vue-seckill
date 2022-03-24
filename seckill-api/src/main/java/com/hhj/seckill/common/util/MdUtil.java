package com.hhj.seckill.common.util;

import cn.hutool.crypto.SecureUtil;
import org.springframework.stereotype.Component;


@Component
public class MdUtil {

    
    public String md5(String firstPassword,String salt){
        // 通过盐值加密
        firstPassword= ""+salt.charAt(0)+salt.charAt(2)+firstPassword+salt.charAt(4)+salt.charAt(5);
        return SecureUtil.md5(firstPassword);
    }
}
