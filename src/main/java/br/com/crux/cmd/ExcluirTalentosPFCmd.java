package br.com.crux.cmd;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.dao.repository.TalentosPFRepository;
import br.com.crux.entity.TalentosPf;
import br.com.crux.exception.NotFoundException;
import br.com.crux.exception.ParametroNaoInformadoException;

@Component
public class ExcluirTalentosPFCmd {

	@Autowired private TalentosPFRepository repository;
	
	public void excluir(Long id) {
		if(Objects.isNull(id)) {
			throw new ParametroNaoInformadoException("Erro ao excluir.");
		}
		
		Optional<TalentosPf> entity = repository.findById(id);
		if(!entity.isPresent()) {
			throw new NotFoundException("Talendos do Funcionario informada não existe.");
		}
		
		repository.deleteById(id);
	}
}
