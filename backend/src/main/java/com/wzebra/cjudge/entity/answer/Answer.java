package com.wzebra.cjudge.entity.answer;

import com.wzebra.cjudge.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Answer {
    int id;
    int likes;
    int dislikes;
    User user;
    String content;

    public AnswerShow toAnswerShow() {
        return new AnswerShow(id,likes,dislikes,user);
    }
}
