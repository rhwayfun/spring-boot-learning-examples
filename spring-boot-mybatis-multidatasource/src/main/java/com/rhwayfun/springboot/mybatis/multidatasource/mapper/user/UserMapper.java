package com.rhwayfun.springboot.mybatis.multidatasource.mapper.user;

import com.rhwayfun.springboot.mybatis.multidatasource.constants.SqlConstants;
import com.rhwayfun.springboot.mybatis.multidatasource.entity.UserEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 用户DAO
 *
 * @author happyxiaofan
 * @since 0.0.1
 */
public interface UserMapper {

    @Select(SqlConstants.GET_ALL_SQL)
    @Results(id = "userResultMap", value = {
            @Result(property = "userId", column = "user_id"),
            @Result(property = "userName", column = "user_name"),
            @Result(property = "age", column = "age"),
            @Result(property = "birth", column = "birth")
    })
    List<UserEntity> getAll();

    @Select(SqlConstants.GET_ONE_SQL)
    @ResultMap(value = "userResultMap")
    UserEntity getOne(Long userId);

    @Insert(SqlConstants.INSERT_SQL)
    int insert(UserEntity user);

    @Update(SqlConstants.UPDATE_SQL)
    int update(UserEntity user);

    @Delete(SqlConstants.DELETE_SQL)
    int delete(Long userId);

    @Delete(SqlConstants.DELETE_ALL_SQL)
    void deleteAll();

}
