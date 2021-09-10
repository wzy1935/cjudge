package com.wzebra.cjudge.repository;

import com.wzebra.cjudge.entity.comment.Comment;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CommentRepository {

    @Insert("insert into problems_comments (problem_id, user_id, replying_id, content) " +
            "VALUES (#{problemId},#{comment.user.id},null,#{comment.content});")
    public int addComment(int problemId, Comment comment);

    @Delete("delete from problems_comments where id = #{id};")
    public int removeCommentById(int id);

    @Update("update problems_comments set (user_id, content)" +
            " = (#{user.id},#{content}) where id = #{id};")
    public int updateComment(Comment comment);

    @Select("select id, problem_id, user_id, content from problems_comments where id = #{id};")
    @Results({
            @Result(property = "id", column = "id", id = true),
            @Result(property = "user", column = "user_id", one = @One(select = "com.wzebra.cjudge.repository.UserRepository.getUserById")),
            @Result(property = "content", column = "content")
    })
    public Comment getCommentById(int id);

    @Select("select id, problem_id, user_id, content from problems_comments where problem_id = #{problemId} limit #{size} offset #{size}*(#{page}-1);")
    @Results({
            @Result(property = "id", column = "id", id = true),
            @Result(property = "user", column = "user_id", one = @One(select = "com.wzebra.cjudge.repository.UserRepository.getUserById")),
            @Result(property = "content", column = "content")
    })
    public List<Comment> getCommentsOfProblem(int problemId, int size, int page);

    @Select("select count(*) from problems_comments where problem_id = #{problemId};")
    public int getCommentsOfProblemAmount(int problemId);

    @Select("select count(*) from problems_comments where user_id = #{userId};")
    public int getCommentsFromUserAmount(int userId);

    @Select("select id, problem_id, user_id, content from problems_comments where user_id = #{userId} limit #{size} offset #{size}*(#{page}-1);")
    @Results({
            @Result(property = "id", column = "id", id = true),
            @Result(property = "user", column = "user_id", one = @One(select = "com.wzebra.cjudge.repository.UserRepository.getUserById")),
            @Result(property = "content", column = "content")
    })
    public List<Comment> getCommentsFromUser(int userId, int size, int page);

    @Select("select problem_id from problems_comments where id = #{commentId};")
    public int getProblemIdByCommentId(int commentId);



}
