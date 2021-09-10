package com.wzebra.cjudge.controller;

import com.wzebra.cjudge.entity.Pager;
import com.wzebra.cjudge.entity.WebData;
import com.wzebra.cjudge.entity.problem.ProblemForm;
import com.wzebra.cjudge.entity.problem.ProblemShow;
import com.wzebra.cjudge.service.ProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class RepositoryController {
    @Autowired
    ProblemService problemService;

    @GetMapping("/api/problem/getAll")
    public Pager<ProblemShow> getProblemShow(int size, int page) {
        return problemService.getProblemShow(size, page);
    }

    @PostMapping("/api/problem/add")
    public WebData addProblem(@RequestBody ProblemForm problemForm) {
        return problemService.addProblem(problemForm);
    }

}
