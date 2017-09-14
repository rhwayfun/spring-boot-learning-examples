package com.rhwayfun.springboot.mybatis.annotation.mapper;

import com.rhwayfun.springboot.mybatis.annotation.constants.SqlConstants;
import com.rhwayfun.springboot.mybatis.annotation.entity.UserEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 用户DAO
 *
 * @author happyxiaofan
 * @since 0.0.1
 */
public interface UserMapper {

    /**
     * 查询所有用户
     *
     * 注解：1、@Select定义一个SELECT映射语句
     *      2、@Results注解与JavaBean属性映射起来
     *      3、@Insert定义一个INSERT映射语句
     *      4、@Update定义一个UPDATE映射语句
     * @return 所有的用户
     */
    @Select(SqlConstants.GET_ALL_SQL)
    @Results(id = "userResultMap", value = {
            @Result(property = "userId", column = "user_id"),
            @Result(property = "userName", column = "user_name"),
            @Result(property = "age", column = "age"),
            @Result(property = "birth", column = "birth")
    })
    List<UserEntity> getAll();

    /**
     * 查询单个用户
     *
     * 这里两个语句的@Results配置完全相同，但还是得重复
     * 解决方法是在@Results注解添加id属性，然后@ResultMap指定这个id就可以了。
     * 也就是这里的@ResultMap(value = "userResultMap")可以替换为getAll()方法的@Results注解的内容
     * @param userId 用户id
     * @return 单个用户
     */
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
