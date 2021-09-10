package com.wzebra.cjudge.service;

import com.wzebra.cjudge.algorithm.base.MainRunner;
import com.wzebra.cjudge.algorithm.judge.JudgeResult;
import com.wzebra.cjudge.algorithm.judge.SingleJudge;
import com.wzebra.cjudge.entity.*;
import com.wzebra.cjudge.entity.problem.Problem;
import com.wzebra.cjudge.entity.problem.ProblemForm;
import com.wzebra.cjudge.entity.problem.ProblemShow;
import com.wzebra.cjudge.entity.user.User;
import com.wzebra.cjudge.repository.AnswerRepository;
import com.wzebra.cjudge.repository.CommentRepository;
import com.wzebra.cjudge.repository.ProblemRepository;
import com.wzebra.cjudge.repository.TestCaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProblemService {
    @Autowired ProblemRepository problemRepository;
    @Autowired AuthService authService;
    @Autowired TestCaseRepository testCaseRepository;
    @Autowired AnswerRepository answerRepository;

    public WebData addProblem(ProblemForm problemForm) {
        //form a problem
        Problem problem = new Problem(-1,
                problemForm.getTitle(),
                authService.getUserFromCookies(),
                problemForm.getDescription(),
                0,0,null,
                problemForm.getDefaultGenerator(),
                null,null
                );

        //make sure generator is legal
        boolean toLong = false;
        boolean error = false;
        for (int i = 0; i < 3; i++) {
            MainRunner mainRunner = new MainRunner(problem.getDefaultGenerator(), "");
            MainRunner.runWithTimeLimit(mainRunner,1000);
            toLong = toLong || mainRunner.getTimeCost() >= 1000;
            error = error || mainRunner.getErrorMessage() != null;
        }

        //return
        if (toLong) return WebData.resultMessageInstance(false,"样例生成时间过长");
        if (error) return WebData.resultMessageInstance(false,"样例生成文件错误");
        {//add problem
            problemRepository.addProblem(problem);
            int problemId = problem.getId();
            for (TestCase tc : problemForm.getTestCases()) {
                testCaseRepository.addTestCase(problemId, tc);
            }
        }
        return WebData.resultMessageInstance(true,"新建问题成功");

    }

    public Pager<ProblemShow> getProblemShow(int size, int page) {
        List<Problem> problems = problemRepository.getProblems(size, page);
        Pager<ProblemShow> output = new Pager<>();
        output.setPage(page);
        output.setSize(size);
        output.setTotal(problemRepository.getProblemsAmount());
        List<ProblemShow> problemShows = new ArrayList<>();
        for (Problem p : problems) {
            String tmp = p.getDescription();
            p.setDescription(tmp.length() > 115 ? tmp.substring(0,110) : tmp);
            problemShows.add(p.toProblemShow());
        }
        output.setRows(problemShows);
        return output;
    }

    public Problem getProblemById(int problemId) {
        Problem problem = problemRepository.getProblemById(problemId);
        problem.setTestCases(testCaseRepository.getTestCaseOfProblem(problemId, 10, 1));
        return problem;
    }

    public WebData voteProblem(int problemId, int userId, int vote) {
        boolean isLike = problemRepository.isLike(problemId,userId,true);
        boolean isDislike = problemRepository.isLike(problemId,userId,false);
        problemRepository.cancelLike(problemId,userId);
        if (vote != 0) problemRepository.setLike(problemId,userId,vote > 0);
        return WebData.newInstance()
                .put("likes",problemRepository.getProblemLike(problemId))
                .put("dislikes",problemRepository.getProblemDislike(problemId))
                .put("vote",vote);
    }

    public WebData getVoteOfProblem(int problemId, int userId) {
        boolean isLike = problemRepository.isLike(problemId,userId,true);
        boolean isDislike = problemRepository.isLike(problemId,userId,false);
        int vote = isLike ? 1 : (isDislike ? -1 : 0);
        return WebData.newInstance()
                .put("likes",problemRepository.getProblemLike(problemId))
                .put("dislikes",problemRepository.getProblemDislike(problemId))
                .put("vote",vote);
    }





}
