package com.algaworks.pedidovenda.validation;

import java.util.HashMap;
import java.util.Map;

import javax.validation.metadata.ConstraintDescriptor;

import org.hibernate.validator.constraints.NotBlank;
import org.primefaces.validate.bean.ClientValidationConstraint;

public class NotBlankClientValidationConstraint implements ClientValidationConstraint {

	public static final String MESSAGE_ID = "{org.hibernate.validator.constraints.NotBlank.message}";
	
	@SuppressWarnings("rawtypes")
	@Override
	public Map<String, Object> getMetadata(ConstraintDescriptor constraintDescriptor) {
		Map<String, Object> metadata = new HashMap<String, Object>();
		Map attrs = constraintDescriptor.getAttributes();
		
		Object message = attrs.get("message");
		
		if (!message.equals(MESSAGE_ID)) {
			metadata.put("data-msg-notblank", message);
		}
		
		return metadata;
	}

	@Override
	public String getValidatorId() {
		return NotBlank.class.getSimpleName();
	}

}
