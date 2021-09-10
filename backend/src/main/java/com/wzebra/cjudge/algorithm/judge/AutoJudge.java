package com.wzebra.cjudge.algorithm.judge;

import com.wzebra.cjudge.algorithm.base.MainRunner;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AutoJudge extends NormalJudge {
    String codeGenerator = null;

    public AutoJudge(String codeStandard, String codeTest, String codeGenerator) {
        super(codeStandard,codeTest,null);
        this.codeGenerator = codeGenerator;
    }

    @Override
    public JudgeResult judge() {
        MainRunner generatorRunner = new MainRunner(codeGenerator,"");
        MainRunner.run(generatorRunner);
        if (generatorRunner.getErrorMessage() != null) {
            standardRunner = new MainRunner(codeStandard,input);
            testRunner = new MainRunner(codeTest,input);
            judgeResult = JudgeResult.SE;
            return judgeResult;
        }
        input = generatorRunner.getOutput();
        return super.judge();
    }

}
