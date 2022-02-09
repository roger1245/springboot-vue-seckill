package com.hhj.seckill.controller;

import cn.hutool.captcha.LineCaptcha;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.SecureUtil;
import com.hhj.seckill.common.Result;
import com.hhj.seckill.common.enums.ErrorEnum;
import com.hhj.seckill.common.excetion.CommonException;
import com.hhj.seckill.common.util.CaptchaUtils;
import com.hhj.seckill.common.util.JwtUtil;
import com.hhj.seckill.common.util.MdUtil;
import com.hhj.seckill.common.util.RedisUtil;
import com.hhj.seckill.entry.User;
import com.hhj.seckill.service.UserService;
import com.hhj.seckill.vo.LoginVo;
import com.hhj.seckill.vo.RegisterVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @Author virtual
 * @Date 2021/6/1 10:23
 * @Version 1.0
 */
@RestController
@Slf4j
@Api("登录控制")
public class LoginController {

    private final String CODE="seckill:userId:code:";

    @Autowired
    UserService service;

    @Autowired
    MdUtil md5Util;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    RedisUtil redisUtil;


    @PostMapping(path = {"login"})
    public Result doLogin(@RequestBody @Validated LoginVo vo, HttpServletResponse response, HttpServletRequest request){
        // 判断验证码
//        String uuid = request.getHeader("capityUUID").replace('"',' ').trim();
//        System.out.println(uuid);
//        //验证码的逻辑
//        Object code = redisUtil.getObj(CODE+uuid,Object.class);
//        log.info(code.toString());
//        if (!(code.equals(vo.getCode()))){
//            throw new CommonException(ErrorEnum.CAPTCHA_WRONG);
//        }

        // 通过昵称查找用户
        User user = service.selectByNick(vo.getNick());
        if(user==null){
            log.error(ErrorEnum.USERNAME_OR_PASSWORD_WRONG.getMsg() + " 未找到该用户");
            throw new CommonException(ErrorEnum.USERNAME_OR_PASSWORD_WRONG);
        }
        // 判断密码
        String temp = md5Util.md5(vo.getPassword(), user.getSalt());
        if(! temp.equals(user.getPassword())){
            // 登录失败
            log.error(ErrorEnum.USERNAME_OR_PASSWORD_WRONG.getMsg() + " 密码错误");
            throw new CommonException(ErrorEnum.USERNAME_OR_PASSWORD_WRONG);
        }
        log.info("用户{}：{}上线了",user.getId(),user.getNick());
        // 返回jwt给前端
        // 清空redis中的旧token
        String jwt = jwtUtil.generateToken(user.getId());
        // 把token存进redis，默认过期时间为7天
        redisUtil.set(jwt,0,7, TimeUnit.DAYS);
        response.setHeader("Authorization",jwt);
        response.setHeader("Access-control-Expose-Headers", "Authorization");
        User user1 = new User();
        BeanUtil.copyProperties(user,user1, new String[]{"password", "salt"});
        return Result.success(user1,"登录成功");
    }



    @GetMapping("getCaptcha")
    @ApiOperation("获取验证码")
    public Result getCaptcha(HttpServletResponse response){
        String s = UUID.randomUUID().toString();
        log.info(s);
        response.addHeader("uuid",s);
        LineCaptcha lineCaptcha = CaptchaUtils.generateCode();
        String code = lineCaptcha.getCode();
        redisUtil.set(CODE+s,code,60);
        return Result.success(lineCaptcha.getImageBase64Data());
    }

    @PostMapping(path = {"register"})
    public Result register(@RequestBody RegisterVo registerVo) {
        User dbUser = service.selectByNick(registerVo.getNick());
        if (dbUser != null) {
            log.error(ErrorEnum.REGISTER_FAULT.getMsg() + " 用户名重复" );
            throw new CommonException(ErrorEnum.REGISTER_FAULT);
        }
        //这条语句应该放在前端注册页面,避免密码明文传递
        String md5 = SecureUtil.md5(registerVo.getPassword());

        String salt= RandomUtil.randomString(10);
        String cryptoPassword = md5Util.md5(md5, salt);
        User user = new User(null, cryptoPassword, registerVo.getNick(), salt, new Date(), null, 0);
        service.addUser(user);
        return Result.success("ok", "注册成功");
    }

}
