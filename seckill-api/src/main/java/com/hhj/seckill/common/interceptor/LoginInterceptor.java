package com.hhj.seckill.common.interceptor;

import com.hhj.seckill.common.enums.ErrorEnum;
import com.hhj.seckill.common.excetion.CommonException;
import com.hhj.seckill.common.util.JwtUtil;
import com.hhj.seckill.common.util.RedisUtil;
import com.mysql.cj.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Component
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {
    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    RedisUtil redisUtil;
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (HttpMethod.OPTIONS.toString().equals(request.getMethod())){
            return true;
        }
        // 取出token
        String authorization = request.getHeader("authorization");
        if (!StringUtils.isNullOrEmpty(authorization)){
//            log.info(authorization);
            // 查看缓存是否有
            String obj = redisUtil.getObj(authorization, String.class);
            if(!StringUtils.isNullOrEmpty(obj)){
                return true;
            }
        }
        throw new CommonException(ErrorEnum.TOKEN_EXPIRED);


    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
