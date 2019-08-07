package com.example.demo.mapper;

import com.example.demo.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Insert("INSERT INTO USER (account_id, name, token, gmt_create, gmt_modified, avatar_url) VALUES(#{accountId}, #{name}, #{token}, #{gmtCreate}, #{gmtModified}, #{avatarUrl})")
    void insert(User user);

    @Select("SELECT * FROM USER WHERE TOKEN = #{token}")
    User findByToken(@Param("token") String token);
}
