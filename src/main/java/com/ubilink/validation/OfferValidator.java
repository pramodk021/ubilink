package com.ubilink.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ubilink.model.Offer;

public class OfferValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return Offer.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		Offer offer=(Offer)target;
		ValidationUtils.rejectIfEmpty(errors, "name", "offer.name.empty");
		ValidationUtils.rejectIfEmpty(errors, "validity", "offer.validity.empty");
		ValidationUtils.rejectIfEmpty(errors, "validFrom", "offer.validFrom.empty");
		
	}
}