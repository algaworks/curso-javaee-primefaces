package com.algaworks.pedidovenda.validation;

import java.util.HashMap;
import java.util.Map;

import javax.validation.metadata.ConstraintDescriptor;

import org.primefaces.validate.bean.ClientValidationConstraint;

public class SkuClientValidationConstraint implements ClientValidationConstraint {

	public static final String MESSAGE_ID = "{com.algaworks.constraints.SKU.message}";
	
	@SuppressWarnings("rawtypes")
	@Override
	public Map<String, Object> getMetadata(ConstraintDescriptor constraintDescriptor) {
		Map<String, Object> metadata = new HashMap<String, Object>();
		Map attrs = constraintDescriptor.getAttributes();
		
		Object message = attrs.get("message");
		
		if (!message.equals(MESSAGE_ID)) {
			metadata.put("data-msg", message);
		}
		
		return metadata;
	}

	@Override
	public String getValidatorId() {
		return SKU.class.getSimpleName();
	}

}
