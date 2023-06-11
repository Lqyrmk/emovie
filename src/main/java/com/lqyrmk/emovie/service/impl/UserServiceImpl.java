package com.lqyrmk.emovie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lqyrmk.emovie.common.MovieException;
import com.lqyrmk.emovie.common.Result;
import com.lqyrmk.emovie.entity.LoginUser;
import com.lqyrmk.emovie.entity.User;
import com.lqyrmk.emovie.entity.UserRole;
import com.lqyrmk.emovie.mapper.UserMapper;
import com.lqyrmk.emovie.mapper.UserRoleMapper;
import com.lqyrmk.emovie.service.UserService;
import com.lqyrmk.emovie.utils.JwtUtil;
import com.lqyrmk.emovie.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.management.relation.Role;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Description
 * @Author YuanmingLiu
 * @Date 2023/3/18 16:48
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCache redisCache;

    @Override
    public Result<Map<String, Object>> login(User user) {
        // AuthenticationManager authenticate方法进行用户认证
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);

        // 认证未通过
        if (authenticate == null) {
            throw new RuntimeException("用户名或密码错误！");
        }
        // 认证通过，使用userid生成jwt
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String userId = loginUser.getUser().getUserId().toString();
        String jwt = JwtUtil.createJWT(userId);
        Map<String, Object> map = new HashMap<>();
        map.put("token", jwt);

        // 将完整的用户信息存入redis，id作为key
//        redisTemplate.opsForValue().set("login:" + userId, loginUser);
        redisCache.setCacheObject("login:" + userId, loginUser);

        List<String> permissions = loginUser.getPermissions();
        // 判断permissions中是否存在管理员权限
        if (permissions.stream().anyMatch("system:manage"::equals)) {
            map.put("permission", 2);
        }
        else {
            map.put("permission", 1);
        }

        // 返回jwt
        return Result.success(map, "登录成功！");
    }

    @Override
    public Result<String> logout() {
        // 获取SecurityContextHolder中的用户id
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long userId = loginUser.getUser().getUserId();
        // 删除redis中的值
        redisCache.deleteObject("login:" + userId);
        return Result.success(null, "已退出登录！");
    }

    @Override
    public User register(User user) {

        // 加密
        String encode = passwordEncoder.encode(user.getPassword());
        user.setPassword(encode);

        // 查询是否存在相同的用户名
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, user.getUsername());
        if (userMapper.selectOne(queryWrapper) != null) {
            throw new MovieException("用户名已存在");
        }
        // 查询邮箱是否已被绑定
        LambdaQueryWrapper<User> queryWrapper2 = new LambdaQueryWrapper<>();
        queryWrapper2.eq(User::getEmail, user.getEmail());
        if (userMapper.selectOne(queryWrapper2) != null) {
            throw new MovieException("该邮箱已绑定其他账号");
        }
        // 查询手机号是否已被绑定
        LambdaQueryWrapper<User> queryWrapper3 = new LambdaQueryWrapper<>();
        queryWrapper3.eq(User::getPhone, user.getPhone());
        if (userMapper.selectOne(queryWrapper3) != null) {
            throw new MovieException("该手机号已绑定其他账号");
        }
        // 用户可被注册
        // 添加用户
        userMapper.insert(user);

        // 创建权限关系，注册获得用户角色
        UserRole userRole = new UserRole(user.getUserId(), 1L);
        userRoleMapper.insert(userRole);

        return user;
    }

    @Override
    public int modifyPassword(Map<String, Object> userMap) {
        // 用户名
        String username = (String) userMap.get("username");

        // 根据用户名和密码查询用户信息
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, username);

        User user = userMapper.selectOne(queryWrapper);

        // 判断输入的用户名或密码是否正确
        if (user == null) {
            throw new MovieException("用户名或密码错误");
        }

        // 输入的明文原密码
        String oldPassword = (String) userMap.get("password");
        if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
            throw new MovieException("用户名或密码错误");
        }
        // 输入的密文新密码
        String newPassword = passwordEncoder.encode((String) userMap.get("newPassword"));

        // 修改密码
        LambdaUpdateWrapper<User> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(User::getUsername, username).set(User::getPassword, newPassword);
        return userMapper.update(null, updateWrapper);
    }

}
