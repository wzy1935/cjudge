package com.wzebra.cjudge.controller;

import com.wzebra.cjudge.entity.*;
import com.wzebra.cjudge.entity.problem.Problem;
import com.wzebra.cjudge.entity.user.AuthUser;
import com.wzebra.cjudge.entity.user.User;
import com.wzebra.cjudge.repository.ProblemRepository;
import com.wzebra.cjudge.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class AuthController {
    @Autowired AuthService authService;

    @PostMapping("/api/login")
    public WebData login(@RequestBody AuthUser authUser) {
        return authService.login(authUser);
    }

    @PostMapping("/api/logout")
    public WebData logout() {
        return authService.logout();
    }


    @PostMapping("/api/register")
    public WebData register(@RequestBody AuthUser authUser) {
        return authService.register(authUser);
    }

    @GetMapping("api/getUser/{withCode}")
    public User getUser() {
        return authService.getUserFromCookies();
    }

}
