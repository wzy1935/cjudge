package com.wzebra.cjudge.service;

import com.wzebra.cjudge.algorithm.judge.AutoJudge;
import com.wzebra.cjudge.entity.Result;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LocalService {

    public Result localJudge(String codeStandard, String codeTest, String generator, int index) {
        AutoJudge autoJudge = new AutoJudge(codeStandard,codeTest,generator);
        autoJudge.judge();
        Result result = new Result();
        result.setIndex(index);
        result.setState(autoJudge.getJudgeResult());
        result.setTestCase(autoJudge.getInput());
        result.setOutputStandard(autoJudge.getStandardRunner().getOutput());
        result.setOutputTest(autoJudge.getTestRunner().getOutput());
        result.setErrorMessage(autoJudge.getTestRunner().getErrorMessage());
        result.setTimeCost((int) autoJudge.getTestRunner().getTimeCost());
        return result;
    }

    public List<Result> groupLocalJudge(String codeStandard, String codeTest, String generator, int page, int size) {
        List<Result> results = new ArrayList<>(size);
        for (int i = page * size + 1; i <= (page+1) * size; i++) {
            results.add(localJudge(codeStandard,codeTest,generator,i));
        }
        return results;
    }

}
