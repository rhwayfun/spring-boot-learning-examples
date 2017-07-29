package com.rhwayfun.springboot.doamin;

import org.junit.BeforeClass;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.Set;

import static org.junit.Assert.*;

/**
 * Created by chubin on 2017/7/29.
 */
public class CarTest {
    private static Validator validator;

    @BeforeClass
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void manufacturerIsNull() {
        CarBO car = new CarBO( null, "DD-AB-123", 4 );

        Set<ConstraintViolation<CarBO>> constraintViolations =
                validator.validate( car );

        assertEquals( 1, constraintViolations.size() );
        assertEquals(
                "may not be null",
                constraintViolations.iterator().next().getMessage()
        );
    }

    @Test
    public void licensePlateTooShort() {
        CarBO car = new CarBO( "Morris", "D", 4 );

        Set<ConstraintViolation<CarBO>> constraintViolations =
                validator.validate( car );

        assertEquals( 1, constraintViolations.size() );
        assertEquals(
                "size must be between 2 and 14",
                constraintViolations.iterator().next().getMessage()
        );
    }

    @Test
    public void seatCountTooLow() {
        CarBO car = new CarBO( "Morris", "DD-AB-123", 1 );

        Set<ConstraintViolation<CarBO>> constraintViolations =
                validator.validate( car );

        assertEquals( 1, constraintViolations.size() );
        assertEquals(
                "must be greater than or equal to 2",
                constraintViolations.iterator().next().getMessage()
        );
    }

    @Test
    public void carIsValid() {
        CarBO car = new CarBO( "Morris", "DD-AB-123", 2 );

        Set<ConstraintViolation<CarBO>> constraintViolations =
                validator.validate( car );

        assertEquals( 0, constraintViolations.size() );
    }

}