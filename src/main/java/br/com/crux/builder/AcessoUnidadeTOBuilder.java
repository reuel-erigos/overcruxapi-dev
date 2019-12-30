package br.com.crux.builder;

import org.springframework.stereotype.Component;

import br.com.crux.entity.Unidade;
import br.com.crux.to.AcessoUnidadeTO;

@Component
public class AcessoUnidadeTOBuilder {
	
	public AcessoUnidadeTO build(Unidade unidade) {
		AcessoUnidadeTO to = new AcessoUnidadeTO();
		to.setId(unidade.getIdUnidade());
		to.setIdentificador(unidade.getSiglaUnidade());
		to.setNome(unidade.getNomeUnidade());
		
		return to;
	}
	

}
