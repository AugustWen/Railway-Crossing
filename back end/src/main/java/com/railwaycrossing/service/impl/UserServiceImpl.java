package com.railwaycrossing.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.railwaycrossing.dao.UserDao;
import com.railwaycrossing.entity.User;
import com.railwaycrossing.exception.UpdateException;
import com.railwaycrossing.service.UserService;
import com.railwaycrossing.utils.JSONUtil;
import com.railwaycrossing.utils.JWTUtil;
import com.railwaycrossing.utils.MD5Utils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {
    @Override
    public JSONObject login(JSONObject message) {
        String userAccount = message.getString("userAccount");
        String password = message.getString("password");

        User user = baseMapper.getUserByUserAccount(userAccount);

        String realPassword = user.getPassword();
        String salt = user.getSalt();

        String md5Password = MD5Utils.getMd5Password(password, salt);

        JSONObject msg = null;

        if (!md5Password.equals(realPassword)) {

            msg = JSONUtil.errorJSON("incorrect");

        } else {

            String token = JWTUtil.createToken(userAccount);

            msg = JSONUtil.successJSON(token);

            updateLoginTime(userAccount);

        }

        return msg;

    }

    @Override
    public JSONObject logout(JSONObject message) {

        JSONObject msg = null;

        if (message.get("message").equals("logout")) {

            Subject currentUser = SecurityUtils.getSubject();

            // 注销
            currentUser.logout();


            msg = JSONUtil.successJSON();

            updateLoginLength(message.getString("userAccount"));
        }

        return msg;

    }


    @Override
    public JSONObject notLogin() {

        return JSONUtil.errorJSON("you haven't login.");

    }

    @Override
    public JSONObject updatePassword(JSONObject message) {

        String userAccount = message.getString("userAccount");

        User user = baseMapper.selectOne(new QueryWrapper<User>().eq("userAccount", userAccount));

        //获取旧密码
        String oldPassword = message.getString("oldPassword");

        // 取出新密码
        String newPassword = message.getString("newPassword");

        // 取出盐值，用于后面的判断
        String salt = user.getSalt();

        // 对输入的原始密码进行加密，再与数据库中的进行匹配
        String md5Password = MD5Utils.getMd5Password(oldPassword, salt);

        // 判断密码是否与数据库中匹配
        if(md5Password.equals(user.getPassword())) {

            // 对新的密码进行加密
            String newMd5Password = MD5Utils.getMd5Password(newPassword, salt);

            user.setPassword(newMd5Password);

            Integer rows = baseMapper.update(user, new QueryWrapper<User>().eq("userAccount", userAccount));

            // 修改失败
            if (rows != 1) {

                return JSONUtil.errorJSON("修改密码时发生未知错误! 修改密码失败! ");

            } else {    // 修改成功！

                return JSONUtil.successJSON();

            }

        } else {    // 原密码输入错误

            return JSONUtil.errorJSON("原密码输入错误！");

        }

    }


    @Override
    public JSONObject unauthorized(String message) {

        return JSONUtil.errorJSON("authority verify failed!");

    }

    @Override
    public JSONObject register(JSONObject message) throws DuplicateKeyException {

        User user = baseMapper.getUserByUserAccount(message.getString("userAccount"));

        // 获取前端界面输入的密码作为原始密码
        String userAccount = message.getString("userAccount");

        String srcPassword = message.getString("password");

        if (user == null) {

            if (srcPassword == null || userAccount == null) {

                return JSONUtil.errorJSON("用户名或密码不能为空！");

            } else {    // 用户名和密码皆不为空
                //保存新用户
                User newUser = new User();
                newUser.setUserAccount(userAccount);
                // 处理密码加密
                // 1. 根据UUID生成盐值，这里的盐值统一大写
                String salt = UUID.randomUUID().toString().toUpperCase();
                newUser.setSalt(salt);

                // 2. 用MD5对密码进行加密, 获得新密码
                String md5Password = MD5Utils.getMd5Password(srcPassword, salt);
                newUser.setPassword(md5Password);

                baseMapper.register(newUser);

                return JSONUtil.successJSON();

            }

        } else {

            return JSONUtil.errorJSON("注册用户失败! 用户名 \'" + message.getString("userAccount") + "\' 已存在!");

        }

    }

    @Transactional(rollbackFor=UpdateException.class)
    @Override
    public JSONObject updateUser(JSONObject message) throws UpdateException {

        User user = new User();
        String userAccount = message.getString("userAccount");
        if (message.containsKey("lastLoginTime")) {
            user.setLastLoginTime(message.getDate("lastLoginTime"));
        }
        if (message.containsKey("lastLoginLength")) {
            user.setLastLoginLength(message.getInteger("lastLoginLength"));
        }

        int result = baseMapper.update(user, new QueryWrapper<User>().eq("userAccount", userAccount));

        if (result != 1) {
            throw new UpdateException();
        } else {
            return JSONUtil.successJSON();
        }

    }

    @Override
    public void updateLoginTime(String userAccount) {
        User user = new User();
        user.setLastLoginTime(new Date());
        baseMapper.update(user, new UpdateWrapper<User>().eq("userAccount",userAccount));
    }

    @Override
    public void updateLoginLength(String userAccount) {
        User user = baseMapper.getUserByUserAccount(userAccount);
        Date logoutTime = new Date();
        user.setLastLoginLength((int)(logoutTime.getTime() - user.getLastLoginTime().getTime()) / 1000);
        baseMapper.update(user, new UpdateWrapper<User>().eq("userAccount",userAccount));
    }

}
