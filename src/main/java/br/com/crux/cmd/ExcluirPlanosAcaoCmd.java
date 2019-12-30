package br.com.crux.cmd;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import br.com.crux.dao.repository.IniciativaRepository;
import br.com.crux.dao.repository.PlanosAcaoRepository;
import br.com.crux.entity.Iniciativa;
import br.com.crux.entity.PlanosAcao;
import br.com.crux.exception.NotFoundException;
import br.com.crux.exception.ParametroNaoInformadoException;
import br.com.crux.exception.TabaleReferenciaEncontradaException;

@Component
public class ExcluirPlanosAcaoCmd {

	@Autowired
	private PlanosAcaoRepository repository;
	
	@Autowired
	private IniciativaRepository iniciativaRepository;
	
	public void excluir(Long id) {
		
		try {
			if(Objects.isNull(id)) {
				throw new ParametroNaoInformadoException("Erro ao excluir, parâmetro ausente.");
			}
			
			Optional<PlanosAcao> entity = repository.findById(id);
			if(!entity.isPresent()) {
				throw new NotFoundException("Plano de Ação informado não existe.");
				
			}
			
			Optional<Iniciativa> iniciativa = iniciativaRepository.findById(entity.get().getIniciativa().getId());
			if(iniciativa.isPresent()) {
				throw new TabaleReferenciaEncontradaException("Por favor, excluir a Iniciativa primeiro!");
			}
			
			repository.deleteById(id);
			
		} catch (DataIntegrityViolationException e) {
			throw new TabaleReferenciaEncontradaException("Erro ao excluir, verifique se há outro cadastro com referência a este plano de ação.");
		}	
		
	}
}
