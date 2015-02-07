PrimeFaces.locales['pt_BR'] = {
	messages : {
		'javax.faces.component.UIInput.REQUIRED' : '{0} é obrigatório'
	}
}

String.prototype.endsWith = function(sufixo) {
	return this.indexOf(sufixo, this.length - sufixo.length) !== -1;
}

PrimeFaces.validator['com.algaworks.EmailBlacklist'] = {

	validate : function(element, value) {
		var dominios = [ 'hotmail.com', 'bol.com.br' ];

		for (i = 0; i < dominios.length; i++) {
			var dominio = dominios[i].trim();

			if (value.endsWith('@' + dominio)) {
				throw {
					summary : 'E-mail inválido',
					detail : value + ' está na lista negra'
				}
			}
		}
	}

}