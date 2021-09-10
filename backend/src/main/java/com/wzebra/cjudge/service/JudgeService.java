package com.wzebra.cjudge.service;

import com.wzebra.cjudge.entity.Result;
import com.wzebra.cjudge.entity.WebData;
import com.wzebra.cjudge.entity.answer.Answer;
import com.wzebra.cjudge.entity.problem.Problem;
import com.wzebra.cjudge.repository.AnswerRepository;
import com.wzebra.cjudge.repository.ProblemRepository;
import com.wzebra.cjudge.repository.TestCaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JudgeService {
    @Autowired
    LocalService localService;
    @Autowired
    AnswerRepository answerRepository;
    @Autowired
    ProblemRepository problemRepository;

    public Result judge(int answerId, String generator, String test, int index) {
        Answer answer = answerRepository.getAnswerById(answerId);
        return localService.localJudge(answer == null ? "" : answer.getContent(), test, generator, index);
    }

    public WebData getGenerator(int problemId) {
        Problem problem = problemRepository.getProblemById(problemId);
        String generator = problem.getDefaultGenerator();
        return WebData.newInstance().put("generator", generator);
    }

}
