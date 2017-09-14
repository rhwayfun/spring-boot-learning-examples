package com.rhwayfun.springboot.mybatis.annotation.constants;

import java.util.Map;

import static org.apache.ibatis.jdbc.SqlBuilder.*;

/**
 * Sql语句生成器
 *
 * @author happyxiaofan
 * @since 0.0.1
 */
public class UserSqlProvider {

    private static final String TABLE_NAME = "user";

    public String findOneSql(Map<String, Object> parameters) {
        Long userId = (Long) parameters.get("userId");
        BEGIN();
        SELECT("user_id, user_name, age, birth");
        FROM(TABLE_NAME);
        if (userId != null) {
            WHERE("user_id = #{userId}");
        }
        return SQL();
    }

    public String findAllSql() {
        BEGIN();
        SELECT("user_id, user_name, age, birth");
        FROM(TABLE_NAME);
        return SQL();
    }

    public String insertSql() {
        BEGIN();
        INSERT_INTO(TABLE_NAME);
        VALUES("user_id", "#{userId}");
        VALUES("user_name", "#{userName}");
        VALUES("age", "#{age}");
        VALUES("birth", "#{birth}");
        return SQL();
    }

    public String updateSql() {
        BEGIN();
        UPDATE(TABLE_NAME);
        SET("user_name = #{userName}");
        SET("age = #{age}");
        SET("birth = #{birth}");
        WHERE("user_id = #{userId}");
        return SQL();
    }

    public String deleteSql() {
        BEGIN();
        DELETE_FROM(TABLE_NAME);
        WHERE("user_id = #{userId}");
        return SQL();
    }

}
