package com.wzebra.cjudge.entity.problem;

import com.wzebra.cjudge.entity.answer.Answer;
import com.wzebra.cjudge.entity.comment.Comment;
import com.wzebra.cjudge.entity.TestCase;
import com.wzebra.cjudge.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Problem {
    int id;
    String title;
    User promoter;
    String description;
    int likes;
    int dislikes;
    List<TestCase> testCases;
    String defaultGenerator;
    List<Comment> comments;
    List<Answer> answers;

    public ProblemShow toProblemShow() {
        return new ProblemShow(id,title,promoter,description,likes,dislikes);
    }
}
