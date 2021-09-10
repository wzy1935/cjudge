package com.wzebra.cjudge.entity.problem;

import com.wzebra.cjudge.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProblemShow {
    int id;
    String title;
    User promoter;
    String description;
    int likes;
    int dislikes;
}
