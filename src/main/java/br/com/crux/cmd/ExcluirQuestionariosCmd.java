package br.com.crux.cmd;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.dao.repository.QuestionariosRepository;
import br.com.crux.entity.Questionario;
import br.com.crux.exception.NotFoundException;
import br.com.crux.exception.ParametroNaoInformadoException;

@Component
public class ExcluirQuestionariosCmd {

	@Autowired
	private QuestionariosRepository repository;
	
	
	public void excluir(Long id) {
		if(Objects.isNull(id)) {
			throw new ParametroNaoInformadoException("Erro ao excluir.");
		}
		
		Optional<Questionario> entity = repository.findById(id);
		if(!entity.isPresent()) {
			throw new NotFoundException("Questionario informado não existe.");
		}
		
		repository.deleteById(id);
			
	}
}
