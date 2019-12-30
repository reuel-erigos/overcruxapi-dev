package br.com.crux.cmd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.UnidadeTOBuilder;
import br.com.crux.dao.repository.UnidadeRepository;
import br.com.crux.entity.Unidade;
import br.com.crux.exception.NotFoundException;
import br.com.crux.exception.base.NegocioException;
import br.com.crux.to.AcessoUnidadeTO;
import br.com.crux.to.UnidadeTO;

@Component
public class GetUnidadeLogadaCmd {
	
	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd; 
	@Autowired private UnidadeRepository unidadeRepository; 
	@Autowired private UnidadeTOBuilder unidadeTOBuilder; 

	public AcessoUnidadeTO get() {
		AcessoUnidadeTO unidadeLogada = getUsuarioLogadoCmd.getUsuarioLogado().getUnidadeLogada();
		
		if(unidadeLogada == null) {
			throw new NotFoundException("Não é possível recuperar a unidade logada.");
		}
		
		return unidadeLogada;
	}

	public UnidadeTO getUnidadeTO() {
		 Long idUnidade = getUsuarioLogadoCmd.getUsuarioLogado().getUnidadeLogada().getId();
		 
		 Unidade unidade = unidadeRepository.findById(idUnidade).orElseThrow(() -> new NegocioException("Não foi possível recuperar a unidade logada"));
		
		return unidadeTOBuilder.buildTOComUnidadeLogada(unidade);
	}
	


}
