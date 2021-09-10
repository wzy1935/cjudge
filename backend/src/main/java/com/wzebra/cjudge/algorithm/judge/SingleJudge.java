package com.wzebra.cjudge.algorithm.judge;

import com.wzebra.cjudge.algorithm.base.MainRunner;
import com.wzebra.cjudge.algorithm.base.TextComparator;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SingleJudge implements Judge {
    String code;
    String input;
    String output;

    int timeLimit = 2000;

    MainRunner runner;
    JudgeResult judgeResult = JudgeResult.WI;

    public SingleJudge(String code, String input, String output) {
        this.code = code;
        this.input = input;
        this.output = output;
    }

    @Override
    public JudgeResult judge() {
        runner = new MainRunner(code,input);
        MainRunner.runWithTimeLimit(runner, timeLimit);

        if (runner.getErrorMessage() != null) {
            judgeResult = JudgeResult.RE;
            return judgeResult;
        }

        if (runner.getTimeCost() > timeLimit) {
            judgeResult = JudgeResult.TLE;
            return judgeResult;
        }

        TextComparator textComparator = new TextComparator(runner.getOutput(),output);
        boolean isSame = textComparator.compare();
        if (!isSame) {
            judgeResult = JudgeResult.WA;
            return judgeResult;
        }

        judgeResult = JudgeResult.AC;
        return judgeResult;

    }
}
