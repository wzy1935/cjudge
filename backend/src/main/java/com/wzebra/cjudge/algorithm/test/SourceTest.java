package com.wzebra.cjudge.algorithm.test;

import com.wzebra.cjudge.entity.*;
import com.wzebra.cjudge.entity.answer.Answer;
import com.wzebra.cjudge.entity.comment.Comment;
import com.wzebra.cjudge.entity.problem.Problem;
import com.wzebra.cjudge.entity.user.User;
import com.wzebra.cjudge.repository.ProblemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

@Component
public class SourceTest {
    @Autowired
    ProblemRepository problemRepository;

    public static void main(String[] args) throws Exception {
//        String path = "src/main/java/com/wzebra/cjudge/algorithm/test/";
//        String codeStandard = getFileContent(path + "Code1.txt");
//        String codeTest = getFileContent(path + "Code2.txt");
//        String codeTcg = getFileContent(path + "Tcg.txt");
//        String case1 = getFileContent(path + "Case2.txt");
//
//        SourceTest sourceTest = new SourceTest();
//        System.out.println(sourceTest.localService.localJudge(codeStandard,codeTest,codeTcg,1));

        SourceTest sourceTest = new SourceTest();
        Problem problem = new Problem(1,"tt",new User(1,"test"),
                "destt",1,2,new ArrayList<TestCase>(),
                "publiv stt",new ArrayList<Comment>(),new ArrayList<Answer>());
        sourceTest.problemRepository.addProblem(problem);

//        Judge judge = new Judge(codeStandard,codeTest,case1);
//        judge.judge();
//        System.out.println(judge.getJudgeResult());
//        System.out.println(judge.getTestRunner().getErrorMessage());
//        System.out.println(judge.getInput());

//        MainRunner runner1 = new MainRunner(codeTest,null);
//        runner1.runWithTimeLimit(2000);
//        System.out.println(runner1.getTimeCost() + "ms");
//        System.out.println(runner1.getOutput());
//        System.out.println(runner1.getErrorMessage());

    }



    private static String getFileContent(String path) {
        String str="";
        File file=new File(path);
        try {
            FileInputStream in=new FileInputStream(file);
            int size=in.available();
            byte[] buffer=new byte[size];
            in.read(buffer);
            in.close();
            str=new String(buffer);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

}
