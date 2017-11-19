package com.rhwayfun.springboot.mybatis.shardingjdbc.masterslave.mapper;

import com.rhwayfun.springboot.mybatis.shardingjdbc.masterslave.constants.SqlConstants;
import com.rhwayfun.springboot.mybatis.shardingjdbc.masterslave.entity.UserEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 用户DAO
 *
 * @author rhwayfun
 * @since 0.0.1
 */
public interface UserRepository {

    @Select(SqlConstants.GET_ALL_SQL)
    @Results(id = "userResultMap", value = {
            @Result(property = "userId", column = "user_id"),
            @Result(property = "cityId", column = "city_id"),
            @Result(property = "userName", column = "user_name"),
            @Result(property = "age", column = "age"),
            @Result(property = "birth", column = "birth")
    })
    List<UserEntity> selectAll();

    @Select(SqlConstants.GET_ONE_SQL)
    @ResultMap(value = "userResultMap")
    UserEntity find(Long userId);

    @Insert(SqlConstants.INSERT_SLAVE_SQL)
    @Options(useGeneratedKeys = true, keyProperty = "userId")
    int insert(UserEntity user);

    @Update(SqlConstants.UPDATE_SQL)
    int update(UserEntity user);

    @Delete(SqlConstants.DELETE_SQL)
    int delete(Long userId);

    @Delete(SqlConstants.DELETE_ALL_SQL)
    void truncate();

}
