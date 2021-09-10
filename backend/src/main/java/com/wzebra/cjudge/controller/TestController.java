package com.wzebra.cjudge.controller;

import com.wzebra.cjudge.repository.UserRepository;
import com.wzebra.cjudge.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Autowired UserRepository userRepository;
    @Autowired AuthService authService;

    @RequestMapping(path = "/api/test/{name}")
    public String test(@PathVariable("name") String name) {
        return userRepository.getAuthUserById(authService.getUserIdFromCookies()).getPassword();
    }

}
