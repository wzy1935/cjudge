package com.wzebra.cjudge.repository;

import com.wzebra.cjudge.entity.answer.Answer;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface AnswerRepository {

    @Insert("insert into problems_answer (problem_id, user_id, content)" +
            " values (#{problemId},#{answer.user.id},#{answer.content});")
    public int addAnswer(int problemId, Answer answer);

    @Delete("delete from problems_answer where id = #{id};")
    public int removeAnswerById(int id);

    @Update("update problems_answer set (problem_id, user_id, likes, dislikes, content) = " +
            "(#{problemId},#{user.id},#{likes},#{dislikes},#{content}) where id = #{id};")
    public int updateAnswer(Answer answer);

    @Select("select * from problems_answer where id = #{id};")
    @Results({
            @Result(property = "id", column = "id", id = true),
            @Result(column = "id", property = "likes", one = @One(select = "com.wzebra.cjudge.repository.AnswerRepository.getAnswerLike")),
            @Result(column = "id", property = "dislikes", one = @One(select = "com.wzebra.cjudge.repository.AnswerRepository.getAnswerDislike")),
            @Result(property = "user", column = "user_id", one = @One(select = "com.wzebra.cjudge.repository.UserRepository.getUserById")),
            @Result(property = "content", column = "content")
    })
    public Answer getAnswerById(int id);

    @Select("select * from problems_answer where problem_id = #{problemId} limit #{size} offset #{size}*(#{page}-1);")
    @Results({
            @Result(property = "id", column = "id", id = true),
            @Result(column = "id", property = "likes", one = @One(select = "com.wzebra.cjudge.repository.AnswerRepository.getAnswerLike")),
            @Result(column = "id", property = "dislikes", one = @One(select = "com.wzebra.cjudge.repository.AnswerRepository.getAnswerDislike")),
            @Result(property = "user", column = "user_id", one = @One(select = "com.wzebra.cjudge.repository.UserRepository.getUserById")),
            @Result(property = "content", column = "content")
    })
    public List<Answer> getAnswersOfProblem(int problemId, int size, int page);

    @Select("select * from problems_answer where user_id = #{userId} limit #{size} offset #{size}*(#{page}-1);")
    @Results({
            @Result(property = "id", column = "id", id = true),
            @Result(column = "id", property = "likes", one = @One(select = "com.wzebra.cjudge.repository.AnswerRepository.getAnswerLike")),
            @Result(column = "id", property = "dislikes", one = @One(select = "com.wzebra.cjudge.repository.AnswerRepository.getAnswerDislike")),
            @Result(property = "user", column = "user_id", one = @One(select = "com.wzebra.cjudge.repository.UserRepository.getUserById")),
            @Result(property = "content", column = "content")
    })
    public List<Answer> getAnswersFromUser(int userId, int size, int page);

    @Select("select count(*) from problems_answer where problem_id = #{problemId};")
    public int getAnswersOfProblemAmount(int problemId);

    @Select("select count(*) from problems_answer where user_id = #{userId};")
    public int getAnswersFromUserAmount(int userId);

    @Select("select problem_id from problem_answer where id = #{answerId}")
    public int getProblemIdByAnswerId(int answerId);

    @Select("select count(*) from answer_likes where is_like = true and answer_id = #{answerId};")
    public int getAnswerLike(int answerId);

    @Select("select count(*) from answer_likes where is_like = false and answer_id = #{answerId};")
    public int getAnswerDislike(int answerId);

    @Insert("insert into answer_likes (answer_id, user_id, is_like) VALUES (#{answerId},#{userId},#{isLike});")
    public int setLike(int answerId, int userId, boolean isLike);

    @Delete("delete from answer_likes where answer_id = #{answerId} and user_id = #{userId};")
    public int cancelLike(int answerId, int userId);

    @Select("select count(*) > 0 from answer_likes where is_like = #{isLike} and answer_id = #{answerId} and user_id = #{userId};")
    public boolean isLike(int answerId, int userId, boolean isLike);

}
