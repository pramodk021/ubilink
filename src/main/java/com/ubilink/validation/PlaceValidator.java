package com.ubilink.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ubilink.model.Place;

public class PlaceValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return Place.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Place place=(Place)target;
		ValidationUtils.rejectIfEmpty(errors, "name", "place.name.empty");
		ValidationUtils.rejectIfEmpty(errors, "location", "place.location.empty");
		ValidationUtils.rejectIfEmpty(errors, "address", "place.address.empty");
	}

}
