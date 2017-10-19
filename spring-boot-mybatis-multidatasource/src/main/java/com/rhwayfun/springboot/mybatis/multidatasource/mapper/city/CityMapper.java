package com.rhwayfun.springboot.mybatis.multidatasource.mapper.city;

import com.rhwayfun.springboot.mybatis.multidatasource.constants.SqlConstants;
import com.rhwayfun.springboot.mybatis.multidatasource.entity.CityEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 城市DAO
 *
 * @author happyxiaofan
 * @since 0.0.1
 */
public interface CityMapper {

    @Select(SqlConstants.CITY_GET_ALL_SQL)
    @Results(id = "cityResultMap", value = {
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "modifyTime", column = "modify_time")
    })
    List<CityEntity> getAll();

    @Select(SqlConstants.CITY_GET_ONE_SQL)
    @ResultMap(value = "cityResultMap")
    CityEntity getOne(Long userId);

    @Insert(SqlConstants.CITY_INSERT_SQL)
    int insert(CityEntity user);

    @Update(SqlConstants.CITY_UPDATE_SQL)
    int update(CityEntity user);

    @Delete(SqlConstants.CITY_DELETE_SQL)
    int delete(Long userId);

    @Delete(SqlConstants.CITY_DELETE_ALL_SQL)
    void deleteAll();

}
