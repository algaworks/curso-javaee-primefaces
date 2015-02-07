package com.algaworks;

import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.primefaces.validate.ClientValidator;

@FacesValidator(EmailBlacklistValidator.VALIDATOR_ID)
public class EmailBlacklistValidator implements Validator, ClientValidator {

	public static final String VALIDATOR_ID = "com.algaworks.EmailBlacklist";

	@Override
	public void validate(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {
		if (value == null) {
			return;
		}

		String email = value.toString();
		String[] dominios = new String[] { "hotmail.com", "bol.com.br" };

		for (String dominio : dominios) {
			if (email.endsWith("@" + dominio.trim())) {
				throw new ValidatorException(new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "E-mail inválido", value
								+ " está na lista negra"));
			}
		}
	}

	@Override
	public Map<String, Object> getMetadata() {
		return null;
	}

	@Override
	public String getValidatorId() {
		return VALIDATOR_ID;
	}

}