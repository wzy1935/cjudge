package com.wzebra.cjudge.entity.comment;

import com.wzebra.cjudge.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentShow {
    int id;
    User user;
    String content;
    boolean deletable;
}
