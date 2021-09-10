package com.wzebra.cjudge.controller;

import com.wzebra.cjudge.entity.Pager;
import com.wzebra.cjudge.entity.WebData;
import com.wzebra.cjudge.entity.answer.Answer;
import com.wzebra.cjudge.entity.answer.AnswerShow;
import com.wzebra.cjudge.entity.user.User;
import com.wzebra.cjudge.service.AnswerService;
import com.wzebra.cjudge.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class AnswerController {
    @Autowired
    AnswerService answerService;
    @Autowired
    AuthService authService;

    @PostMapping("/api/problem/addAnswer")
    public WebData addAnswer(@RequestBody Map<String,Object> param) {
        int problemId = (int) param.get("problemId");
        String content = (String) param.get("content");
        return answerService.addAnswer(problemId, authService.getUserFromCookies(), content);
    }

    @GetMapping("/api/problem/getAnswer")
    public Pager<AnswerShow> getAnswer(int problemId, int size, int page) {
        return answerService.getAnswersOfProblem(problemId, size, page);
    }

    @GetMapping("/api/problem/getAnswerAmount")
    public WebData getAnswerOfProblemAmount(int problemId) {
        return answerService.getAnswerOfProblemAmount(problemId);
    }

    @PostMapping("/api/problem/testAnswer")
    public WebData testAnswer(@RequestBody Map<String,Object> param) {
        int answerId = (int) param.get("answerId");
        String input = (String) param.get("input");
        return answerService.testAnswer(answerId, input);

    }

    @PostMapping("/api/answer/vote")
    public WebData voteProblem(@RequestBody Map<String,Object> param) {
        int answerId = (int) param.get("answerId");
        int vote = (int) param.get("vote");
        int userId = authService.getUserIdFromCookies();
        return answerService.voteAnswer(answerId, userId, vote);
    }

    @GetMapping("/api/answer/vote/get")
    public WebData getVoteProblem(int answerId) {
        int userId = authService.getUserIdFromCookies();
        return answerService.getVoteOfAnswer(answerId, userId);
    }

}
