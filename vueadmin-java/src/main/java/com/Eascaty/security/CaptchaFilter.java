package com.Eascaty.security;

import com.Eascaty.common.exception.CaptchaException;
import com.Eascaty.common.lang.Const;
import com.Eascaty.utils.RedisUtil;
import com.sun.org.apache.bcel.internal.classfile.Code;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class CaptchaFilter extends OncePerRequestFilter {



    @Autowired
    RedisUtil redisUtil;

    @Autowired
    LoginFailureHandler loginFailureHandler;


    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String url = httpServletRequest.getRequestURI();
//        log.info("获取到login链接，正在校验验证码 -- " + url);
//        log.info("httpmethod -- " + httpServletRequest.getMethod());
        if ("/login".equals(url) && httpServletRequest.getMethod().equals("POST")) {

            try {
//                校验验证码
                validate(httpServletRequest);
            }catch (Exception e) {
//                如果不正确，就跳转到认证失败处理器

                loginFailureHandler.onAuthenticationFailure(httpServletRequest, httpServletResponse, (AuthenticationException) e);
            }
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);

    }

    //  校验验证码逻辑
    private void validate(HttpServletRequest httpServletRequest) {

        String code = httpServletRequest.getParameter("code");
        String key = httpServletRequest.getParameter("token");
        log.info("key:" + key);
        if (StringUtils.isBlank(code) || StringUtils.isBlank(key)) {
            throw new CaptchaException("验证码为空");
        }

        if (!code.equals(redisUtil.hget(Const.CAPTCHA_KEY, key))) {
            throw new CaptchaException("验证码错误");
        }
//        一次性使用
        redisUtil.hdel(Const.CAPTCHA_KEY, key);
    }
}
