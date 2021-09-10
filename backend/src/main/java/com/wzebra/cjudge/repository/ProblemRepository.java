package com.wzebra.cjudge.repository;

import com.wzebra.cjudge.entity.problem.Problem;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ProblemRepository {

    @Insert("insert into problems (title,promoter_id,description,default_generator) " +
            "values (#{title},#{promoter.id},#{description},#{defaultGenerator})")
    @Options(useGeneratedKeys=true)
    public int addProblem(Problem problem);

    @Delete("delete from problems where id = #{id}")
    public int removeProblemById(int id);

    @Update("update problems set (title,promoter_id,description,likes,dislikes,default_generator) = " +
            "(#{title},#{promoter.id},#{description},#{likes},#{dislikes},#{defaultGenerator}) where id = #{id};")
    public int updateProblem(Problem problem);

    //WARNING: using this method would get empty test case, answer, and comment.
    @Select("select * from problems where id = #{id};")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "title", property = "title"),
            @Result(column = "promoter_id", property = "promoter", one = @One(select = "com.wzebra.cjudge.repository.UserRepository.getUserById")),
            @Result(column = "description", property = "description"),
            @Result(column = "id", property = "likes", one = @One(select = "com.wzebra.cjudge.repository.ProblemRepository.getProblemLike")),
            @Result(column = "id", property = "dislikes", one = @One(select = "com.wzebra.cjudge.repository.ProblemRepository.getProblemDislike")),
            @Result(column = "default_generator", property = "defaultGenerator")
    })
    public Problem getProblemById(int id);

    //page start from 1
    @Select("select * from problems limit #{size} offset #{size}*(#{page}-1);")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "title", property = "title"),
            @Result(column = "promoter_id", property = "promoter", one = @One(select = "com.wzebra.cjudge.repository.UserRepository.getUserById")),
            @Result(column = "description", property = "description"),
            @Result(column = "id", property = "likes", one = @One(select = "com.wzebra.cjudge.repository.ProblemRepository.getProblemLike")),
            @Result(column = "id", property = "dislikes", one = @One(select = "com.wzebra.cjudge.repository.ProblemRepository.getProblemDislike")),
            @Result(column = "default_generator", property = "defaultGenerator")
    })
    public List<Problem> getProblems(int size, int page);


    @Select("select * from problems where title like ('%${str}%') limit #{size} offset #{size}*(#{page}-1);")
    public List<Problem> searchProblems(int size, int page, String str);

    @Select("select * from problems where promoter_id = #{userId} limit #{size} offset #{size}*(#{page}-1);")
    public List<Problem> userProblems(int size, int page, int userId);

    @Select("select count(*) from problems;")
    public int getProblemsAmount();

    @Select("select count(*) from problem_likes where is_like = true and problem_id = #{problemId};")
    public int getProblemLike(int problemId);

    @Select("select count(*) from problem_likes where is_like = false and problem_id = #{problemId};")
    public int getProblemDislike(int problemId);

    @Insert("insert into problem_likes (problem_id, user_id, is_like) VALUES (#{problemId},#{userId},#{isLike});")
    public int setLike(int problemId, int userId, boolean isLike);

    @Delete("delete from problem_likes where problem_id = #{problemId} and user_id = #{userId};")
    public int cancelLike(int problemId, int userId);

    @Select("select count(*) > 0 from problem_likes where is_like = #{isLike} and problem_id = #{problemId} and user_id = #{userId};")
    public boolean isLike(int problemId, int userId, boolean isLike);


}
