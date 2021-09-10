package com.wzebra.cjudge.repository;

import com.wzebra.cjudge.entity.user.AuthUser;
import com.wzebra.cjudge.entity.user.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserRepository {

    @Select("select * from users where id = #{id}")
    public AuthUser getAuthUserById(int id);

    @Select("select id,name from users where id = #{id}")
    @Results({
            @Result(column = "id", property = "id", id = true),
            @Result(column = "name", property = "name")
    })
    public User getUserById(int id);

    @Select("select * from users where name = #{name}")
    public AuthUser getAuthUserByName(String name);

    @Insert("insert into users (name, password) values (#{name},#{password})")
    public int addUser(AuthUser authUser);

    @Delete("delete from users where id = #{id}")
    public int deleteUserById(int id);

    @Select("select password from users where name = #{name}")
    public String getPasswordByUserName(String name);

    @Select("select * from users where name = #{name}")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "name", property = "name"),
            @Result(column = "password", property = "password")
    })
    public AuthUser refreshUserId(AuthUser authUser);

}
