package br.com.crux.cmd;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.dao.repository.GrausInstrucaoRepository;
import br.com.crux.entity.GrausInstrucao;
import br.com.crux.exception.NotFoundException;
import br.com.crux.exception.ParametroNaoInformadoException;

@Component
public class ExcluirGrausInstrucaoCmd {

	@Autowired
	private GrausInstrucaoRepository repository;
	
	
	public void excluir(Long id) {
		if(Objects.isNull(id)) {
			throw new ParametroNaoInformadoException("Erro ao excluir.");
		}
		
		Optional<GrausInstrucao> entity = repository.findById(id);
		if(!entity.isPresent()) {
			throw new NotFoundException("Grau de Instrucao informado não existe.");
		}
		
		repository.deleteById(id);
	}
}
