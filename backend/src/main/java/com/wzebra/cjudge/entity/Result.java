package com.wzebra.cjudge.entity;

import com.wzebra.cjudge.algorithm.judge.JudgeResult;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Result {
    int index;
    JudgeResult state;
    String testCase;
    String outputStandard;
    String outputTest;
    String errorMessage;
    int timeCost;
}
