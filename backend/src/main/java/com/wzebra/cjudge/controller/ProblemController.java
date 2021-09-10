package com.wzebra.cjudge.controller;

import com.wzebra.cjudge.entity.Pager;
import com.wzebra.cjudge.entity.WebData;
import com.wzebra.cjudge.entity.comment.Comment;
import com.wzebra.cjudge.entity.comment.CommentShow;
import com.wzebra.cjudge.entity.problem.Problem;
import com.wzebra.cjudge.entity.problem.ProblemShow;
import com.wzebra.cjudge.entity.user.User;
import com.wzebra.cjudge.service.AuthService;
import com.wzebra.cjudge.service.CommentService;
import com.wzebra.cjudge.service.ProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class ProblemController {
    @Autowired
    ProblemService problemService;
    @Autowired
    AuthService authService;
    @Autowired
    CommentService commentService;

    @GetMapping("/api/problem/get")
    public Problem getProblemById(int id) {
        return problemService.getProblemById(id);
    }

    @PostMapping("/api/problem/vote")
    public WebData voteProblem(@RequestBody Map<String,Object> param) {
        int problemId = (int) param.get("problemId");
        int vote = (int) param.get("vote");
        int userId = authService.getUserIdFromCookies();
        return problemService.voteProblem(problemId, userId, vote);
    }

    @GetMapping("/api/problem/vote/get")
    public WebData getVoteProblem(int problemId) {
        int userId = authService.getUserIdFromCookies();
        return problemService.getVoteOfProblem(problemId, userId);
    }

    @GetMapping("/api/problem/comment/get")
    public Pager<CommentShow> getCommentProblem(int problemId, int page, int size) {
//        int problemId = (int) param.get("problemId");
//        int page = (int) param.get("page");
//        int size = (int) param.get("size");
        User user = authService.getUserFromCookies();
        return commentService.getCommentsOfProblem(problemId,size,page,user);
    }

    @PostMapping("/api/problem/comment/add")
    public WebData commentProblem(@RequestBody Map<String,Object> param) {
        int problemId = (int) param.get("problemId");
        String content = (String) param.get("content");
        User user = authService.getUserFromCookies();
        commentService.addComment(problemId,user,content);
        return WebData.resultMessageInstance(true,"评论成功");
    }

    @PostMapping("/api/problem/comment/delete")
    public WebData deleteCommentProblem(@RequestBody Map<String,Object> param) {
        int commentId = (int) param.get("commentId");
        User user = authService.getUserFromCookies();
        commentService.deleteComment(commentId,user);
        return WebData.resultMessageInstance(true,"删除成功");
    }

}
