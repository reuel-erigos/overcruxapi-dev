package br.com.crux.cmd;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.dao.repository.CargoRepository;
import br.com.crux.entity.Cargo;
import br.com.crux.exception.NotFoundException;
import br.com.crux.exception.ParametroNaoInformadoException;

@Component
public class ExcluirCargosCmd {

	@Autowired
	private CargoRepository repository;
	
	public void excluir(Long id) {
		if(Objects.isNull(id)) {
			throw new ParametroNaoInformadoException("Erro ao excluir o cargo.");
		}
		
		Optional<Cargo> entity = repository.findById(id);
		if(!entity.isPresent()) {
			throw new NotFoundException("Cargo informado não existe.");
		}
		
		repository.deleteById(id);
		
	}
}
