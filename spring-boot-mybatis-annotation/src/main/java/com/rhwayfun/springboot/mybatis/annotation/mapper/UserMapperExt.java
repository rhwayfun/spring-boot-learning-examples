package com.rhwayfun.springboot.mybatis.annotation.mapper;

import com.rhwayfun.springboot.mybatis.annotation.constants.SqlConstants;
import com.rhwayfun.springboot.mybatis.annotation.constants.UserSqlProvider;
import com.rhwayfun.springboot.mybatis.annotation.entity.UserEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 用户DAO扩展类，使用Sql生成器
 * @see UserSqlProvider
 *
 * @author happyxiaofan
 * @since 0.0.1
 */
public interface UserMapperExt extends UserMapper {

    /**
     * 区别：findOneV2  VS  findOne
     * 如果使用@SelectProvider创建SQL语句，且使用#{}获取参数，必须在参数加上@Param注解
     * 否则使用@Select定义Sql映射语句，则不需要添加@Param注解
     *
     * @param userId
     * @return
     */
    @SelectProvider(type = UserSqlProvider.class, method = "findOneSql")
    @ResultMap(value = "userResultMap")
    UserEntity findOne(@Param("userId") Long userId);

    @Select(SqlConstants.GET_ONE_SQL)
    @ResultMap(value = "userResultMap")
    UserEntity findOneV2(Long userId);

    @SelectProvider(type = UserSqlProvider.class, method = "findAllSql")
    @ResultMap(value = "userResultMap")
    List<UserEntity> findAll();

    @InsertProvider(type = UserSqlProvider.class, method = "insertSql")
    int add(UserEntity user);

    @UpdateProvider(type = UserSqlProvider.class, method = "updateSql")
    int modify(UserEntity user);

    @DeleteProvider(type = UserSqlProvider.class, method = "deleteSql")
    int remove(Long userId);

}
