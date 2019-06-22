/*****************************************************************************
 * FILE NAME   : GenderValidator.java
 * VERSION     : 1.0
 * AUTHOR      : avijit
 * DATE        : Dec 11, 2017
 * DESCRIPTION : together-server
 ****************************************************************************/
package com.avijit.together.core.util.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author avijit
 *
 */
public class EnumValidator implements ConstraintValidator<Enum, String> {
	private Enum gender;

	@Override
	public void initialize(Enum gender) {
		this.gender = gender;
	}

	@Override
	public boolean isValid(String valueForValidation, ConstraintValidatorContext constraintValidatorContext) {
		boolean result = false;

		Object[] enumValues = this.gender.enumClass().getEnumConstants();

		if (enumValues != null) {
			for (Object enumValue : enumValues) {
				if (valueForValidation.equals(enumValue.toString())
						|| (this.gender.ignoreCase() && valueForValidation.equalsIgnoreCase(enumValue.toString()))) {
					result = true;
					break;
				}
			}
		}

		return result;
	}
}
