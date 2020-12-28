package com.railwaycrossing.config.shiro;

import com.alibaba.fastjson.JSONObject;
import com.railwaycrossing.dao.UserDao;
import com.railwaycrossing.utils.JWTUtil;
import jdk.nashorn.internal.scripts.JO;
import org.apache.log4j.Logger;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.context.annotation.Lazy;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;


public class UserRealm extends AuthorizingRealm {

    @Resource
    private UserDao userDao;

    private static Logger logger = Logger.getLogger(UserRealm.class);

    /**
     * Permission Verify：授权
     * @param principal
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {

//        System.out.println("------------- Permission Verify ------------");

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        info.addRole("admin");
        info.addStringPermission("query");
        info.addStringPermission("delete");
        info.addStringPermission("update");
        info.addStringPermission("insert");
        return info;
    }


    /**
     * 必须重写
     * @param token
     * @return
     */
    @Override
    public boolean supports(AuthenticationToken token) {

        return token instanceof JWTToken;

    }


    /**
     * Identity Verify: 身份认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
            throws AuthenticationException {

        // 加这一步的目的是在Post请求的时候会先进认证，然后在到请求
        if (authenticationToken.getPrincipal() == null) {
            return null;
        }
//        logger.info("-------------- Identity Verify ----------------");

        String token = (String) authenticationToken.getCredentials();

//        logger.info("token: " + token);

        String userAccount = JWTUtil.getUserAccount(token);


        if (userAccount == null || !JWTUtil.verify(token, userAccount)) {

            throw new AuthenticationException("verify token failed!");

        }

        String password = userDao.getUserByUserAccount(userAccount).getPassword();

        if (password == null) {

            throw new AuthenticationException("user is not exist!");

        }

        // 5.返回身份处理对象
        return new SimpleAuthenticationInfo(token, token, "SunRealm");

    }

}

