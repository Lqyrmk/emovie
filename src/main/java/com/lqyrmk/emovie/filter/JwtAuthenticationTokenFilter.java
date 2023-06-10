package com.lqyrmk.emovie.filter;

import com.lqyrmk.emovie.entity.LoginUser;
import com.lqyrmk.emovie.utils.JwtUtil;
import com.lqyrmk.emovie.utils.RedisCache;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * @Description
 * @Author YuanmingLiu
 * @Date 2023/6/7 12:27
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private RedisCache redisCache;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        // 获取token
        String token = httpServletRequest.getHeader("token");
        // 判断是否存在token
        if (!StringUtils.hasText(token)) {
            // 放行
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }
        // 有token则解析token
        String userId = null;
        try {
            Claims claims = JwtUtil.parseJWT(token);
            userId = claims.getSubject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("携带的token非法");
        }

        // 从redis中获取用户信息
        String redisKey = "login:" + userId;
//        ValueOperations<String, LoginUser> operation = redisTemplate.opsForValue();
//        LoginUser loginUser = operation.get(redisKey);
        LoginUser loginUser = redisCache.getCacheObject(redisKey);

        if (Objects.isNull(loginUser)) {
            throw new RuntimeException("用户未登录");
        }

        // 存入SecurityContextHolder
        // 获取权限信息封装到Authentication中
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginUser, null, loginUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        // 放行
        filterChain.doFilter(httpServletRequest, httpServletResponse);

    }
}
