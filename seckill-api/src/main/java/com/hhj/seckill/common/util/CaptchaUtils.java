package com.hhj.seckill.common.util;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import cn.hutool.captcha.GifCaptcha;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.captcha.generator.RandomGenerator;
import org.springframework.stereotype.Component;


@Component
public class CaptchaUtils {

    private static RandomGenerator randomGenerator=new RandomGenerator(4);

    public static LineCaptcha generateCode(){
        LineCaptcha circleCaptcha = CaptchaUtil.createLineCaptcha(100, 30,4,50);
        circleCaptcha.setGenerator(randomGenerator);
        circleCaptcha.createCode();

        return circleCaptcha;
    }
}
