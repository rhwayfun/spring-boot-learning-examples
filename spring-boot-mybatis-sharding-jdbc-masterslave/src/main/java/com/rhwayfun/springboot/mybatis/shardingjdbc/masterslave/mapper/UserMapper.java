package com.rhwayfun.springboot.mybatis.shardingjdbc.masterslave.mapper;

import com.rhwayfun.springboot.mybatis.shardingjdbc.masterslave.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author rhwayfun
 * @since 0.0.1
 */
@Mapper
public interface UserMapper {

    void createIfNotExistsTable();

    void truncateTable();

    Long insert(UserEntity model);

    UserEntity find(Long userId);

    void delete(Long userId);

    void dropTable();

}
