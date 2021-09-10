package com.wzebra.cjudge.repository;

import com.wzebra.cjudge.entity.TestCase;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface TestCaseRepository {

    @Insert("insert into problems_testcase (problem_id, input, output) " +
            "VALUES (#{problemId},#{testCase.input},#{testCase.output});")
    public int addTestCase(int problemId, TestCase testCase);

    @Delete("delete from problems_testcase where id = #{id};")
    public int removeTestCaseById(int id);

    @Update("update problems_testcase set (input, output)" +
            " = (#{input},#{output}) where id = #{id};")
    public int updateTestCase(TestCase testCase);

    @Select("select id, problem_id, input, output from problems_testcase where id = #{id};")
    @Results({
            @Result(property = "id", column = "id", id = true),
            @Result(property = "input", column = "input"),
            @Result(property = "output", column = "output")
    })
    public TestCase getTestCaseById(int id);

    @Select("select id, problem_id, input, output from problems_testcase where problem_id = #{problemId} limit #{size} offset #{size}*(#{page}-1);")
    public List<TestCase> getTestCaseOfProblem(int problemId, int size, int page);

    @Select("select problem_id from problems_testcase where id = ?;")
    public int getProblemIdByTestCaseId(int commentId);

}
