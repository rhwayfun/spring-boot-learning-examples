package com.rhwayfun.springboot.mybatis.annotation.constants;

/**
 * Sql语句
 *
 * @author happyxiaofan
 * @since 0.0.1
 */
public abstract class SqlConstants {

    public static final String GET_ALL_SQL = "SELECT * FROM user";

    public static final String GET_ONE_SQL = "SELECT * FROM user WHERE user_id = #{userId}";

    public static final String INSERT_SQL = "INSERT INTO user(user_id,user_name,age,birth) VALUES(#{userId}, #{userName}, #{age}, #{birth})";

    public static final String UPDATE_SQL = "UPDATE user SET user_name=#{userName},age=#{age},birth=#{birth} WHERE user_id =#{userId}";

    public static final String DELETE_SQL = "DELETE FROM user WHERE user_id =#{userId}";

    public static final String DELETE_ALL_SQL = "DELETE FROM user";

}
