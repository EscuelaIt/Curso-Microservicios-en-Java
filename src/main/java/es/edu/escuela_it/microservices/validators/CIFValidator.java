package es.edu.escuela_it.microservices.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CIFValidator implements ConstraintValidator<CIF, String>{

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {

		if (value == null) {
			return false;
		}
		
		return value.length() == 9;
	}
	
}

