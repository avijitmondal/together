/*****************************************************************************
 * FILE NAME   : Gender.java
 * VERSION     : 1.0
 * AUTHOR      : avijit
 * DATE        : Dec 11, 2017
 * DESCRIPTION : together-server
 ****************************************************************************/
package com.avijit.together.core.util.validator;

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
	public abstract String message() default "Invalid Emum value.";

	public abstract Class<?>[] groups() default {};

	public abstract Class<? extends Payload>[] payload() default {};

	public abstract Class<? extends java.lang.Enum<?>> enumClass();

	public abstract boolean ignoreCase() default false;
}
