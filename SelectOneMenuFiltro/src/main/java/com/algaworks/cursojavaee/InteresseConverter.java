package com.algaworks.cursojavaee;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("interesse")
public class InteresseConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value != null) {
			for (Interesse interesse : PerfilUsuarioBean.INTERESSES) {
				if (value.equals(interesse.getNomeIcone())) {
					return interesse;
				}
			}
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null && !value.equals("")) {
			Interesse interesse = (Interesse) value;
			return interesse.getNomeIcone();
		}
		return null;
	}

}