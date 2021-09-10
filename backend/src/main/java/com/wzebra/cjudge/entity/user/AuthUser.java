package com.wzebra.cjudge.entity.user;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AuthUser {
    int id;
    String name;
    String password;

    public User toUser() {
        return new User(id,name);
    }

}
