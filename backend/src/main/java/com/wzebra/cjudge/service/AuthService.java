package com.wzebra.cjudge.service;

import com.wzebra.cjudge.algorithm.encrypt.Encipher;
import com.wzebra.cjudge.algorithm.encrypt.EncryptInfo;
import com.wzebra.cjudge.config.PathConfig;
import com.wzebra.cjudge.entity.user.AuthUser;
import com.wzebra.cjudge.entity.user.User;
import com.wzebra.cjudge.entity.WebData;
import com.wzebra.cjudge.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

@Service
public class AuthService {
    @Autowired UserRepository userRepository;

    //by name
    public WebData login(AuthUser authUser) {
        if (getUserIdFromCookies() > -1) return WebData.resultMessageInstance(false,"请勿重复登陆！");
        if (authUser.getName() == null || authUser.getName().equals("")) return WebData.resultMessageInstance(false,"用户名为空");
        if (authUser.getPassword() == null || authUser.getPassword().equals("")) return WebData.resultMessageInstance(false,"密码为空");

        String passwordNeeded = userRepository.getPasswordByUserName(authUser.getName());
        if (passwordNeeded == null) return WebData.resultMessageInstance(false,"用户不存在");
        if (passwordNeeded.equals(authUser.getPassword())) {
            HttpServletResponse response =
                    ((ServletRequestAttributes) Objects.requireNonNull(
                            RequestContextHolder.getRequestAttributes()
                    )).getResponse();
            assert response != null;
            Cookie cookie = new Cookie("token", Encipher.encrypt(new EncryptInfo(refreshAuthUser(authUser).getId())));
            cookie.setPath(PathConfig.path);
            response.addCookie(cookie);
            return WebData.resultMessageInstance(true,"登陆成功");
        } else {
            return WebData.resultMessageInstance(false,"用户名或密码错误");
        }
    }

    public WebData logout() {
        int id = getUserIdFromCookies();
        if (id == -1) return WebData.resultMessageInstance(false,"您已登出过");

        HttpServletResponse response =
                ((ServletRequestAttributes) Objects.requireNonNull(
                        RequestContextHolder.getRequestAttributes()
                )).getResponse();
        assert response != null;
        Cookie cookie = new Cookie("token", Encipher.encrypt(new EncryptInfo(-1,0)));
        cookie.setPath(PathConfig.path);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return WebData.resultMessageInstance(true,"登出成功");
    }

    //it is used when the information have name but no id
    private AuthUser refreshAuthUser(AuthUser authUser) {
        return userRepository.refreshUserId(authUser);
    }

    public User getUserFromCookies() {
        AuthUser authUser = userRepository.getAuthUserById(getUserIdFromCookies());
        if (authUser == null) return new User(-1,"");
        return authUser.toUser();
    }

    public WebData register(AuthUser authUser) {
        if (authUser.getName() == null || authUser.getPassword() == null
        || authUser.getName().equals("") || authUser.getPassword().equals(""))
            return WebData.resultMessageInstance(false,"用户名或密码为空");
        try {
            userRepository.addUser(authUser);
        } catch (Exception e) {
            return WebData.resultMessageInstance(false,"用户名已存在");
        }
        return WebData.resultMessageInstance(true,"注册成功");
    }

    public int getUserIdFromCookies() {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        Cookie[] cookies = request.getCookies();
        EncryptInfo encryptInfo = Encipher.getFromCookie(cookies);
        int id = encryptInfo.getUserId();

        //update token
        HttpServletResponse response =
                ((ServletRequestAttributes) Objects.requireNonNull(
                        RequestContextHolder.getRequestAttributes()
                )).getResponse();
        assert response != null;
        Cookie cookie = new Cookie("token", Encipher.encrypt(new EncryptInfo(id)));
        cookie.setPath(PathConfig.path);
        response.addCookie(cookie);

        return id;
    }


}
