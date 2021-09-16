/*****************************************************************************
 * FILE NAME   : Gender.java
 * VERSION     : 1.0
 * AUTHOR      : avijit
 * DATE        : Dec 11, 2017
 * DESCRIPTION : together
 ****************************************************************************/
package com.avijitmondal.together.core.util.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author avijit
 *
 */
@Documented
@Constraint(validatedBy = { EnumValidator.class })
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface Enum {
	String message() default "Invalid Enum value.";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	Class<? extends java.lang.Enum<?>> enumClass();

	boolean ignoreCase() default false;
}
