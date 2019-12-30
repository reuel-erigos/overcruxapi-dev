package br.com.crux.cmd;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.dao.repository.UnidadeRepository;
import br.com.crux.entity.Unidade;
import br.com.crux.to.AcessoUnidadeTO;

@Component
public class CarregarUnidadeLogadaCmd {

	@Autowired private UnidadeRepository unidadeRepository;
	
	
	public AcessoUnidadeTO carregarUnidadeLogada(Long idUnidade) {
		AcessoUnidadeTO unidadeLogada = new AcessoUnidadeTO();
		
		if( Objects.nonNull(idUnidade) ) {
			Optional<Unidade> unidade = unidadeRepository.findById(idUnidade);
			
			if(unidade.isPresent()) {
				unidadeLogada.setId(unidade.get().getIdUnidade());
				unidadeLogada.setNome(unidade.get().getNomeUnidade());
				unidadeLogada.setIdentificador(unidade.get().getSiglaUnidade());
			}
		}
		
		return unidadeLogada;
	}
}
