package com.algaworks.pedidovenda.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;

import com.algaworks.pedidovenda.model.Grupo;
import com.algaworks.pedidovenda.repository.Grupos;

@FacesConverter("grupoConverter")
public class GrupoConverter implements Converter {

	@Inject
	private Grupos grupos;
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Grupo retorno = null;

		if (StringUtils.isNotEmpty(value)) {
			retorno = this.grupos.porId(new Long(value));
		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Grupo grupo = (Grupo) value;
			return grupo == null || grupo.getId() == null ? 
					null : grupo.getId().toString();
		}
		return "";
	}
}