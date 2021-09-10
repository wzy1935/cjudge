package com.wzebra.cjudge.entity.problem;

import com.wzebra.cjudge.entity.TestCase;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProblemForm {
    String title;
    String description;
    List<TestCase> testCases;
    String defaultGenerator;

}