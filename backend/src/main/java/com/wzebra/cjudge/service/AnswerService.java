package com.wzebra.cjudge.service;

import com.wzebra.cjudge.algorithm.base.MainRunner;
import com.wzebra.cjudge.algorithm.judge.JudgeResult;
import com.wzebra.cjudge.algorithm.judge.SingleJudge;
import com.wzebra.cjudge.entity.Pager;
import com.wzebra.cjudge.entity.answer.Answer;
import com.wzebra.cjudge.entity.TestCase;
import com.wzebra.cjudge.entity.WebData;
import com.wzebra.cjudge.entity.answer.AnswerShow;
import com.wzebra.cjudge.entity.user.User;
import com.wzebra.cjudge.repository.AnswerRepository;
import com.wzebra.cjudge.repository.TestCaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AnswerService {
    @Autowired TestCaseRepository testCaseRepository;
    @Autowired AnswerRepository answerRepository;

    public WebData addAnswer(int problemId, User user, String content) {
        Answer answer = new Answer(-1,0,0,user,content);
        List<TestCase> testCases = testCaseRepository.getTestCaseOfProblem(problemId, 10, 1);
        boolean isValid = true;
        for (TestCase tc : testCases) {
            SingleJudge singleJudge = new SingleJudge(answer.getContent(), tc.getInput(), tc.getOutput());
            singleJudge.judge();
            if (singleJudge.getJudgeResult() != JudgeResult.AC) {
                isValid = false;
                break;
            }
        }
        if (isValid) {
            answerRepository.addAnswer(problemId, answer);
            return WebData.resultMessageInstance(true, "添加成功！");
        } else {
            return WebData.resultMessageInstance(false, "未通过样例测试，添加失败！");
        }
    }

    public Pager<AnswerShow> getAnswersOfProblem(int problemId, int size, int page) {
        List<Answer> answers = answerRepository.getAnswersOfProblem(problemId, size, page);
        List<AnswerShow> answerShows = new ArrayList<>();
        for (Answer a : answers) {
            answerShows.add(a.toAnswerShow());
        }
        Pager<AnswerShow> output = new Pager<>();
        output.setPage(page);
        output.setSize(size);
        output.setRows(answerShows);
        output.setTotal(answerRepository.getAnswersOfProblemAmount(problemId));
        return output;
    }

    public WebData getAnswerOfProblemAmount(int problemId) {
        return WebData.newInstance().put("amount",answerRepository.getAnswersOfProblemAmount(problemId));
    }

    public WebData testAnswer(int answerId, String input) {
        Answer answer = answerRepository.getAnswerById(answerId);
        MainRunner mainRunner = new MainRunner(answer.getContent(), input);
        MainRunner.runWithTimeLimit(mainRunner, 2000);
        String output = "";
        if (mainRunner.getErrorMessage() == null || mainRunner.getErrorMessage().equals("")) {
            output = mainRunner.getOutput();
        } else {
            output = mainRunner.getErrorMessage();
        }
        return WebData.newInstance().put("output", output);
    }

    public WebData voteAnswer(int answerId, int userId, int vote) {
        boolean isLike = answerRepository.isLike(answerId,userId,true);
        boolean isDislike = answerRepository.isLike(answerId,userId,false);
        answerRepository.cancelLike(answerId,userId);
        if (vote != 0) answerRepository.setLike(answerId,userId,vote > 0);
        return WebData.newInstance()
                .put("likes",answerRepository.getAnswerLike(answerId))
                .put("dislikes",answerRepository.getAnswerDislike(answerId))
                .put("vote",vote);
    }

    public WebData getVoteOfAnswer(int answerId, int userId) {
        boolean isLike = answerRepository.isLike(answerId,userId,true);
        boolean isDislike = answerRepository.isLike(answerId,userId,false);
        int vote = isLike ? 1 : (isDislike ? -1 : 0);
        return WebData.newInstance()
                .put("likes",answerRepository.getAnswerLike(answerId))
                .put("dislikes",answerRepository.getAnswerDislike(answerId))
                .put("vote",vote);
    }


}
