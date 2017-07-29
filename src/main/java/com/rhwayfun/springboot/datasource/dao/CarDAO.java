package com.rhwayfun.springboot.datasource.dao;

import com.rhwayfun.springboot.datasource.mapper.CarMapper;
import com.rhwayfun.springboot.datasource.model.Car;
import com.rhwayfun.springboot.doamin.CarBO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

/**
 * Created by chubin on 2017/7/29.
 */
@Repository
public class CarDAO implements InitializingBean {

    private Validator validator;

    @Resource
    private CarMapper carMapper;

    public Car findById(Integer id) {
        return carMapper.selectByPrimaryKey(id);
    }

    public boolean saveOrUpdate(CarBO car) {
        validate(car);
        return carMapper.insertSelective(buildCar(car)) > 0;
    }

    private void validate(CarBO car) {
        Set<ConstraintViolation<CarBO>> constraintViolations = validator.validate( car );
        if (!constraintViolations.isEmpty()) {
            throw new RuntimeException("参数非法！！" + getValidateMsg(constraintViolations));
        }
    }

    private String getValidateMsg(Set<ConstraintViolation<CarBO>> constraintViolations) {
        StringBuilder msg = new StringBuilder();
        for (ConstraintViolation<CarBO> violation : constraintViolations) {
            msg.append(violation.getPropertyPath())
                    .append(violation.getMessage())
                    .append(",");
        }
        return msg.substring(0, msg.lastIndexOf(","));
    }

    private Car buildCar(CarBO car) {
        Car ca = new Car();
        BeanUtils.copyProperties(car, ca);
        return ca;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
}
