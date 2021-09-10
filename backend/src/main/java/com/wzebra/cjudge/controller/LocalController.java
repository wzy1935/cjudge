package com.wzebra.cjudge.controller;

import com.wzebra.cjudge.entity.Result;
import com.wzebra.cjudge.service.LocalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController
public class LocalController {
    @Autowired
    LocalService localService;


    @PostMapping("/api/local/judge")
    public Result localJudge(@RequestBody Map<String,Object> param) {
        return localService.localJudge(
                (String) param.get("codeStandard"),
                (String) param.get("codeTest"),
                (String) param.get("generator"),
                ((Integer) param.get("index"))
        );
    }

}
