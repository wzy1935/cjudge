package com.wzebra.cjudge.controller;

import com.wzebra.cjudge.algorithm.judge.JudgeResult;
import com.wzebra.cjudge.entity.Result;
import com.wzebra.cjudge.entity.WebData;
import com.wzebra.cjudge.entity.answer.Answer;
import com.wzebra.cjudge.repository.AnswerRepository;
import com.wzebra.cjudge.service.JudgeService;
import com.wzebra.cjudge.service.LocalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class JudgeController {
    @Autowired
    JudgeService judgeService;

    @PostMapping("/api/problem/judge")
    public Result judge(@RequestBody Map<String,Object> param) {
        int answerId = (int) param.get("answerId");
        String generator = (String) param.get("generator");
        String test = (String) param.get("test");
        int index = (int) param.get("index");
        return judgeService.judge(answerId, generator, test, index);

    }

    @GetMapping("/api/problem/getGenerator")
    public WebData getGenerator(int problemId) {
        return judgeService.getGenerator(problemId);
    }

}
