package com.wzebra.cjudge.algorithm.judge;

import com.wzebra.cjudge.algorithm.base.MainRunner;
import com.wzebra.cjudge.algorithm.base.TextComparator;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class NormalJudge implements Judge {
    String codeStandard = null;
    String codeTest = null;
    String input = null;

    //settings
    int timeLimit = 2000;

    //results
    JudgeResult judgeResult = JudgeResult.WI;
    MainRunner standardRunner;
    MainRunner testRunner;

    public NormalJudge(String codeStandard, String codeTest, String input) {
        this.codeStandard = codeStandard;
        this.codeTest = codeTest;
        this.input = input;
    }

    @Override
    public JudgeResult judge() {
        standardRunner = new MainRunner(codeStandard,input);
        testRunner = new MainRunner(codeTest,input);
        MainRunner.runWithTimeLimit(standardRunner, timeLimit*2);
        MainRunner.runWithTimeLimit(testRunner, timeLimit);

        if (standardRunner.getErrorMessage() != null || standardRunner.getTimeCost() >= timeLimit) {
            judgeResult = JudgeResult.SE;
            return judgeResult;
        }

        if (testRunner.getErrorMessage() != null) {
            judgeResult = testRunner.isCompileError() ? JudgeResult.CE : JudgeResult.RE;
            return judgeResult;
        }

        if (testRunner.getTimeCost() >= timeLimit) {
            judgeResult = JudgeResult.TLE;
            return judgeResult;
        }

        TextComparator textComparator = new TextComparator(standardRunner.getOutput(),testRunner.getOutput());
        boolean isSame = textComparator.compare();
        if (!isSame) {
            judgeResult = JudgeResult.WA;
            return judgeResult;
        }


        judgeResult = JudgeResult.AC;
        return judgeResult;
    }


}
