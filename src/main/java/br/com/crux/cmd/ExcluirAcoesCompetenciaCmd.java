package br.com.crux.cmd;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import br.com.crux.dao.repository.AcoesCompetenciaRepository;
import br.com.crux.entity.AcoesCompetencia;
import br.com.crux.exception.NotFoundException;
import br.com.crux.exception.ParametroNaoInformadoException;
import br.com.crux.exception.TabaleReferenciaEncontradaException;

@Component
public class ExcluirAcoesCompetenciaCmd {

	@Autowired private AcoesCompetenciaRepository repository;
	
	public void excluir(Long id) {
		try {
			if(Objects.isNull(id)) {throw new ParametroNaoInformadoException("Erro ao excluir, parâmetro ausente");}
			
			Optional<AcoesCompetencia> entity = repository.findById(id);
			if(!entity.isPresent()) {
				throw new NotFoundException("Ação da competência do talento do funcionario informada não existe.");
			}
			
			repository.deleteById(id);
			
		} catch (Exception e) {
			if(e.getCause() instanceof DataIntegrityViolationException || e.getCause().toString().contains("ConstraintViolationException")) {
				throw new TabaleReferenciaEncontradaException("Erro ao excluir, apague antes os cadastros com referência a esse registro.");
			}
			throw new RuntimeException(e.getMessage());
		}		
		
	}
}
